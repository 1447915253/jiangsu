package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/11/7.
 */
public class LoginBean {
    @NotNull
    @Pattern(regexp = RegexpConstants.MOBILE,message = "手机号不合法")
    private String mobile;
    @NotBlank
    private String password;
    @NotBlank
    private String randomCode;

    //
    @NotBlank
    private String terminal;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
