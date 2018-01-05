package com.rmkj.microcap.common.modules.pay.mobao.bean;

/**
 * Created by Administrator on 2017/11/8.
 */
public class MoBaoCodeRespBean {
    private String status;
    private String merId;
    private String orderId;
    private String ksPayOrderId;
    private String chanlRefCode;
    private String bankOrderId;
    private String yzm;
    private String transDate;
    private String transTime;
    private String refCode;
    private String refMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

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

    public String getChanlRefCode() {
        return chanlRefCode;
    }

    public void setChanlRefCode(String chanlRefCode) {
        this.chanlRefCode = chanlRefCode;
    }

    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefMsg() {
        return refMsg;
    }

    public void setRefMsg(String refMsg) {
        this.refMsg = refMsg;
    }
}
