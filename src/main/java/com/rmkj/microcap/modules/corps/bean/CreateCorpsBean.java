package com.rmkj.microcap.modules.corps.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/11/23.
 */
public class CreateCorpsBean {

    @NotBlank
    private String idCard;
    @NotBlank
    private String tradePassword;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
