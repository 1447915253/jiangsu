package com.rmkj.microcap.modules.corps.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.corps.bean.CorpsMoneyWithTime;
import com.rmkj.microcap.modules.corps.bean.Percent3;
import com.rmkj.microcap.modules.corps.bean.UserCorps;
import com.rmkj.microcap.modules.user.entity.User;

import java.util.List;

/**
 * Created by 123 on 2016/11/22.
 */
@DataSource
public interface CorpsDao {

    /**
     * 查询用户军团信息
     * @param userId
     * @return
     */
    UserCorps queryUserCorpsCount(String userId);

    /**
     * 查询用户军团返佣
     * @param userId
     * @return
     */
    UserCorps queryUserCorpsMoney(String userId);

    /**
     * 军团返佣时间范围查询
     * @param corpsMoneyWithTime
     * @return
     */
    UserCorps queryUserCorpsMoneyWithTime(CorpsMoneyWithTime corpsMoneyWithTime);

    void settlement(Percent3 percent3);

    List<User> settlementTotalMoney();

    /**
     * 兵团人员详情
     * @param user
     * @return
     */
    List<User> queryUserCorpsDetail(User user);

    int addReturnMoneyTotal(User user);

    int settlementEnd();
}
