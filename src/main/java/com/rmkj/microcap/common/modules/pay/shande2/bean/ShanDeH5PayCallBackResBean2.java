package com.rmkj.microcap.common.modules.pay.shande2.bean;

/**
 * Created by Administrator on 2017/7/25.
 */
public class ShanDeH5PayCallBackResBean2 {
    private String merchant_order_sn;
    private String total_fee;

    public String getMerchant_order_sn() {
        return merchant_order_sn;
    }

    public void setMerchant_order_sn(String merchant_order_sn) {
        this.merchant_order_sn = merchant_order_sn;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
}
