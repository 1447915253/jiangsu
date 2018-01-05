package com.rmkj.microcap.modules.trade.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.trade.entity.Ml3MemberUnitsMoneyRecord;

/**
 * Created by Administrator on 2017/5/16.
 */
@DataSource
public interface Ml3MemberUnitsMoneyRecordDao {
    /**
     * 查询会员单位累计出金金额
     * @param unitsId
     * @return
     */
    Ml3MemberUnitsMoneyRecord findUnitsMoneyRecord(String unitsId);
}
