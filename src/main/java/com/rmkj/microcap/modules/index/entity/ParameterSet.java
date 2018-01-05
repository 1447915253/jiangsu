package com.rmkj.microcap.modules.index.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;

/**
 * TODO 参数配置类
 */
public class ParameterSet extends DataEntity{
    private String id;

    private Integer defaultPayType;

    private BigDecimal percentQibing;

    private BigDecimal percentBubing;

    private BigDecimal unitsMoneyAlarm;

    private BigDecimal unitsMoneyNotEnough;

    private Integer holdCount;

    private BigDecimal holdMoney;

    private BigDecimal cashMoney;

    private BigDecimal cashMoneyRation;

    private Integer cashMoneyCount;

    private String qrCodeUrl;

    private String qrCodeMenuUrl;

    //最小建仓金额
    private BigDecimal ordersMinMoney;
    //每种商品同时持仓单数
    private Integer holdOrdersCount;

    public BigDecimal getPercentQibing() {
        return percentQibing;
    }

    public void setPercentQibing(BigDecimal percentQibing) {
        this.percentQibing = percentQibing;
    }

    public BigDecimal getPercentBubing() {
        return percentBubing;
    }

    public void setPercentBubing(BigDecimal percentBubing) {
        this.percentBubing = percentBubing;
    }

    public Integer getDefaultPayType() {
        return defaultPayType;
    }

    public void setDefaultPayType(Integer defaultPayType) {
        this.defaultPayType = defaultPayType;
    }

    public BigDecimal getUnitsMoneyAlarm() {
        return unitsMoneyAlarm;
    }

    public void setUnitsMoneyAlarm(BigDecimal unitsMoneyAlarm) {
        this.unitsMoneyAlarm = unitsMoneyAlarm;
    }

    public BigDecimal getUnitsMoneyNotEnough() {
        return unitsMoneyNotEnough;
    }

    public void setUnitsMoneyNotEnough(BigDecimal unitsMoneyNotEnough) {
        this.unitsMoneyNotEnough = unitsMoneyNotEnough;
    }

    public BigDecimal getOrdersMinMoney() {
        return ordersMinMoney;
    }

    public void setOrdersMinMoney(BigDecimal ordersMinMoney) {
        this.ordersMinMoney = ordersMinMoney;
    }

    public Integer getHoldOrdersCount() {
        return holdOrdersCount;
    }

    public void setHoldOrdersCount(Integer holdOrdersCount) {
        this.holdOrdersCount = holdOrdersCount;
    }

    public String getQrCodeMenuUrl() {
        return qrCodeMenuUrl;
    }

    public void setQrCodeMenuUrl(String qrCodeMenuUrl) {
        this.qrCodeMenuUrl = qrCodeMenuUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getHoldCount() {
        return holdCount;
    }

    public void setHoldCount(Integer holdCount) {
        this.holdCount = holdCount;
    }

    public BigDecimal getHoldMoney() {
        return holdMoney;
    }

    public void setHoldMoney(BigDecimal holdMoney) {
        this.holdMoney = holdMoney;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public BigDecimal getCashMoneyRation() {
        return cashMoneyRation;
    }

    public void setCashMoneyRation(BigDecimal cashMoneyRation) {
        this.cashMoneyRation = cashMoneyRation;
    }

    public Integer getCashMoneyCount() {
        return cashMoneyCount;
    }

    public void setCashMoneyCount(Integer cashMoneyCount) {
        this.cashMoneyCount = cashMoneyCount;
    }
}