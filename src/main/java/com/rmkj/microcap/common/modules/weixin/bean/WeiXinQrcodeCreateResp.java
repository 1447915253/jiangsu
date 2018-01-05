package com.rmkj.microcap.common.modules.weixin.bean;

/**
 * Created by 123 on 2016/11/22.
 */
public class WeiXinQrcodeCreateResp {
    /**
     * ticket : gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==
     * expire_seconds : 60
     * url : http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI
     */

    private String ticket;
    private int expire_seconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
