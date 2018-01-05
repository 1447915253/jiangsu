package com.rmkj.microcap.common.modules.pay.fiveeight.controller;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayResBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayResultResBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayResultResBeanData;
import com.rmkj.microcap.common.modules.pay.fiveeight.service.FiveEightOpenPayService;
import com.rmkj.microcap.common.modules.pay.nineeight.service.NineEightScanPayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2016/12/12.
 */
@RequestMapping("${v1}/fiveeight")
@RestController
public class FiveEightPayController {

    private static final Logger Log = Logger.getLogger(FiveEightPayController.class);

    @Autowired
    private FiveEightOpenPayService fiveEightOpenPayService;

    /**
     * 接收扫码支付结果
     */
    @RequestMapping(value = "/openPay/callback",method = RequestMethod.POST)
    public ResponseEntity getPayResult(@RequestBody String jsonReq){
        Log.info("58微信支付"+jsonReq);
        FiveEightOpenPayResultResBeanData fiveEightOpenPayResultResBeanData = JSON.parseObject(jsonReq,FiveEightOpenPayResultResBeanData.class);
        return fiveEightOpenPayService.getOpenPayResult(fiveEightOpenPayResultResBeanData);
    }
}
