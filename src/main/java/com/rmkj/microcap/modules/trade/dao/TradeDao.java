package com.rmkj.microcap.modules.trade.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.trade.bean.TradeHistoryBean;
import com.rmkj.microcap.modules.trade.bean.UserTradeBean;
import com.rmkj.microcap.modules.trade.entity.Agent3Trade;
import com.rmkj.microcap.modules.trade.entity.Contract;
import com.rmkj.microcap.modules.trade.entity.Trade;

import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2016/10/19.
 */
@DataSource
public interface TradeDao {
    Contract findContractByCode(String code);

    Contract findContractById(String code);

    int make(Trade trade);

    int make3(Agent3Trade agent3Trade);

    int sell(Trade trade);

    UserTradeBean findTradeById(String id);

    List<Trade> tradeListByUserId(String userId);

    List<Trade> tradeHistory(TradeHistoryBean tradeHistoryBean);
    /**
     * 查询今天的结算数据
     * @param userId
     * @return
     */
    List<Trade> toDayTradeListByUserId(String userId);

    List<Contract> contractList();

    /**
     * 查询需要止盈止损平仓的数据
     * @param latest
     * @return
     */
    List<UserTradeBean> findStopProfixOrLoss(MarketPointBean latest);

    List<UserTradeBean> findNoSellForSettlement();

    List<UserTradeBean> findOffTimeTrade(Date now);

    List<UserTradeBean> findNotOnTime(Date date);

    /**
     * TODO 查询用户持仓数量
     * @param userId
     * @return
     */
    List<Trade> findUserTradeCount(String userId);

    /**
     * 查询最新交易记录
     */
    List<Trade> getNewTradeRecord();

    /**
     * TODO 根据id查询订单
     * @param id
     * @return
     */
    Trade getHoldId(String id);

    /**
     * TODO 根据code查询商品持仓单数
     * @param code
     * @return
     */
    int getHoldCount(String code,String userId);

    /**
     * TODO 根据code查询当前用户持仓单的时间
     * @param trade
     * @return
     */
    Integer findHoldTimeByCode(Trade trade);

    List<Trade> lasts10(Date time);

    /**
     * 分组查询会员单位交易统计总盈亏
     * @return
     */
    List<Trade> findUnitsMoneySumList();
    /**
     * 根据会员单位id查询会员单位下用户交易金额总量
     * @return
     */
    Trade findTradeSumGroupByUnitsId(Trade trade);
}
