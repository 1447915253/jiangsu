package com.rmkj.microcap.modules.coupon.controller;

import com.rmkj.microcap.common.bean.PageEntity;
import com.rmkj.microcap.common.bean.PagerBean;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.interceptor.MybatisPagerInterceptor;
import com.rmkj.microcap.modules.coupon.entity.Coupon;
import com.rmkj.microcap.modules.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 123 on 2017/3/8.
 */
@RestController
@RequestMapping("${v1}/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/findCoupons", method = RequestMethod.GET)
    @LoginAnnot
    public List<Coupon> findCoupons(){
        return couponService.findCouponsGroupType();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity list(Integer start){
        start = start == null ? 0 : start;
        int rows = 3;
        Coupon coupon = new Coupon();
        coupon.setStart(start);
        coupon.setRows(rows);
        return ResponseEntity.ok(new PagerBean<>(couponService.findCoupons(coupon), MybatisPagerInterceptor.getTotal()));
    }

}
