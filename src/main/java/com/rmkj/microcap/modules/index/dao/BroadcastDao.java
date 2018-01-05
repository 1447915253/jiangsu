package com.rmkj.microcap.modules.index.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.index.entity.Broadcast;

import java.util.List;

/**
 * Created by 123 on 2016/10/18.
 */
@DataSource
public interface BroadcastDao {
    List<Broadcast> findList();

    Broadcast findById(String id);
}
