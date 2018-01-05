package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2016/11/18.
 */
public class FirstPartResetTradePwd {
    @NotNull
    @Pattern(regexp = RegexpConstants.MOBILE)
    private String mobile;
    @NotBlank
    @Length(min = 4,max = 6)
    private String validCode;

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
}
