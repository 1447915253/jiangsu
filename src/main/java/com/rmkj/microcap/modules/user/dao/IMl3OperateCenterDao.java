package com.rmkj.microcap.modules.user.dao;

import com.rmkj.microcap.common.base.BaseDao;
import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.user.entity.Ml3OperateCenterBean;

import java.math.BigDecimal;
import java.util.List;

/**
* Created by Administrator on 2016-11-17.
*/
@DataSource
public interface IMl3OperateCenterDao extends BaseDao<Ml3OperateCenterBean>{

    /**
     * 选择性查询
     */
    Ml3OperateCenterBean get(String id);

    BigDecimal getOperateCenterPercent(String centerId);
}
