package com.rmkj.microcap.common.modules.pay.fiveeight.bean;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FiveEightOpenPayResBeanData2 {
    private String payInfo;
    private String orderId;
    private String merchantId;
    private String outOrderId;

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
