package com.rmkj.microcap.common.modules.weixin.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.weixin.bean.CustomMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 123 on 2017/3/14.
 */
@HttpApi(value = "WEI_XIN_BASE_URL", interceptor = {})
public interface WeiXinMessageCustomSendApi {

    @POST("cgi-bin/message/custom/send")
    Call<String> messageCustomSend(@Query("access_token") String accessToken, @Body CustomMessage customMessage);
}
