package com.rmkj.microcap.modules.money.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 123 on 2016/10/18.
 */
public class MoneyRecord extends DataEntity {
    /**
     * serialNo : 流水号
     * money : 金额
     * fee : 手续费
     * type : 类型：0 充值 1 提现
     * thirdSerialNo : 第三方流水号
     * status : 状态 0 处理中 1 成功 2 失败
     * remark : 备注
     * createTime : 创建时间
     * completeTime : 完成时间
     */

    private String userId;

    private String serialNo;
    private BigDecimal money;
    private BigDecimal fee;
    private String type;
    private String thirdSerialNo;
    private String channel;
    private String status;
    private String remark;
    private Date createTime;
    private Date completeTime;

    //支付方式名称
    private String chnName;
    private String bankAccount;
    private String bankName;
    private String openBankName;
    private String province;
    private String city;

    private String idCard;
    private String lianHangNo;
    private String bankCode;

    private String failureReason;

    //冲(重)提查询页面时间字段
    private String withDrawTime;

    public String getWithDrawTime() {
        return withDrawTime;
    }

    public void setWithDrawTime(String withDrawTime) {
        this.withDrawTime = withDrawTime;
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThirdSerialNo() {
        return thirdSerialNo;
    }

    public void setThirdSerialNo(String thirdSerialNo) {
        this.thirdSerialNo = thirdSerialNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
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

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLianHangNo() {
        return lianHangNo;
    }

    public void setLianHangNo(String lianHangNo) {
        this.lianHangNo = lianHangNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    @Override
    public String toString() {
        return "MoneyRecord{" +
                "userId='" + userId + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", money=" + money +
                ", fee=" + fee +
                ", type='" + type + '\'' +
                ", thirdSerialNo='" + thirdSerialNo + '\'' +
                ", channel='" + channel + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", completeTime=" + completeTime +
                ", chnName='" + chnName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankName='" + bankName + '\'' +
                ", openBankName='" + openBankName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", idCard='" + idCard + '\'' +
                ", lianHangNo='" + lianHangNo + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", failureReason='" + failureReason + '\'' +
                ", withDrawTime='" + withDrawTime + '\'' +
                '}';
    }
}
