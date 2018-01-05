package com.rmkj.microcap.common.modules.pay.xinwenxin.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanCallBackBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.service.WebScanPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2016/10/19.
 */
@RestController
@RequestMapping("${v1}/WebScanPay")
public class WebScanPayController extends BaseController {

    @Autowired
    private WebScanPayService webScanPayService;

    /**
     * 新Web扫码支付  异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(WebScanCallBackBean webScanCallBackBean){
        return webScanPayService.callBack(webScanCallBackBean);
    }


}
