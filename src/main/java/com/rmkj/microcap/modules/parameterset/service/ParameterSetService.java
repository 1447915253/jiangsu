package com.rmkj.microcap.modules.parameterset.service;


import com.rmkj.microcap.modules.parameterset.dao.IParameterSetDao;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Object;
import java.util.List;


/**
* Created by Administrator on 2016-12-16.
*/
@Service
@Transactional
public class ParameterSetService   {
    @Autowired
    private IParameterSetDao parameterSetDao;


    public List<ParameterSetBean> queryList(ParameterSetBean parameterSetBean){
        return parameterSetDao.queryList(parameterSetBean);
    }

}
