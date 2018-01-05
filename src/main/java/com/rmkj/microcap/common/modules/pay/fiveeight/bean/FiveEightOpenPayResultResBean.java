package com.rmkj.microcap.common.modules.pay.fiveeight.bean;

/**
 * Created by Administrator on 2017/6/10.
 */
public class FiveEightOpenPayResultResBean {
    private String orderId;
    private String openId;
    private String trueName;
    private String totalAmount;
    private String transactionId;
    private String merchantId;
    private String outOrderId;
    private String paymentType;
    private String tradeType;
    private String payTime;
    private String attach;
    private String channelSign;
    private String merchantSign;
    private String attachOrderId;

    public String getAttachOrderId() {
        return attachOrderId;
    }

    public void setAttachOrderId(String attachOrderId) {
        this.attachOrderId = attachOrderId;
    }

    public String getChannelSign() {
        return channelSign;
    }

    public void setChannelSign(String channelSign) {
        this.channelSign = channelSign;
    }

    public String getMerchantSign() {
        return merchantSign;
    }

    public void setMerchantSign(String merchantSign) {
        this.merchantSign = merchantSign;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
