package com.rmkj.microcap.modules.trade.bean;

import com.rmkj.microcap.modules.trade.entity.Trade;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 123 on 2016/10/27.
 */
public class UserTradeBean extends Trade {
    /**
     * 用户账号剩余金额 资金/代金券
     */
    private BigDecimal userMoney;

    private List<ControlGroup> groups;

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public List<ControlGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ControlGroup> groups) {
        this.groups = groups;
    }
}
