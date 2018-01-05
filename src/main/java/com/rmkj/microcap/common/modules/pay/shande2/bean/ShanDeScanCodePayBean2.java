package com.rmkj.microcap.common.modules.pay.shande2.bean;

/**
 * Created by Administrator on 2017/8/11.
 */
public class ShanDeScanCodePayBean2 {

    private String order_sn;
    private String qr_code;
    private String merchant_order_sn;
    private String result_code;
    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
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

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

}
