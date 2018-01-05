package com.rmkj.microcap.common.modules.pay.yizhifu.controller;

import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayResultBackBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.service.UnionPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/6.
 */
@RequestMapping("${v1}/pay")
@RestController
public class UnionPayController {
    @Autowired
    private UnionPayOrderService unionPayOrderService;

    @RequestMapping(value = "/yizhifu/callback")
    public ResponseEntity callBack(PayResultBackBean payResultBackBean){
        return new ResponseEntity(unionPayOrderService.getPayResult(payResultBackBean), HttpStatus.OK);
    }

}
