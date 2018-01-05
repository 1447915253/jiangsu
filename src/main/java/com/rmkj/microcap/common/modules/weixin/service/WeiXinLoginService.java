package com.rmkj.microcap.common.modules.weixin.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.interceptor.ContextInterceptor;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.common.modules.weixin.http.WeiXinPageAuthApi;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by 123 on 2016/11/4.
 * 微信oauth2.0 获取用户信息
 */
@Service
public class WeiXinLoginService {

    private static Logger logger = Logger.getLogger(WeiXinLoginService.class);

    @HttpService
    private WeiXinPageAuthApi weiXinPageAuthApi;

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    /**
     * 第二步
     * @param code
     * @return
     */
    public ResponseToken getOAuthToken(String code){
        ResponseToken responseTokenBean = null;
        try {
            WeChatPublic weChatPublic = weChatPublicDao.findByAppId(WeiXinInterceptor.appId());
            // 第二步 获取access_token openid
            Response<ResponseToken> tokenResponse = weiXinPageAuthApi.getOAuthToken(weChatPublic.getAppId(), weChatPublic.getSecret(), code, "authorization_code").execute();
            if(tokenResponse.isSuccessful()){
                responseTokenBean = tokenResponse.body();
                if (!responseTokenBean.isSuccessFul()) {
                    logger.error("第二步失败 responseTokenBean" + JSON.toJSONString(responseTokenBean));
                }
            }else {
                logger.error("第二步失败 tokenResponse " + JSON.toJSONString(tokenResponse));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseTokenBean;
    }

    /**
     * 第三步
     * @param responseTokenBean
     * @return
     */
    public WeiXinUserInfo userinfo(ResponseToken responseTokenBean){
        WeiXinUserInfo weiXinUserInfo = null;
        // 微信拉取用户信息
        if(responseTokenBean != null && responseTokenBean.isSuccessFul()){
            try {
                // 第三步 获取用户信息
                Response<WeiXinUserInfo> userInfoResponse = weiXinPageAuthApi.userinfo(responseTokenBean.getAccess_token(), responseTokenBean.getOpenid(), "zh_CN").execute();
                if(userInfoResponse.isSuccessful()){
                    weiXinUserInfo = userInfoResponse.body();
                    logger.info(JSON.toJSONString(weiXinUserInfo));
                }else {
                    logger.error("第三步失败 userInfoResponse" + JSON.toJSONString(userInfoResponse));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return weiXinUserInfo;
    }
}
