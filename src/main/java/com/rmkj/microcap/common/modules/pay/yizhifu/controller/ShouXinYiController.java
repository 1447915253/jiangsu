package com.rmkj.microcap.common.modules.pay.yizhifu.controller;

import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayResultBackBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/3.
 */
@RequestMapping("${v1}/scanCodePay")
@RestController
public class ShouXinYiController {

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping(value = "/yizhifu/callBack")
    public ResponseEntity callBack(PayResultBackBean payResultBackBean){
        return new ResponseEntity(payOrderService.getPayResult(payResultBackBean), HttpStatus.OK);
    }
}
