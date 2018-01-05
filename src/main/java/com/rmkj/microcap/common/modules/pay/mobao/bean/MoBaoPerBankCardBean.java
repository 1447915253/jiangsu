package com.rmkj.microcap.common.modules.pay.mobao.bean;

/**
 * Created by Administrator on 2017/11/22.
 */
public class MoBaoPerBankCardBean {

    private String id;
    private String userId;
    private String mobile;
    private String cardByName;
    private String cardByNo;
    private String cerNumber;

    public void setId(String id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCardByName(String cardByName) {
        this.cardByName = cardByName;
    }

    public void setCardByNo(String cardByNo) {
        this.cardByNo = cardByNo;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCardByName() {
        return cardByName;
    }

    public String getCardByNo() {
        return cardByNo;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MoBaoPerBankCardBean{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", cardByName='" + cardByName + '\'' +
                ", cardByNo='" + cardByNo + '\'' +
                ", cerNumber='" + cerNumber + '\'' +
                '}';
    }
}
