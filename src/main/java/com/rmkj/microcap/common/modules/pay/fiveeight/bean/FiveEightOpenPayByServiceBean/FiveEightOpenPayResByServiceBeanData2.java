package com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean;

/**
 * Created by Administrator on 2017/7/10.
 */
public class FiveEightOpenPayResByServiceBeanData2 {
    private String payInfo;
    private String orderId;
    private String merchantId;
    private String outOrderId;
    private String merchantSign;
    private String channelSign;

    public String getMerchantSign() {
        return merchantSign;
    }

    public void setMerchantSign(String merchantSign) {
        this.merchantSign = merchantSign;
    }

    public String getChannelSign() {
        return channelSign;
    }

    public void setChannelSign(String channelSign) {
        this.channelSign = channelSign;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
