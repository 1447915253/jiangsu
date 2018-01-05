package com.rmkj.microcap.common.modules.pay.nineeight.bean;

/**
 * Created by Administrator on 2017/6/6.
 */
public class NineEightScanPayResultResBean {

    // 响应头
    public String version; // 业务响应码
    public String charset; // 返回信息描述
    public String sign_type;
    public String resp_time; // 响应时间
    public String resp_code; // 响应代码
    public String resp_msg; // 响应描述
    public String sign;

    private String mch_no; 	// 商户号
    private String out_trade_no; 	// 商户订单号
    private String trade_no; 		// 98平台订单号
    private String bank_trade_no; 	// 渠道流水号
    private String currency; 		// 交易币种
    private String trade_time; 		// 交易时间（交易成功时交易时间）
    private String trade_state;		//交易状态
    private String amount; 			// 交易金额（单位分）
    private String acquirer_type; 	// 交易类型（支付宝、微信、qq钱包、百度钱包、京东钱包）
    private String device_info;		// 设备信息
    private String attach; 			// 附件
    private String extend;			//扩展字段（保留）

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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_no() {
        return mch_no;
    }

    public void setMch_no(String mch_no) {
        this.mch_no = mch_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getBank_trade_no() {
        return bank_trade_no;
    }

    public void setBank_trade_no(String bank_trade_no) {
        this.bank_trade_no = bank_trade_no;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(String trade_time) {
        this.trade_time = trade_time;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAcquirer_type() {
        return acquirer_type;
    }

    public void setAcquirer_type(String acquirer_type) {
        this.acquirer_type = acquirer_type;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    /* private String service;
    private String version;
    private String charset;
    private String sign_type;
    private String trade_status;
    private String message;
    private String out_trade_no;
    private String mch_no;
    private String sign;
    private String out_transaction_id;
    private String transaction_id;
    private String acquirer_type;
    private String amount;
    private String currency;
    private String device_info;
    private String attach;
    private String order_time;
    private String resp_time;
    private String trade_no;
    private String resp_code;
    private String bank_trade_no;
    private String trade_time;

    public String getBank_trade_no() {
        return bank_trade_no;
    }

    public void setBank_trade_no(String bank_trade_no) {
        this.bank_trade_no = bank_trade_no;
    }

    public String getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(String trade_time) {
        this.trade_time = trade_time;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getMch_no() {
        return mch_no;
    }

    public void setMch_no(String mch_no) {
        this.mch_no = mch_no;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOut_transaction_id() {
        return out_transaction_id;
    }

    public void setOut_transaction_id(String out_transaction_id) {
        this.out_transaction_id = out_transaction_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAcquirer_type() {
        return acquirer_type;
    }

    public void setAcquirer_type(String acquirer_type) {
        this.acquirer_type = acquirer_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getResp_time() {
        return resp_time;
    }

    public void setResp_time(String resp_time) {
        this.resp_time = resp_time;
    }*/
}
