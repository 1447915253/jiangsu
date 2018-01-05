package com.rmkj.microcap.modules.user.entity;

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;

/**
* Created by Administrator on 2016-11-17.
*/
public class Ml3OperateCenterBean extends DataEntity {
    //运营中心名称
    private String name;
    //余额
    private BigDecimal money;
    //保证金
    private Double bondMoney;
    //真实姓名
    private String realName;
    //身份证
    private String idCard;
    //银行开户姓名
    private String bankAccountName;
    //银行账号
    private String bankAccount;
    //开户行
    private String bankName;
    //开户银行支行名
    private String bankChildName;
    //状态：0正常 1停用
    private Integer status;

	private BigDecimal returnFeePercent;

	public BigDecimal getReturnFeePercent() {
		return returnFeePercent;
	}

	public void setReturnFeePercent(BigDecimal returnFeePercent) {
		this.returnFeePercent = returnFeePercent;
	}

	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setMoney(BigDecimal money){
		this.money=money;
	}
	public BigDecimal getMoney(){
		return this.money;
	}
	public void setBondMoney(Double bondMoney){
		this.bondMoney=bondMoney;
	}
	public Double getBondMoney(){
		return this.bondMoney;
	}
	public void setRealName(String realName){
		this.realName=realName;
	}
	public String getRealName(){
		return this.realName;
	}
	public void setIdCard(String idCard){
		this.idCard=idCard;
	}
	public String getIdCard(){
		return this.idCard;
	}
	public void setBankAccountName(String bankAccountName){
		this.bankAccountName=bankAccountName;
	}
	public String getBankAccountName(){
		return this.bankAccountName;
	}
	public void setBankAccount(String bankAccount){
		this.bankAccount=bankAccount;
	}
	public String getBankAccount(){
		return this.bankAccount;
	}
	public void setBankName(String bankName){
		this.bankName=bankName;
	}
	public String getBankName(){
		return this.bankName;
	}
	public void setBankChildName(String bankChildName){
		this.bankChildName=bankChildName;
	}
	public String getBankChildName(){
		return this.bankChildName;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return this.status;
	}

}
