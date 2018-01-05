package com.rmkj.microcap.modules.trade.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by 123 on 2016/10/19.
 */
public class MakeTradeBean {

    /**
     * code : 合约代码
     * type : 类型 0 资金 1 代金券
     * buyUpDown : 买涨买跌 0 买涨 1 买跌
     * money : 购买金额
     * profitMax : 止盈值
     * lossMax : 止损值
     */
    @NotBlank
    private String code;
//    @NotBlank
    private String type;
    @NotBlank
    private String buyUpDown;
    @NotNull
    @Min(0)
    private BigDecimal money;
//    @NotNull
    private Integer profitMax;
//    @NotNull
    private Integer lossMax;
    @NotNull
    private String offTime;

    private String couponId;
    //券的种类
    private String couponType;
    //券的数量
    private Integer couponNum;
    //券的面额
    private Integer couponMoney;

    public Integer getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    //    @NotNull
    private String tradePassword;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuyUpDown() {
        return buyUpDown;
    }

    public void setBuyUpDown(String buyUpDown) {
        this.buyUpDown = buyUpDown;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getProfitMax() {
        return profitMax;
    }

    public void setProfitMax(Integer profitMax) {
        this.profitMax = profitMax;
    }

    public Integer getLossMax() {
        return lossMax;
    }

    public void setLossMax(Integer lossMax) {
        this.lossMax = lossMax;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
