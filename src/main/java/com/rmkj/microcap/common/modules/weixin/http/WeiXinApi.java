package com.rmkj.microcap.common.modules.weixin.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import com.rmkj.microcap.common.modules.weixin.bean.TicketBean;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhangbowen on 2016/6/7.
 */
@HttpApi(value = "WEI_XIN_BASE_URL", interceptor = {WeiXinInterceptor.class})
public interface WeiXinApi {
    /**
     * 获得token
     *
     * @return
     */
    @GET("cgi-bin/token")
    Call<ResponseToken> getToken(@Query("grant_type") String grant_type, @Query("appid") String appid, @Query("secret") String secret);

    /**
     * 获得jsapi_ticket
     *
     * @return
     */
    @GET("cgi-bin/ticket/getticket")
    Call<TicketBean> getTicket(@Query("type") String type);

    /**
     * TODO 获取jsapi_ticket
     * @param accessToken
     * @param type
     * @return
     */
    @GET("cgi-bin/ticket/getticket")
    Call<TicketBean> getTickets(@Query("access_token") String accessToken,@Query("type") String type);

    /**
     *
     * @param openId
     * @param lang
     * @return
     */
    @GET("cgi-bin/user/info")
    Call<WeiXinUserInfo> userInfo(@Query("openid") String openId, @Query("lang") String lang);
}
