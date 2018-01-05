package com.rmkj.microcap.common.modules.pay.weipeng.bean;/**
 * Created by Administrator on 2017/3/6.
 */

/**
 * TODO 威鹏扫码支付下单，异步返回通知 (成功才返回，不成功不返回)
 * @author k
 * @create -03-06-17:35
 **/

public class WeiPengQrCodeAsyncResultBean {

    //返回结果标示	return_code(10000成功)	必填
    private String return_code;
    //订单号	out_trade_no	成功必填
    private String out_trade_no;
    //支付结果	trade_result (success)	成功必填
    private String trade_result;
    //信息描述	message
    private String message;
    //商户订单号	pay_num	原样返回
    private String pay_num;
    //订单金额	total_fee  （整数 分）
    private String total_fee;
    //验签	sign
    private String sign;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_result() {
        return trade_result;
    }

    public void setTrade_result(String trade_result) {
        this.trade_result = trade_result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

