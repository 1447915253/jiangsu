package com.rmkj.microcap.common.modules.weixin.http.interceptor;

import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhangbowen on 2016/6/7.
 */
public class WeiXinInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String token = CacheFacade.getObject(appKey());
        HttpUrl newsUrl = originalRequest.url().newBuilder().addQueryParameter("access_token", token).build();
        Request request = originalRequest
                .newBuilder()
                .url(newsUrl)
                .build();
        return chain.proceed(request);
    }

    private static ThreadLocal<String> witchAppId = new ThreadLocal();

    public static String appKey(){
        String appId = witchAppId.get();
        return appKey(appId);
    }

    public static String appKey(String appId){
        return Constants.WeiXin.TOKEN.concat("_").concat(appId);
    }

    public static String appId(){
        return witchAppId.get();
    }

    public static void setAppId(String appId){
        witchAppId.set(appId);
    }

    public static void clearAppId(){
        witchAppId.remove();
    }
}
