package com.rmkj.microcap.common.modules.retrofit.processor;

import com.rmkj.microcap.common.modules.retrofit.RetrofitBeanFactory;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.CustomPropertyPlaceholderConfigurer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by zhangbowen on 2016/5/12.
 */
public class RetrofitAutowiredProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    private Logger logger = Logger.getLogger(RetrofitAutowiredProcessor.class);
    @Autowired
    private CustomPropertyPlaceholderConfigurer propertyConfigurer;

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                HttpService httpApi = field.getAnnotation(HttpService.class);
                if (httpApi == null) {
                    return;
                }
                createRetrofitService(bean, field, field.getType());
            }
        });
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    private void createRetrofitService(Object bean, Field field, Class clazz) throws IllegalAccessException {
        //读取注解中的值
        HttpApi httpApi = (HttpApi) clazz.getAnnotation(HttpApi.class);
        String key = httpApi.value();
        if (StringUtils.isBlank(key)) {
            return;
        }
        //根据注解的key获得配置文件中的url
        Object value = propertyConfigurer.getProperty(key);
        if (value == null) {
            value = key;
        }
        //根据地址创建retrofit
        Object object = RetrofitBeanFactory.putBean(value.toString(), clazz, httpApi.interceptor());
        if (object == null) {
            return;
        }
        field.setAccessible(true);
        field.set(bean, object);
    }
}
