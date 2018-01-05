package com.rmkj.microcap.modules.parameterset.entity;

import com.rmkj.microcap.common.bean.DataEntity;
import java.math.BigDecimal;

/**
* Created by Administrator on 2016-12-16.
*/

public class ParameterSetBean extends DataEntity    {



    //最大持仓单数
    private Integer holdCount;
    //最大持仓金额
    private BigDecimal holdMoney;
    //提现XXXX元以下系统实时返现
    private BigDecimal cashMoney;
    //每日提现限额
    private BigDecimal cashMoneyRation;
    //每日提现次数
    private Integer cashMoneyCount;
	//推广二维码url
	private String qrCodeUrl;
	//微信菜单url
	private String qrCodeMenuUrl;

	//最低建仓金额
	private BigDecimal ordersMinMoney;
	//每种商品同时持仓单数
	private Integer holdOrdersCount;

	//充值时商品名称
	private String productName;
	//威富通商户号
	private String wftBusinessNumber;
	//威富通商户key
	private String wftBusinessKey;


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWftBusinessNumber() {
		return wftBusinessNumber;
	}

	public void setWftBusinessNumber(String wftBusinessNumber) {
		this.wftBusinessNumber = wftBusinessNumber;
	}

	public String getWftBusinessKey() {
		return wftBusinessKey;
	}

	public void setWftBusinessKey(String wftBusinessKey) {
		this.wftBusinessKey = wftBusinessKey;
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

	public void setHoldCount(Integer holdCount){
		this.holdCount=holdCount;
	}
	public Integer getHoldCount(){
		return this.holdCount;
	}
	public void setHoldMoney(BigDecimal holdMoney){
		this.holdMoney=holdMoney;
	}
	public BigDecimal getHoldMoney(){
		return this.holdMoney;
	}
	public void setCashMoney(BigDecimal cashMoney){
		this.cashMoney=cashMoney;
	}
	public BigDecimal getCashMoney(){
		return this.cashMoney;
	}
	public void setCashMoneyRation(BigDecimal cashMoneyRation){
		this.cashMoneyRation=cashMoneyRation;
	}
	public BigDecimal getCashMoneyRation(){
		return this.cashMoneyRation;
	}
	public void setCashMoneyCount(Integer cashMoneyCount){
		this.cashMoneyCount=cashMoneyCount;
	}
	public Integer getCashMoneyCount(){
		return this.cashMoneyCount;
	}

}
