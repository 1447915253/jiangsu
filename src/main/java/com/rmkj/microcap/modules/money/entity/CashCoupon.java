package com.rmkj.microcap.modules.money.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 123 on 2016/11/24.
 */
public class CashCoupon extends DataEntity{

    private BigDecimal money;
    private String userId;
    private Date createTime;
    private Date endDate;
    private BigDecimal minMoney;
    private String status;
    private String remark;
    private String cashCouponCounts;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCashCouponCounts() {
        return cashCouponCounts;
    }

    public void setCashCouponCounts(String cashCouponCounts) {
        this.cashCouponCounts = cashCouponCounts;
    }
}
