package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ResetTradePwd {
    @NotNull
    @Pattern(regexp = RegexpConstants.MOBILE)
    private String mobile;
    @NotBlank
    private String tradePassword;
    @NotBlank
    private String validCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
