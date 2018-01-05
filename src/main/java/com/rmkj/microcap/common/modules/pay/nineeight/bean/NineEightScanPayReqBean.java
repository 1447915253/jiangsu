package com.rmkj.microcap.common.modules.pay.nineeight.bean;

/**
 * Created by Administrator on 2017/6/6.
 */
public class NineEightScanPayReqBean {
    private String service;
    private String version;
    private String charset;
    private String sign_type;
    private String merchant_no;
    private String out_trade_no;
    private String body;
    private Integer total_fee;
    private String notify_url;
    private String client_ip;
    private String acquirer_type;
    private String sign;
    private String time_expire;
    private String bank_card_limit;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getBank_card_limit() {
        return bank_card_limit;
    }

    public void setBank_card_limit(String bank_card_limit) {
        this.bank_card_limit = bank_card_limit;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMerchant_no() {
        return merchant_no;
    }

    public void setMerchant_no(String merchant_no) {
        this.merchant_no = merchant_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAcquirer_type() {
        return acquirer_type;
    }

    public void setAcquirer_type(String acquirer_type) {
        this.acquirer_type = acquirer_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
