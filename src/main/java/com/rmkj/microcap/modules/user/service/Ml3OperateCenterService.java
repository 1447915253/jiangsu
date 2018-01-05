package com.rmkj.microcap.modules.user.service;

import com.rmkj.microcap.modules.user.dao.IMl3OperateCenterDao;
import com.rmkj.microcap.modules.user.entity.Ml3OperateCenterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* Created by Administrator on 2016-11-17.
*/
@Service
@Transactional
public class Ml3OperateCenterService{
    @Autowired
    private IMl3OperateCenterDao ml3OperateCenterDao;

    public Ml3OperateCenterBean get(String id){
        return ml3OperateCenterDao.get(id);
    }
}
