package com.rmkj.microcap.common.modules.pay.yizhifu.bean;

/**
 * Created by Administrator on 2016/12/30.
 */
public class PayOrderResBean {
    private String status;
    private String statusdesc;
    private String oid;
    private String amount;
    private String moneytype;
    private String codeurl;
    private String bankurl;
    private String sign;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMoneytype() {
        return moneytype;
    }

    public void setMoneytype(String moneytype) {
        this.moneytype = moneytype;
    }

    public String getCodeurl() {
        return codeurl;
    }

    public void setCodeurl(String codeurl) {
        this.codeurl = codeurl;
    }

    public String getBankurl() {
        return bankurl;
    }

    public void setBankurl(String bankurl) {
        this.bankurl = bankurl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
