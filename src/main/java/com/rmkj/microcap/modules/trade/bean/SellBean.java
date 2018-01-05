package com.rmkj.microcap.modules.trade.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/10/19.
 */
public class SellBean {
    /**
     * id : 140d5967766043d2941dfde964efec33
     */

    @NotBlank
    private String id;

    @NotBlank
    private String tradePassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
