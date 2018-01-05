package com.rmkj.microcap.modules.corps.bean;

import java.math.BigDecimal;

/**
 * Created by 123 on 2016/11/22.
 */
public class UserCorps {
    private int bingCount;
    private int paoBingCount;
    private int qiBingCount;
    private int buBingCount;

    private BigDecimal paoBingMoney;
    private BigDecimal qiBingMoney;
    private BigDecimal buBingMoney;

    private BigDecimal paoBingMoneyTotal;
    private BigDecimal qiBingMoneyTotal;
    private BigDecimal buBingMoneyTotal;

    public int getBingCount() {
        return bingCount;
    }

    public void setBingCount(int bingCount) {
        this.bingCount = bingCount;
    }

    public int getPaoBingCount() {
        return paoBingCount;
    }

    public void setPaoBingCount(int paoBingCount) {
        this.paoBingCount = paoBingCount;
    }

    public int getQiBingCount() {
        return qiBingCount;
    }

    public void setQiBingCount(int qiBingCount) {
        this.qiBingCount = qiBingCount;
    }

    public int getBuBingCount() {
        return buBingCount;
    }

    public void setBuBingCount(int buBingCount) {
        this.buBingCount = buBingCount;
    }

    public BigDecimal getBuBingMoney() {
        return buBingMoney;
    }

    public void setBuBingMoney(BigDecimal buBingMoney) {
        this.buBingMoney = buBingMoney;
    }

    public BigDecimal getQiBingMoney() {
        return qiBingMoney;
    }

    public void setQiBingMoney(BigDecimal qiBingMoney) {
        this.qiBingMoney = qiBingMoney;
    }

    public BigDecimal getPaoBingMoney() {
        return paoBingMoney;
    }

    public void setPaoBingMoney(BigDecimal paoBingMoney) {
        this.paoBingMoney = paoBingMoney;
    }

    public BigDecimal getPaoBingMoneyTotal() {
        return paoBingMoneyTotal;
    }

    public void setPaoBingMoneyTotal(BigDecimal paoBingMoneyTotal) {
        this.paoBingMoneyTotal = paoBingMoneyTotal;
    }

    public BigDecimal getQiBingMoneyTotal() {
        return qiBingMoneyTotal;
    }

    public void setQiBingMoneyTotal(BigDecimal qiBingMoneyTotal) {
        this.qiBingMoneyTotal = qiBingMoneyTotal;
    }

    public BigDecimal getBuBingMoneyTotal() {
        return buBingMoneyTotal;
    }

    public void setBuBingMoneyTotal(BigDecimal buBingMoneyTotal) {
        this.buBingMoneyTotal = buBingMoneyTotal;
    }
}
