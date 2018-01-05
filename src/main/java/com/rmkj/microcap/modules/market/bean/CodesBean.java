package com.rmkj.microcap.modules.market.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/10/19.
 */
public class CodesBean {
    /**
     * codes : 合约代码，英文逗号隔开 gold,gold1
     * type : 不填返回所有信息，0只返回当前价格
     */
    @NotBlank
    private String codes;
    private String type;

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
