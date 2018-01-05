package com.rmkj.microcap.common.modules.weixin.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/28.
 */
@HttpApi(value = "COMMON_SERVER_BASE_URL")
public interface GetAccessTokenApi {
    /**
     * 获得token
     *
     * @return
     */
    @GET("getAccessToken")
    Call<String> getToken(@Query("appId") String appId);
}
