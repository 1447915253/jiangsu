package com.rmkj.microcap.common.modules.pay.shande2.bean;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ShanDeH5PayReqBean2 {
    private String merchant_order_sn;
    private String open_id;
    private String sub_open_id;
    private String total_fee;

    public String getMerchant_order_sn() {
        return merchant_order_sn;
    }

    public void setMerchant_order_sn(String merchant_order_sn) {
        this.merchant_order_sn = merchant_order_sn;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getSub_open_id() {
        return sub_open_id;
    }

    public void setSub_open_id(String sub_open_id) {
        this.sub_open_id = sub_open_id;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
}
