package com.rmkj.microcap.common.modules.weixin.bean;/**
 * Created by Administrator on 2017/2/10.
 */

/**
 * @author k
 * @create -02-10-17:37
 **/

public class WeiXinShare {
    private String tid;
    private String ticket;
    private String errcode;
    private String errmsg;
    private String expires_in;
    private String acquiretime;
    //生成签名的随机串
    private String nonceStr;
    //生成签名的时间戳
    private String timestamp;
    //生成的签名
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAcquiretime() {
        return acquiretime;
    }

    public void setAcquiretime(String acquiretime) {
        this.acquiretime = acquiretime;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
