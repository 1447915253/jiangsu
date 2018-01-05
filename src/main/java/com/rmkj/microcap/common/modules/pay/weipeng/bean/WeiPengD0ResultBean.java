package com.rmkj.microcap.common.modules.pay.weipeng.bean;/**
 * Created by Administrator on 2017/3/6.
 */

/**
 * @author k
 * @create -03-06-11:17
 **/

public class WeiPengD0ResultBean {

    //商户自己系统订单号
    private String pay_num;
    //返回结果标识
    private String return_code;
    //信息描述
    private String message;
    //二维码跳转链接
    private String code_url;
    //订单号
    private String out_trade_no;

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
