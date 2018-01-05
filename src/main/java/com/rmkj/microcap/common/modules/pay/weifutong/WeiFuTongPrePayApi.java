package com.rmkj.microcap.common.modules.pay.weifutong;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 123 on 2016/12/12.
 */
@HttpApi(value = "https://pay.swiftpass.cn/")
public interface WeiFuTongPrePayApi {

    /**
     *
     * @param xml
     * @return
     */
    @POST("pay/gateway")
    Call<String> gateway(@Body String xml);
}
