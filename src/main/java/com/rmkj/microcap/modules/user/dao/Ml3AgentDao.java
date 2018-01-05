package com.rmkj.microcap.modules.user.dao;


import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.user.entity.Ml3Agent;

import java.util.List;

@DataSource
public interface Ml3AgentDao {
    int deleteByPrimaryKey(String id);

    int insert(Ml3Agent record);

    int insertSelective(Ml3Agent record);

    Ml3Agent selectByPrimaryKey(String id);

    Ml3Agent selectByAgentInviteCode(String agentInviteCode);

    int updateByPrimaryKeySelective(Ml3Agent record);

    int updateByPrimaryKey(Ml3Agent record);

    /**
     * TODO 根据验证码查询代理人
     * @param code
     * @return
     */
    Ml3Agent findMl3AgentSelective(String code);

    /**
     * TODO 选择性查询代理数据
     * @param ml3Agent
     * @return
     */
    Ml3Agent findMl3AgentSelectived(Ml3Agent ml3Agent);
}