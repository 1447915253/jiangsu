package com.rmkj.microcap.common.modules.pay.weipeng.controller;/**
 * Created by Administrator on 2017/3/6.
 */

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengQrCodeAsyncResultBean;
import com.rmkj.microcap.common.modules.pay.weipeng.service.WeiPengScanQrcodePayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.validation.Valid;

/**
 * @author k
 * @create -03-06-17:32
 **/
@RequestMapping("${v1}/weiPengPay")
@RestController
public class WeiPengPayController {

    private static final Logger Log = Logger.getLogger(WeiPengPayController.class);

    @Autowired
    private WeiPengScanQrcodePayService weiPengScanQrcodePayService;

    @RequestMapping(value = "/getQrCodeAsyncResult", method = RequestMethod.POST)
    public ResponseEntity getQrCodeAsyncResult(WeiPengQrCodeAsyncResultBean asyncResultBean){
        Log.info("下单成功异步跳转链接：" + JSON.toJSONString(asyncResultBean));
        return new ResponseEntity(weiPengScanQrcodePayService.getGeiPengQrCodeAsyncResult(asyncResultBean), HttpStatus.OK);
    }

    /**
     * 威鹏微信H5支付回调
     * @param asyncResultBean
     * @return
     */
    @RequestMapping(value = "/getWeChatH5AsyncResult", method = RequestMethod.POST)
    public ResponseEntity getWeChatH5AsyncResult(WeiPengQrCodeAsyncResultBean asyncResultBean){
        Log.info("威鹏微信H5充值成功异步跳转链接：" + JSON.toJSONString(asyncResultBean));
        return new ResponseEntity(weiPengScanQrcodePayService.getGeiPengQrCodeAsyncResult(asyncResultBean, "H5"), HttpStatus.OK);
    }
}
