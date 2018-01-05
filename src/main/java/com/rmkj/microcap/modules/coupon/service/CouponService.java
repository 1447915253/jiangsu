package com.rmkj.microcap.modules.coupon.service;

import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.coupon.dao.CouponDao;
import com.rmkj.microcap.modules.coupon.entity.Coupon;
import com.rmkj.microcap.modules.index.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/8.
 */
@Service
public class CouponService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private WeiXinService weiXinService;

    /**
     *
     * 直营劵1张，9张增益劵
     * @param userId
     */
    public void reg(String userId){
        add(userId, new BigDecimal(10), Constants.Coupon.TYPE_2, 1, 30);
        add(userId, new BigDecimal(100), Constants.Coupon.TYPE_3, 9, 7);

        weiXinService.sendMessage(ProjectConstants.WEI_XIN_MESSAGE_CUSTOM_TYPE.YOU_HUI_QUAN, userId, "您注册成功，获得直盈劵1张、增益券9张！");
    }

    /**
     * 首充送券
     * @param userId
     * @param money
     */
    public void firstRecharge(String userId, BigDecimal money, String inviterUserId){
        BigDecimal minMoney = (BigDecimal) scheduleService.getParameterSet(Constants.ParameterSet.FIRST_RECHARGE_MIN_MONEY_FOR_COUPON);
        if(minMoney.doubleValue() <= money.doubleValue()){
            // 增益券
            BigDecimal giveCashCouponMoney = money.divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP);
            int c1 = giveCashCouponMoney.intValue();
            add(userId, new BigDecimal(100), Constants.Coupon.TYPE_3, c1, 7);
            weiXinService.sendMessage(ProjectConstants.WEI_XIN_MESSAGE_CUSTOM_TYPE.YOU_HUI_QUAN, userId, "您首次充值成功，获得增益券"+c1+"张！");
            // 赠券给推荐人
            if(inviterUserId != null && !"".equals(inviterUserId)){
                add(inviterUserId, new BigDecimal(10), Constants.Coupon.TYPE_2, 2, 30);
                weiXinService.sendMessage(ProjectConstants.WEI_XIN_MESSAGE_CUSTOM_TYPE.YOU_HUI_QUAN, inviterUserId, "您的推荐的人首次充值成功，获得直盈券2张，有效期30天！");
            }
        }
    }

    public boolean use(String userId, int money ,String type, int num){
        return use(userId, money ,type, num, null);
    }

    public boolean use(String userId, int money ,String type, int num, String tradeId){
        if(num < 0){
            return false;
        }else if(num == 0){
            return true;
        }
        Coupon coupon = new Coupon();
        coupon.setUserId(userId);
        coupon.setType(type);
        coupon.setMoney(new BigDecimal(money));
        Long l = couponDao.checkEnough(coupon);
        if(l == null || l < num){
            return false;
        }

        // 到期日期顺序排序
        coupon.setNum(num);
        List<Coupon> list = couponDao.findCoupon(coupon);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("remark", tradeId);
        couponDao.use(map);

        return true;
    }

    /**
     * 还原
     * @param userId
     * @param tradeId
     * @return
     */
    public boolean restore(String userId, String type, String tradeId){
        Coupon coupon = new Coupon();

        coupon.setUserId(userId);
        coupon.setType(type);
        coupon.setRemark(tradeId);
        return couponDao.restore(coupon) > 0;
    }

    /**
     * 还原
     * @param type
     * @param tradeIds
     * @return
     */
    public boolean restore(String type, List<String> tradeIds){
        Coupon coupon = new Coupon();
        coupon.setType(type);
        coupon.setTradeIds(tradeIds);
        return couponDao.restore(coupon) > 0;
    }

    /**
     *
     * @param userId
     * @param type
     * @param num
     * @param expireDay
     * @return
     */
    private int add(String userId, BigDecimal money, String type, int num, int expireDay){
        if(num <= 0){
            return 0;
        }
        Coupon coupon = new Coupon();
        coupon.setMoney(money);
        coupon.setUserId(userId);
        coupon.setType(type);
        Calendar instance = Calendar.getInstance();
        coupon.setCreateTime(instance.getTime());
        instance.add(Calendar.DAY_OF_MONTH, expireDay);
        coupon.setEndDate(instance.getTime());
        int count = 0;
        for(int i = 0; i < num; i++){
            coupon.preInsert();
            count += couponDao.add(coupon);
        }
        return count;
    }

    /**
     * 查询可用三种券的数量
     * @return
     */
    public List<Coupon> findCouponsGroupType() {
        return couponDao.findCouponsGroupType(AuthContext.getUserId());
    }

    /**
     * 用来展示券
     * @return
     * @param coupon
     */
    public List<Coupon> findCoupons(Coupon coupon){
        coupon.setUserId(AuthContext.getUserId());
        return couponDao.findCoupons(coupon);
    }

    /**
     * 当日直盈券使用记录
     * @return
     */
    public List<Coupon> todayUsedType1(){
        return couponDao.todayUsedType1(AuthContext.getUserId());
    }

}
