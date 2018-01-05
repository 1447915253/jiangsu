package com.rmkj.microcap.common.modules.pay.shande.controller;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5CommonResBean;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5PayCallBackCommonBean;
import com.rmkj.microcap.common.modules.pay.shande.http.ShanDeH5Api;
import com.rmkj.microcap.common.modules.pay.shande.service.ShanDeH5PayService;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2017/7/21.
 */
@RequestMapping("${v1}/shande")
@RestController
public class ShanDePayController {

    private static final Logger Log = Logger.getLogger(ShanDePayController.class);

    @Autowired
    private ShanDeH5PayService shanDeH5PayService;

    /**
     * 杉德支付 微信H5支付 网页授权
     */
    @RequestMapping(value = "/shanDeH5Pay",method = RequestMethod.POST)
    @LoginAnnot
    public String createOrder(@Valid @RequestBody ShanDePrePayBean shanDePrePayBean, HttpServletRequest request){
        return shanDeH5PayService.shanDeH5AuthService(shanDePrePayBean,request);
    }

    /**
     * 杉德支付 微信H5支付 异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(ShanDeH5PayCallBackCommonBean shanDeH5PayCallBackCommonBean){
        return shanDeH5PayService.callBack(shanDeH5PayCallBackCommonBean);
    }
}
