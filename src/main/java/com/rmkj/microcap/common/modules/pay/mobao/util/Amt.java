package com.rmkj.microcap.common.modules.pay.mobao.util;

public class Amt {
	@Override
	public String toString() {
		return amt + "^" + cerNo + "^" + name + "^" + pan;
	}
	private String   pan;
	private  String  cerNo;
	private String   name;
	private  String   amt;
	private  String    mb;
	public String getMb() {
		return mb;
	}
	public void setMb(String mb) {
		this.mb = mb;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getCerNo() {
		return cerNo;
	}
	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		
		
		this.amt = amt;
	}
	
	
	
}
