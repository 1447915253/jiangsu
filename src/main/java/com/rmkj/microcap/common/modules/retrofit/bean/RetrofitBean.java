package com.rmkj.microcap.common.modules.retrofit.bean;

import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbowen on 2016/5/12.
 */
public class RetrofitBean {
    private Retrofit retrofit;
    private Map<Class,Object> service = new HashMap<>();
/*123213*/
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Map<Class, Object> getService() {
        return service;
    }

    public void setService(Map<Class, Object> service) {
        this.service = service;
    }
}
