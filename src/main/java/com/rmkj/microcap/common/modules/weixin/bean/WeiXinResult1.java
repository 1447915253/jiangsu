package com.rmkj.microcap.common.modules.weixin.bean;

/**
 * Created by zhangbowen on 2016/6/7.
 */
public class WeiXinResult1 {
    protected int errcode;
    protected String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean isSuccessFul() {
        return errcode == 0;
    }
}
