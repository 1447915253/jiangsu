package com.rmkj.microcap.common.modules.pay.baishi.controller;

import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiCallBackBean;
import com.rmkj.microcap.common.modules.pay.baishi.service.BaiShiCallBackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by M on 2017/10/23.
 */
@RequestMapping("${v1}/baiShiPay")
@RestController
public class BaiShiPayController {
    private static final Logger Log = Logger.getLogger(BaiShiPayController.class);

    @Autowired
    private BaiShiCallBackService baiShiCallBackService;
    /**
     * 百时支付异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(BaiShiCallBackBean baiShiCallBackBean){
        return baiShiCallBackService.callBack(baiShiCallBackBean);
    }
}
