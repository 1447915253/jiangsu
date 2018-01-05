package com.rmkj.microcap.common.modules.pay.shande2.controller;

import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5PayCallBackCommonBean2;
import com.rmkj.microcap.common.modules.pay.shande2.service.ShanDeH5PayService2;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean2;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/7/21.
 */
@RequestMapping("${v1}/shande2")
@RestController
public class ShanDePayController2 {

    private static final Logger Log = Logger.getLogger(ShanDePayController2.class);

    @Autowired
    private ShanDeH5PayService2 shanDeH5PayService;

    /**
     * 杉德支付 微信H5支付 网页授权
     */
    @RequestMapping(value = "/shanDeH5Pay2",method = RequestMethod.POST)
    @LoginAnnot
    public String createOrder(@Valid @RequestBody ShanDePrePayBean2 shanDePrePayBean, HttpServletRequest request){
        return shanDeH5PayService.shanDeH5AuthService(shanDePrePayBean,request);
    }

    /**
     * 杉德支付 微信H5支付 异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(ShanDeH5PayCallBackCommonBean2 shanDeH5PayCallBackCommonBean2){
        return shanDeH5PayService.callBack(shanDeH5PayCallBackCommonBean2);
    }
}
