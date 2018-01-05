package com.rmkj.microcap.common.constants;

/**
 * Created by 123 on 2016/10/18.
 */
public interface CacheKey {
    /**
     * 登录信息
     */
    String TOKEN_APP = "-app-token";

    // 手机验证码
    String MOBILE_VALIDATE = "_mobile_code_";

    interface WeiXin {
        String TOKEN = "weixin_access_token";
        String TICKET = "weixin_jsapi_ticket";
    }
}
