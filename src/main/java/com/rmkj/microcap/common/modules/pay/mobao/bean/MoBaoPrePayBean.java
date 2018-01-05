package com.rmkj.microcap.common.modules.pay.mobao.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MoBaoPrePayBean {
    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    private String money;
    private String cardByName;
    private String cardByNo;
    private String cerNumber;
    private String mobile;
    private String yzm;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCardByName() {
        return cardByName;
    }

    public void setCardByName(String cardByName) {
        this.cardByName = cardByName;
    }

    public String getCardByNo() {
        return cardByNo;
    }

    public void setCardByNo(String cardByNo) {
        this.cardByNo = cardByNo;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
