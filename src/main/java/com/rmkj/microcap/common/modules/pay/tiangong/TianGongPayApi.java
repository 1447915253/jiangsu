package com.rmkj.microcap.common.modules.pay.tiangong;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 123 on 2017/1/7.
 */
@HttpApi(value = "tian_gong_url")
public interface TianGongPayApi {

    @POST(value = "charge/")
    @FormUrlEncoded
    Call<String> charge(@Field("client_id") String client_id, @Field("notify_url") String notify_url, @Field("return_url") String return_url,
        @Field("client_secret") String client_secret, @Field("order_no") String order_no, @Field("channel") String channel,
            @Field("amount") String amount, @Field("subject") String subject, @Field("metadata") String metadata, @Field("wx_openid") String wx_openid);

}
