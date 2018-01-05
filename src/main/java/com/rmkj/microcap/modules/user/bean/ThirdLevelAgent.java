package com.rmkj.microcap.modules.user.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ThirdLevelAgent {
    private String id;
    private String userId;
    private String unitsId;
    private String roleType;
    private String account;
    private String mobile;
    private String safePassword;
    private String agentInviteCode;
    private String realName;
    private String agentHeader;
    private BigDecimal money;
    private BigDecimal totalMoney;
    private int status;
    private int reviewStatus;
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
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSafePassword() {
        return safePassword;
    }

    public void setSafePassword(String safePassword) {
        this.safePassword = safePassword;
    }

    public String getAgentInviteCode() {
        return agentInviteCode;
    }

    public void setAgentInviteCode(String agentInviteCode) {
        this.agentInviteCode = agentInviteCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAgentHeader() {
        return agentHeader;
    }

    public void setAgentHeader(String agentHeader) {
        this.agentHeader = agentHeader;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
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
        this.lastLoginIp = lastLoginIp;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankChildName() {
        return bankChildName;
    }

    public void setBankChildName(String bankChildName) {
        this.bankChildName = bankChildName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
