
package com.rmkj.microcap.common.modules.pay.xionyunPay.bean;

/**
 * 支付回调的参数实体类
 * 
 * @author zhoutingting
 */

public class XionyunNotifyBean {

    private String trade_no;
    private String out_trade_no;
    private String money;
    private String return_code;
    private String token;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

