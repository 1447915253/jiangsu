package com.rmkj.microcap.common.bean;


import com.rmkj.microcap.common.bean.annotation.CodeAnnot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbowen on 2015/6/24.
 */
public enum StatusCode {
    @CodeAnnot() OK(0),
    @CodeAnnot("服务器正在维护") SERVER_ERROR(1),
    @CodeAnnot("余额不足")MONEY_NOT_ENOUGH(2),
    @CodeAnnot("参数异常")VALIDATION_FAILED(3),
    @CodeAnnot("密码不正确")PASSWORD_ERROR(4),
    @CodeAnnot("账号不存在")ACCOUNT_NO_EXIST(5),
    @CodeAnnot("验证码不正确")VALIDATE_CODE_ERROR(6),
    @CodeAnnot("手机号已存在")MOBILE_EXIST(7),
    @CodeAnnot("用户已被查封")USER_CLOSE(8),
    @CodeAnnot("验证码发送失败")SEND_SMS_ERROR(9),

    @CodeAnnot("用户不存在")USER_NOT_EXIST(10),
    @CodeAnnot("交易密码不正确")TRADE_PASSWORD_INVALID(11),
    @CodeAnnot("下单失败") MAKE_TRADE_ERROR(12),

    @CodeAnnot("预支付失败") WX_PRE_PAY_ERROR(13),
    @CodeAnnot("邀请码不正确") AGENT_INVITE_CODE_ERROR(14),
    @CodeAnnot("手机号不存在") MOBILE_NOT_EXIST(15),
    @CodeAnnot("银行卡已绑定") BANK_CARD_IS_BOUND(16),
    @CodeAnnot("优惠券不可用") COUPON_INVALID(17),
    @CodeAnnot("请填写邀请码") AGENT_VALID_CODE_REQUIRE(18),
    @CodeAnnot("目前持仓数量达到上限") HOLD_POSITIONS(19),
    @CodeAnnot("目前持仓金额超出规定额度") HOLD_MONEY(20),
    @CodeAnnot("每日提现金额达到上限") CASH_MONEY_RATION_MAX(21),
    @CodeAnnot("每日提现次数达到上限") CASH_MONEY_COUNT_MAX(22),
    @CodeAnnot("错误的请求") BAD_REQUEST_ADDRESS(23),
    @CodeAnnot("请先绑定银行卡") NOT_BANKACCOUNT(24),
    @CodeAnnot("每种商品限同时持仓1单") HOLD_ORDERS_COUNT(25),
    @CodeAnnot("最低建仓金额10元") ORDERS_MIN_MONEY(26),
    @CodeAnnot("最低提现金额10元") WITHDRAW_MIN_MONEY(27),
    @CodeAnnot("请在周一至周五的9:00-16:00申请提现") NOT_WITHDRAWTIME(28),
    @CodeAnnot("单笔提现超出上限") BEYOND_MONEY_WITHDRAW(29),
    @CodeAnnot("最低入金金额10元") RECHARGE_MIN_MONEY(30),
    @CodeAnnot("直盈券一天最多使用10张") COUPON_TYPE2_EVERYDAY_MAX(31),
    @CodeAnnot("直盈券至少间隔1个小时使用") COUPON_TYPE2_INTERVAL(32),
    @CodeAnnot("必盈券直盈券只能买白银1分钟") COUPON_TYPE1_TYPE2_BAIYIN_1M(33),
    @CodeAnnot("券数量不足") COUPON_NOT_ENOUGH(34),
    @CodeAnnot("相同产品请间隔5秒之后下单") HOLDTIME_INTERVAL(35),
    @CodeAnnot("银行支行信息不存在") BANK_NOT_EXIST(36),
    @CodeAnnot("尚未签约银行") NOT_SIGN_BANK(37),
    @CodeAnnot("不符合券使用规则") INVALID_COUPON_RULE(38),
    @CodeAnnot("银行卡信息已存在") BANK_CARD_EXIST(39),
    @CodeAnnot("会员单位保证金不足") MEMBER_MONEY_NOE_ENOUGH(40),
    @CodeAnnot("该用户已禁止交易") CAN_NOT_TRADE(41),
    @CodeAnnot("未查询到该银行卡信息")NOT_FIND_BANK_NO(42),
    @CodeAnnot("代理商处于停用状态")UNITS_IS_NOT_ONWORK(43),
    @CodeAnnot("休市停盘") MARKET_CLOSE(99),
    @CodeAnnot("代金券余额不足")COUPON_MONEY_NOT_ENOUGH(2),
    @CodeAnnot("支付失败！")MO_BAO_UNION_ERROR(60),
    @CodeAnnot("支付成功！")MO_BAO_UNION_SUCCESS(50),
    @CodeAnnot("交易处理中！")MO_BAO_UNION_DEFAULT(49),
    @CodeAnnot("银行卡未开通认证支付！")MO_BAO_UNION_CARDERROR(61);

    private static final Map<String, String> hMap = new HashMap<>();

    static {
        Field[] fields = StatusCode.class.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CodeAnnot.class)) {
                hMap.put(field.getName(), field.getAnnotation(CodeAnnot.class).value());
            }
        }
    }

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    StatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return hMap.get(this.toString());
    }
}
