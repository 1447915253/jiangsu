package com.rmkj.microcap.modules.coupon.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.coupon.entity.Coupon;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/8.
 */
@DataSource
public interface CouponDao {

    int add(Coupon coupon);

    int toExpired(Date date);

    Long checkEnough(Coupon coupon);

    List<Coupon> findCoupon(Coupon coupon);

    int use(Map<String, Object> map);

    int restore(Coupon coupon);

    List<Coupon> findCouponsGroupType(String userId);

    List<Coupon> findCoupons(Coupon coupon);

    List<Coupon> todayUsedType1(String userId);
}
