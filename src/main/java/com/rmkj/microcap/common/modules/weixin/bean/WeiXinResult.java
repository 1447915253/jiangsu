package com.rmkj.microcap.common.modules.weixin.bean;

/**
 * Created by 123 on 2016/10/28.
 * 业务结果
 */
public class WeiXinResult extends WeiXinReturn {

    private String result_code;
    private String err_code;
    private String err_code_des;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public boolean success(){
        return super.success() && "SUCCESS".equals(result_code);
    }
}
