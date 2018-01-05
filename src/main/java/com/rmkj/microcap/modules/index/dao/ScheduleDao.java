package com.rmkj.microcap.modules.index.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.user.entity.Ml3MemberUnits;
import com.rmkj.microcap.modules.weChatPublic.entity.WechatMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/1/10.
 */
@DataSource
public interface ScheduleDao {
    List<Ml3MemberUnits> queryUnits();

    int overtimeMoneyIn(Date time);

    Map<String, Object> queryParameterSet();

    List<WechatMessage> queryWechatMessage();
}
