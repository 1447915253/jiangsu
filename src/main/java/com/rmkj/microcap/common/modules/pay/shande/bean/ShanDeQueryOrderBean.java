package com.rmkj.microcap.common.modules.pay.shande.bean;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ShanDeQueryOrderBean {
    private String merchant_order_sn;
    private String order_sn;
    private String trade_no;
    private String trade_state;
    private float total_fee;
    private float fee;
    private float net_money;
    private int pay_time;
    private int store_id;
    private int cashier_id;
    private String platform_order_no;

    public String getMerchant_order_sn() {
        return merchant_order_sn;
    }

    public void setMerchant_order_sn(String merchant_order_sn) {
        this.merchant_order_sn = merchant_order_sn;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(float total_fee) {
        this.total_fee = total_fee;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public float getNet_money() {
        return net_money;
    }

    public void setNet_money(float net_money) {
        this.net_money = net_money;
    }

    public int getPay_time() {
        return pay_time;
    }

    public void setPay_time(int pay_time) {
        this.pay_time = pay_time;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(int cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getPlatform_order_no() {
        return platform_order_no;
    }

    public void setPlatform_order_no(String platform_order_no) {
        this.platform_order_no = platform_order_no;
    }
}
