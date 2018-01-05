package com.rmkj.microcap.common.constants;

import java.math.BigDecimal;

/**
 * Created by zhangbowen on 2015/12/22.
 * 业务常量
 */
public interface Constants {
    int MINUTE = 60;
    int HOUR = MINUTE * 60;

    // 更新合约时间间隔
    long REFRESH_CONTRACTS = 30000;

    interface LoginType{
        String DEFAULT = "";
        String AGENT = "agent";
    }

    interface User{
        // 经纪人
        String TYPE = "1";
    };

    interface Agent{
        //经纪人审核状态  0  审核中 1 审核通过 2 审核未通过
        int AGENT_REVIEW_STATUS_ING = 0;
        int AGENT_REVIEW_STATUS_SUCCESS = 1;
        int AGENT_REVIEW_STATUS_FAILURE = 2;
    }

    interface Market {
        // default 只获取当前价格
        // all 获取当前价格及 昨收 今开 最高 最低
        enum GetType {DEFAULT,ALL}
    }

    interface Trade {
        String BUY_UP = "0";
        String BUY_DOWN = "1";
        //0 资金 1 代金券
        String TYPE_MONEY = "0";
        String TYPE_COUPON_MONEY = "1";
        String TYPE_MONEY_AND_COUPON = "2";

        String MODEL_DEFAULT = "0";
        String MODEL_TIME = "1";
        String MODEL_POINT = "2";

        String BALANCE_DEFAULT = "0";
        String BALANCE_ING = "1";
        String BALANCE_OVER = "2";

        String RESULT_WIN = "1";
        String RESULT_LOSE = "-1";
        String RESULT_DRAW = "0";

        enum SellType {
            HAND("0"),
            STOP("1"),
            TIME_STOP("2"),
            ERROR("3");

            private String val;
            SellType(String val){
                this.val = val;
            }
            public String val(){
                return val;
            }
        }
    }

    interface AgentMoney{
        //类型 0 交易返佣 1 提现
        String AGENT_MONEY_CHANGE_TYPE_IN = "0";
        String AGENT_MONEY_CHANGE_TYPE_OUT = "1";

        //返佣比例
        Double RATIO = 0.01;
    }
    interface Money {
        // 类型 0 充值 1 提现 2 建仓 3 平仓 4提现失败退款 5佣金转余额
        String MONEY_CHANGE_TYPE_MONEY_IN = "0";
        String MONEY_CHANGE_TYPE_MONEY_OUT = "1";
        String MONEY_CHANGE_TYPE_TRADE_MAKE = "2";
        String MONEY_CHANGE_TYPE_TRADE_SELL = "3";


        // 类型：0 充值 1 提现
        String MONEY_RECORD_TYPE_IN = "0";
        String MONEY_RECORD_TYPE_OUT = "1";

        String MONEY_RECORD_STATUS_DEFAULT = "0";
        String MONEY_RECORD_STATUS_SUCCESS = "1";
        String MONEY_RECORD_STATUS_FAIL = "2";
    }

    interface CashCoupon{
        //赠送代金券的额度
        String GIVE_MONEY = "20.00";
        //代金券的使用条件
        String MIN_MONEY = "100.00";
        //代金券使用期限
        int interval = 30;
        //赠送代金券的数量
        int counts = 5;
        //代金券状态：0 正常 1 已使用 2 已过期
        String NORMAL_STATUS = "0";
        String USE_STATUS = "1";
    }

    interface Http {
        String AUTHORIZATION = "_auth";
        String AUTHORIZATION_AGENT = "_auth_agent";
    }

    interface IM {
        String TOKEN = "im_access_token";
//        //发送消息
//        String PUSH_MESSAGE = BASE_URL + "/{org_name}/{app_name}/messages";
//        //更新群组信息
//        String UPDATE_GROUP_INFO = BASE_URL + "/{org_name}/{app_name}/chatgroups/{group_id}";
    }

    interface WeiXin {
        String TOKEN = "weixin_access_token";
        String TICKET = "weixin_jsapi_ticket";
    }

    /**
     * 终端类型
     */
    interface Terminal {
        String TERMINAL_ANDROID = "1";
        String TERMINAL_IOS = "2";
        String TERMINAL_WAP = "3";
        String TERMINAL_WEB = "4";
        String TERMINAL_NAME = "terminal";
    }

    /**
     * 验证码类型
     * reg 注册 mtpwd 修改交易密码 mmoble 修改手机号 moneyout 提现
     */
    interface ValidateCode {
        String REG = "reg";//注册
        String M_TRADE_PWD = "mtpwd";
        String M_MOBILE = "mmoble";
        String MONEY_OUT = "moneyout";
    }

    interface USER_TYPE {
        String COMMON = "1"; // 普通用户
    }

    // 0 正常 1 停用
    interface USER_STATUE {
        String VALID = "0"; // 正常
        String INVALID = "1"; // 停用
        String NO_TRADE = "2";//禁止交易
    }

    interface Pay {
        String PAY_TYPE_WEI_XIN = "1";
    }

    /**
     * 微信 参数常量
     */
    interface WX {
        String TRADE_TYPE_JSAPI = "JSAPI";
    }

    public interface UserMessage {
        String TITLE_TRADE = "交易";
        String USE_CASH_COUPON = "使用代金券";
    }

    public interface Coupon {
        String STATUS_DEFAULT = "0";
        String STATUS_USED = "1";
        String STATUS_EXPIRE = "2";

        String TYPE_0 = "0";
        // 必盈券
        String TYPE_1 = "1";
        // 直盈券
        String TYPE_2 = "2";
        // 增益券
        String TYPE_3 = "3";
    }

    /**
     * 系统参数键
     */
    interface ParameterSet {
        String FIRST_RECHARGE_MIN_MONEY_FOR_COUPON = "first_recharge_min_money_for_coupon";
    }

    //会员单位保证金浮动记录表类型
    interface UNITS_MONEY_CHANGE_TYPE{
        //浮动保证金金额
        Integer FLOAT_MONEY = 1;
        //追加保证金金额
        Integer ADD_BOND_MONEY = 2;
    }
}
