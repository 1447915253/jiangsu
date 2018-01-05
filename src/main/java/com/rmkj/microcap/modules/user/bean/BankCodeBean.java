package com.rmkj.microcap.modules.user.bean;/**
 * Created by Administrator on 2017/3/7.
 */

/**
 * TODO 威鹏支付，支行信息实体类
 * @author k
 * @create -03-07-16:06
 **/

public class BankCodeBean {
    private Integer id;
    private String aa;
    //支行号
    private String bankNo;
    //清算行号
    private String settQsBankCode;
    //支行名称
    private String name;
    //地区码
    private String settAreaCode;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getSettQsBankCode() {
        return settQsBankCode;
    }

    public void setSettQsBankCode(String settQsBankCode) {
        this.settQsBankCode = settQsBankCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSettAreaCode() {
        return settAreaCode;
    }

    public void setSettAreaCode(String settAreaCode) {
        this.settAreaCode = settAreaCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
