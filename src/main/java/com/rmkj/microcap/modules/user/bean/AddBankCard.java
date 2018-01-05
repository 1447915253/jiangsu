package com.rmkj.microcap.modules.user.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/11/24.
 */
public class AddBankCard {
    @NotBlank
    private String chnName;
    @NotNull(message = "银行卡号不能为空")
    @Pattern(regexp = RegexpConstants.BANK_ACCOUNT_NUMBER, message = "银行卡号格式不对")
    private String bankAccount;
//    @NotBlank
    private String bankName;
//    @NotBlank
    private String openBankName;
    @NotNull(message = "身份证号不合法")
    @Pattern(regexp = RegexpConstants.CHINA_ID_CARD_NO, message = "身份证号不合法")
    private String idCard;

    //@NotBlank
    private String province;
    //@NotBlank
    private String city;

    //@NotBlank
    private String password;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
