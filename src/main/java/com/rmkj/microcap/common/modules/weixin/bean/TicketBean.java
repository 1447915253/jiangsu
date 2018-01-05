package com.rmkj.microcap.common.modules.weixin.bean;

/**
 * Created by lichq on 2016-07-29.
 */
public class TicketBean extends WeiXinResult1{
    private String ticket;
    private int expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
