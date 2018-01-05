package com.rmkj.microcap.modules.coupon.controller.wap;

import com.rmkj.microcap.common.bean.PagerBean;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.interceptor.MybatisPagerInterceptor;
import com.rmkj.microcap.modules.coupon.entity.Coupon;
import com.rmkj.microcap.modules.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 123 on 2017/3/9.
 */
@Controller
@RequestMapping("${wap}/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping("/list")
    @LoginAnnot
    public String index(Model model){
        Coupon coupon = new Coupon();
        int rows = 3;

        coupon.setStart(0);
        coupon.setRows(rows);
        model.addAttribute("page", new PagerBean<>(couponService.findCoupons(coupon), MybatisPagerInterceptor.getTotal()));
        return "wap/coupon/coupon";
    }
}
