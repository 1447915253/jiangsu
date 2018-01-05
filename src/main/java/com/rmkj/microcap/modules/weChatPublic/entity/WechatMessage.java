package com.rmkj.microcap.modules.weChatPublic.entity;

/**
 * Created by 123 on 2017/3/14.
 */
public class WechatMessage {

    String type;
    String sendType;
    String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
