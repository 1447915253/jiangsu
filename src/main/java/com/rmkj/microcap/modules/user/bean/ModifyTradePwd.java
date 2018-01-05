package com.rmkj.microcap.modules.user.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/10/18.
 */
public class ModifyTradePwd {
    @NotBlank
    private String preTradePassword;
    @NotBlank
    private String tradePassword;
/*    @NotBlank
    private String validCode;*/

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getPreTradePassword() {
        return preTradePassword;
    }

    public void setPreTradePassword(String preTradePassword) {
        this.preTradePassword = preTradePassword;
    }

    /*    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }*/
}
