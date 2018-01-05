package com.rmkj.microcap.common.modules.weixin.service.msg;

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.weixin.annotation.MsgType;
import com.rmkj.microcap.common.modules.weixin.bean.CustomMessage;
import com.rmkj.microcap.common.modules.weixin.bean.CustomMessageNews;
import com.rmkj.microcap.common.modules.weixin.bean.CustomMessageText;
import com.rmkj.microcap.common.modules.weixin.bean.msg.SubscribeEventReq;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinLoginService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.service.ScheduleService;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicArticlesDao;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.apache.log4j.LogManager.getLogger;

/**
 * Created by Administrator on 2017/7/6.
 */
@MsgType("event_CLICK")
@Service
public class ClickEventService extends EventBaseMsgService<SubscribeEventReq> {

    private static org.apache.log4j.Logger logger = getLogger(ClickEventService.class);
    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private WeChatPublicArticlesDao weChatPublicArticlesDao;

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    @Override
    public Object handleMsg(SubscribeEventReq obj) {
        WeiXinEventClickBean weiXinEventClickBean = weChatPublicArticlesDao.getContentById(obj.getEventKey());
        WeChatPublic weChatPublic = weChatPublicDao.findByAppId(weiXinEventClickBean.getAppId());
        if(weChatPublic == null){
            return null;
        }
        WeiXinInterceptor.setAppId(weChatPublic.getAppId());
        String cacheToken = weiXinService.initToken(weChatPublic);
        logger.info("获取accesstoken"+cacheToken);
        CustomMessage customMessage = new CustomMessage();
        //是否是点击登录
        String content = null;
        if(weiXinEventClickBean.getTextType() == 0){
            String tokenId = UUID.randomUUID().toString();
            CacheFacade.set(tokenId,"0",60);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND,60);
            String url = "http://"+weChatPublic.getDomainName()+"/front/wap/home?tokenId="+tokenId;
            content = "<a href="+"'"+url+"'"+">登录入口,点击后失效</a>"+"\r\n"+"有效期至"+ sdf.format(calendar.getTime());
            logger.info("============="+content+"=============");
        }else{
            content = weiXinEventClickBean.getContent();
        }
        if(StringUtils.isNotBlank(cacheToken)){
            customMessage.setMsgtype("text");
            customMessage.setTouser(obj.getFromUserName());
            customMessage.setText(new CustomMessageText());
            customMessage.getText().setContent(content);
            weiXinService.sendMessage(cacheToken, customMessage);
        }
        return null;
    }
}
