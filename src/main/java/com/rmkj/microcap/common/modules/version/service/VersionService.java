package com.rmkj.microcap.common.modules.version.service;

import com.rmkj.microcap.common.modules.version.bean.VersionBean;
import com.rmkj.microcap.common.modules.version.dao.VersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhangbowen on 2016/1/12.
 */
@Service
public class VersionService {
    @Autowired
    private VersionDao versionDao;

    /**
     * 最新版本
     *
     * @return
     */
    public VersionBean lastVersion() {
        return versionDao.lastVersion();
    }
}
