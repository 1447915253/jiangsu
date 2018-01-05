package com.rmkj.microcap.common.modules.pay.shande.bean;

/**
 * Created by Administrator on 2017/8/11.
 */
public class ShanDePayResultResBean {

    private String merchant_order_sn;    //第三方商户的订单号,确保唯一
    private int type;                     //支付方式[微信1/支付宝2]
    private float total_fee;             //订单金额(元)
    private int cashier;                 //收银员ID

    public String getMerchant_order_sn() {
        return merchant_order_sn;
    }

    public void setMerchant_order_sn(String merchant_order_sn) {
        this.merchant_order_sn = merchant_order_sn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(float total_fee) {
        this.total_fee = total_fee;
    }

    public int getCashier() {
        return cashier;
    }

    public void setCashier(int cashier) {
        this.cashier = cashier;
    }


}
