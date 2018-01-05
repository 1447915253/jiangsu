package com.rmkj.microcap.modules.money.bean;

import com.rmkj.microcap.common.constants.RegexpConstants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by 123 on 2016/10/19.
 */
public class MoneyOutBean {
    /**
     * money : 提现金额
     * bankAccount : 银行卡号
     * bankName : 银行名称
     * chnName : 姓名
     * validCode : 验证码
     */

    @NotNull
    @Pattern(regexp = RegexpConstants.MONEY, message = "金额不合法")
    private String money;
    //@NotBlank
    private String password;
    // 0 余额 1 返佣余额
    @NotBlank
    private String type;
    @Min(10)
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
