package com.rmkj.microcap.modules.index.service;

import com.rmkj.microcap.modules.index.dao.BroadcastDao;
import com.rmkj.microcap.modules.index.entity.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2016/10/18.
 */
@Service
public class BroadcastService {

    @Autowired
    private BroadcastDao broadcastDao;

    public List<Broadcast> findList() {
        return broadcastDao.findList();
    }

    public Broadcast findById(String id) {
        return broadcastDao.findById(id);
    }
}
