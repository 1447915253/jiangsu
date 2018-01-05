package com.rmkj.microcap.common.modules.money.out.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/10.
 */
@HttpApi("jiu_ba_url")
public interface JiuBaOutApi {

    @FormUrlEncoded
    @POST("v2/liquidation/gateway.shtml")
    Call<String> checkBankNo(@FieldMap Map<String, String> map);

}
