package com.rmkj.microcap.common.modules.money.out.pojo;

/**
 * Created by Administrator on 2017/6/10.
 */
public class CheckBankNoResp {
    private String version;
    private String charset;
    private String resp_time;
    private String resp_code;
    private String resp_msg;
    private String nonce_str;
    private String sign_type;
    private String sign;
    private String merchant_no;
    private String card_no;
    private String bank_code;
    private String bank_name;
    private String card_name;
    private String card_bin;
    private String card_type;
    private String head_code;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getResp_time() {
        return resp_time;
    }

    public void setResp_time(String resp_time) {
        this.resp_time = resp_time;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchant_no() {
        return merchant_no;
    }

    public void setMerchant_no(String merchant_no) {
        this.merchant_no = merchant_no;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_bin() {
        return card_bin;
    }

    public void setCard_bin(String card_bin) {
        this.card_bin = card_bin;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getHead_code() {
        return head_code;
    }

    public void setHead_code(String head_code) {
        this.head_code = head_code;
    }
}
