package com.rmkj.microcap.common.modules.version.dao;


import com.rmkj.microcap.common.base.BaseDao;
import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.common.modules.version.bean.VersionBean;

/**
 * Created by zhangbowen on 2016/1/12.
 */
@DataSource
public interface VersionDao extends BaseDao<VersionBean> {
    /**
     * 获得最新版本
     *
     * @return
     */
    VersionBean lastVersion();
}
