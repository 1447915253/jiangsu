package com.rmkj.microcap.modules.money.bean;

/**
 * Created by Administrator on 2017/2/7.
 */
public class FromAppReqBean {
    private String serialNo;
    private String userId;
    private String money;
    private String thirdSerialNo;
    private String status;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getThirdSerialNo() {
        return thirdSerialNo;
    }

    public void setThirdSerialNo(String thirdSerialNo) {
        this.thirdSerialNo = thirdSerialNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
