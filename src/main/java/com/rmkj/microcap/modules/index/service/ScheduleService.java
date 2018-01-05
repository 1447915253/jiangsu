package com.rmkj.microcap.modules.index.service;

import com.rmkj.microcap.modules.coupon.dao.CouponDao;
import com.rmkj.microcap.modules.index.dao.ScheduleDao;
import com.rmkj.microcap.modules.user.entity.Ml3MemberUnits;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import com.rmkj.microcap.modules.weChatPublic.entity.WechatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 123 on 2017/1/10.
 * 缓存 周期更新类
 */
@Service
@Lazy(false)
public class ScheduleService {

    private volatile List<Ml3MemberUnits> list = null;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    private Map<String, Object> parameterSetMap = null;

    private Map<String, WeChatPublic> weChatPublicMap = new HashMap<>();

    private Map<String, WechatMessage> wechatMessageMap = new HashMap<>();

    /**
     * 获取系统设置参数
     * @param key
     * @return
     */
    public Object getParameterSet(String key){
        if(parameterSetMap == null){
            update();
        }
        return parameterSetMap.get(key);
    }

    /**
     * 获取会员单位信息
     * @return
     */
    public List<Ml3MemberUnits> ml3MemberUnits(){
        if(list == null){
            update();
        }
        return list;
    }

    /**
     * 一分钟更新一次
     */
    @Scheduled(initialDelay = 6000, fixedDelay = 60000)
    public void update(){
        list  = scheduleDao.queryUnits();
        parameterSetMap = scheduleDao.queryParameterSet();

        // 更新公众号缓存
        weChatPublicMap.clear();
        weChatPublicDao.list(new WeChatPublic()).forEach(weChatPublic -> weChatPublicMap.put(weChatPublic.getDomainName(), weChatPublic));

        // 消息推送模板
        wechatMessageMap.clear();
        scheduleDao.queryWechatMessage().forEach(wechatMessage -> wechatMessageMap.put(wechatMessage.getType(), wechatMessage));
    }

    @Autowired
    private CouponDao couponDao;

    /**
     *
     * @param domain
     * @return
     */
    public WeChatPublic getWeChatPublic(String domain){
        return weChatPublicMap.get(domain);
    }

    /**
     *
     * @param type
     * @return
     */
    public WechatMessage getWechatMessage(String type){
        if(wechatMessageMap.isEmpty()){
            // 消息推送模板
            wechatMessageMap.clear();
            scheduleDao.queryWechatMessage().forEach(wechatMessage -> wechatMessageMap.put(wechatMessage.getType(), wechatMessage));
        }
        return wechatMessageMap.get(type);
    }

}
