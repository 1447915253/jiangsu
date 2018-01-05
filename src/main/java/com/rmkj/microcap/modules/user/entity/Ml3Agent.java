package com.rmkj.microcap.modules.user.entity;/**
 * Created by Administrator on 2016/12/16.
 */

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 * @author k
 * @create -12-16-17:30
 **/

public class Ml3Agent extends DataEntity {
    private String id;

    private String unitsId;

    private String userId;

    private Integer roleType;

    private String account;

    private String mobile;

    private String safePassword;

    private String agentInviteCode;

    private String realName;

    private String agentHeader;

    private BigDecimal money;

    private BigDecimal totalMoney;

    private Integer status;

    private Integer reviewStatus;

    private Date createTime;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String bankAccountName;

    private String bankAccount;

    private String bankName;

    private String bankChildName;

    private String idCard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId == null ? null : unitsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSafePassword() {
        return safePassword;
    }

    public void setSafePassword(String safePassword) {
        this.safePassword = safePassword == null ? null : safePassword.trim();
    }

    public String getAgentInviteCode() {
        return agentInviteCode;
    }

    public void setAgentInviteCode(String agentInviteCode) {
        this.agentInviteCode = agentInviteCode == null ? null : agentInviteCode.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getAgentHeader() {
        return agentHeader;
    }

    public void setAgentHeader(String agentHeader) {
        this.agentHeader = agentHeader == null ? null : agentHeader.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }
}
