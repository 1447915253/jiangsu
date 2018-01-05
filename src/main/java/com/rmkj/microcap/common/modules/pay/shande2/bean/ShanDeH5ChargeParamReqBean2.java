package com.rmkj.microcap.common.modules.pay.shande2.bean;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ShanDeH5ChargeParamReqBean2 {
    private String prepay_id;
    private String callback_url;
    private String cancel_url;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }
}
