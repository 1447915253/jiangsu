package com.rmkj.microcap.common.modules.pay.shande2.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ShanDeH5OpenIdBean2 {
    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    private String money;
    private String open_id;
    private String sub_open_id;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
