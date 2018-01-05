package com.rmkj.microcap.common.modules.sms;

import java.util.Map;

/**
 * Created by 123 on 2016/10/13.
 * 短信服务接口
 */
public interface SmsSend {

    /**
     * 发送短信
     * @param msg
     * @param phone
     * @return
     */
    boolean send(String msg, String phone);

    /**
     * 群发短信
     * @param msg
     * @param phone
     * @return
     */
    boolean sendMore(String msg, String ... phone);

    boolean send(String type, Map<String, String> params, String phone);
}
