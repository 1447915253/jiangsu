package com.rmkj.microcap.common.modules.pay.shande2.bean;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ShanDeH5PayResBean2 {
    private String prepay_id;
    private String order_sn;
    private String merchant_order_sn;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getMerchant_order_sn() {
        return merchant_order_sn;
    }

    public void setMerchant_order_sn(String merchant_order_sn) {
        this.merchant_order_sn = merchant_order_sn;
    }
}
