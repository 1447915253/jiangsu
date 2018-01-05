package com.rmkj.microcap.common.modules.weixin.service.msg;

import com.rmkj.microcap.common.modules.weixin.annotation.MsgType;
import com.rmkj.microcap.common.modules.weixin.bean.msg.SubscribeEventReq;
import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import com.rmkj.microcap.modules.corps.service.CorpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 123 on 2017/01/17
 * 用户已关注公众号时，进行扫描后的事件推送
 */
@MsgType("event_SCAN")
@Service
public class ScanEventService extends EventBaseMsgService<SubscribeEventReq> {

    @Autowired
    private CorpsService corpsService;

    @Override
    public Object handleMsg(SubscribeEventReq subscribeEventReq) {
        // 绑定军团成员关系
        corpsService.bindToParents(subscribeEventReq.getFromUserName(),
                subscribeEventReq.getEventKey());
        return null;
    }
}
