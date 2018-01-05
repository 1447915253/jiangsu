package com.rmkj.microcap.modules.market.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/10/25.
 */
public class KDataBean {
    /**
     * code : 合约代码
     * interval : 必选（例：1：分时图 5:5分钟 15:15分钟 30:30分钟 1h:1小时 1d:日线图）
     */

    @NotBlank
    private String code;
    @NotBlank
    private String interval;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
