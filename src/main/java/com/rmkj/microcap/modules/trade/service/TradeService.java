package com.rmkj.microcap.modules.trade.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.coupon.entity.Coupon;
import com.rmkj.microcap.modules.coupon.service.CouponService;
import com.rmkj.microcap.modules.index.dao.ParameterSetDao;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.index.service.ParameterService;
import com.rmkj.microcap.modules.market.service.MarketService;
import com.rmkj.microcap.modules.money.dao.CashCouponDao;
import com.rmkj.microcap.modules.money.entity.CashCoupon;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import com.rmkj.microcap.modules.sms.service.SmsService;
import com.rmkj.microcap.modules.trade.bean.MakeTradeBean;
import com.rmkj.microcap.modules.trade.bean.TradeHistoryBean;
import com.rmkj.microcap.modules.trade.bean.UserTradeBean;
import com.rmkj.microcap.modules.trade.dao.Ml3MemberUnitsMoneyChangeDao;
import com.rmkj.microcap.modules.trade.dao.Ml3MemberUnitsMoneyRecordDao;
import com.rmkj.microcap.modules.trade.dao.TradeDao;
import com.rmkj.microcap.modules.trade.entity.*;
import com.rmkj.microcap.modules.trade.http.MrGaoApi;
import com.rmkj.microcap.modules.user.dao.*;
import com.rmkj.microcap.modules.user.entity.*;
import com.rmkj.microcap.modules.user.service.Ml3OperateCenterService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.weaver.MemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 123 on 2016/10/19.
 * 交易核心业务类
 * 建仓
 * 平仓：手动、止盈止损、结算
 */
@Service
public class TradeService {

    private final Logger Log = Logger.getLogger(TradeService.class);

    @Autowired
    private Ml3AgentDao ml3AgentDao;

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMessageDao userMessageDao;

    @Autowired
    private IMl3OperateCenterDao ml3OperateCenterDao;

    @Autowired
    private CashCouponDao cashCouponDao;

    @Autowired
    private ParameterSetDao parameterSetDao;

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private MarketServer marketServer;

    @Autowired
    private CouponService couponService;

    @Autowired
    private Ml3MemberUnitsMoneyChangeDao ml3MemberUnitsMoneyChangeDao;

    @Autowired
    private Ml3MemberUnitsMoneyRecordDao ml3MemberUnitsMoneyRecordDao;

    @Autowired
    private Ml3MemberUnitsDao ml3MemberUnitsDao;

    @Autowired
    private SmsService smsService;

    @Autowired
    private Ml3OperateCenterService ml3OperateCenterService;

    @HttpService
    private MrGaoApi mrGaoApi;

    private List<Contract> contracts;

    private final static Map<String, Integer> map_precision = new HashMap();

    private String[] _codes = null;

    private String[] _codesOpen = null;

    // 存放开盘的合约
    private List<Contract> openContracts = new ArrayList<>();

    /**
     * 更新合约
     */
    @Scheduled(initialDelay = 3000, fixedRate = Constants.REFRESH_CONTRACTS)
    public void contracts(){
        contracts = tradeDao.contractList();
        int len = Math.min(contracts.size(), 4);
        String[] codes = _codes;
        _codes = new String[len];
        Contract contract = null;

        List<String> openCodes = new ArrayList<>();
        openContracts.clear();
        for (int i = 0; i < len; i++){
            contract = contracts.get(i);
            contract.setMarketOpen(isMarketOpen(new String[]{contract.getBeginTime(), contract.getEndTime()}, contract.getExemptClosed() == 1));

            map_precision.put(contract.getCode(), contract.getPrecision());
            _codes[i] = contract.getCode();

            if(contract.isMarketOpen()){
                openContracts.add(contract);
                openCodes.add(contract.getCode());
            }
        }

        _codesOpen = openCodes.toArray(new String[openCodes.size()]);

        if(codes != null){
            // 新旧数据总数可能不相同
            int newCount = _codes.length;
            int oldCount = codes.length;
            boolean[] bb = new boolean[oldCount];
            // 检查code是否变动
            if(codes != null){
                for (int i = 0; i < _codes.length; i++){
                    for(int j = 0; j < codes.length; j++){
                        if(_codes[i].equals(codes[j])){
                            newCount--;
                            oldCount--;
                            bb[j] = true;
                        }
                    }
                }
            }

            if(newCount != 0){
                marketServer.reset();
            }

            for (int i = 0; i < bb.length; i++){
                if(!bb[i]){
                    marketServer.clearCodeCache(codes[i]);
                }
            }
        }
    }

    /**
     * 产品价格精度
     * @return
     */
    public static Map<String, Integer> getPrecisions(){
        return map_precision;
    }

    public List<Contract> contractList() {
        if(contracts == null){
            contracts();
        }
        return contracts;
    }

    public String[] contractCodes() {
        if(_codes == null){
            tradeDao.contractList();
        }
        return _codes;
    }

    public String[] contractCodesOpen() {
        if(_codes == null){
            tradeDao.contractList();
        }
        return _codesOpen;
    }


    /**
     * 获取登录用户的持仓数据
     * @return
     */
    public List<Trade> tradeList() {
        return tradeDao.tradeListByUserId(AuthContext.getUserId());
    }

    /**
     * 获取登录用户的所有交易数据
     * @return
     */
    public List<Trade> tradeHistory(TradeHistoryBean tradeHistoryBean) {
        if(StringUtils.isBlank(tradeHistoryBean.getContractName())){
            tradeHistoryBean.setContractName(null);
        }
        tradeHistoryBean.setUserId(AuthContext.getUserId());
        return tradeDao.tradeHistory(tradeHistoryBean);
    }

    /**
     * 获取登录用户的今天结算数据
     * @return
     */
    public List<Trade> toDayTradeList() {
        return tradeDao.toDayTradeListByUserId(AuthContext.getUserId());
    }

    /**
     * 获取最新交易记录
     */
    public List<Trade> getNewTradeRecord(){
        return tradeDao.getNewTradeRecord();
    }
    /**
     * 建仓下单
     * @param makeTradeBean
     * @return
     */
    @Transactional
    public ResponseEntity make(MakeTradeBean makeTradeBean) {
        Contract contract = tradeDao.findContractByCode(makeTradeBean.getCode());
        boolean isExemptClosed = false;
        if(contract != null && contract.getExemptClosed() == 1){
            isExemptClosed = true;
        }
        if(!isMarketOpen() || contract == null || !isMarketOpen(new String[]{contract.getBeginTime(), contract.getEndTime()},isExemptClosed)){
            return new ResponseErrorEntity(StatusCode.MARKET_CLOSE, HttpStatus.BAD_REQUEST);
        }

        AuthEntity curAuth = AuthContext.getCurAuth();
        //验证最小建仓金额、每种商品同时持仓单数
        ParameterSet parameterSet = parameterSetDao.findParameterSet();
        Ml3MemberUnits ml3MemberUnits = null;
        User userById = null;
        // 查询用户
        if(ProjectConstants.THREE_SALE_SYS){
            userById = userDao.findAgent3UserById(curAuth.getUserId());
            //用户所属会员单位保证金低于某个阀值，限制交易
            Agent3User memberAgentUser = (Agent3User) userById;
            ml3MemberUnits =  ml3MemberUnitsDao.get(memberAgentUser.getUnitsId());
            Ml3Agent ml3Agent=ml3AgentDao.selectByAgentInviteCode(userById.getAgentInviteCode());
            if(ml3MemberUnits.getMoney().compareTo(parameterSet.getUnitsMoneyNotEnough()) == -1){
                return new ResponseErrorEntity(StatusCode.MEMBER_MONEY_NOE_ENOUGH,HttpStatus.BAD_REQUEST);
            }
            if(ml3Agent.getStatus()==1){
                return new ResponseErrorEntity(StatusCode.UNITS_IS_NOT_ONWORK,HttpStatus.BAD_REQUEST);
            }
        }else{
            userById = userDao.findUserById(curAuth.getUserId());
        }
        //判断用户状态是否禁止交易  0：正常 2：禁止交易
        if(userById.getStatus() == 2){
            return new ResponseErrorEntity(StatusCode.CAN_NOT_TRADE, HttpStatus.BAD_REQUEST);
        }

        //验证当前购买合约是否大于5秒
        if(!findHoldTimeByCode(makeTradeBean)){
            return new ResponseErrorEntity(StatusCode.HOLDTIME_INTERVAL, HttpStatus.BAD_REQUEST);
        }

        StringBuffer stringBuffer = null;
        if(tradeDao.getHoldCount(makeTradeBean.getCode(), AuthContext.getUserId()) >= parameterSet.getHoldOrdersCount()){
            stringBuffer = new StringBuffer();
            stringBuffer.append("每种商品限同时持仓");
            stringBuffer.append(parameterSet.getHoldOrdersCount());
            stringBuffer.append("单");
            return new ResponseErrorEntity(stringBuffer.toString(), HttpStatus.BAD_REQUEST);
        }
        //验证下单金额。排除必盈券，直盈券 //如果是直盈和必盈券，限制金额为0
        if(Constants.Coupon.TYPE_1.equals(makeTradeBean.getCouponType()) || Constants.Coupon.TYPE_2.equals(makeTradeBean.getCouponType())){
            if(makeTradeBean.getMoney().compareTo(new BigDecimal(0)) != 0){
                return new ResponseErrorEntity(StatusCode.INVALID_COUPON_RULE, HttpStatus.BAD_REQUEST);
            }
        }else{
            if((makeTradeBean.getMoney().compareTo(parameterSet.getOrdersMinMoney()) == -1)){
                stringBuffer = new StringBuffer();
                stringBuffer.append("最低建仓金额");
                stringBuffer.append(parameterSet.getOrdersMinMoney());
                stringBuffer.append("元");
                return new ResponseErrorEntity(stringBuffer.toString(), HttpStatus.BAD_REQUEST);
            }
        }
        //查询用户持仓单数,持仓金额，持仓单数和持仓金额不得大于ParameterSet表中的持仓单数，金额

        /**
         * 查询全部持有仓数金额相加,再加新持仓金额进行比较
         */
        List<Trade> holdList = tradeDao.findUserTradeCount(curAuth.getUserId());
        Double holdMoney = 0D;
        for(Trade trade : holdList){
            holdMoney += trade.getMoney().doubleValue();
        }
        holdMoney += makeTradeBean.getMoney().doubleValue();
        if(null != parameterSet) {
            if (parameterSet.getHoldMoney().compareTo(new BigDecimal(holdMoney)) == -1){ //1000  1100
                return new ResponseErrorEntity(StatusCode.HOLD_MONEY, HttpStatus.BAD_REQUEST);
            }

            if (holdList.size() >= parameterSet.getHoldCount()) {
                return new ResponseErrorEntity(StatusCode.HOLD_POSITIONS, HttpStatus.BAD_REQUEST);
            }
        }

//        // 校验交易密码
//        if(!makeTradeBean.getTradePassword().equals(userById.getTradePassword())){
//            return new ResponseErrorEntity(StatusCode.TRADE_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
//        }
        BigDecimal makeTradeFee = makeTradeBean.getMoney().multiply(new BigDecimal("0.02"));
        if(userById.getMoney().subtract(makeTradeBean.getMoney().add(makeTradeFee)).doubleValue() < 0){
            return new ResponseErrorEntity(StatusCode.MONEY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }

        // 获取当前最新行情
        MarketPointBean latest = marketService.latest(makeTradeBean.getCode());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        Date now = calendar.getTime();

        Trade trade = ProjectConstants.THREE_SALE_SYS ? new Agent3Trade() : new Trade();
        trade.setSerialNo(serialNo());
        trade.setModel(Constants.Trade.MODEL_TIME);
        trade.setUserId(curAuth.getUserId());
        // 交易类型 资金 资金+代金券
        if(StringUtils.isNotBlank(makeTradeBean.getCouponId())){
            trade.setType(Constants.Trade.TYPE_MONEY_AND_COUPON);
        }else {
            trade.setType(Constants.Trade.TYPE_MONEY);
        }
        trade.setBuyTime(now);

        trade.setBuyPoint(Utils.toBigDecimal(latest.getPrice()));
        trade.setBuyUpDown(makeTradeBean.getBuyUpDown());
        trade.setCode(contract.getCode());
        trade.setContractName(contract.getName());
        trade.setMoney(makeTradeBean.getMoney());

        // 买时间的交易
        trade.setOffTime(makeTradeBean.getOffTime());
        BigDecimal bigDecimal = new BigDecimal(trade.getOffTime().replaceAll("M",""));
        BigDecimal secondBigDecimal = bigDecimal.multiply(new BigDecimal(60));
        calendar.add(Calendar.SECOND, secondBigDecimal.intValue());
        trade.setSellTime(calendar.getTime());
        trade.setParent1Id(userById.getParent1Id());
        trade.setParent2Id(userById.getParent2Id());
        trade.setParent3Id(userById.getParent3Id());

        if(trade.getParent1Id() == null && trade.getParent2Id() == null && trade.getParent3Id() == null){
            trade.setBalanceStatus(Constants.Trade.BALANCE_OVER);
        }

        trade.preInsert();

        // 校验各种券
        if(makeTradeBean.getCouponNum() == null) {
            makeTradeBean.setCouponNum(0);
        }
        if(Constants.Coupon.TYPE_1.equals(makeTradeBean.getCouponType())
                || Constants.Coupon.TYPE_2.equals(makeTradeBean.getCouponType())
                || Constants.Coupon.TYPE_3.equals(makeTradeBean.getCouponType())){
            if (Constants.Coupon.TYPE_1.equals(makeTradeBean.getCouponType())) {
                if(!contract.getName().equals("白银") || !makeTradeBean.getOffTime().equals("1M")){
                    return new ResponseErrorEntity(StatusCode.COUPON_TYPE1_TYPE2_BAIYIN_1M, HttpStatus.BAD_REQUEST);
                }
            }
            if(Constants.Coupon.TYPE_1.equals(makeTradeBean.getCouponType())
                    || Constants.Coupon.TYPE_2.equals(makeTradeBean.getCouponType())){
                if(makeTradeBean.getCouponNum() > 1){
                    return new ResponseErrorEntity(StatusCode.INVALID_COUPON_RULE, HttpStatus.BAD_REQUEST);
                }
            }
            // 直盈券规则
            if (Constants.Coupon.TYPE_2.equals(makeTradeBean.getCouponType()) && makeTradeBean.getOffTime().equals("0.5M")) {
                List<Coupon> coupons = couponService.todayUsedType1();
                if (coupons.size() >= 10) {
                    return new ResponseErrorEntity(StatusCode.COUPON_TYPE2_EVERYDAY_MAX, HttpStatus.BAD_REQUEST);
                } /*else if(!coupons.isEmpty()) {
                    Calendar instance = Calendar.getInstance();
                    instance.add(Calendar.HOUR_OF_DAY, -1);
                    if (coupons.get(0).getUseTime().after(instance.getTime())) {
                        return new ResponseErrorEntity(StatusCode.COUPON_TYPE2_INTERVAL, HttpStatus.BAD_REQUEST);
                    }
                }*/
                trade.setCouponMoney(new BigDecimal(makeTradeBean.getCouponMoney()));
            }else{
                return new ResponseErrorEntity(StatusCode.INVALID_COUPON_RULE, HttpStatus.BAD_REQUEST);
            }
            // 增益券规则
            if(Constants.Coupon.TYPE_3.equals(makeTradeBean.getCouponType())
                    && makeTradeBean.getCouponNum()*makeTradeBean.getCouponMoney() > makeTradeBean.getMoney().doubleValue()/10){
                return new ResponseErrorEntity(StatusCode.INVALID_COUPON_RULE, HttpStatus.BAD_REQUEST);
            }else if(Constants.Coupon.TYPE_3.equals(makeTradeBean.getCouponType())
                    && makeTradeBean.getCouponNum()*makeTradeBean.getCouponMoney() <= makeTradeBean.getMoney().doubleValue()/10){
                trade.setCouponMoney(new BigDecimal(makeTradeBean.getCouponMoney()*makeTradeBean.getCouponNum()));
            }
            if (!couponService.use(userById.getId(),  makeTradeBean.getCouponMoney(),makeTradeBean.getCouponType(),makeTradeBean.getCouponNum(), trade.getId())) {
                return new ResponseErrorEntity(StatusCode.COUPON_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
            }
        }else{
            trade.setCouponMoney(new BigDecimal(0));
        }

        // 设置盈利金额和手续费
        BigDecimal percentProfit = Utils.toBigDecimal(contract.percentProfit(makeTradeBean.getOffTime())).divide(new BigDecimal(100));
        trade.setWinMoney(trade.getMoney().add(trade.getCouponMoney()).multiply(percentProfit));
        trade.setFee(makeTradeFee);
        trade.setCouponType(makeTradeBean.getCouponType());

        if(ProjectConstants.THREE_SALE_SYS && !Constants.Trade.TYPE_COUPON_MONEY.equals(makeTradeBean.getType())){
            Agent3User agent3User = (Agent3User) userById;
            //查询返运营中心手续费
            Ml3OperateCenterBean ml3OperateCenterBean = ml3OperateCenterService.get(agent3User.getCenterId());
            BigDecimal returnFeeByOperate = ml3OperateCenterBean.getReturnFeePercent();
            //交易所返还运营中心手续费
            trade.setReturnOperateFee(makeTradeFee.multiply(returnFeeByOperate.divide(new BigDecimal(100))));
            //运营中心返会员单位手续费
            BigDecimal returnFee = makeTradeFee.multiply(ml3MemberUnits.getReturnFeePercent().divide(new BigDecimal(100))).multiply(returnFeeByOperate.divide(new BigDecimal(100)));
            trade.setReturnFee(returnFee);

            Agent3Trade agent3Trade = (Agent3Trade) trade;
            agent3Trade.setAgentId(agent3User.getAgentId());
            agent3Trade.setUnitsId(agent3User.getUnitsId());
            agent3Trade.setCenterId(agent3User.getCenterId());
            if(tradeDao.make3(agent3Trade) != 1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
            }
        }else {
            if(tradeDao.make(trade) != 1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
            }
        }

        //添加交易总量
        userDao.updateUserTradeCount(curAuth.getUserId());

        BigDecimal difMoney = trade.getMoney().add(makeTradeFee).multiply(new BigDecimal(-1));

        if(Constants.Trade.TYPE_COUPON_MONEY.equals(trade.getType())){
            if(!moneyService.changeCouponMoney(curAuth.getUserId(), difMoney, Constants.UserMessage.TITLE_TRADE,
                    Utils.formatStr("建仓代金券变更{0}元，剩余{1}元，下单流水号：{2}", difMoney.toString(), userById.getCouponMoney().add(difMoney).toString(), trade.getSerialNo()))){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
            }
        }else if(Constants.Trade.TYPE_MONEY.equals(trade.getType()) || Constants.Trade.TYPE_MONEY_AND_COUPON.equals(trade.getType())){
            // 满多少钱代金券抵扣
            CashCoupon cashCoupon = null;
            if(StringUtils.isNotBlank(makeTradeBean.getCouponId())){
                cashCoupon = cashCouponDao.findCashCouponById(makeTradeBean.getCouponId());
                if(!Constants.Coupon.STATUS_DEFAULT.equals(cashCoupon.getStatus()) || !cashCoupon.getUserId().equals(curAuth.getUserId())){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new ResponseErrorEntity(StatusCode.COUPON_INVALID, HttpStatus.BAD_REQUEST);
                }
                cashCoupon.setRemark(trade.getSerialNo());
                cashCouponDao.useCashCoupon(cashCoupon);
                difMoney = difMoney.add(cashCoupon.getMoney());
            }

            // cashCoupon == null ? Utils.formatStr("建仓资金变更{0}，下单流水号：{1}", difMoney.toString(), trade.getSerialNo()) : Utils.formatStr("建仓资金变更{0}，下单流水号：{1}，使用代金券{2}元", difMoney.toString(), trade.getSerialNo(), cashCoupon.getMoney().toString());
            String remark = trade.getSerialNo();
            if(!moneyService.changeMoney(curAuth.getUserId(), difMoney, userById.getMoney(), Constants.Money.MONEY_CHANGE_TYPE_TRADE_MAKE, remark)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
            }
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
        }

        if(!ProjectConstants.PRO_DEBUG){
            //访问下高主管的接口
            try{
                //mrGaoApi.mrGaoReq(makeTradeBean.getBuyUpDown(),makeTradeBean.getMoney().toString(),makeTradeBean.getCode(),trade.getBuyPoint().toString(),trade.getSerialNo(),trade.getUserId(),trade.getOffTime()).execute();
                Call<String> call = mrGaoApi.mrGaoReq(makeTradeBean.getBuyUpDown(),makeTradeBean.getMoney().toString(),makeTradeBean.getCode(),trade.getBuyPoint().toString(),trade.getSerialNo(),trade.getUserId(),trade.getOffTime(),String.valueOf(trade.getBuyTime().getTime()));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {

                        } else {
                            Log.error("mrGaoService response is error!");
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Log.error("onFailure is error!");
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * TODO 判断当前持仓单的建仓时间是否大于5秒
     * @param makeTradeBean
     * @return boolean
     */
    public boolean findHoldTimeByCode(MakeTradeBean makeTradeBean){
        Trade trade = new Trade();
        trade.setCode(makeTradeBean.getCode());
        trade.setUserId(AuthContext.getUserId());
        Integer  holdTime = tradeDao.findHoldTimeByCode(trade);
        if(null == holdTime || holdTime > 5){
            return true;
        }
        return false;
    }

//    /**
//     * 手动平仓
//     * @param sellBean
//     * @return
//     */
//    @Transactional
//    @Deprecated
//    public ResponseEntity sell(SellBean sellBean) {
//        if(!isMarketOpen()){
//            return new ResponseErrorEntity(StatusCode.MARKET_CLOSE, HttpStatus.BAD_REQUEST);
//        }
//
//        AuthEntity curAuth = AuthContext.getCurAuth();
//        User userById = userDao.findUserById(curAuth.getUserId());
//        // 校验交易密码
//        if(!sellBean.getTradePassword().equals(userById.getTradePassword())){
//            return new ResponseErrorEntity(StatusCode.TRADE_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
//        }
//        UserTradeBean trade = tradeDao.findTradeById(sellBean.getId());
//        trade.setUserMoney(Constants.Trade.TYPE_COUPON_MONEY.equals(trade.getType()) ? userById.getCouponMoney() : userById.getMoney());
//        // 获取当前最新行情
//        MarketPointBean latest = marketService.latest(trade.getCode());
//
//        if(!doSell(trade, latest, Constants.Trade.SellType.HAND)){
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return new ResponseErrorEntity(StatusCode.MAKE_TRADE_ERROR, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity(trade, HttpStatus.OK);
//    }

//    /**
//     * 最新行情变更时，触发调用此方法
//     * 加锁
//     * 止盈止损平仓
//     * @param latest
//     */
//    public synchronized void checkToSell(MarketPointBean... latest){
//        for (MarketPointBean marketPointBean : latest){
//            List<UserTradeBean> trades = tradeDao.findStopProfixOrLoss(marketPointBean);
//            if(!trades.isEmpty()){
//                Log.info("发现".concat(trades.size()+"").concat("笔持仓数据需要止盈止损平仓"));
//                for (UserTradeBean trade : trades){
//                    // 自动平仓 TODO 1秒内波动变化很大，如何处理
//                    if(!doSell(trade, marketPointBean, Constants.Trade.SellType.STOP)){
//                        Log.error(trade.getSerialNo().concat("交易止盈止损平仓失败"));
//                    }
//                }
//            }
//        }
//    }

    // 线程池
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor = null;
    /**
     * 定时平仓
     */
   /*@Scheduled(initialDelay = 10000, fixedRate = 100)*/
    public void checkToSell(){
        long currentTimeMillis = System.currentTimeMillis();
        long currentMillis = currentTimeMillis%1000;
        // 毫秒数 = 0xx
        if(currentMillis/100 != 0){
            return;
        }else{
            threadPoolTaskExecutor.execute(() -> toSell(currentTimeMillis, currentMillis));
        }
    }

    /**
     * 定时平仓
     */
    public void toSell(long currentTimeMillis, long currentMillis){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MILLISECOND, 0);
        Date now = instance.getTime();

        List<MarketPointBean> latest = marketService.latest(_codes);
        Map<String, MarketPointBean> map = new HashMap<>();
        latest.forEach(point -> {
            if(point != null){
                if(point.getTime() == null || point.getTime().getTime() - now.getTime() > 60*1000){
                    if(point.getTime() != null){
                        Log.error("定时平仓 > ".concat(point.getCode().concat("时间差：").concat((point.getTime().getTime() - now.getTime())/1000l+"")));
                    }
                }
                point.setTime(null);
                map.put(point.getCode(), point);
            }
        });

        List<UserTradeBean> trades = tradeDao.findOffTimeTrade(now);

        StringBuilder sb = new StringBuilder();
        trades.forEach(trade -> {
            MarketPointBean latestPoint = map.get(trade.getCode());
            if(!doSell(trade, latestPoint, Constants.Trade.SellType.STOP)){
                Log.error(trade.getSerialNo().concat("系统自动平仓失败"));
            }
        });

        if(!trades.isEmpty()){
            sb.append("time:".concat(String.format("%1$tH:%1$tM:%1$tS", new Date(currentTimeMillis))).concat("."+(currentMillis)));
            sb.append(",[");
            map.forEach((code, point) -> {
                sb.append(point.getCode()).append(",");
                sb.append(point.getPrice()).append(",");
                sb.append(String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", new Date(point.getTimestamp()))).append(";");
            });
            sb.append("]");
            sb.append("，共".concat(trades.size()+"").concat("笔，耗时：".concat(System.currentTimeMillis() - currentTimeMillis+"ms")));
            Log.info(sb.toString());
        }

    }
    private String doTradeResult(Trade trade){
        return doTradeResult(trade, false);
    }

    /**
     * 得到盈亏平的结果
     * @param trade
     * @param force
     * @return
     */
    private String doTradeResult(Trade trade, boolean force){
        if(force || (trade != null && trade.getResult() == null)){
            if(trade.getBuyPoint().doubleValue() == trade.getSellPoint().doubleValue()){
                // 平
                trade.setResult(Constants.Trade.RESULT_DRAW);
            }else {
                if(Constants.Trade.BUY_UP.equals(trade.getBuyUpDown())){
                    if(trade.getBuyPoint().doubleValue() < trade.getSellPoint().doubleValue()){
                        // 赢
                        trade.setResult(Constants.Trade.RESULT_WIN);
                    }else if(trade.getBuyPoint().doubleValue() > trade.getSellPoint().doubleValue()){
                        // 亏
                        trade.setResult(Constants.Trade.RESULT_LOSE);
                    }
                }else if(Constants.Trade.BUY_DOWN.equals(trade.getBuyUpDown())){
                    if(trade.getBuyPoint().doubleValue() > trade.getSellPoint().doubleValue()){
                        // 赢
                        trade.setResult(Constants.Trade.RESULT_WIN);
                    }else if(trade.getBuyPoint().doubleValue() < trade.getSellPoint().doubleValue()){
                        // 亏
                        trade.setResult(Constants.Trade.RESULT_LOSE);
                    }
                }
            }
            return trade.getResult();
        }
        return trade == null ? null : trade.getResult();
    }

    /**
     * 平仓 手动 止盈止损 结算平仓
     *
     * @param trade
     * @param latest
     * @return
     */
    private boolean doSell(UserTradeBean trade, MarketPointBean latest, Constants.Trade.SellType sellType){
        preSell(trade, latest, sellType);
        User userById=null;
        userById = userDao.findAgent3UserById(trade.getUserId());
        Agent3User memberAgentUser = (Agent3User) userById;
        BigDecimal unitsPercent = ml3MemberUnitsDao.getUnitsPercent(memberAgentUser.getUnitsId());
        BigDecimal operateCenterPercent = ml3OperateCenterDao.getOperateCenterPercent(memberAgentUser.getCenterId());
        trade.setDifMoneyUnits(trade.getDifMoney().multiply(unitsPercent.divide(new BigDecimal(100))));
        trade.setDifMoneyOperator(trade.getDifMoney().multiply(operateCenterPercent.divide(new BigDecimal(100))));
        if(tradeDao.sell(trade) != 1){
            return false;
        }
        // 本金+盈亏金额
        BigDecimal difMoney;
        if(Constants.Trade.RESULT_DRAW.equals(trade.getResult())){
            difMoney = trade.getMoney().add(trade.getFee());
        }else{
            difMoney = trade.getMoney().add(trade.getDifMoney());
        }
        if(Constants.Trade.TYPE_COUPON_MONEY.equals(trade.getType())){
            if(!moneyService.changeCouponMoney(trade.getUserId(), difMoney, Constants.UserMessage.TITLE_TRADE,
                    Utils.formatStr("平仓代金券变更{0}，剩余{1}元，下单流水号：{2}", difMoney.toString(), trade.getUserMoney().add(difMoney).toString(), trade.getSerialNo()))){
                return false;
            }
        }else if(Constants.Trade.TYPE_MONEY.equals(trade.getType()) || Constants.Trade.TYPE_MONEY_AND_COUPON.equals(trade.getType())){
            // Utils.formatStr("平仓资金变更{0}，下单流水号：{1}", difMoney.toString(), trade.getSerialNo())
            if(!moneyService.changeMoney(trade.getUserId(), difMoney, trade.getUserMoney(), Constants.Money.MONEY_CHANGE_TYPE_TRADE_SELL,
                    trade.getSerialNo())){
                return false;
            }
        }else {
            return false;
        }

        if(Constants.Trade.SellType.TIME_STOP == sellType){
            // 写入消息
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(trade.getUserId());
            userMessage.setTitle(Constants.UserMessage.TITLE_TRADE);
            userMessage.setContent(Utils.formatStr("系统休市自动平仓，交易流水：{0}", trade.getSerialNo()));
            userMessage.preInsert();
            if(userMessageDao.record(userMessage) != 1){
                return false;
            }
        }

        // 返回直盈券
        if(!Constants.Trade.RESULT_WIN.equals(trade.getResult()) && Constants.Coupon.TYPE_1.equals(trade.getCouponType())){
            synchronized (this){
                restoreCouponTradeIds.add(trade.getId());
            }
        }

        return true;
    }

    /**
     * 盈利算法
     * point或者point.price为空，则全额返钱
     * @param trade
     * @param point
     */
    private void preSell(Trade trade, MarketPointBean point, Constants.Trade.SellType sellType){
        if(point == null || StringUtils.isBlank(point.getPrice())){
            trade.setSellPoint(new BigDecimal(0));
            trade.setSellTime(new Date());
            trade.setSellType(sellType.val());
            trade.setDifMoney(new BigDecimal(0));

            return ;
        }

        BigDecimal price = Utils.toBigDecimal(point.getPrice());
//        // 止盈止损按止盈止损点位计算，不按当前时间价格
//        if(Constants.Trade.SellType.STOP == sellType){
//            if(Math.abs(trade.getProfitMaxPoint().subtract(price).doubleValue())
//                    < Math.abs(trade.getLossMaxPoint().subtract(price).doubleValue())){
//                price = trade.getProfitMaxPoint();
//            }else {
//                price = trade.getLossMaxPoint();
//            }
//        }
        trade.setSellPoint(price);
        trade.setSellTime(point.getTime());
        if(point.getTime() == null){
            Log.warn("平仓行情点 ".concat(JSON.toJSONString(point)));
        }
        //平仓类型 0 手动平仓 1 止盈止损平仓 2 休市平仓 3 异常平仓
        trade.setSellType(sellType.val());

        // 买时间的 计算盈亏
        doTradeResult(trade, true);
        if(Constants.Trade.RESULT_WIN.equals(trade.getResult())){
            trade.setDifMoney(trade.getWinMoney());
        }else if(Constants.Trade.RESULT_LOSE.equals(trade.getResult())){
            trade.setDifMoney(trade.getMoney().multiply(new BigDecimal(-1)));
        }else {
            trade.setDifMoney(new BigDecimal(0));
        }

//        // 盈亏=点值*波动值-手续费
//        // 买涨 波动值=平仓价-建仓价
//        // 买跌 波动值=建仓价-平仓价
//        if(Constants.Trade.BUY_UP.equals(trade.getBuyUpDown())){
//            trade.setDifMoney(trade.getPointValue().multiply((trade.getSellPoint().subtract(trade.getBuyPoint()))).subtract(trade.getFee()));
//        }else if(Constants.Trade.BUY_DOWN.equals(trade.getBuyUpDown())){
//            trade.setDifMoney(trade.getPointValue().multiply((trade.getBuyPoint().subtract(trade.getSellPoint()))).subtract(trade.getFee()));
//        }
    }

    private int restoreCouponTradeIdsMaxSize = 1024;
    private List<String> restoreCouponTradeIds = new ArrayList<>(restoreCouponTradeIdsMaxSize);

    /**
     * 定时返回直盈券
     */
    @Scheduled(initialDelay = 10000, fixedDelay = 3000)
    public void restoreCoupon(){
        if(!restoreCouponTradeIds.isEmpty()){
            couponService.restore(Constants.Coupon.TYPE_1, restoreCouponTradeIds.subList(0, restoreCouponTradeIds.size()));
            synchronized (this){
                if(restoreCouponTradeIds.size() > restoreCouponTradeIdsMaxSize){
                    Log.error("返还直盈券数量超" + restoreCouponTradeIdsMaxSize);
                    restoreCouponTradeIds = new ArrayList<>(restoreCouponTradeIdsMaxSize);
                }else{
                    restoreCouponTradeIds.clear();
                }
            }
        }
    }

    public boolean isMarketOpen(String[] times){
        return isMarketOpen(times, false);
    }
    public boolean isMarketOpen(String[] times,boolean isExemptClosed){
        if(times == null || times.length != 2 || times[0] == null || times[1] == null){
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        String[] beginStr = times[0].split(":");
        String[] endStr = times[1].split(":");

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(beginStr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(beginStr[1]));

        Date begin = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endStr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(endStr[1]));
        Date end = calendar.getTime();

        // 结束时间小于开始时间，则加一天
        if(end.before(begin)){
            if(now.before(begin)){
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                begin = calendar.getTime();
            }else{
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                end = calendar.getTime();
            }
        }

        //判断开始时间是否是周末
        calendar.setTime(begin);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        // 周末休市
        // 周末休市
        if(!isExemptClosed){
            if(week == 7 || week == 1){
                return false;
            }
        }

        return !now.before(begin) && !now.after(end);
    }

    /**
     * 系统默认 开市时间
     * @return
     */
    public boolean isMarketOpen(){
        return true;
    }

    /**
     * 周末统一休市
     * @return
     */
    public String[] marketOpenTime(){
        // TODO 开盘时间
        return new String[]{"08:00", "04:00"};
    }

    private final Random random = new Random();

    /**
     * 获取交易流水号
     * @return
     */
    private String serialNo(){
        return "JY" + new Date().getTime()+""+String.format("%1$03d",random.nextInt(1000));
    }

    /**
     * TODO 获取最新交易或交易记录订单
     * @param flag
     * @return
     */
    public Map<String, Object> getTradingRecord(String flag){
        Map<String, Object> result = new HashedMap();
        if("1".equals(flag)) {
            List<Trade> toDayTradeList = toDayTradeList();
            result.put("list", toDayTradeList);
        }else if("2".equals(flag)){
            List<Integer> timeList = new ArrayList<Integer>();
            long time = new Date().getTime();
            List<Trade> list = tradeList();
            result.put("list", list);
            result.put("now", new Date().getTime());

            for (Trade trade : list){
                int aa = (int) (trade.getSellTime().getTime() - time) / 1000;
                timeList.add(aa);
            }
            result.put("timeList", timeList);
        }else{
            //取得最新交易10条记录
            List<Trade> newTradeRecord = getNewTradeRecord();
            result.put("list", newTradeRecord);
        }
        return result;
    }

    public Map<String, Object> getHoldId(String id){
        Map<String,Object> result = new HashedMap();
        Trade trade = tradeDao.getHoldId(id);
        if(null == trade){
            try {
                Thread.currentThread().sleep(1000);
//                getHoldId(id);
                result.put("trade", "");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            result.put("trade", trade);
        }
        return result;
    }

    private List<Trade> lastsTrades;

    private int[] bigMoney = new int[]{2000, 2600, 3000, 3500, 4000, 4800, 5000};

    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void createLasts(){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, -1);
        instance.set(Calendar.MILLISECOND, 0);

        List<Trade> list = tradeDao.lasts10(instance.getTime());

        if(list.isEmpty()){
            List<Contract> contracts = openContracts;
            int a = contracts.size();
            if(a > 0){
                for (int i = 0 ; i < random.nextInt(a*2); i++){
                    Contract contract = contracts.get(random.nextInt(a));

                    Trade trade = new Trade();
                    trade.setContractName(contract.getName());
                    trade.setBuyUpDown(random.nextInt(2) == 0 ? Constants.Trade.BUY_UP : Constants.Trade.BUY_DOWN);
                    trade.setBuyTime(instance.getTime());
                    int r = random.nextInt(30);
                    trade.setMoney(new BigDecimal(r >= 26 ? bigMoney[random.nextInt(bigMoney.length)] : (random.nextInt(100)+10)*10));

                    list.add(trade);
                }
            }
        }

        lastsTrades = list;
    }


    public List<Trade> lasts() {
        return lastsTrades;
    }
}
