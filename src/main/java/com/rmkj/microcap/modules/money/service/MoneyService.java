package com.rmkj.microcap.modules.money.service;
import com.alibaba.fastjson.JSON;
import com.capinfo.crypt.Md5;
import com.capinfo.crypt.RSA_MD5;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiCallBackBean;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiPrePayBean;
import com.rmkj.microcap.common.modules.pay.baishi.service.BaiShiPayService;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.*;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean.FiveEightOpenPayResByServiceBeanData2;
//import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean.FiveEightOpenPayResByServiceBeanData3;
import com.rmkj.microcap.common.modules.pay.fiveeight.service.FiveEightOpenPayService;
import com.rmkj.microcap.common.modules.pay.huizhitong.service.HuizhitongScanPayService;
import com.rmkj.microcap.common.modules.pay.mobao.bean.*;
import com.rmkj.microcap.common.modules.pay.mobao.service.MoBaoPayService;
import com.rmkj.microcap.common.modules.pay.nineeight.bean.NineEightScanPayResBean;
import com.rmkj.microcap.common.modules.pay.nineeight.bean.NineEightScanPayResultResBean;
import com.rmkj.microcap.common.modules.pay.nineeight.bean.QuickPayOrderResBean;
import com.rmkj.microcap.common.modules.pay.nineeight.service.NineEightScanPayService;
import com.rmkj.microcap.common.modules.pay.qianhongPay.bean.QianHongNotifyBean;
import com.rmkj.microcap.common.modules.pay.qianhongPay.service.QianhongPayService;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.PayUtil;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5OpenIdBean;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5PayCallBackResBean;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5PayParamBean;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeScanCodePayBean;
import com.rmkj.microcap.common.modules.pay.shande.service.ShanDeH5PayService;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5OpenIdBean2;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5PayCallBackResBean2;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5PayParamBean2;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeScanCodePayBean2;
import com.rmkj.microcap.common.modules.pay.shande2.service.ShanDeH5PayService2;
import com.rmkj.microcap.common.modules.pay.tiangong.TianGongPayService;
import com.rmkj.microcap.common.modules.pay.tiangong.bean.PrePayReqBean;
import com.rmkj.microcap.common.modules.pay.tonglian.bean.WeiFuTongScanResultBean;
import com.rmkj.microcap.common.modules.pay.tonglian.service.WeiFuTongScanPayService;
import com.rmkj.microcap.common.modules.pay.weifutong.WeiFuTongPayService;
import com.rmkj.microcap.common.modules.pay.weifutong.bean.PayResultReqBean;
import com.rmkj.microcap.common.modules.pay.weifutong.bean.PrePayRespBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengD0ResultBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengDoBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengQrCodeAsyncResultBean;
import com.rmkj.microcap.common.modules.pay.weipeng.service.WeiPengScanQrcodePayService;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanCallBackBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanPayBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.service.WebScanPayService;
import com.rmkj.microcap.common.modules.pay.xionyunPay.bean.XionyunNotifyBean;
import com.rmkj.microcap.common.modules.pay.xionyunPay.service.XionyunPayService;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayOrderResBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayRequestParamBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.UnionPayOrderReq;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.UnionPayRequestParam;
import com.rmkj.microcap.common.modules.pay.yizhifu.service.PayOrderService;
import com.rmkj.microcap.common.modules.pay.yizhifu.service.UnionPayOrderService;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderRespBean;
import com.rmkj.microcap.common.modules.weixin.face.NotifyForPayService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinPayService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.coupon.service.CouponService;
import com.rmkj.microcap.modules.index.dao.ParameterSetDao;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.money.bean.*;
import com.rmkj.microcap.modules.money.dao.MoneyDao;
import com.rmkj.microcap.modules.money.entity.MoneyChange;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.entity.ReturnMoneyOut;
import com.rmkj.microcap.modules.user.dao.UserDao;
import com.rmkj.microcap.modules.user.dao.UserMessageDao;
import com.rmkj.microcap.modules.user.entity.BankCard;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.entity.UserMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.valueOf;

/**
 * Created by 123 on 2016/10/19.
 */
@Service
@Transactional
public class MoneyService implements NotifyForPayService {

    private static final Logger Log = Logger.getLogger(NotifyForPayService.class);

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMessageDao userMessageDao;

    @Autowired
    private ParameterSetDao parameterSetDao;

    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    private WeiFuTongPayService weiFuTongPayService;

    @Autowired
    private WeiFuTongScanPayService weiFuTongScanPayService;

    @Autowired
    private NineEightScanPayService nineEightScanPayService;

    @Autowired
    private FiveEightOpenPayService fiveEightOpenPayService;

    @Autowired
    private TianGongPayService tianGongPayService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private UnionPayOrderService unionPayOrderService;

    @Autowired
    private WeiPengScanQrcodePayService weiPengScanQrcodePayService;

    @Autowired
    private ShanDeH5PayService shanDeH5PayService;

    @Autowired
    private ShanDeH5PayService2 shanDeH5PayService2;

    @Autowired
    private CouponService couponService;

    @Autowired
    private BaiShiPayService baiShiPayService;

    @Autowired
    private MoBaoPayService moBaoPayService;

    @Autowired
    private WebScanPayService weCharService;

    @Autowired
    private QianhongPayService qianhongPayService;

    @Autowired
    private XionyunPayService xionyunPayService;

    @Autowired
    private HuizhitongScanPayService huizhitongScanPayService;

    public ResponseEntity out(MoneyOutBean moneyOutBean) {
        String userId = AuthContext.getUserId();

        User userById = userDao.findUserById(userId);

//        if(userById == null || !userById.getTradePassword().equals(moneyOutBean.getPassword())){
//            return new ResponseErrorEntity(StatusCode.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
//        }

        if (null == userDao.findBankCard(userId)) {
            return new ResponseErrorEntity(StatusCode.NOT_BANKACCOUNT, HttpStatus.BAD_REQUEST);
        }
       /* //验证提现时间
        if (!getWithdrawTime()) {
            return new ResponseErrorEntity(StatusCode.NOT_WITHDRAWTIME, HttpStatus.BAD_REQUEST);
        }
*/
        //提现最低金额要求5元起-提示六元
        if (Utils.toBigDecimal(moneyOutBean.getMoney()).compareTo(Utils.toBigDecimal("10.00")) == -1) {
            return new ResponseErrorEntity(StatusCode.WITHDRAW_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }
        //每日单笔提现最高20000
        if (new BigDecimal(moneyOutBean.getMoney()).compareTo(new BigDecimal(20000)) == 1) {
            return new ResponseErrorEntity(StatusCode.BEYOND_MONEY_WITHDRAW, HttpStatus.BAD_REQUEST);
        }

        //验证提现金额，提现次数，是否超过规定
        ParameterSet parameterSet = parameterSetDao.findParameterSet();
        List<MoneyRecord> moneyRecordList = moneyDao.findUserMoneyRecord(userId);

        BigDecimal bigDecimal = new BigDecimal(0);
        Double moneyRecordMax = 0D;
        for (MoneyRecord moneyRecord : moneyRecordList) {
            moneyRecordMax += moneyRecord.getMoney().doubleValue();
        }
        moneyRecordMax += new BigDecimal(moneyOutBean.getMoney()).doubleValue();
        if (null != parameterSet) {
            if (new BigDecimal(moneyRecordMax).compareTo(parameterSet.getCashMoneyRation()) == 1) {//提现额度大于规定额度时
                return new ResponseErrorEntity(StatusCode.CASH_MONEY_RATION_MAX, HttpStatus.BAD_REQUEST);
            }
            if (moneyRecordList.size() >= parameterSet.getCashMoneyCount()) {
                return new ResponseErrorEntity(StatusCode.CASH_MONEY_COUNT_MAX, HttpStatus.BAD_REQUEST);
            }
        }

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(userId);
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_OUT);
        moneyRecord.setMoney(Utils.toBigDecimal(moneyOutBean.getMoney()));

        //提现50元以下收5元手续费，反之2元 - 外扣
        BigDecimal recordMoney = new BigDecimal(moneyOutBean.getMoney());
        if (Utils.toBigDecimal(moneyOutBean.getMoney()).compareTo(new BigDecimal("50")) == -1) {
            BigDecimal money = new BigDecimal(moneyOutBean.getMoney()).subtract(new BigDecimal("5.00"));
            moneyRecord.setMoney(money);
            moneyRecord.setFee(new BigDecimal(5.00));
        } else {
            BigDecimal money = new BigDecimal(moneyOutBean.getMoney()).subtract(new BigDecimal("2.00"));
            moneyRecord.setMoney(money);
            moneyRecord.setFee(new BigDecimal(2.00));
        }


        BankCard bankCard = userDao.findBankCard(userId);
        /**
         * 验证支行信息
         */
        if (bankCard == null) {
            return new ResponseErrorEntity(StatusCode.NOT_SIGN_BANK, HttpStatus.BAD_REQUEST);
        }
//        //威鹏支付查询联行号
//        String lianHangNo = userDao.findBankCodeByName(bankCard.getOpenBankName()).getBankNo();
//        if(StringUtils.isBlank(lianHangNo)){
//            return new ResponseErrorEntity(StatusCode.BANK_NOT_EXIST, HttpStatus.BAD_REQUEST);
//        }

        moneyRecord.setChnName(bankCard.getChnName());
        moneyRecord.setBankAccount(bankCard.getBankAccount());
        moneyRecord.setBankName("招商银行");
        moneyRecord.setOpenBankName("招商银行股份有限公司杭州萧山支行");
        moneyRecord.setProvince("浙江省");
        moneyRecord.setCity("杭州市");
        moneyRecord.setBankCode(bankCard.getBankCode());
        moneyRecord.setIdCard(bankCard.getIdCard());
        moneyRecord.setLianHangNo("308331012100");

        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        moneyRecord.preInsert();

        //
        //变更金额
        BigDecimal difMoney = moneyRecord.getMoney().add(moneyRecord.getFee()).multiply(new BigDecimal(-1));

        // Utils.formatStr("平仓资金变更{0}，下单流水号：{1}", difMoney.toString(), trade.get·())
        if (moneyDao.record(moneyRecord) != 1
                || !changeMoney(userId, difMoney, userById.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_OUT,
                moneyRecord.getSerialNo())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseErrorEntity(StatusCode.MONEY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * 0 充值 1 提现 2 建仓 3 平仓 4提现失败退款 5 佣金转余额
     *
     * @param userId
     * @param difMoney
     * @param beforeMoney
     * @param type
     * @param remark
     * @return
     */
    public boolean changeMoney(String userId, BigDecimal difMoney, BigDecimal beforeMoney, String type, String remark) {
        // 资金变更
        Log.info("资金变更");
        MoneyChange moneyChange = new MoneyChange();
        moneyChange.setUserId(userId);
        moneyChange.setCreateTime(new Date());
        moneyChange.setDifMoney(difMoney);
        moneyChange.setBeforeMoney(beforeMoney);
        moneyChange.setAfterMoney(beforeMoney.add(difMoney));
        // 类型 0 充值 1 提现 2 建仓 3 平仓
        moneyChange.setType(type);
        moneyChange.setRemark(remark);
        moneyChange.preInsert();
        System.out.print("moneyChange"+moneyChange);
        if (moneyDao.recordChangeMoney(moneyChange) != 1 || userDao.changeUserMoney(moneyChange) != 1) {
            System.out.print("====1");
            return false;
        }
        if (Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN.equals(type) && difMoney.doubleValue() > 0) {
            if (userDao.changeUserRechargeMoney(moneyChange) != 1) {
                    System.out.print("修改金额");
                return false;
            }
        }
        return true;
    }

    /**
     * 代金券变更
     *
     * @param userId
     * @param difMoney
     * @param title
     * @param content
     * @return
     */
    public boolean changeCouponMoney(String userId, BigDecimal difMoney, String title, String content) {
        UserMessage message = new UserMessage();
        message.setUserId(userId);
        message.setTitle(title);
        message.setContent(content);

        User user = new User();
        user.setId(userId);
        user.setCouponMoney(difMoney);

        message.preInsert();
        if (userMessageDao.record(message) != 1 || userDao.changeCouponMoney(user) != 1) {
            return false;
        }
        return true;
    }

    private final Random random = new Random();

    /**
     * 获取流水号
     *
     * @return
     */
    private String serialNo() {
        return "MO" + new Date().getTime() + "" + String.format("%1$03d", random.nextInt(1000));
    }

    /**
     * 微信预支付
     *
     * @param prePayBean
     * @return
     */
    public ResponseEntity prePay(PrePayBean prePayBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("微信");
        moneyRecord.setMoney(Utils.toBigDecimal(prePayBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        User userById = userDao.findUserById(AuthContext.getUserId());
        UnifiedOrderRespBean unifiedOrderRespBean = weiXinPayService.unifiedOrder("WEB", "会员微信充值", moneyRecord.getSerialNo(),
                moneyRecord.getMoney().multiply(new BigDecimal(100)).intValue(), prePayBean.getSpbillCreateIp(), prePayBean.getTradeType(), userById.getOpenId());
        if (!unifiedOrderRespBean.success()) {
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }

        //微信支付流水号
        moneyRecord.setThirdSerialNo(unifiedOrderRespBean.getPrepay_id());
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(weiXinPayService.toWCPayReqBean(unifiedOrderRespBean), HttpStatus.OK);
    }

    @Override
    public boolean payResult(NotifyReqBean notifyReqBean) {
        String serialNo = notifyReqBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(notifyReqBean.getOut_trade_no());
        moneyRecord.setFailureReason(notifyReqBean.getErr_code_des());
        moneyRecord.setCompleteTime(new Date());
        if (notifyReqBean.success()) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        }
        User user = userDao.findUserById(m.getUserId());
        if (moneyDao.updateRecord(moneyRecord) != 1
                || (notifyReqBean.success() && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, notifyReqBean.getOut_trade_no()))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity deletePrePay(String thirdSerialNo) {
        Map<String, String> map = new HashMap<>(2);
        map.put("thirdSerialNo", thirdSerialNo);
        map.put("userId", AuthContext.getUserId());
        if (moneyDao.deletePrePayMoneyRecord(map) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity noReturnMoneyOut() {
        return new ResponseErrorEntity("请点击佣金转余额按钮，将佣金转至余额提现!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity returnMoneyOut(MoneyOutBean moneyOutBean) {
        String userId = AuthContext.getUserId();


        User userById = userDao.findUserById(userId);

//        if(userById == null || !userById.getTradePassword().equals(moneyOutBean.getPassword())){
//            return new ResponseErrorEntity(StatusCode.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
//        }
        if (null == userDao.findBankCard(userId)) {
            return new ResponseErrorEntity(StatusCode.NOT_BANKACCOUNT, HttpStatus.BAD_REQUEST);
        }
        //验证提现时间
        if (!getWithdrawTime()) {
            return new ResponseErrorEntity(StatusCode.NOT_WITHDRAWTIME, HttpStatus.BAD_REQUEST);
        }

        //提现最低金额要求5元起
        if (Utils.toBigDecimal(moneyOutBean.getMoney()).compareTo(Utils.toBigDecimal("10.00")) == -1 || Utils.toBigDecimal(moneyOutBean.getMoney()).compareTo(Utils.toBigDecimal("10.00")) == 0) {
            return new ResponseErrorEntity(StatusCode.WITHDRAW_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }

        //每日单笔提现最高5000
        if (new BigDecimal(moneyOutBean.getMoney()).compareTo(new BigDecimal(5000)) == 1) {
            return new ResponseErrorEntity(StatusCode.BEYOND_MONEY_WITHDRAW, HttpStatus.BAD_REQUEST);
        }

        //验证提现金额，提现次数，是否超过规定
        ParameterSet parameterSet = parameterSetDao.findParameterSet();
        ReturnMoneyOut outMoney = moneyDao.findUserOutMoneyRecord(userId);

        if (null != outMoney) {
            BigDecimal money = outMoney.getMoney().add(new BigDecimal(moneyOutBean.getMoney()));
            if (money.compareTo(parameterSet.getCashMoneyRation()) == 1) {//提现额度大于规定额度时
                return new ResponseErrorEntity(StatusCode.CASH_MONEY_RATION_MAX, HttpStatus.BAD_REQUEST);
            }
            if (outMoney.getCount() >= parameterSet.getCashMoneyCount()) {
                return new ResponseErrorEntity(StatusCode.CASH_MONEY_COUNT_MAX, HttpStatus.BAD_REQUEST);
            }
        }


        ReturnMoneyOut returnMoneyOut = new ReturnMoneyOut();
        returnMoneyOut.setUserId(userId);
        returnMoneyOut.setSerialNo(serialNo());
        returnMoneyOut.setCreateTime(new Date());

        returnMoneyOut.setMoney(Utils.toBigDecimal(moneyOutBean.getMoney()));
        //提现50元以下收5元手续费，反之2元 -手续费外扣
        BigDecimal recordMoney = Utils.toBigDecimal(moneyOutBean.getMoney());
        if (Utils.toBigDecimal(moneyOutBean.getMoney()).compareTo(new BigDecimal("50")) == -1) {
            BigDecimal moeny = Utils.toBigDecimal(moneyOutBean.getMoney()).subtract(Utils.toBigDecimal("5.00"));
            returnMoneyOut.setMoney(moeny);
            returnMoneyOut.setFee(Utils.toBigDecimal("5.00"));
        } else {
            BigDecimal moeny = Utils.toBigDecimal(moneyOutBean.getMoney()).subtract(Utils.toBigDecimal("2.00"));
            returnMoneyOut.setMoney(moeny);
            returnMoneyOut.setFee(Utils.toBigDecimal("2.00"));
        }


        BankCard bankCard = userDao.findBankCard(userId);
        /**
         * 验证支行信息
         */
        if (bankCard == null) {
            return new ResponseErrorEntity(StatusCode.NOT_SIGN_BANK, HttpStatus.BAD_REQUEST);
        }
        //威鹏支付查询联行号
//        BankCodeBean bankCodeByName = userDao.findBankCodeByName(bankCard.getOpenBankName());
//        String lianHangNo = bankCodeByName == null ? null : bankCodeByName.getBankNo();
//        if(StringUtils.isBlank(lianHangNo)){
//            return new ResponseErrorEntity(StatusCode.BANK_NOT_EXIST, HttpStatus.BAD_REQUEST);
//        }

        returnMoneyOut.setChnName(bankCard.getChnName());
        returnMoneyOut.setBankAccount(bankCard.getBankAccount());
        returnMoneyOut.setBankName(bankCard.getBankName());
        returnMoneyOut.setOpenBankName(bankCard.getOpenBankName());
        returnMoneyOut.setProvince(bankCard.getProvince());
        returnMoneyOut.setCity(bankCard.getCity());

        returnMoneyOut.preInsert();

        //保留没有扣除手续费的金额
        ReturnMoneyOut subtractReturnMoney = new ReturnMoneyOut();
        subtractReturnMoney.setMoney(returnMoneyOut.getMoney());
        subtractReturnMoney.setUserId(returnMoneyOut.getUserId());
        subtractReturnMoney.setFee(returnMoneyOut.getFee());

        if (moneyDao.returnMoneyOut(returnMoneyOut) != 1 || userDao.subtractReturnMoney(subtractReturnMoney) != 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseErrorEntity(StatusCode.MONEY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 通联扫码下单
     *
     * @param prePayBean
     * @return
     */
    public ResponseEntity tongLianScanCodePay(PrePayBean prePayBean) {

        //验证入金金额-最低10元
        if (new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1) {
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("微信线上扫码充值");
        moneyRecord.setMoney(Utils.toBigDecimal(prePayBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        User userById = userDao.findUserById(AuthContext.getUserId());
        WeiFuTongScanResultBean weiFuTongScanResultBean = weiFuTongScanPayService.weiFuTongScanCodeRequest(moneyRecord, prePayBean.getSpbillCreateIp());
        //判断扫码下单返回结果
        if (!Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(weiFuTongScanResultBean.getStatus()) &&
                !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(weiFuTongScanResultBean.getResult_code())) {
            Log.error("通联扫码支付接口调用失败 :".concat(JSON.toJSONString(weiFuTongScanResultBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(weiFuTongScanResultBean.getCode_url(), HttpStatus.OK);
    }

    /**
     * 威富通支付
     *
     * @param prePayBean
     * @return
     */
    public ResponseEntity prePayOfWeiFuTong(PrePayBean prePayBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("微信");
        moneyRecord.setMoney(Utils.toBigDecimal(prePayBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        User userById = userDao.findUserById(AuthContext.getUserId());
        PrePayRespBean prePayRespBean = weiFuTongPayService.payInit(moneyRecord.getSerialNo(), "充值支付",
                userById.getOpenId(), moneyRecord.getMoney().multiply(new BigDecimal(100)).intValue(), prePayBean.getSpbillCreateIp());
        if (!prePayRespBean.success()) {
            Log.error("WFT预支付接口调用失败 ".concat(JSON.toJSONString(prePayRespBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }

        //威富通公众号js支付
//        moneyRecord.setThirdSerialNo();
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(prePayRespBean.getToken_id(), HttpStatus.OK);
    }

    /**
     * 威富通支付回调
     *
     * @param payResultReqBean
     * @return
     */
    public boolean payResultOfWeiFuTong(PayResultReqBean payResultReqBean) {
        Log.info("异步通知验签成功！");
        String serialNo = payResultReqBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus()) || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(payResultReqBean.getOut_trade_no());
        moneyRecord.setFailureReason(payResultReqBean.getErr_msg());
        moneyRecord.setThirdSerialNo(payResultReqBean.getOut_transaction_id());//第三方流水号
        moneyRecord.setCompleteTime(new Date());

        User user = userDao.findUserById(m.getUserId());
        if (payResultReqBean.success()) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
            //充值成功异步通知之后，送券
            if (null == user.getParent2Id()) {
                user.setParent2Id("");
            }
            if (userDao.findOutMoneyList(user) == 0) {
                couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
            }
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        }

        if (moneyDao.updateRecord(moneyRecord) != 1
                || (payResultReqBean.success() && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付"))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        Log.info("充值成功-------！");
        return true;
    }

    /**
     * 天宫支付
     *
     * @param prePayBean
     * @return
     */
    public ResponseEntity prePayOfTianGong(TianGongPrePayBean prePayBean) {
        String type = prePayBean.getType();
        String serialNo = serialNo();
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setMoney(Utils.toBigDecimal(prePayBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        moneyRecord.preInsert();
        if ("1".equals(type)) {
            String chargeWeiXin = tianGongPayService.chargeWeiXin(serialNo, prePayBean.getMoney());
            if (!"error".equals(chargeWeiXin)) {
                moneyRecord.setChannel("天宫微信支付");
                if (moneyDao.record(moneyRecord) != 1) {
                    return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity(chargeWeiXin, HttpStatus.OK);
        } else if ("2".equals(type)) {
            // 银联
            moneyRecord.setChannel("天宫银联支付");
            if (moneyDao.record(moneyRecord) != 1) {
                return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
            }
            PrePayReqBean prePayReqBean = tianGongPayService.chargeYinLian(serialNo, prePayBean.getMoney());
            return new ResponseEntity(prePayReqBean, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * 天宫支付回调
     *
     * @param payResultReqBean
     * @return
     */
    public boolean payResultOfTianGong(com.rmkj.microcap.common.modules.pay.tiangong.bean.PayResultReqBean payResultReqBean) {
        String serialNo = payResultReqBean.getOrder_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(payResultReqBean.getOrder_no());
        moneyRecord.setCompleteTime(new Date());
        if (payResultReqBean.getIs_success()) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        }
        User user = userDao.findUserById(m.getUserId());
        if (moneyDao.updateRecord(moneyRecord) != 1
                || (payResultReqBean.getIs_success() && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付"))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    //首信易支付扫码下单
    public ResponseEntity shouXinYinCreateOrder(PayRequestParamBean payRequestParamBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dateId1 = df1.format(new Date());//获取当前日期
        String serialNo = serialNo();
        String thirdSeriaNo = dateId1 + "-" + ProjectConstants.V_MID + "-" + serialNo;
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(thirdSeriaNo);
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("微信充值");
        moneyRecord.setMoney(Utils.toBigDecimal(payRequestParamBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        //调用下单接口
        PayOrderResBean payOrderResBean = payOrderService.createOrder(payRequestParamBean, moneyRecord.getThirdSerialNo(), moneyRecord.getSerialNo());
        //验证数据签名
        //v_status + v_oid + v_amount + v_moneytype+v_codeurl+v_bankurl
        String dataSign = payOrderResBean.getStatus() + payOrderResBean.getOid() + payOrderResBean.getAmount() + payOrderResBean.getMoneytype() + payOrderResBean.getCodeurl() + payOrderResBean.getBankurl();
        //验证签名
        //首信公钥文件路径
        String publicKey = MoneyService.class.getClassLoader().getResource("cert/Public1024.key").getPath();
        //签名信息
        String SignString = payOrderResBean.getSign();
        //原信息
        String strSource = dataSign;
        RSA_MD5 rsaMD5 = new RSA_MD5();
        //公钥验证 k=0验证成功
        int k = rsaMD5.PublicVerifyMD5(publicKey, SignString, strSource);
        if ((!"0".equals(payOrderResBean.getStatus())) || (k != 0)) {
            Log.error("create order fail respCode is " + payOrderResBean.getStatus());
            return new ResponseErrorEntity("创建订单失败", HttpStatus.BAD_REQUEST);
        }
        //
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(payOrderResBean, HttpStatus.OK);
    }

    /**
     * 易支付 根据结果判断是否，充值和增加充值记录和资金变化
     */
    public boolean toAddMoney(String serialNo, String status) {
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (m == null) {
            return false;
        }
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setCompleteTime(new Date());
        if ("1".equals(status)) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        } else if ("0".equals(status)) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
            moneyRecord.setFailureReason("未支付");
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
            moneyRecord.setFailureReason("支付失败");
        }
        User user = userDao.findUserById(m.getUserId());
        if (moneyDao.updateRecord(moneyRecord) != 1 || ("1".equals(status) && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, serialNo))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 根据充值记录表查询，处理中的充值记录的支付结果
     */
    public List<MoneyRecord> selPayResult() {
        return moneyDao.selChargeResult();
    }

    /**
     * 首信易支付 银联支付下单
     */
    public ResponseEntity createUnionPayOrder(UnionPayRequestParam unionPayRequestParam) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dateId1 = df1.format(new Date());//获取当前日期
        String serialNo = serialNo();
        String thirdSeriaNo = dateId1 + "-" + ProjectConstants.V_MID + "-" + serialNo;
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(thirdSeriaNo);
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("银联充值");
        moneyRecord.setMoney(Utils.toBigDecimal(unionPayRequestParam.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        UnionPayOrderReq unionPayOrderReq = unionPayOrderService.createUnionPayOrder();
        unionPayOrderReq.setV_amount(unionPayRequestParam.getMoney());
        unionPayOrderReq.setV_oid(thirdSeriaNo);
        unionPayOrderReq.setV_ymd(dateId1);
        //数字签名加密
        String v_md5infoStr = unionPayOrderReq.getV_moneytype() + unionPayOrderReq.getV_ymd() + unionPayOrderReq.getV_amount() + unionPayOrderReq.getV_rcvname() + unionPayOrderReq.getV_oid() + unionPayOrderReq.getV_mid() + unionPayOrderReq.getV_url();
        String v_md5info = null;
        Md5 md5 = new Md5("");
        try {
            md5.hmac_Md5(v_md5infoStr, ProjectConstants.MD5KEY); //第一个参数是加密参数，第二个参数是加密密钥，测试密钥：test，正式上线之前注意修改
            byte b[] = md5.getDigest();
            v_md5info = md5.stringify(b);
            unionPayOrderReq.setV_md5info(v_md5info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(unionPayOrderReq, HttpStatus.OK);
    }

    /**
     * 98支付 快捷支付下单
     */
    public ResponseEntity createNineEightQuickPayOrder(NineEightPrePayBean nineEightPrePayBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dateId1 = df1.format(new Date());//获取当前日期
        String serialNo = serialNo();
        String thirdSeriaNo = dateId1 + "-" + ProjectConstants.V_MID + "-" + serialNo;
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(thirdSeriaNo);
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("9银联充值");
        BigDecimal chargeMoney = Utils.toBigDecimal(nineEightPrePayBean.getMoney());
        moneyRecord.setMoney(chargeMoney);
        BigDecimal fee = chargeMoney.multiply(new BigDecimal(0.015));
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        QuickPayOrderResBean quickPayOrderResBean = nineEightScanPayService.createQuickPayOrder(moneyRecord, nineEightPrePayBean);
        if (quickPayOrderResBean == null || !"000000".equals(quickPayOrderResBean.getResp_code())) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(quickPayOrderResBean, HttpStatus.OK);
    }

    /**
     * TODO 获取用户充值提现记录
     *
     * @return
     */
    public List<MoneyRecord> findUserPayMoneyRecord(MoneyRecord moneyRecord) {
        String userId = AuthContext.getUserId();
        moneyRecord.setUserId(userId);
        if (StringUtils.isBlank(moneyRecord.getType())) {
            moneyRecord.setType(null);
        }
        if (StringUtils.isBlank(moneyRecord.getWithDrawTime()))
            moneyRecord.setWithDrawTime(null);
        List<MoneyRecord> list = moneyDao.findUserPayMoneyRecord(moneyRecord);
        return list;
    }

    /**
     * TODO 验证提现时间
     *
     * @return
     */
    public boolean getWithdrawTime() {
        Date date = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(date);
        if (nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }
        if (nowCalendar.compareTo(getStartWithdrawTime(date)) == -1 || nowCalendar.compareTo(getEndWithdrawTime(date)) == 1) {
            return false;
        }
        return true;
    }

    /**
     * TODO 获取提现开始时间
     *
     * @param date
     * @return
     */
    public Calendar getStartWithdrawTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 00);
        return calendar;
    }

    /**
     * TODO 提现结束时间
     *
     * @param date
     * @return
     */
    public Calendar getEndWithdrawTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 00);
        return calendar;
    }

    /*
     * App下单成功之后，调用
     */
    public ResponseEntity moneyRecordFromApp(FromAppReqBean fromAppReqBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(fromAppReqBean.getUserId());
        moneyRecord.setSerialNo(fromAppReqBean.getSerialNo());
        moneyRecord.setThirdSerialNo(fromAppReqBean.getThirdSerialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("App充值");
        moneyRecord.setMoney(Utils.toBigDecimal(fromAppReqBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * TODO 威鹏扫码支付下单
     *
     * @param weiPengDoBean
     * @return
     */
    public ResponseEntity weiPengScanQrcodePayOrder(WeiPengDoBean weiPengDoBean) {
        //验证入金金额-最低一元
        if (new BigDecimal(weiPengDoBean.getMoney()).compareTo(new BigDecimal("10")) == -1) {
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("微信线上扫码充值");
        moneyRecord.setMoney(new BigDecimal(weiPengDoBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        WeiPengD0ResultBean weiPengD0ResultBean = weiPengScanQrcodePayService.weiPengScanQrcodePayService(weiPengDoBean, moneyRecord.getSerialNo());
        if (!"10000".equals(weiPengD0ResultBean.getReturn_code())) {
            Log.error("创建订单失败：" + weiPengD0ResultBean.getMessage());
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
            return new ResponseErrorEntity("创建订单失败", HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setThirdSerialNo(weiPengD0ResultBean.getOut_trade_no());
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(weiPengD0ResultBean.getCode_url(), HttpStatus.OK);
    }

    /**
     * 威鹏扫码下单返回结果
     *
     * @param asyncResultBean 异步通知结果
     * @return
     */
    public boolean getWeiPengQrCodeAsyncResult(WeiPengQrCodeAsyncResultBean asyncResultBean) {
        String serialNo = asyncResultBean.getPay_num();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus()) || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setFailureReason(asyncResultBean.getTrade_result().equals("success") ? "" : asyncResultBean.getMessage());

        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        if ("success".equals(asyncResultBean.getTrade_result())) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);

            //充值成功异步通知之后，送券
            if (null == user.getParent1Id()) {
                user.setParent1Id("");
            }
            if (userDao.findOutMoneyList(user) == 0) {
                couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent1Id());
            }
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        }
        if (moneyDao.updateRecord(moneyRecord) != 1 || ("success".equals(asyncResultBean.getTrade_result()) && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, serialNo))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 支付宝支付下单
     *
     * @param prePayBean
     * @return
     */
    public ResponseEntity aLiScanCodePay(PrePayBean prePayBean) {
        //验证入金金额-最低10元
        if (new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1) {
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("支付宝支付");
        moneyRecord.setMoney(Utils.toBigDecimal(prePayBean.getMoney()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        WeiFuTongScanResultBean weiFuTongScanResultBean = weiFuTongScanPayService.WeiFuTongALiScanCodePay(moneyRecord, prePayBean.getSpbillCreateIp());
        if (Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(weiFuTongScanResultBean.getStatus()) && !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(weiFuTongScanResultBean.getResult_code())) {
            Log.error("支付宝支付接口调用失败 :".concat(JSON.toJSONString(weiFuTongScanResultBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(weiFuTongScanResultBean.getCode_url(), HttpStatus.OK);
    }

    /**
     * TODO 根据用户id查询资金变更明细
     *
     * @return
     */
    public void findMoneyChangeByUserId(Model model, String flag) {
        MoneyChange moneyChange = new MoneyChange();
        moneyChange.setUserId(AuthContext.getUserId());
        moneyChange.setFlag(flag);
        model.addAttribute("moneyChangeList", userDao.findMoneyChangeByUserId(moneyChange));
        model.addAttribute("flag", flag);
    }

    /*
    * 查询用户是否提现成功过
    * */
    public List<MoneyRecord> findUserWithdraw(String userId) {
        return moneyDao.findUserWithdraw(userId);
    }

    /*
   * 佣金转余额
   *
   *
     * 0 充值 1 提现 2 建仓 3 平仓 4提现失败退款 5 佣金转余额
     * @param userId
     * @param difMoney
     * @param beforeMoney
     * @param type
     * @param remark
     * @return
     */

    public ResponseEntity commissionToMoney() {
        try {
            User user = userDao.findUserById(AuthContext.getUserId());
            ReturnMoneyOut rmo = new ReturnMoneyOut();
            rmo.setUserId(user.getId());
            rmo.setMoney(user.getReturnMoney());
            if (!changeMoney(user.getId(), user.getReturnMoney(), user.getMoney(), "5", "佣金转余额") ||
                    userDao.commissionToMoney(rmo) != 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public String findMoneyRecordStatusByUserId() {
        User user = new User();
        user.setId(AuthContext.getUserId());
        Integer recordCount = moneyDao.findMoneyRecordStatusByUserId(user);
        return recordCount.toString();
    }

    /**
     * 98支付 扫码支付下单
     */
    public ResponseEntity nineEightScanPay(NineEightPrePayBean prePayBean) {
        //验证入金金额-最低10元
/*        if(new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*/
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("9扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        NineEightScanPayResBean nineEightScanPayResBean = nineEightScanPayService.nineEightScanPayOrder(moneyRecord, prePayBean);
        if (nineEightScanPayResBean == null || !"0000".equals(nineEightScanPayResBean.getResp_code())) {
            Log.error("98扫码支付接口调用失败 :".concat(JSON.toJSONString(nineEightScanPayResBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(nineEightScanPayResBean.getCode_url(), HttpStatus.OK);
    }

    /**
     * 98支付 微信扫码回调
     *
     * @param payResultReqBean
     * @return
     */
    public boolean payResultOfNineEight(NineEightScanPayResultResBean payResultReqBean) {
        String serialNo = payResultReqBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(payResultReqBean.getOut_trade_no());
        moneyRecord.setFailureReason(payResultReqBean.getResp_msg());
        moneyRecord.setCompleteTime(new Date());
        moneyRecord.setThirdSerialNo(payResultReqBean.getTrade_no());
        User user = userDao.findUserById(m.getUserId());
        if ("0000".equals(payResultReqBean.getResp_code())) {
            if ("S".equals(payResultReqBean.getTrade_state())) {
                moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
                //充值成功异步通知之后，送券
                if (null == user.getParent2Id()) {
                    user.setParent2Id("");
                }
                if (userDao.findOutMoneyList(user) == 0) {
                    couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
                }
            } else {
                moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
            }
            if (moneyDao.updateNoThirdNoRecord(moneyRecord) != 1
                    || ("S".equals(payResultReqBean.getTrade_state()) && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付"))) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 衫德支付 扫码支付下单
     */
    public ResponseEntity ShanDeScanPay(ShanDePrePayBean prePayBean) {

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("杉德扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        ShanDeScanCodePayBean shanDeScanCodePayBean = shanDeH5PayService.createScanCodePageService(moneyRecord, prePayBean);
        shanDeScanCodePayBean.setStatus("0");
        if (shanDeScanCodePayBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean.getStatus())) { // ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean.getResult_code())
            Log.error("杉德扫码支付接口调用失败 :".concat(JSON.toJSONString(shanDeScanCodePayBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setThirdSerialNo(shanDeScanCodePayBean.getOrder_sn());
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(shanDeScanCodePayBean.getQr_code(), HttpStatus.OK);
    }

    /**
     * 衫德支付 QQ钱包扫码支付下单
     */
    public ResponseEntity ShanDeQQScanPay(ShanDePrePayBean prePayBean) {

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("杉德扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        ShanDeScanCodePayBean shanDeScanCodePayBean = shanDeH5PayService.createQQScanCodePageService(moneyRecord, prePayBean);
        shanDeScanCodePayBean.setStatus("0");
        if (shanDeScanCodePayBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean.getStatus())) { // ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean.getResult_code())
            Log.error("杉德扫码支付接口调用失败 :".concat(JSON.toJSONString(shanDeScanCodePayBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setThirdSerialNo(shanDeScanCodePayBean.getOrder_sn());
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(shanDeScanCodePayBean.getQr_code(), HttpStatus.OK);
    }

    /**
     * 百时支付 扫码下单
     */
    public ResponseEntity baiShiScanPay(BaiShiPrePayBean prePayBean, HttpServletRequest request) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("百时扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        try {
            String uri = baiShiPayService.baiShiScanPay(moneyRecord, prePayBean, request);
            if (uri != null && !uri.isEmpty()) {
                moneyRecord.preInsert();
                moneyRecord.setMoney(chargeMoney.subtract(fee));
                if (moneyDao.record(moneyRecord) != 1) {
                    return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(uri, HttpStatus.OK);
            }
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
    }

    /**
     * 百时支付 公众号下单
     */
    public ResponseEntity baiShiWeChatPubPay(BaiShiPrePayBean prePayBean, HttpServletRequest request) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("百时公众号支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        try {
            String uri = baiShiPayService.baiShiWeChatPubPay(moneyRecord, prePayBean, request);
            if (uri != null && !uri.isEmpty()) {
                moneyRecord.preInsert();
                moneyRecord.setMoney(chargeMoney.subtract(fee));
                if (moneyDao.record(moneyRecord) != 1) {
                    return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(uri, HttpStatus.OK);
            }
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
    }

    /**
     * 摩宝支付 银联下单
     */
    public ResponseEntity moBaoUnionPay(MoBaoPrePayBean prePayBean, HttpServletRequest request) {

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("摩宝银联支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        MoBaoPerBankCardBean moBaoPerBankCardBean = new MoBaoPerBankCardBean();
        moBaoPerBankCardBean.setId(Utils.uuid());
        moBaoPerBankCardBean.setUserId(AuthContext.getUserId());
        moBaoPerBankCardBean.setCardByName(prePayBean.getCardByName());
        moBaoPerBankCardBean.setCardByNo(prePayBean.getCardByNo());
        moBaoPerBankCardBean.setCerNumber(prePayBean.getCerNumber());
        moBaoPerBankCardBean.setMobile(prePayBean.getMobile());

        try {
            String data = moBaoPayService.moBaoUnionPay(moneyRecord, prePayBean, request);
            if (data != null && !data.isEmpty()) {
                MoBaoRespBean moBaoResp = com.alibaba.fastjson.JSONObject.parseObject(data, MoBaoRespBean.class);
                if ("00".equals(moBaoResp.getStatus()) && "01".equals(moBaoResp.getRefCode())) {
                    moneyRecord.setThirdSerialNo(moBaoResp.getKsPayOrderId());
                    moneyRecord.preInsert();
                    moneyRecord.setMoney(chargeMoney.subtract(fee));

                    if (moneyDao.record(moneyRecord) != 1) {
                        return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                    }
                    CacheFacade.set(moneyRecord.getSerialNo(),moBaoPerBankCardBean,60 * 3);
                    return new ResponseEntity(moBaoResp.getOrderId(), HttpStatus.OK);
                }
                return new ResponseErrorEntity(data, HttpStatus.BAD_REQUEST);
            }
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
    }


    /**
     * 摩宝支付 验证码完成支付
     */
    public ResponseEntity moBaoUnionCodePay(MoBaoCodeReqBean moBaoCodeReqBean) {
        MoneyRecord thisRecord = moneyDao.findMoneyRecordBySerialNoWithLock(moBaoCodeReqBean.getOrderId());
        moBaoCodeReqBean.setKsPayOrderId(thisRecord.getThirdSerialNo());
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(moBaoCodeReqBean.getOrderId());
        moneyRecord.setThirdSerialNo(thisRecord.getThirdSerialNo());
        //初始化为失败，成功时修改状态
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        moneyRecord.setCompleteTime(new Date());
        moneyRecord.setFailureReason("支付失败！");

        try {
            String data = moBaoPayService.moBaoUnionCodePay(moBaoCodeReqBean);
            if (data != null && !data.isEmpty()) {
                MoBaoCodeRespBean moBaoCodeRespBean = com.alibaba.fastjson.JSONObject.parseObject(data, MoBaoCodeRespBean.class);
                //MoBaoCodeRespBean moBaoCodeRespBean = new MoBaoCodeRespBean();
                if ("00".equals(moBaoCodeRespBean.getStatus())) {
                    if ("00".equals(moBaoCodeRespBean.getRefCode())) {
                        moneyRecord.setCompleteTime(new Date());
                        User user = userDao.findUserById(thisRecord.getUserId());
                        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
                        moneyRecord.setFailureReason("");
                        moneyRecord.setSerialNo(moBaoCodeReqBean.getOrderId());
                        moneyRecord.setThirdSerialNo(thisRecord.getThirdSerialNo());
                        //充值成功之后，送券
                        if (null == user.getParent2Id()) {
                            user.setParent2Id("");
                        }
                        if (userDao.findOutMoneyList(user) == 0) {
                            couponService.firstRecharge(thisRecord.getUserId(), thisRecord.getMoney(), user.getParent2Id());
                        }
                        if (moneyDao.updateRecord(moneyRecord) != 1 || !changeMoney(thisRecord.getUserId(), thisRecord.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_ERROR, HttpStatus.BAD_REQUEST);
                        }
                        //查询是否已经存在
                        MoBaoPerBankCardBean moBaoPerBank = moneyDao.moBaoPayQueryBack(AuthContext.getUserId());
                        if(moBaoPerBank ==null ){
                            moBaoPerBank= new MoBaoPerBankCardBean();
                        }
                        MoBaoPerBankCardBean moBaoPerBankCardBean= CacheFacade.getObject(moBaoCodeReqBean.getOrderId());
                        Log.info("*----------"+moBaoPerBank.toString());

                        if(moBaoPerBank.getCerNumber() == null && moBaoPerBank.getCardByNo() == null && moBaoPerBank.getMobile() ==null && moBaoPerBank.getCardByName() == null){
                            moneyDao.personageBankCard(moBaoPerBankCardBean);
                            CacheFacade.delete(moBaoCodeReqBean.getOrderId());
                            return new ResponseEntity(StatusCode.MO_BAO_UNION_SUCCESS, HttpStatus.OK);
                        }
                        moneyDao.personageBankCardUpdate(moBaoPerBankCardBean);
                        CacheFacade.delete(moBaoCodeReqBean.getOrderId());
                        return new ResponseEntity(StatusCode.MO_BAO_UNION_SUCCESS, HttpStatus.OK);

                    }
                    if ("03".equals(moBaoCodeRespBean.getRefCode())) {

                        return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_DEFAULT, HttpStatus.OK);
                    }
                    moneyDao.updateRecord(moneyRecord);
                    return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_ERROR, HttpStatus.BAD_REQUEST);
                }
                moneyDao.updateRecord(moneyRecord);
                return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_ERROR, HttpStatus.BAD_REQUEST);
            }
            moneyDao.updateRecord(moneyRecord);
            return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        moneyDao.updateRecord(moneyRecord);
        return new ResponseErrorEntity(StatusCode.MO_BAO_UNION_ERROR, HttpStatus.BAD_REQUEST);
    }

    /**
     * 摩宝银联支付回调
     */
    public boolean MoBaoCallBack(MoBaoCallBackBean moBaoCallBackBean, String orderID) {
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(orderID);
        //如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //验证回调金额是否正确
        BigDecimal amt = new BigDecimal(moBaoCallBackBean.getTransAmount());
        if (amt.compareTo(m.getMoney().add(m.getFee())) != 0) {
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(orderID);
        moneyRecord.setThirdSerialNo(m.getThirdSerialNo());
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateRecord(moneyRecord) != 1 || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 百时支付回调
     */
    public boolean baiShiCallBack(BaiShiCallBackBean baiShiCallBackBean, String orderID) {
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(orderID);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //验证回调金额是否正确
        BigDecimal amt = new BigDecimal(baiShiCallBackBean.getAmt()).divide(new BigDecimal(100));
        if (amt.compareTo(m.getMoney().add(m.getFee())) != 0) {
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(orderID);
        moneyRecord.setThirdSerialNo(baiShiCallBackBean.getThirdSerial());
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateRecord(moneyRecord) != 1 || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 98支付 银联快捷扫码回调
     *
     * @param payResultReqBean
     * @return
     */
    public boolean payResultOfNineEightQuickPay(QuickPayOrderResBean payResultReqBean) {
        String serialNo = payResultReqBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(payResultReqBean.getOut_trade_no());
        moneyRecord.setFailureReason(payResultReqBean.getResp_msg());
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        if ("000000".equals(payResultReqBean.getResp_code())) {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
            //充值成功异步通知之后，送券
            if (null == user.getParent2Id()) {
                user.setParent2Id("");
            }
            if (userDao.findOutMoneyList(user) == 0) {
                couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
            }
        } else {
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
        }
        if (moneyDao.updateRecord(moneyRecord) != 1
                || ("000000".equals(payResultReqBean.getResp_code()) && !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付"))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 杉德支付 微信H5支付回调
     */
    public boolean shanDeCallBack(ShanDeH5PayCallBackResBean shanDeH5PayCallBackResBean, String orderSn) {
        String serialNo = shanDeH5PayCallBackResBean.getMerchant_order_sn();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //验证回调金额是否正确
        if (new BigDecimal(shanDeH5PayCallBackResBean.getTotal_fee()).compareTo(m.getMoney().add(m.getFee())) != 0) {
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(orderSn);
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateRecord(moneyRecord) != 1
                || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 58支付 微信openPay
     */
    public ResponseEntity fiveEightOpenPay(FiveEightOpenPayReqBean fiveEightOpenPayReqBean) {
        //验证入金金额-最低10元
/*        if(new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*/

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("58微信扫码支付");
        moneyRecord.setMoney(Utils.toBigDecimal(fiveEightOpenPayReqBean.getTotalAmount()));
        moneyRecord.setFee(new BigDecimal(0.00));
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        User user = userDao.findUserById(AuthContext.getUserId());
        fiveEightOpenPayReqBean.setOpenId(user.getOpenId());
        String result = fiveEightOpenPayService.fiveEightOpenPayOrder(moneyRecord, fiveEightOpenPayReqBean);
        FiveEightOpenPayResBean fiveEightOpenPayResBean = JSON.parseObject(result, FiveEightOpenPayResBean.class);
        String data = fiveEightOpenPayResBean.getData();
        FiveEightOpenPayResBeanData1 fiveEightOpenPayResBeanData1 = JSON.parseObject(data, FiveEightOpenPayResBeanData1.class);
        String data1 = fiveEightOpenPayResBeanData1.getData();
        FiveEightOpenPayResBeanData2 fiveEightOpenPayResBeanData2 = JSON.parseObject(data1, FiveEightOpenPayResBeanData2.class);
/*        if(fiveEightOpenPayResBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(fiveEightOpenPayResBean.getStatus()) ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(nineEightScanPayResBean.getResult_code())){
            Log.error("58微信扫码支付接口调用失败 :".concat(JSON.toJSONString(fiveEightOpenPayResBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }*/
        moneyRecord.preInsert();
        moneyRecord.setThirdSerialNo(fiveEightOpenPayResBeanData2.getOrderId());
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(fiveEightOpenPayResBeanData2, HttpStatus.OK);
    }

    /**
     * 58支付 微信扫码回调
     *
     * @param fiveEightOpenPayResultResBean
     * @return
     */
    public boolean payResultOfFiveEight(FiveEightOpenPayResultResBean fiveEightOpenPayResultResBean) {
        String serialNo = fiveEightOpenPayResultResBean.getAttach();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //支付平台太lj，这里验证回调金额和订单号是否正确
        BigDecimal chargeMoney = (m.getMoney().add(m.getFee())).multiply(new BigDecimal(100));
        BigDecimal returnMoney = new BigDecimal(fiveEightOpenPayResultResBean.getTotalAmount());
        Log.info("本地下单金额：" + chargeMoney.toString() + "  回调金额：" + returnMoney.toString());
        Log.info("本地流水号：" + m.getSerialNo() + "   回调流水号：" + serialNo);
        if (returnMoney.compareTo(chargeMoney) != 0 || !serialNo.equals(m.getSerialNo())) {
            Log.error("回调金额或订单流水号不正确!不作处理");
            return true;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setSerialNo(fiveEightOpenPayResultResBean.getAttach());
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setThirdSerialNo(fiveEightOpenPayResultResBean.getTransactionId());
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateNoThirdNoRecord(moneyRecord) != 1
                || (!changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付"))) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 58支付 微信网关模式支付
     */
    public ResponseEntity createGateWayPayOrder(FiveEightGatewayOpenPayReqBean fiveEightOpenPayReqBean, HttpServletRequest request) {
        //验证入金金额-最低10元
/*        if(new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*/

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("5微信支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(fiveEightOpenPayReqBean.getTprice());
        BigDecimal fee = Utils.toBigDecimal(fiveEightOpenPayReqBean.getTprice()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
/*        if(fiveEightOpenPayResBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(fiveEightOpenPayResBean.getStatus()) ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(nineEightScanPayResBean.getResult_code())){
            Log.error("58微信扫码支付接口调用失败 :".concat(JSON.toJSONString(fiveEightOpenPayResBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }*/
        fiveEightOpenPayReqBean.setUserId(ProjectConstants.FIVE_EIGHT_WEI_XIN_GATE_WAY_MCH_ID);
        fiveEightOpenPayReqBean.setTprice(chargeMoney.toString());
        fiveEightOpenPayReqBean.setAttach(moneyRecord.getSerialNo());
        fiveEightOpenPayReqBean.setTname("众宝乐商");
        fiveEightOpenPayReqBean.setJumpUrl("http://" + request.getServerName() + "/front/wap/user");
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(fiveEightOpenPayReqBean, HttpStatus.OK);
    }

    /**
     * 58支付 微信网关模式支付 接口提交方式
     */
    public ResponseEntity createGateWayPayOrderByService(FiveEightPrePayBean fiveEightPrePayBean, HttpServletRequest request) {
        //验证入金金额-最低10元
/*        if(new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*/

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("5微信支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(fiveEightPrePayBean.getMoney());
        BigDecimal fee = chargeMoney.multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
/*        if(fiveEightOpenPayResBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(fiveEightOpenPayResBean.getStatus()) ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(nineEightScanPayResBean.getResult_code())){
            Log.error("58微信扫码支付接口调用失败 :".concat(JSON.toJSONString(fiveEightOpenPayResBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }*/
        FiveEightOpenPayResByServiceBeanData2 fiveEightOpenPayResByServiceBeanData2 = fiveEightOpenPayService.fiveEightOpenPayOrderByService(moneyRecord, request);
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.preInsert();
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(fiveEightOpenPayResByServiceBeanData2.getPayInfo(), HttpStatus.OK);
    }

    /**
     * 58支付 支付宝H5模式支付 接口提交方式
     */


   /* public ResponseEntity createGateWayAliPayOrderByService(FiveEightPrePayBean fiveEightPrePayBean, HttpServletRequest request){

        //验证入金金额-最低10元
 /*       if(new BigDecimal(prePayBean.getMoney()).compareTo(new BigDecimal("10")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*//*

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("58支付宝H5支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(fiveEightPrePayBean.getMoney());
        BigDecimal fee = chargeMoney.multiply(new BigDecimal(0.01));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
*//*        if(fiveEightOpenPayResBean == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(fiveEightOpenPayResBean.getStatus()) ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(nineEightScanPayResBean.getResult_code())){
            Log.error("58微信扫码支付接口调用失败 :".concat(JSON.toJSONString(fiveEightOpenPayResBean)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }*//*
        FiveEightOpenPayResByServiceBeanData3 fiveEightOpenPayResByServiceBeanData3 = fiveEightOpenPayService.fiveEightAliPayOrderByService(moneyRecord,request);
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.preInsert();
        if(moneyDao.record(moneyRecord) != 1){
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(fiveEightOpenPayResByServiceBeanData3.getCodeUrl(),HttpStatus.OK);
    }*/

    /**
     * 威鹏微信H5支付下单
     *
     * @param weiPengDoBean
     * @return
     */
    public ResponseEntity weiPengH5Pay(WeiPengDoBean weiPengDoBean) {
        //验证入金金额-最低一元
/*        if(new BigDecimal(weiPengDoBean.getMoney()).compareTo(new BigDecimal("100")) == -1){
            return new ResponseErrorEntity(StatusCode.RECHARGE_MIN_MONEY, HttpStatus.BAD_REQUEST);
        }*/
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("威H5线上扫码充值");
        BigDecimal chargeMoney = Utils.toBigDecimal(weiPengDoBean.getMoney());
        moneyRecord.setMoney(chargeMoney);
        BigDecimal fee = Utils.toBigDecimal(weiPengDoBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        WeiPengD0ResultBean weiPengD0ResultBean = weiPengScanQrcodePayService.weiPengH5PayService(weiPengDoBean, moneyRecord.getSerialNo());
        if (!"10000".equals(weiPengD0ResultBean.getReturn_code())) {
            Log.error("创建订单失败：" + weiPengD0ResultBean.getMessage());
            moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_FAIL);
            return new ResponseErrorEntity("创建订单失败", HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setThirdSerialNo(weiPengD0ResultBean.getOut_trade_no());
        if (moneyDao.record(moneyRecord) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(weiPengD0ResultBean.getCode_url(), HttpStatus.OK);
    }

    /**
     * 杉德H5支付
     */
    public String shanDeH5Pay(ShanDeH5OpenIdBean shanDeH5OpenIdBean, HttpServletRequest request) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        String serialNo = serialNo();
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("杉微信H5充值");
        BigDecimal chargeMoney = Utils.toBigDecimal(shanDeH5OpenIdBean.getMoney());
        moneyRecord.setMoney(chargeMoney);
        BigDecimal fee = Utils.toBigDecimal(shanDeH5OpenIdBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        ShanDeH5PayParamBean shanDeH5PayParamBean = shanDeH5PayService.shanDeH5PayService(shanDeH5OpenIdBean, serialNo, request);
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setThirdSerialNo(shanDeH5PayParamBean.getOrder_sn());
        if (moneyDao.record(moneyRecord) != 1) {
            return null;
        }
        return shanDeH5PayParamBean.getUrl();
    }


    /**
     * 新Web 扫码支付下单
     */
    public ResponseEntity xinWebScanPay(WebScanPayBean prePayBean,HttpServletRequest request) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("新Web扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
            try {
                String codeUrl = weCharService.WebScanPay(moneyRecord,request);
                if (codeUrl != null && !codeUrl.isEmpty()) {
                    moneyRecord.preInsert();
                    moneyRecord.setMoney(chargeMoney.subtract(fee));
                    if (moneyDao.record(moneyRecord) != 1) {
                        return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity(codeUrl, HttpStatus.OK);
                }
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
                }
        return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }

    /**
     * 新Web扫码 支付回调
     */
    public boolean WebScanCallBack(WebScanCallBackBean WebScanCallBackBean, String orderSn) {
        String serialNo = WebScanCallBackBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //验证回调金额是否正确
        if (new BigDecimal(WebScanCallBackBean.getTotal_fee()).compareTo((m.getMoney().add(m.getFee())).multiply(new BigDecimal(100))) != 0) {
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(orderSn);
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateRecord(moneyRecord) != 1
                || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }


    /**
     * 杉微信公众号充值2
     */
    public String shanDeH5Pay2(ShanDeH5OpenIdBean2 shanDeH5OpenIdBean2){
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        String serialNo = serialNo();
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setCreateTime(new Date());
        moneyRecord.setChannel("杉微信公众号充值");
        BigDecimal chargeMoney = Utils.toBigDecimal(shanDeH5OpenIdBean2.getMoney());
        moneyRecord.setMoney(chargeMoney);
        BigDecimal fee = Utils.toBigDecimal(shanDeH5OpenIdBean2.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        ShanDeH5PayParamBean2 shanDeH5PayParamBean2 = shanDeH5PayService2.shanDeH5PayService2(shanDeH5OpenIdBean2,serialNo);
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        Log.info("杉微信公众号充值Order_sn:"+shanDeH5PayParamBean2.getOrder_sn());
        moneyRecord.setThirdSerialNo(shanDeH5PayParamBean2.getOrder_sn());
        if(moneyDao.record(moneyRecord) != 1){
            return null;
        }
        return shanDeH5PayParamBean2.getPrepay_id();
    }


    /**
     * 衫德支付 扫码支付下单2
     */
    public ResponseEntity ShanDeScanPay2(ShanDePrePayBean2 prePayBean2){

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(serialNo());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("衫德扫码支付");
        //充值收取1%手续费
        BigDecimal chargeMoney = Utils.toBigDecimal(prePayBean2.getMoney());
        BigDecimal fee = Utils.toBigDecimal(prePayBean2.getMoney()).multiply(new BigDecimal(0.015));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);

        ShanDeScanCodePayBean2 shanDeScanCodePayBean2 = shanDeH5PayService2.createScanCodePageService2(moneyRecord, prePayBean2);
        shanDeScanCodePayBean2.setStatus("0");
        if(shanDeScanCodePayBean2 == null || !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean2.getStatus())){ // ||  !Constants.Money.MONEY_RECORD_STATUS_DEFAULT.equals(shanDeScanCodePayBean.getResult_code())
            Log.error("衫德扫码支付接口调用失败 :".concat(JSON.toJSONString(shanDeScanCodePayBean2)));
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        }
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        moneyRecord.setThirdSerialNo(shanDeScanCodePayBean2.getOrder_sn());
        if(moneyDao.record(moneyRecord) != 1){
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(shanDeScanCodePayBean2.getQr_code(),HttpStatus.OK);
    }

    /**
     *  杉德支付 微信H5支付回调2
     */
    public boolean shanDeCallBack2(ShanDeH5PayCallBackResBean2 shanDeH5PayCallBackResBean2) {
        String serialNo = shanDeH5PayCallBackResBean2.getMerchant_order_sn();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if(Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())){
            return true;
        }
        //验证回调金额是否正确
        if(new BigDecimal(shanDeH5PayCallBackResBean2.getTotal_fee()).compareTo(m.getMoney().add(m.getFee())) != 0){
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(m.getThirdSerialNo());
        //充值成功异步通知之后，送券
        if(null == user.getParent2Id()){
            user.setParent2Id("");
        }
        if(userDao.findOutMoneyList(user) == 0){
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if(moneyDao.updateRecord(moneyRecord) != 1
                || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 千红支付 扫码支付下单
     */
    public Map<String, Object> qianhongScanPay(String price, int istype){

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(PayUtil.generateOrderId());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("千红扫码支付");
        //充值收取3%手续费
        BigDecimal chargeMoney = new BigDecimal(price);
        BigDecimal fee = new BigDecimal(price).multiply(new BigDecimal(0.03));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);


        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap =qianhongPayService.qianhongScanPay(moneyRecord,price,istype);

        if (resultMap != null){
            if(moneyDao.record(moneyRecord) != 1){
                return null;
            }
        }

        return  resultMap;
    }

    /**
     * 千红支付 支付回调
     */
    public boolean qianhongNotify(QianHongNotifyBean qianHongNotifyBean) {
        String serialNo = qianHongNotifyBean.getOrderid();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }
        //验证回调金额是否正确
        Log.info("个人支付Price："+qianHongNotifyBean.getAmount());
        Log.info("待校验金额："+m.getMoney().add(m.getFee()));
        if (new BigDecimal(qianHongNotifyBean.getAmount()).compareTo(m.getMoney().add(m.getFee())) != 0) {
            return false;
        }
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId()); 
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(qianHongNotifyBean.getTransaction_id());
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());
        }
        if (moneyDao.updateRecord(moneyRecord) != 1
                || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 熊云支付
     * @param xionyunPayBean
     */
    public Map<String, Object> xionyunScanPay(XionyunPayBean xionyunPayBean){

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(PayUtil.generateOrderId());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("熊云支付");
        //充值收取3%手续费
        BigDecimal chargeMoney = new BigDecimal(xionyunPayBean.getMoney());
        BigDecimal fee = new BigDecimal(xionyunPayBean.getMoney()).multiply(new BigDecimal(0.02));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));

        //查询用户的信息
        User userById = null;
        userById = userDao.findAgent3UserById(AuthContext.getUserId());
        moneyRecord.setChnName(userById.getChnName());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap =xionyunPayService.xionyunScanPay(moneyRecord,xionyunPayBean);
        Log.info(resultMap);
        if (resultMap != null){
            if(moneyDao.record(moneyRecord) != 1){
                return null;
            }
        }

        return  resultMap;
    }

    /**
     * 熊云支付 支付回调
     */
    public boolean xionyunNotify(XionyunNotifyBean xionyunNotifyBean) {
        String serialNo = xionyunNotifyBean.getOut_trade_no();
        MoneyRecord m = moneyDao.findMoneyRecordBySerialNoWithLock(serialNo);
        // 如果已经处理直接返回成功
        if (Constants.Money.MONEY_RECORD_STATUS_SUCCESS.equals(m.getStatus())
                || Constants.Money.MONEY_RECORD_STATUS_FAIL.equals(m.getStatus())) {
            return true;
        }

        //验证回调金额是否正确
        Log.info("个人支付Price："+xionyunNotifyBean.getMoney());
        Log.info("待校验金额："+m.getMoney().add(m.getFee()));
       /* if (new BigDecimal(xionyunNotifyBean.getMoney()).compareTo(m.getMoney().add(m.getFee())) != 0) {
            Log.info(xionyunNotifyBean.getMoney().compareTo(m.getMoney()));
            return false;
        }*/
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setCompleteTime(new Date());
        User user = userDao.findUserById(m.getUserId());
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_SUCCESS);
        moneyRecord.setFailureReason("");
        moneyRecord.setSerialNo(serialNo);
        moneyRecord.setThirdSerialNo(xionyunNotifyBean.getTrade_no());
        //充值成功异步通知之后，送券
        if (null == user.getParent2Id()) {
            user.setParent2Id("");
        }
        if (userDao.findOutMoneyList(user) == 0) {
            couponService.firstRecharge(m.getUserId(), m.getMoney(), user.getParent2Id());

        }
        if (moneyDao.updateRecord(moneyRecord) != 1 || !changeMoney(m.getUserId(), m.getMoney(), user.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_MONEY_IN, "充值支付")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            Log.info("进入修改金额");
            return false;
        }
        return true;
    }

    /**
     * 汇通支付
     * @param
     * @return
     */
    public ResponseEntity huizhitongQQScanPay(WebScanPayBean prePayBean) {
        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUserId(AuthContext.getUserId());
        moneyRecord.setSerialNo(PayUtil.generateOrderId());
        moneyRecord.setCreateTime(new Date());

        moneyRecord.setChannel("汇智通qq支付");
        //充值收取3%手续费
        BigDecimal chargeMoney = new BigDecimal(prePayBean.getMoney());
        BigDecimal fee = new BigDecimal(prePayBean.getMoney()).multiply(new BigDecimal(0.03));
        moneyRecord.setMoney(chargeMoney);
        moneyRecord.setFee(fee);
        moneyRecord.setType(Constants.Money.MONEY_RECORD_TYPE_IN);
        moneyRecord.setStatus(Constants.Money.MONEY_RECORD_STATUS_DEFAULT);
        moneyRecord.preInsert();
        moneyRecord.setMoney(chargeMoney.subtract(fee));
        try {
            String codeUrl = huizhitongScanPayService.HuizhitongQQPay(moneyRecord);
            if (codeUrl != null && !codeUrl.isEmpty()) {
                moneyRecord.preInsert();
                moneyRecord.setMoney(chargeMoney.subtract(fee));
                if (moneyDao.record(moneyRecord) != 1) {
                    return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(codeUrl, HttpStatus.OK);
            }
            return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseErrorEntity(StatusCode.WX_PRE_PAY_ERROR, HttpStatus.BAD_REQUEST);

    }
}
