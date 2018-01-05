package com.rmkj.microcap.modules.coupon.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2017/3/8.
 */
public class Coupon extends DataEntity{

    private String userId;
    private Integer num;
    private BigDecimal money;
    private String type;
    private Date createTime;
    private Date useTime;
    private Date endDate;
    private BigDecimal minMoney;
    private Integer status;
    private String remark;

    private List<String> tradeIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getTradeIds() {
        return tradeIds;
    }

    public void setTradeIds(List<String> tradeIds) {
        this.tradeIds = tradeIds;
    }
}
