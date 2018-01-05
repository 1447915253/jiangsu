package com.rmkj.microcap.modules.index.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import java.util.List;

@DataSource
public interface ParameterSetDao {
    int deleteByPrimaryKey(String id);

    int insert(ParameterSet record);

    int insertSelective(ParameterSet record);

    ParameterSet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ParameterSet record);

    int updateByPrimaryKey(ParameterSet record);

    /**
     * TODO 查询参数设置表全部数据
     * @return
     */
    ParameterSet findParameterSet();
}