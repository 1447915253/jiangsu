package com.rmkj.microcap.common.modules.pay.fiveeight.bean;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FiveEightOpenPayReqBean {
    private String service;
    private String nonce;
    private String sign;
    private String merchantId;
    private String outOrderId;
    private String totalAmount;
    private String mchCreateIp;
    private String subJect;
    private String desc;
    private String openId;
    private String paymentType;
    private String callBack;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMchCreateIp() {
        return mchCreateIp;
    }

    public void setMchCreateIp(String mchCreateIp) {
        this.mchCreateIp = mchCreateIp;
    }

    public String getSubJect() {
        return subJect;
    }

    public void setSubJect(String subJect) {
        this.subJect = subJect;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
