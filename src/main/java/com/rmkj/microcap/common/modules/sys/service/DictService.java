package com.rmkj.microcap.common.modules.sys.service;

import com.rmkj.microcap.common.modules.sys.dao.DictDao;
import com.rmkj.microcap.common.modules.sys.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangbowen on 2015/12/22.
 */
@Service
public class DictService {
    @Autowired
    private DictDao dictDao;

    /**
     * 查询字典
     *
     * @param d
     * @return
     */
    public List<Dict> findList(Dict d) {
        return dictDao.findList(d);
    }
}
