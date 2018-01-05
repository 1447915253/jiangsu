package com.rmkj.microcap.common.modules.weixin.mapper;

import com.rmkj.microcap.common.modules.weixin.annotation.MsgType;
import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import com.rmkj.microcap.common.modules.weixin.factory.WeiXinServiceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;

/**
 * Created by zhangbowen on 2016/6/8.
 * 初始化微信服务
 */
public class WeiXinScannerConfigurer extends InstantiationAwareBeanPostProcessorAdapter implements BeanDefinitionRegistryPostProcessor {
    private Logger logger = Logger.getLogger(WeiXinScannerConfigurer.class);
    private String basePackage;
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private TypeFilter typeFilter = new AnnotationTypeFilter(MsgType.class, false);
    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * 检查当前扫描到的Bean含有指定的注解标记
     *
     * @param reader
     * @param readerFactory
     * @return
     * @throws IOException
     */
    private boolean matchesEntityTypeFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
        if (typeFilter.match(reader, readerFactory)) {
            return true;
        }
        return false;
    }

    /**
     * 注册bean到spring容器中
     *
     * @param registry
     * @param name
     * @param beanClass
     */
    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);

        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        // 可以自动生成name
        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, name);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = reader.getClassMetadata();
                    String className = classMetadata.getClassName();
                    boolean hasInter = classMetadata.getSuperClassName().equals(EventBaseMsgService.class.getName());
                    if (matchesEntityTypeFilter(reader, readerFactory) && hasInter) {
                        //匹配到相应service，将其放入spring容器中
                        Class serviceClass = Class.forName(className);
                        registerBean(beanDefinitionRegistry, className, serviceClass);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {
        //读取注解中的值
        MsgType msgType = bean.getClass().getAnnotation(MsgType.class);
        if (msgType != null) {
            WeiXinServiceFactory.putService(msgType.value(), (EventBaseMsgService) bean);
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
