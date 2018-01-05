package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/10/18.
 */
public class ModifyMobile {

    @NotNull
    @Pattern(regexp = RegexpConstants.MOBILE)
    private String mobile;
    @NotBlank
    private String validCode;
    @NotBlank
    private String tradePassword;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
