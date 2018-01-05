package com.rmkj.microcap.common.modules.pay.yizhifu.bean;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/1/3.
 */
public class PayRequestParamBean {
    @NotNull
    private String money;
    @NotNull
    private String type;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
