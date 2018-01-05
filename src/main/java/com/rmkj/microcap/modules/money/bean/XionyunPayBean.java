package com.rmkj.microcap.modules.money.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2018/1/4.
 */
public class XionyunPayBean {

    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    //金额
    private String money;

    //交易方式
    private int tradeType;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }
}
