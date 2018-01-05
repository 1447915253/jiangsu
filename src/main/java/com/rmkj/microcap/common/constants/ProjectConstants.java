package com.rmkj.microcap.common.constants;

import com.rmkj.microcap.common.bean.annotation.Config;

/**
 * Created by zhangbowen on 2016/5/11.
 * 配置文件常量,spring启动时注入值
 */
public class ProjectConstants {
    @Config("v")
    public static String V;
    @Config("v1")
    public static String V1;
    @Config("wap")
    public static String WAP;
    @Config("wap_agent")
    public static String WAP_AGENT;

    @Config("project_name")
    public static String PROJECT_NAME;

    //项目是否为debug模式
    @Config("project_debug")
    public static boolean PRO_DEBUG;
    //短信是否为debug模式
    @Config("sms_debug")
    public static boolean SMS_DEBUG;
    //行情是否为debug模式
    @Config("market_debug")
    public static boolean MARKET_DEBUG;

    // 阿里云缓存用户ID
    @Config("cache_ocs_uid")
    public static String CACHE_UID;
    // 阿里云缓存密码
    @Config("cache_ocs_pwd")
    public static String CACHE_PWD;
    // 阿里云缓存连接串
    @Config("cache_ocs_url")
    public static String CACHE_URL;

    //会员二级域名是否为debug模式
    @Config("two_level_domain_debug")
    public static boolean TWO_LEVEL_DOMAIN_DEBUG;
    //会员二级域名结尾
    @Config("two_level_domain")
    public static String TWO_LEVEL_DOMAIN;
    @Config("wei_xin_login")
    public static boolean WEI_XIN_LOGIN;

    //预警系统开关
    @Config("warning_debug")
    public static boolean WARNING_DEBUG;
    @Config("warning_mobile")
    public static String WARNING_MOBILE;

    // 是否支持三级营销系统
    @Config("three_sale_sys")
    public static boolean THREE_SALE_SYS;

    @Config("wei_xin_app_id")
    public static String WEI_XIN_APP_ID;
    @Config("wei_xin_secret")
    public static String WEI_XIN_SECRET;

    //回调用
    @Config("wei_xin_token")
    public static String WEI_XIN_TOKEN;

    @Config("wei_xin_page_access_url")
    public static String WEI_XIN_PAGE_ACCESS_URL;

    @Config("wei_xin_redirect_uri")
    public static String WEI_XIN_REDIRECT_URI;

    @Config("wei_xin_redirect_uri_agent")
    public static String WEI_XIN_REDIRECT_URI_AGENT;


    @Config("wei_xin_login_url")
    public static String WEI_XIN_LOGIN_URL;

    @Config("wei_xin_page_access_check_url")
    public static String WEI_XIN_PAGE_ACCESS_CHECK_URL;


    //微信公众号支付
    @Config("wei_xin_mch_id")
    public static String WEI_XIN_MCH_ID;
    @Config("wei_xin_pay_callback_url")
    public static String WEI_XIN_PAY_CALLBACK_URL;
    @Config("wei_xin_key")
    public static String WEI_XIN_KEY;

    @Config("validate_code_msg")
    public static String VALIDATE_CODE_MSG;

    @Config("wei_fu_tong_url")
    public static String WEI_FU_TONG_PAY_URL;
    @Config("wei_fu_tong_pay_mch_id")
    public static String WEI_FU_TONG_PAY_MCH_ID;
    @Config("wei_fu_tong_pay_front_callback_url")
    public static String WEI_FU_TONG_PAY_FRONT_CALLBACK_URL;
    @Config("wei_fu_tong_pay_notify_url")
    public static String WEI_FU_TONG_PAY_NOTIFY_URL;
    @Config("wei_fu_tong_pay_key")
    public static String WEI_FU_TONG_PAY_KEY;

    //98支付
    @Config("nine_eight_url")
    public static String NINE_EIGHT_PAY_URL;
    @Config("nine_eight_pay_mch_id")
    public static String NINE_EIGHT_PAY_MCH_ID;
    @Config("nine_eight_pay_front_callback_url")
    public static String NINE_EIGHT_PAY_FRONT_CALLBACK_URL;
    @Config("nine_eight_pay_notify_url")
    public static String NINE_EIGHT_PAY_NOTIFY_URL;
    @Config("nine_eight_pay_key")
    public static String NINE_EIGHT_PAY_KEY;
    @Config("nine_eight_quick_pay_notify_url")
    public static String NINE_EIGHT_QUICK_PAY_NOTIFY_URL;

    //58支付
    @Config("five_eight_pay_mch_id")
    public static String FIVE_EIGHT_PAY_MCH_ID;
    @Config("five_eight_pay_key")
    public static String FIVE_EIGHT_PAY_KEY;
    @Config("five_eight_pay_notify_url")
    public static String FIVE_EIGHT_PAY_NOTIFY_URL;
    //58网关模式微信支付商户id
    @Config("five_eight_wei_xin_gate_way_mch_id")
    public static String FIVE_EIGHT_WEI_XIN_GATE_WAY_MCH_ID;

    //杉德支付
    @Config("shan_de_merchant_no")
    public static String SHAN_DE_MERCHANT_NO;
    @Config("shan_de_secret")
    public static String SHAN_DE_SECRET;
    @Config("shan_de_mchid")
    public static String SHAN_DE_MCHID;
    //会员代理公众号二维码
    @Config("agent_qrcode")
    public static String AGENT_QRCODE;
    @Config("agent_qrcode_type")
    public static String AGENT_QRCODE_TYPE;

    //百时支付
    @Config("bai_shi_merchant_no")
    public static String BAI_SHI_MERCHANT_NO;
    @Config("bai_shi_key")
    public static String BAI_SHI_KEY;
    @Config("bai_shi_notify_url")
    public static String BAI_SHI_NOTIFY_URL;

    //摩宝支付
    @Config("mo_bao_merchant_no")
    public static String MO_BAO_MERCHANT_NO;
    @Config("mo_bao_key")
    public static String MO_BAO_KEY;
    @Config("mo_bao_pageNotifyUrl")
    public static String MO_BAO_PAGENOTIFYURL;
    @Config("mo_bao_backNotifyUrl")
    public static String MO_BAO_BACKNOTIFYURL;

    //新Web支付
    @Config("new_web_merchant_no")
    public static String NEW_WEB_MERCHANT_NO;
    @Config("new_web_key")
    public static String NEW_WEB_KEY;
    @Config("new_web_notify_url")
    public static String NEW_WEB_NOTIFY_URL;

    //熊云支付
    @Config("xion_yun_merchant_no")
    public static String XION_YUN_MERCHANT_NO;
    @Config("xion_yun_key")
    public static String XION_YUN_KEY;
    @Config("xion_yun_pay_notifyurl")
    public static String XION_YUN_PAY_NOTIFYURL;
    @Config("xion_yun_pay_callbackurl")
    public static String XION_YUN_PAY_CALLBACKURL;


    //千红支付
    @Config("qian_hong_merchant_no")
    public static String QIAN_HONG_MERCHANT_NO;
    @Config("qian_hong_key")
    public static String QIAN_HONG_KEY;
    @Config("qian_hong_pay_notifyurl")
    public static String QIAN_HONG_PAY_NOTIFYURL;
    @Config("qian_hong_pay_callbackurl")
    public static String QIAN_HONG_PAY_CALLBACKURL;


    //首信易支付
    @Config("key")
    public static String MD5KEY;
    @Config("v_mid")
    public static String V_MID;

    //威鹏支付
    //微信扫码下单异步通知地址
    @Config("weipeng_qrcode_pay_asyncresult_url")
    public static String WEIPENG_QRCODE_PAY_ASYNCRESULT_URL;
    //商户号
    @Config("weipeng_pay_merchant_no")
    public static String WEIPENG_PAY_MERCHANT_NO;
    //秘钥
    @Config("weipeng_pay_secret")
    public static String WEIPENG_PAY_SECRET;
    //端口号
    @Config("weipeng_pay_port")
    public static String WEIPENG_PAY_PORT;

    //阿里大于短信
    //短信签名
    @Config("alidayu_signName")
    public static String ALIDAYU_SIGNNAME;
    //短信模板id
    @Config("alidayu_message_id_reg")
    public static String ALIDAYU_MESSAGE_ID_REG;
    @Config("alidayu_message_id_update")
    public static String ALIDAYU_MESSAGE_ID_UPDATE;
    @Config("alidayu_message_id_normal")
    public static String ALIDAYU_MESSAGE_ID_NORMAL;
    @Config("alidaty_http")
    public static String ALIDAYU_HTTP;
    @Config("alidaty_https")
    public static String ALIDAYU_HTTPS;
    @Config("alidayu_app_key")
    public static String ALIDAYU_APP_KEY;
    @Config("alidayu_app_secre")
    public static String ALIDAYU_APP_SECRE;

    //通联支付


    @Config("sms_sign")
    public static String SMS_SIGN;

    @Config("login_url")
    public static String LOGIN_URL = "http://{0}/front/wap/login";
    @Config("home_url")
    public static String HOME_URL = "http://{0}/front/wap/home";

    @Config("market_kdata_url")
    public static String MARKET_KDATA_URL;
    @Config("market_new_url")
    public static String MARKET_NEW_URL;
    @Config("market_new_websocket_url")
    public static String MARKET_NEW_WEBSOCKET_URL;
    @Config("trade_url")
    public static String TRADE_URL;

    public interface WEI_XIN_MESSAGE_CUSTOM_SEND_TYPE {
        String TU_WEN= "tuwen";
        String WEN_BEN = "wenben";
    }

    public interface WEI_XIN_MESSAGE_CUSTOM_TYPE {
        String GUAN_ZHU = "0";
        String TI_XIAN = "1";
        String FAN_YONG = "2";
        String YOU_HUI_QUAN = "3";
    }
    @Config("only_weixin_login")
    public static boolean ONLY_WEIXIN_LOGIN;

    //微信H5支付下单异步通知地址
    @Config("weipeng_wechat_pay_asyncresult_url_h5")
    public static String WEIPENG_WECHAT_ASYNCRESULT_URL_H5;
    //微信H5单独的商户号
    @Config("weipeng_pay_merchant_no_h5")
    public static String WEIPENG_PAY_MERCHANT_NO_H5;
    //微信H5单独的秘钥
    @Config("weipeng_pay_secret_h5")
    public static String WEIPENG_PAY_SECRET_H5;
}
