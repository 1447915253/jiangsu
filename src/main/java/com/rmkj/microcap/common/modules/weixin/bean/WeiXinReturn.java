package com.rmkj.microcap.common.modules.weixin.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 123 on 2016/10/28.
 * 请求结果
 */
public class WeiXinReturn {
    private String return_code;
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public boolean success(){
        return "SUCCESS".equals(return_code);
    }

}
