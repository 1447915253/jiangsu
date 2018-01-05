package com.rmkj.microcap.modules.money.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 123 on 2016/10/19.
 */
public class MoneyChange extends DataEntity {

    private String userId;
    private BigDecimal difMoney;
    private String type;
    private BigDecimal beforeMoney;
    private BigDecimal afterMoney;
    private Date createTime;
    private String remark;

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getDifMoney() {
        return difMoney;
    }

    public void setDifMoney(BigDecimal difMoney) {
        this.difMoney = difMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(BigDecimal beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public BigDecimal getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(BigDecimal afterMoney) {
        this.afterMoney = afterMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
