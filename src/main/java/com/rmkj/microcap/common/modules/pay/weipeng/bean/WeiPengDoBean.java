package com.rmkj.microcap.common.modules.pay.weipeng.bean;/**
 * Created by Administrator on 2017/3/6.
 */

import com.rmkj.microcap.common.constants.RegexpConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * TODO 威鹏付款
 * @author k
 * @create -03-06-11:30
 **/

public class WeiPengDoBean {
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
