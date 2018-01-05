package com.rmkj.microcap.common.modules.weixin.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 123 on 2016/10/13.
 */
@HttpApi(value = "WEI_XIN_BASE_URL")
public interface WeiXinPageAuthApi {
    /**
     * 获得OAuth接口所需要的token
     * @return
     */
    @GET("sns/oauth2/access_token")
    Call<ResponseToken> getOAuthToken(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    // https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    @GET("sns/userinfo")
    Call<WeiXinUserInfo> userinfo(@Query("access_token") String accessToken, @Query("openid") String openId, @Query("lang") String lang);
}
