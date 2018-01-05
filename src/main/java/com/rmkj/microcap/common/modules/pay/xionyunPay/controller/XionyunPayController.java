package com.rmkj.microcap.common.modules.pay.xionyunPay.controller;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.xionyunPay.bean.XionyunNotifyBean;
import com.rmkj.microcap.common.modules.pay.xionyunPay.util.MD5Util;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;


/**
 * Created by M on 2017/10/23.
 */

@RequestMapping("${v1}/xionyunScanPay")
@RestController
public class XionyunPayController {

    private static final Logger Log = Logger.getLogger(XionyunPayController.class);

    @Autowired
    private MoneyService moneyService;

    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public ResponseEntity notify(XionyunNotifyBean xionyunNotifyBean) {
/*
        String key = "66987BFECB7B42FD3E98FB1CE91D2299";
      */
        Log.info("熊云支付回调参数："+ JSON.toJSONString(xionyunNotifyBean));
        int shopId =15;
        String key ="B6567F9CF90879219A3251CCAD2FB13A";

       /* int shopId = 2;
        String key ="66987BFECB7B42FD3E98FB1CE91D2299";*/
        String trade_no = xionyunNotifyBean.getTrade_no();
        String out_trade_no = xionyunNotifyBean.getOut_trade_no();
        String money = xionyunNotifyBean.getMoney();
        String return_code = xionyunNotifyBean.getReturn_code();
        String token = xionyunNotifyBean.getToken();


        String SignTemp2 = shopId +out_trade_no + money + key +return_code;
        String md5sign2 = MD5Util.getPwd(SignTemp2);//MD5加密
        Log.info("拼接的SignTemp2"+SignTemp2);
        Log.info("加密后的"+md5sign2);
        if (token.equals(md5sign2)) {
            if (return_code.equals("success")) {
                //支付成功，写返回数据逻辑
                Boolean flag =moneyService.xionyunNotify(xionyunNotifyBean);
                Log.info(flag+"----------true还是false");
                Log.info("熊云支付回调成功！（单号："+xionyunNotifyBean.getOut_trade_no()+"）");
                return new ResponseEntity("success", HttpStatus.OK);
            } else {
                return new ResponseEntity("fail", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

}

