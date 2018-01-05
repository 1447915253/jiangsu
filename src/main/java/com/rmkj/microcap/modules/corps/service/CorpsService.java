package com.rmkj.microcap.modules.corps.service;

import com.rmkj.microcap.common.bean.PagerBean;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.interceptor.MybatisPagerInterceptor;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.corps.bean.CorpsMoneyWithTime;
import com.rmkj.microcap.modules.corps.bean.CreateCorpsBean;
import com.rmkj.microcap.modules.corps.bean.Percent3;
import com.rmkj.microcap.modules.corps.bean.UserCorps;
import com.rmkj.microcap.modules.corps.dao.CorpsDao;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.index.service.ParameterService;
import com.rmkj.microcap.modules.user.dao.UserDao;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 123 on 2016/11/22.
 * 军团模块业务代码
 */
@Service
public class CorpsService {

    @Autowired
    private CorpsDao corpsDao;

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WeChatPublicDao weChatPublicDao;

    @Autowired
    private ParameterService parameterService;

    /**
     * 用户注册，加入军团
     * @param openId
     * @param scene
     */
    public void bindToParents(String openId, String scene){
        // 根据openid在数据库中查找用户
        User user = userDao.findUserByOpenId(openId);
        if(user == null){
            WeiXinUserInfo weiXinUserInfo = weiXinService.userInfo(openId);
            user = new User();
            if(weiXinUserInfo != null){
                user.setUserHeader(weiXinUserInfo.getHeadimgurl());
                user.setChnName(weiXinUserInfo.getNickname());
            }
            user.setOpenId(openId);
            WeChatPublic weChatPublic = weChatPublicDao.findByAppId(WeiXinInterceptor.appId());
            user.setWechatPublicId(weChatPublic.getId());
            if(StringUtils.isNotBlank(weChatPublic.getAgentInviteCode())){
                user.setAgentInviteCode(weChatPublic.getAgentInviteCode());
            }
            user.preInsert();
            userDao.registerUser(user);
        }
        // 查找推荐人 绑定代理关系和军团关系
        User userById = userDao.findUserByAutoId(scene);
        if(userById != null){
            userById.setOpenId(openId);
            if(StringUtils.isBlank(userById.getAgentInviteCode())){
                userById.setAgentInviteCode("000000");
            }
            // 排除自己
            userDao.bindToParents(userById);
        }

        // 关注发送消息
        weiXinService.sendMessage(ProjectConstants.WEI_XIN_MESSAGE_CUSTOM_TYPE.GUAN_ZHU, user.getId());

        // 给推荐人送券
//        Calendar instance = Calendar.getInstance();
//        CashCoupon cashCoupon = new CashCoupon();
//        cashCoupon.setUserId(userById.getId());
//        cashCoupon.setMoney(new BigDecimal(10));
//        cashCoupon.setMinMoney(new BigDecimal(100));
//        cashCoupon.setCreateTime(instance.getTime());
//        instance.add(Calendar.DAY_OF_MONTH, 30);
//        cashCoupon.setEndDate(instance.getTime());
//        cashCoupon.setStatus(Constants.CashCoupon.NORMAL_STATUS);
//
//        for(int i=0; i<2; i++){
//            cashCoupon.preInsert();
//            cashCouponDao.giveCashCoupon(cashCoupon);
//        }

    }

    /**
     * 创建军团
     * @return
     */
    public StatusCode create(CreateCorpsBean createCorpsBean){
        String userId = AuthContext.getUserId();

        User userById = userDao.findUserById(userId);
        if(userById == null || !userById.getTradePassword().equals(createCorpsBean.getTradePassword())){
            return StatusCode.PASSWORD_ERROR;
        }
        if(userById.getIdCard() != null && !"".equals(userById.getIdCard())){
            return StatusCode.SERVER_ERROR;
        }

        User user = new User();
        user.setId(userId);
        user.setIdCard(createCorpsBean.getIdCard());
        // 获取微信二维码的ticket
//        if(ProjectConstants.PRO_DEBUG){
//            user.setTicket(null);
//        }else {
//            user.setTicket(weiXinService.qrcodeCreate(userId));
//        }

        userDao.createCorps(user);

        return StatusCode.OK;
    }

    /**
     *
     * @param timeType
     * @return
     */
    public UserCorps queryUserCorpsMoneyWithTime(String timeType){
        if(timeType == null || !Pattern.matches("^all|today|week|month$", timeType)){
            return null;
        }
        CorpsMoneyWithTime corpsMoneyWithTime = new CorpsMoneyWithTime();
        corpsMoneyWithTime.setUserId(AuthContext.getUserId());
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        switch (timeType){
            case "all":
                return corpsDao.queryUserCorpsMoney(corpsMoneyWithTime.getUserId());
            case "today":
                corpsMoneyWithTime.setEndTime(instance.getTime());
                instance.add(Calendar.DAY_OF_MONTH, -1);
//                instance.add(Calendar.DAY_OF_MONTH, -1);
                corpsMoneyWithTime.setStartTime(instance.getTime());
                break;
            case "week": //本周
                corpsMoneyWithTime.setEndTime(instance.getTime());
                instance.add(Calendar.DAY_OF_MONTH, -1);
                // 如果是周末
                if(instance.get(Calendar.DAY_OF_WEEK) == 1){
                    instance.add(Calendar.DAY_OF_MONTH, -1);
                }
                instance.set(Calendar.DAY_OF_WEEK, 2);
                corpsMoneyWithTime.setStartTime(instance.getTime());
                break;
            case "month": //本月
                corpsMoneyWithTime.setEndTime(instance.getTime());
                instance.add(Calendar.DAY_OF_MONTH, -1);
                instance.set(Calendar.DAY_OF_MONTH, 1);
                corpsMoneyWithTime.setStartTime(instance.getTime());
                break;
        }
        return corpsDao.queryUserCorpsMoneyWithTime(corpsMoneyWithTime);
    }

    /**
     * 分页查询军团成员详情
     * @param user
     * @return
     */
    public PagerBean queryUserCorpsDetail(User user){
        user.setId(AuthContext.getUserId());
        List<User> users = corpsDao.queryUserCorpsDetail(user);
        return new PagerBean<User>(users, MybatisPagerInterceptor.getTotal());
    }

}
