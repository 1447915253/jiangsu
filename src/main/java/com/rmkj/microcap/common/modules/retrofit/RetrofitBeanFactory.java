package com.rmkj.microcap.common.modules.retrofit;

import com.rmkj.microcap.common.modules.retrofit.bean.RetrofitBean;
import com.rmkj.microcap.common.modules.retrofit.convert.FastJsonConverterFactory;
import com.rmkj.microcap.common.modules.retrofit.interceptor.LoggingInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbowen on 2016/5/12.
 */
public class RetrofitBeanFactory {
    //key:请求地址 value:当前请求地址下class所对应的service（key:class value:service）
    private static Map<String, RetrofitBean> resolvableDependencies = new HashMap(16);
    private static final int readTimeOut = 15;
    private static final int writeTimeOut = 15;
    private static final int connTimeOut = 15;

    /**
     * 获得service服务实体
     *
     * @param requiredType
     * @return
     * @throws BeansException
     */
    public static Object getBean(Class requiredType) throws BeansException {
        for (Map.Entry<String, RetrofitBean> entrySet : resolvableDependencies.entrySet()) {
            RetrofitBean retrofitBean = entrySet.getValue();
            for (Map.Entry<Class, Object> serviceSet : retrofitBean.getService().entrySet()) {
                if (requiredType == serviceSet.getKey()) {
                    return serviceSet.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 创建service服务实体
     *
     * @param baseUrl
     * @param serviceClass
     */
    public static Object putBean(String baseUrl, Class serviceClass, Class... interceptorClass) {
        if (StringUtils.isEmpty(baseUrl)) {
            return null;
        }
        RetrofitBean retrofitBean = resolvableDependencies.get(serviceClass.getName());
        //如果为空设置一个
        if (retrofitBean == null) {
            retrofitBean = new RetrofitBean();
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .connectTimeout(readTimeOut, TimeUnit.SECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                    .readTimeout(connTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor());
            if (interceptorClass != null && interceptorClass.length > 0) {
                for (Class clazz : interceptorClass) {
                    if (Interceptor.class.isAssignableFrom(clazz)) {
                        try {
                            clientBuilder.addInterceptor((Interceptor) clazz.newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(clientBuilder.build())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .build();
            retrofitBean.setRetrofit(retrofit);
            resolvableDependencies.put(serviceClass.getName(), retrofitBean);
        }
        Retrofit retrofit = retrofitBean.getRetrofit();
        Object bean = retrofit.create(serviceClass);
        retrofitBean.getService().put(serviceClass, bean);
        return bean;
    }

    public static Map<String, RetrofitBean> getResolvableDependencies() {
        return resolvableDependencies;
    }
}
