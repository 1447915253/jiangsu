package com.rmkj.microcap.modules.user.dao;


import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.user.entity.Ml3MemberUnits;

import java.math.BigDecimal;

@DataSource
public interface Ml3MemberUnitsDao {
    int insert(Ml3MemberUnits record);

    int insertSelective(Ml3MemberUnits record);

    /**
     * TODO 根据邀请码查询会员单位
     * @param ml3MemberUnits
     * @return
     */
    Ml3MemberUnits findMl3MemberUnits(Ml3MemberUnits ml3MemberUnits);

    /**
     * 根据id查询会员单位信息
     * @param id
     * @return
     */
    Ml3MemberUnits get(String id);

    /**
     * 计算会员单位保证金余额 累计保证金-总盈亏
     * @param trade
     * @return
     */
    int updateUnitsMoneyLossAndProfitById(Trade trade);

    int updateUnitsHistoryMoneyLossAndProfitById(Trade trade);

    BigDecimal getUnitsPercent(String unitsId);
}