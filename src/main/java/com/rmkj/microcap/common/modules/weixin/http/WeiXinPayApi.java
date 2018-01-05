package com.rmkj.microcap.common.modules.weixin.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 123 on 2016/10/28.
 */
@HttpApi(value = "https://api.mch.weixin.qq.com/pay/")
public interface WeiXinPayApi {

    /**
     * 统一下单接口
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     * @param xml
     * @return
     */
    @POST(value = "unifiedorder")
    Call<String> unifiedOrder(@Body String xml);
}
