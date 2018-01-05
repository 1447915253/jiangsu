package com.rmkj.microcap.common.modules.retrofit.bean;

/**
 * Created by zhangbowen on 2016/7/26.
 */
public class HttpApiBean {
    private String baseUrl;
    private Class[] interceptors;
    private Class clazz;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Class[] getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(Class[] interceptors) {
        this.interceptors = interceptors;
    }
}
