package com.rmkj.microcap.modules.user.entity;

import java.math.BigDecimal;

/**
 * TODO 会员单位实体类
 */
public class Ml3MemberUnits {
    private String id;

    private String centerId;

    private String name;

    private String agentInviteCode;

    //会员单位二级域名
    private String twoLevelDomain;
    //返会员单位手续费比例
    private BigDecimal returnFeePercent;

    private BigDecimal money;

    private Double bondMoney;

    private String realName;

    private String idCard;

    private String bankAccountName;

    private String bankAccount;

    private String bankName;

    private String bankChildName;

    private Integer status;

    public BigDecimal getReturnFeePercent() {
        return returnFeePercent;
    }

    public void setReturnFeePercent(BigDecimal returnFeePercent) {
        this.returnFeePercent = returnFeePercent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId == null ? null : centerId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAgentInviteCode() {
        return agentInviteCode;
    }

    public void setAgentInviteCode(String agentInviteCode) {
        this.agentInviteCode = agentInviteCode == null ? null : agentInviteCode.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getBondMoney() {
        return bondMoney;
    }

    public void setBondMoney(Double bondMoney) {
        this.bondMoney = bondMoney;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankChildName() {
        return bankChildName;
    }

    public void setBankChildName(String bankChildName) {
        this.bankChildName = bankChildName == null ? null : bankChildName.trim();
    }

    public String getTwoLevelDomain() {
        return twoLevelDomain;
    }

    public void setTwoLevelDomain(String twoLevelDomain) {
        this.twoLevelDomain = twoLevelDomain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}