package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/10/18.
 */
public class RegBean {
    /**
     * chnName : 中文姓名
     * mobile : 手机号
     * tradePassword : 交易密码
     * validCode : 验证码
     * agentInviteCode:经纪人邀请码
     */

//    @NotBlank
//    @Length(max = 16)
    private String chnName;
    @NotNull
    @Pattern(regexp = RegexpConstants.MOBILE, message = "手机号不合法")
    private String mobile;
    @NotBlank
    @Length(min = 6, max = 20)
    private String tradePassword;
    @NotBlank
    @Length(min = 4, max = 6)
    private String validCode;
//    @NotBlank
//    @Length(min = 4, max = 4)
    private String randomCode;

    private String agentInviteCode;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String terminal = Constants.Terminal.TERMINAL_WAP;

    private boolean thirdReg = false;

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

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

    public String getAgentInviteCode() {
        return agentInviteCode;
    }

    public void setAgentInviteCode(String agentInviteCode) {
        this.agentInviteCode = agentInviteCode;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public boolean isThirdReg() {
        return thirdReg;
    }

    public void setThirdReg(boolean thirdReg) {
        this.thirdReg = thirdReg;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
