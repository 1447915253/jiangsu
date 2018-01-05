package com.rmkj.microcap.common.modules.weixin.factory;

import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbowen on 2016/6/8.
 */
public class WeiXinServiceFactory {
    private static Map<String, EventBaseMsgService> msgServiceMap = new HashMap(16);

    /**
     * 获得service服务
     *
     * @param msgType
     * @return
     * @throws BeansException
     */
    public static EventBaseMsgService getService(String msgType) throws BeansException {
        return msgServiceMap.get(msgType);
    }

    /**
     * 创建service服务
     *
     * @param msgType
     * @param service
     */
    public static void putService(String msgType, EventBaseMsgService service) {
        if (StringUtils.isEmpty(msgType) || msgServiceMap.containsKey(msgType)) {
            return;
        }
        msgServiceMap.put(msgType, service);
    }

    public static Map<String, EventBaseMsgService> getMsgServiceMap() {
        return msgServiceMap;
    }

    public static void setMsgServiceMap(Map<String, EventBaseMsgService> msgServiceMap) {
        WeiXinServiceFactory.msgServiceMap = msgServiceMap;
    }
}
