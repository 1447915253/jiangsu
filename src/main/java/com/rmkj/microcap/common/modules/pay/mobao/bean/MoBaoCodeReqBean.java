package com.rmkj.microcap.common.modules.pay.mobao.bean;


import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/11/9.
 */
public class MoBaoCodeReqBean {

    @NotNull
    private String orderId;
    private String ksPayOrderId;
    private String yzm;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKsPayOrderId() {
        return ksPayOrderId;
    }

    public void setKsPayOrderId(String ksPayOrderId) {
        this.ksPayOrderId = ksPayOrderId;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
