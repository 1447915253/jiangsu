package com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean;/**
 * Created by Administrator on 2017/3/6.
 */

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FiveEightPrePayBean {
    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
