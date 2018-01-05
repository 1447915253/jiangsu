package com.rmkj.microcap.common.modules.pay.qianhongPay.controller;


import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.qianhongPay.bean.QianHongNotifyBean;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.MD5Util;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by M on 2017/10/23.
 */
@RequestMapping("${v1}/qianhongScanPay")
@RestController
public class QianhongPayController {

    private static final Logger Log = Logger.getLogger(QianhongPayController.class);

    @Autowired
    private MoneyService moneyService;

    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public ResponseEntity notifyPay(HttpServletRequest request, HttpServletResponse response, QianHongNotifyBean qianHongNotifyBean) {
        Log.info("千红支付回调参数："+ JSON.toJSONString(qianHongNotifyBean));
        String memberid = qianHongNotifyBean.getMemberid();
        String orderid = qianHongNotifyBean.getOrderid();
        String amount = qianHongNotifyBean.getAmount();
        String datetime = qianHongNotifyBean.getDatetime();
        String returncode = qianHongNotifyBean.getReturncode();
        String transaction_id = qianHongNotifyBean.getTransaction_id();
        String attach = qianHongNotifyBean.getAttach();
        String sign = qianHongNotifyBean.getSign();
        String keyValue = ProjectConstants.QIAN_HONG_KEY;
        //String SignTemp = "amount=" + amount + "+datetime=" + datetime + "+memberid=" + memberid + "+orderid=" + orderid + "+returncode=" + returncode + "+transaction_id=" + transaction_id + "+key=" + keyValue + "";
        String SignTemp2 = "amount=" + amount + "&datetime=" + datetime + "&memberid=" + memberid + "&orderid=" + orderid + "&returncode=" + returncode + "&transaction_id=" + transaction_id + "&key=" + keyValue + "";
        //String md5sign = MD5Util.md5(SignTemp);//MD5加密
        String md5sign2 = MD5Util.md5(SignTemp2);//MD5加密
        //Log.info("待验签sign："+sign);

        //Log.info("待加密字符串SignTemp："+SignTemp);
        //Log.info("   待加密字符串SignTemp2："+SignTemp2);
        //Log.info("签名后sign："+md5sign);
        //Log.info("   签名后sign2："+md5sign2);
        if (sign.equals(md5sign2)) {
            if (returncode.equals("00")) {
                //支付成功，写返回数据逻辑
                Boolean flag =moneyService.qianhongNotify(qianHongNotifyBean);
                Log.info("千红支付回调成功！（单号："+qianHongNotifyBean.getOrderid()+"）");
                return new ResponseEntity("200", HttpStatus.OK);
            } else {
                return new ResponseEntity("fail", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }


}
