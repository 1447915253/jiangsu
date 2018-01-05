package com.rmkj.microcap.modules.money.service;

import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.money.dao.CashCouponDao;
import com.rmkj.microcap.modules.money.entity.CashCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2016/11/24.
 * 代金券业务类
 */
@Service
public class CashCouponService {

    @Autowired
    private CashCouponDao cashCouponDao;

    public List<CashCoupon> findUserCashCouponByUserId() {
        return cashCouponDao.findCashCouponByUserId(AuthContext.getUserId());
    }

    public List<CashCoupon> findUserCashCouponCanUse() {
        return cashCouponDao.findUserCashCouponCanUse(AuthContext.getUserId());
    }

    public void giveCashCoupon(String userId,String money,String minMoney,int interval,int counts){
        CashCoupon cashCoupon = new CashCoupon();
        cashCoupon.setUserId(userId);
        BigDecimal _money = new BigDecimal(money);
        BigDecimal _minMoney = new BigDecimal(minMoney);
        Calendar createTimeCalendar = Calendar.getInstance();
        createTimeCalendar.setTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,interval);
        for(int i=0;i<counts;i++){
            cashCoupon.preInsert();
            cashCoupon.setMoney(_money);
            cashCoupon.setMinMoney(_minMoney);
            cashCoupon.setCreateTime(createTimeCalendar.getTime());
            cashCoupon.setEndDate(calendar.getTime());
            cashCoupon.setStatus(Constants.CashCoupon.NORMAL_STATUS);
            cashCouponDao.giveCashCoupon(cashCoupon);
        }
    }
}
