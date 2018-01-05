package com.rmkj.microcap.modules.trade.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 123 on 2016/10/18.
 */
public class Trade extends DataEntity{

    /**
     * serialNo : 流水号
     * money : 购买金额
     * type : 类型 0 资金 1 代金券
     * fee : 手续费
     * contractName : 合约名称
     * pointValue : 波动一个点，盈亏多少钱
     * profitMax : 止盈百分比
     * lossMax : 止损百分比
     * buyUpDown : 买涨买跌 0 买涨 1 买跌
     * buyTime : 建仓时间
     * buyPoint : 建仓价
     * sellPoint : 平仓价
     * difMoney : 盈亏金额（含手续费）
     * sellType : 平仓类型 0 手动平仓 1 止盈止损平仓 2 休市平仓
     * sellTime : 平仓（交割）时间
     */

    private String userId;
    private String serialNo;
    private BigDecimal money;
    private String couponType;
    private BigDecimal couponMoney;
    private String model;
    private String type;
    private BigDecimal winMoney;
    private BigDecimal fee;
    private BigDecimal returnFee;
    private BigDecimal returnOperateFee;
    private String status;
    private String code;
    private String contractName;
    private BigDecimal pointValue;
    private Integer profitMax;
    private Integer lossMax;
    private BigDecimal profitMaxPoint;
    private BigDecimal lossMaxPoint;
    private String buyUpDown;
    private Date buyTime;
    private BigDecimal buyPoint;
    private BigDecimal sellPoint;
    private BigDecimal difMoney;
    private BigDecimal difMoneyOperator;
    private BigDecimal difMoneyUnits;
    private String sellType;
    private Date sellTime;
    private String brokerId;

    // 1 赢 -1 亏 0 平
    private String result = null;

    private String offTime;
    private String offPoint;
    private String balanceStatus;
    private String parent1Id;
    private String parent2Id;
    private String parent3Id;

    //会员单位盈亏金额
    private BigDecimal sumUnitsDifMoney;
    private BigDecimal sumOperatorDifMoney;
    //保证金浮动延时秒数
    private Integer lagSecond;

    //会员单位id
    private String unitsId;

    private BigDecimal sumReturnMoney;
    private BigDecimal sumReturnFee;

    public BigDecimal getDifMoneyOperator() {
        return difMoneyOperator;
    }

    public void setDifMoneyOperator(BigDecimal difMoneyOperator) {
        this.difMoneyOperator = difMoneyOperator;
    }

    public BigDecimal getDifMoneyUnits() {
        return difMoneyUnits;
    }

    public void setDifMoneyUnits(BigDecimal difMoneyUnits) {
        this.difMoneyUnits = difMoneyUnits;
    }

    public BigDecimal getSumOperatorDifMoney() {
        return sumOperatorDifMoney;
    }

    public void setSumOperatorDifMoney(BigDecimal sumOperatorDifMoney) {
        this.sumOperatorDifMoney = sumOperatorDifMoney;
    }

    public BigDecimal getReturnOperateFee() {
        return returnOperateFee;
    }

    public void setReturnOperateFee(BigDecimal returnOperateFee) {
        this.returnOperateFee = returnOperateFee;
    }

    public BigDecimal getSumReturnMoney() {
        return sumReturnMoney;
    }

    public void setSumReturnMoney(BigDecimal sumReturnMoney) {
        this.sumReturnMoney = sumReturnMoney;
    }

    public BigDecimal getSumReturnFee() {
        return sumReturnFee;
    }

    public void setSumReturnFee(BigDecimal sumReturnFee) {
        this.sumReturnFee = sumReturnFee;
    }

    public BigDecimal getReturnFee() {
        return returnFee;
    }

    public void setReturnFee(BigDecimal returnFee) {
        this.returnFee = returnFee;
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public BigDecimal getSumUnitsDifMoney() {
        return sumUnitsDifMoney;
    }

    public void setSumUnitsDifMoney(BigDecimal sumUnitsDifMoney) {
        this.sumUnitsDifMoney = sumUnitsDifMoney;
    }

    public Integer getLagSecond() {
        return lagSecond;
    }

    public void setLagSecond(Integer lagSecond) {
        this.lagSecond = lagSecond;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(BigDecimal winMoney) {
        this.winMoney = winMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getPointValue() {
        return pointValue;
    }

    public void setPointValue(BigDecimal pointValue) {
        this.pointValue = pointValue;
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

    public BigDecimal getProfitMaxPoint() {
        return profitMaxPoint;
    }

    public void setProfitMaxPoint(BigDecimal profitMaxPoint) {
        this.profitMaxPoint = profitMaxPoint;
    }

    public BigDecimal getLossMaxPoint() {
        return lossMaxPoint;
    }

    public void setLossMaxPoint(BigDecimal lossMaxPoint) {
        this.lossMaxPoint = lossMaxPoint;
    }

    public String getBuyUpDown() {
        return buyUpDown;
    }

    public void setBuyUpDown(String buyUpDown) {
        this.buyUpDown = buyUpDown;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getBuyPoint() {
        return buyPoint;
    }

    public void setBuyPoint(BigDecimal buyPoint) {
        this.buyPoint = buyPoint;
    }

    public BigDecimal getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(BigDecimal sellPoint) {
        this.sellPoint = sellPoint;
    }

    public BigDecimal getDifMoney() {
        return difMoney;
    }

    public void setDifMoney(BigDecimal difMoney) {
        this.difMoney = difMoney;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getOffPoint() {
        return offPoint;
    }

    public void setOffPoint(String offPoint) {
        this.offPoint = offPoint;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(String balanceStatus) {
        this.balanceStatus = balanceStatus;
    }

    public String getParent1Id() {
        return parent1Id;
    }

    public void setParent1Id(String parent1Id) {
        this.parent1Id = parent1Id;
    }

    public String getParent2Id() {
        return parent2Id;
    }

    public void setParent2Id(String parent2Id) {
        this.parent2Id = parent2Id;
    }

    public String getParent3Id() {
        return parent3Id;
    }

    public void setParent3Id(String parent3Id) {
        this.parent3Id = parent3Id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
