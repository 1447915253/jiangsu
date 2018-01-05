package com.rmkj.microcap.common.constants;

/**
 * 常用正则表达式
 */
public interface RegexpConstants {
    /**
     * 11位手机号
     */
    String MOBILE = "^[0-9]{11}$";
    /**
     * 银行卡账号
     */
    String BANK_ACCOUNT_NUMBER = "^[0-9]+$";
    /**
     * 中国身份照
     */
    String CHINA_ID_CARD_NO = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
    /**
     * 金额
     */
    String MONEY = "^[0-9]+(\\.[0-9]{1,2})?$";

    /**
     * 英文逗号间隔的多个ID组合字符串
     * ID格式为英文大小写和数字混合
     */
    String IDS_LETTER_NUMBER = "^[0-9a-zA-Z]+(,[0-9a-zA-Z]+)*$";

    /**
     * 英文大小写和数字混合
     */
    String ID_LETTER_NUMBER = "^[0-9a-zA-Z]+$";

    // JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
    // MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
    String WX_TRADE_TYPE = "^JSAPI|NATIVE|APP|MICROPAY$";

    //98支付类型
    String NINE_EIGHT_TRADE_TYPE = "^alipay|wechat|qq$";

}
