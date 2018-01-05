package com.rmkj.microcap.common.modules.pay.fiveeight.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FiveEightOpenPayResBean {
    private String ret;
    private String data;
    private String msg;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
