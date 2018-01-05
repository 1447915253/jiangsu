package com.rmkj.microcap.modules.money.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/10/28.
 */
public class FiveEightPrePayBean {
    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    private String money;

    private String spbillCreateIp;

    private String tradeType;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
