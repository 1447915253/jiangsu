package com.rmkj.microcap.common.modules.weixin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.constants.CacheKey;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.weixin.bean.*;
import com.rmkj.microcap.common.modules.weixin.http.*;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.error.WeiXinError;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.service.ScheduleService;
import com.rmkj.microcap.modules.user.dao.UserDao;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicArticlesDao;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublicArticle;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublicArticle2;
import com.rmkj.microcap.modules.weChatPublic.entity.WechatMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangbowen on 2016/6/7.
 */
@Service
public class WeiXinService {

    private final Logger Log = Logger.getLogger(WeiXinService.class);

    @HttpService
    private WeiXinPageAuthApi weiXinPageAuthApi;

    @HttpService
    private WeiXinApi weiXinApi;

    @HttpService
    private WeiXinQrcodeGetApi weiXinQrcodeGetApi;

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    @HttpService
    private WeiXinMessageCustomSendApi weiXinMessageCustomSendApi;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WeChatPublicArticlesDao weChatPublicArticlesDao;

    @HttpService
    private GetAccessTokenApi getAccessTokenApi;

    protected org.apache.log4j.Logger logger = Logger.getLogger(getClass());

    public String initToken(WeChatPublic weChatPublic){
        //设置请求参数
        if(weChatPublic == null){
            return null;
        }
        String accessToken = null;
        int expireSeconds = 0;
        Date now = new Date();
        if(StringUtils.isNotBlank(weChatPublic.getAccessToken())
                && now.before(weChatPublic.getAccessTokenExpireTime())){
            accessToken = weChatPublic.getAccessToken();
            CacheFacade.set(WeiXinInterceptor.appKey(weChatPublic.getAppId()), accessToken, 0);
        }else{
            try {
                Response<String> response = getAccessTokenApi.getToken(weChatPublic.getAppId()).execute();
                if(response.isSuccessful()){
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return accessToken;
    }

    public String initToken() {
        if(ProjectConstants.PRO_DEBUG){
            throw new WeiXinError("本地缓存模式不建议操作微信");
        }
        String cacheToken = CacheFacade.getObject(WeiXinInterceptor.appKey());
        if (cacheToken != null) {
            return cacheToken;
        }
        //设置请求参数
        WeChatPublic weChatPublic =  weChatPublicDao.findByAppId(WeiXinInterceptor.appId());
        return initToken(weChatPublic);
    }

    /**
     * 获取永久二维码 带参数
     * @param scene_str
     * @return
     */
    public String qrcodeCreate(String scene_str){
        WeiXinQrcodeCreateReq weiXinQrcodeCreateReq = new WeiXinQrcodeCreateReq();
        weiXinQrcodeCreateReq.setAction_name("QR_LIMIT_STR_SCENE");
        weiXinQrcodeCreateReq.setAction_info(new WeiXinQrcodeCreateReq.ActionInfoBean());
        weiXinQrcodeCreateReq.getAction_info().setScene(new WeiXinQrcodeCreateReq.ActionInfoBean.SceneBean());
        weiXinQrcodeCreateReq.getAction_info().getScene().setScene_str(scene_str);
        try {
            initToken();
            Response<String> execute = weiXinQrcodeGetApi.create(CacheFacade.getObject(WeiXinInterceptor.appKey()), weiXinQrcodeCreateReq).execute();
            if(execute.isSuccessful()){
                WeiXinQrcodeCreateResp w = JSON.parseObject(execute.body(), WeiXinQrcodeCreateResp.class);
                return w.getTicket();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String qrcodeCreateTemp(BigInteger scene_id, int expireSeconds){
        WeiXinQrcodeTempCreateReq weiXinQrcodeTempCreateReq = new WeiXinQrcodeTempCreateReq();
        weiXinQrcodeTempCreateReq.setExpire_seconds(expireSeconds);
        weiXinQrcodeTempCreateReq.setAction_name("QR_SCENE");
        weiXinQrcodeTempCreateReq.setAction_info(new WeiXinQrcodeTempCreateReq.ActionInfoBean());
        weiXinQrcodeTempCreateReq.getAction_info().setScene(new WeiXinQrcodeTempCreateReq.ActionInfoBean.SceneBean());
        weiXinQrcodeTempCreateReq.getAction_info().getScene().setScene_id(scene_id);
        try {
            initToken();
            Response<String> execute = weiXinQrcodeGetApi.create(CacheFacade.getObject(WeiXinInterceptor.appKey()), weiXinQrcodeTempCreateReq).execute();
            if(execute.isSuccessful()){
                WeiXinQrcodeCreateResp w = JSON.parseObject(execute.body(), WeiXinQrcodeCreateResp.class);
                return w.getTicket();
            }else{
                Log.error(execute.errorBody().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据openid获取用户信息
     * @param openId
     * @return
     */
    public WeiXinUserInfo userInfo(String openId){
        try {
            initToken();
            Response<WeiXinUserInfo> userInfoExecute = weiXinApi.userInfo(openId, "zh_CN").execute();
            if(userInfoExecute.isSuccessful()){
                return userInfoExecute.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO 获取微信分享需要的信息
     * @return
     */
    public WeiXinShare getWeixinShare(){
        //获取accessToken
        ResponseToken responseTokenBean = null;
        try {
            Response<ResponseToken> response = weiXinApi.getToken("client_credential", ProjectConstants.WEI_XIN_APP_ID, ProjectConstants.WEI_XIN_SECRET).execute();
            responseTokenBean = response.body();
            if (!responseTokenBean.isSuccessFul()) {
                throw new WeiXinError(responseTokenBean.getErrmsg());
            }
            CacheFacade.set(CacheKey.WeiXin.TOKEN, responseTokenBean.getAccess_token(), responseTokenBean.getExpires_in());
        }catch (IOException e){
            e.printStackTrace();
        }
        //获取签名随即字符串
        String nonceStr = Utils.uuid();
        //获取签名时间戳
        String timestamp = Long.toString(System.currentTimeMillis());
        //获取appid
        String appId = ProjectConstants.WEI_XIN_APP_ID;
        //生成signature
        String str = "noncestr="+nonceStr+"&jsapi_ticket="+weiXinApi.getTickets(responseTokenBean.getAccess_token(),"jsapi")+"&timestamp="+timestamp+"&url="+"http://trade.dzwjyzx.com/front/wap/invite";
        String signature = sha(str);
        System.out.println("parameter："+str+"\nsignature："+signature);
        WeiXinShare weiXinShare = new WeiXinShare();
        weiXinShare.setTid(appId);
        weiXinShare.setNonceStr(nonceStr);
        weiXinShare.setTimestamp(timestamp);
        weiXinShare.setSignature(signature);
        return weiXinShare;
    }

    public static String sha(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     *
     * @return
     */
    public boolean sendMessage(String accessToken, CustomMessage customMessage){
        if(ProjectConstants.PRO_DEBUG){
            return false;
        }
        String json = JSONObject.toJSONString(customMessage);
        Log.info(json);
        Call<String> call = weiXinMessageCustomSendApi.messageCustomSend(accessToken, customMessage);
        try {
            Response<String> execute = call.execute();
            if(execute.isSuccessful()){
                String result = execute.body();
                // {"errcode":40003,"errmsg":"invalid openid hint: [n3I63a0099ge21]"}
                if(result.startsWith("{\"errcode\":0,")){
                    Log.info("sendMessage: ".concat(result));
                }else{
                    Log.error("sendMessage: ".concat(result));
                }
                return true;
            }else{
                Log.error(execute.errorBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param type
     * @param userId
     * @param params
     */
    public void sendMessage(String type, String userId, String... params){
        if(ProjectConstants.PRO_DEBUG){
            return;
        }
        try{
            WechatMessage wechatMessage = scheduleService.getWechatMessage(type);
            if(wechatMessage != null) {
                User userById = userDao.findUserById(userId);
                CustomMessage customMessage = new CustomMessage();
                WeChatPublic weChatPublic = weChatPublicDao.findById(userById.getWechatPublicId());
                if(weChatPublic == null){
                    return ;
                }
                String cacheToken = CacheFacade.getObject(WeiXinInterceptor.appKey(weChatPublic.getAppId()));
                if (cacheToken == null) {
                    WeiXinInterceptor.setAppId(weChatPublic.getAppId());
                    cacheToken = initToken(weChatPublic);
                }
                if(StringUtils.isNotBlank(cacheToken)){
                    customMessage.setMsgtype(wechatMessage.getSendType());
                    customMessage.setTouser(userById.getOpenId());

                    if("text".equals(customMessage.getMsgtype())){
                        customMessage.setText(new CustomMessageText());
                        customMessage.getText().setContent(Utils.formatStr(wechatMessage.getContent(), params));
                    }else if("news".equals(customMessage.getMsgtype())){
                        customMessage.setNews(new CustomMessageNews());
                        customMessage.getNews().setArticles(new ArrayList<>());
                        WeChatPublicArticle2 weChatPublicArticle2 = weChatPublicArticlesDao.findById2(wechatMessage.getContent());
                        if(weChatPublicArticle2 == null){
                            return;
                        }
                        if(ProjectConstants.WEI_XIN_MESSAGE_CUSTOM_TYPE.GUAN_ZHU.equals(type)){
                            weChatPublicArticle2.setUrl("http://".concat(weChatPublic.getDomainName()).concat("/front/wap/home?tokenId=").concat("renrenbao"));
                        }
                        customMessage.getNews().getArticles().add(weChatPublicArticle2);
                    }
                    sendMessage(cacheToken, customMessage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
