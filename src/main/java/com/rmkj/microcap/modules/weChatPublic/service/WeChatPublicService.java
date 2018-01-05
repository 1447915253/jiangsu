package com.rmkj.microcap.modules.weChatPublic.service;

import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2017/2/11.
 */
@Service
public class WeChatPublicService {

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    /**
     *
     * @param weChatPublic
     */
    public void save(WeChatPublic weChatPublic) {
        if(StringUtils.isBlank(weChatPublic.getId())){
            weChatPublic.setId(Utils.uuid());
            weChatPublicDao.insert(weChatPublic);
        }else{
            weChatPublicDao.update(weChatPublic);
        }
    }

    public List<WeChatPublic> list(WeChatPublic weChatPublic) {
        return weChatPublicDao.list(weChatPublic);
    }

    public WeChatPublic findById(String id) {
        return weChatPublicDao.findById(id);
    }

    public int del(String id) {
        return weChatPublicDao.del(id);
    }
}
