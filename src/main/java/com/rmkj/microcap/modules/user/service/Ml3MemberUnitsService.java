package com.rmkj.microcap.modules.user.service;/**
 * Created by Administrator on 2016/12/16.
 */

import com.rmkj.microcap.common.interceptor.ContextInterceptor;
import com.rmkj.microcap.modules.user.bean.RegBean;
import com.rmkj.microcap.modules.user.dao.Ml3MemberUnitsDao;
import com.rmkj.microcap.modules.user.entity.Ml3MemberUnits;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author k
 * @create -12-16-18:20
 **/
@Service
public class Ml3MemberUnitsService {
    private static Logger logger = Logger.getLogger(ContextInterceptor.class);

    @Autowired
    private Ml3MemberUnitsDao ml3MemberUnitsDao;

    /**
     * TODO 根据经纪人邀请码查询会员单位
     * @param regBean
     * @return
     */
    public Ml3MemberUnits findMl3MemberUnits(RegBean regBean){
        Ml3MemberUnits ml3MemberUnits = new Ml3MemberUnits();
        ml3MemberUnits.setAgentInviteCode(regBean.getAgentInviteCode());
        return ml3MemberUnitsDao.findMl3MemberUnits(ml3MemberUnits);
    }
}
