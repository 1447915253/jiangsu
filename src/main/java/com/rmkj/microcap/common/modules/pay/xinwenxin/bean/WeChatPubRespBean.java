package com.rmkj.microcap.common.modules.pay.xinwenxin.bean;

/**
 * Created by Administrator on 2017/11/15.
 */
public class WeChatPubRespBean {
    private String version;
    private String charset;
    private String sign_type;
    private String result_code;
    private String message;
    private String pay_info;

    public String getVersion() {
        return version;
    }

    public String getCharset() {
        return charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getMessage() {
        return message;
    }

    public String getPay_info() {
        return pay_info;
    }
}
