package com.rmkj.microcap.common.modules.weixin.bean;

import java.util.ArrayList;

/**
 * Created by 123 on 2017/3/14.
 */
public class CustomMessage {
    private String touser;
    private String msgtype;
    private CustomMessageNews news;
    private CustomMessageText text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomMessageNews getNews() {
        return news;
    }

    public void setNews(CustomMessageNews news) {
        this.news = news;
    }

    public CustomMessageText getText() {
        return text;
    }

    public void setText(CustomMessageText text) {
        this.text = text;
    }
}
