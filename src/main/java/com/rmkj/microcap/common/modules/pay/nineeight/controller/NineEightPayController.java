package com.rmkj.microcap.common.modules.pay.nineeight.controller;

import com.rmkj.microcap.common.modules.pay.nineeight.bean.NineEightScanPayResultResBean;
import com.rmkj.microcap.common.modules.pay.nineeight.bean.QuickPayOrderResBean;
import com.rmkj.microcap.common.modules.pay.nineeight.service.NineEightScanPayService;
import com.rmkj.microcap.common.modules.pay.weifutong.WeiFuTongPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 123 on 2016/12/12.
 */
@Controller
@RequestMapping("${v1}/nineeight")
public class NineEightPayController {

    @Autowired
    private NineEightScanPayService nineEightScanPayService;

    /**
     * 接收扫码支付结果
     */
    @RequestMapping(value = "/newScanPay/callback",method = RequestMethod.POST)
    public ResponseEntity getPayResult(NineEightScanPayResultResBean nineEightScanPayResultResBean){
        return nineEightScanPayService.getScanPayResult(nineEightScanPayResultResBean);
    }

    /**
     * 接收银联支付结果
     */
    @RequestMapping(value = "/quickPay/callback",method = RequestMethod.POST)
    public ResponseEntity quickPay(QuickPayOrderResBean quickPayOrderResBean){
        return nineEightScanPayService.getQuickPayResult(quickPayOrderResBean);
    }
}
