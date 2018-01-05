package com.rmkj.microcap.modules.user.dao;


import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.user.entity.Ml3AgentRole;

@DataSource
public interface Ml3AgentRoleDao {
    int deleteByPrimaryKey(Ml3AgentRole key);

    int insert(Ml3AgentRole record);

    int insertSelective(Ml3AgentRole record);
}