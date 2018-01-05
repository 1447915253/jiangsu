package com.rmkj.microcap.modules.trade.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.trade.entity.Ml3MemberUnitsMoneyChange;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
@DataSource
public interface Ml3MemberUnitsMoneyChangeDao {

    int insert(Ml3MemberUnitsMoneyChange ml3MemberUnitsMoneyChange);

    /**
     * 分组查询会员单位保证金最后一次浮动
     * @return
     */
    List<Ml3MemberUnitsMoneyChange> findEndTimeMaxGroupByUnitsId();

    /**
     * 根据会员单位id查询最后一次浮动记录
     * @return
     */
    Ml3MemberUnitsMoneyChange findUnitsMoneyRecordByunitsId(String unitsId);
}
