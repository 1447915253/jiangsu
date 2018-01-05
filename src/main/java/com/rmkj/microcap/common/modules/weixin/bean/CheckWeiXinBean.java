package com.rmkj.microcap.common.modules.weixin.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhangbowen on 2016/6/8.
 */
public class CheckWeiXinBean {
    @NotBlank
    private String signature;
    @NotBlank
    private String timestamp;
    @NotBlank
    private String nonce;
    @NotBlank
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}
