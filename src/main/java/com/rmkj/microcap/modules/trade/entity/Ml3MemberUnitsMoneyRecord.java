package com.rmkj.microcap.modules.trade.entity;/**
 * Created by Administrator on 2017/5/16.
 */

import java.math.BigDecimal;

/**
 * @author k
 * @create -05-16-13:54
 **/

public class Ml3MemberUnitsMoneyRecord {
    private String id;

    private String unitsId;

    //累计提现成功金额
    private BigDecimal sumMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }
}
