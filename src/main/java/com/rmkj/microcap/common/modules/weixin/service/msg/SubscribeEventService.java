package com.rmkj.microcap.common.modules.weixin.service.msg;

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.annotation.MsgType;
import com.rmkj.microcap.common.modules.weixin.bean.msg.SubscribeEventReq;
import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.modules.corps.service.CorpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 123 on 2016/11/22.
 * 用户未关注时，进行关注后的事件推送
 */
@MsgType("event_subscribe")
@Service
public class SubscribeEventService extends EventBaseMsgService<SubscribeEventReq> {

    @Autowired
    private CorpsService corpsService;

    @Autowired
    private WeiXinService weiXinService;

    @Override
    public Object handleMsg(SubscribeEventReq subscribeEventReq) {
        // 绑定军团成员关系
        corpsService.bindToParents(subscribeEventReq.getFromUserName(),
                subscribeEventReq.getEventKey().replaceFirst("qrscene_", ""));

        return null;
    }
}
