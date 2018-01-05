package com.rmkj.microcap.common.modules.sms.qixin;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by 123 on 2016/10/13.
 */

@HttpApi(value = "http://open.96xun.com/")
public interface QiXinHttpApi {

    /**
     * 发送短信
     * @param username
     * @param pwd
     * @param msg url编码
     * @param mobile 13888888888
     * @return
     */
    @GET("Api/SendSms")
    Call<String> send(@Query("Uname") String username, @Query("Upass") String pwd, @Query(value = "Content", encoded = true) String msg, @Query("Mobile") String mobile);
}
