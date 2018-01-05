package com.rmkj.microcap.common.modules.pay.qianhongPay.service;


import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.MD5Util;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.PayUtil;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M on 2017/10/23.
 */
@Service
public class QianhongPayService {

    private static final Logger Log = Logger.getLogger(QianhongPayService.class);

    @Autowired
    private ParameterSetService parameterSetService;

    public Map<String, Object> qianhongScanPay(MoneyRecord moneyRecord, String price, int istype) {

        //String AuthorizationURL = "https://pay.xxx.com/Pay_Index.html";
        //String merchantId = "10002";
        String keyValue = ProjectConstants.QIAN_HONG_KEY ;
        String pay_bankcode=String.valueOf(istype);
        String pay_memberid=ProjectConstants.QIAN_HONG_MERCHANT_NO;//商户id
        String pay_orderid=moneyRecord.getSerialNo();//20位订单号 时间戳+6位随机字符串组成
        String pay_applydate= PayUtil.generateTime();//yyyy-MM-dd HH:mm:ss
        String pay_notifyurl= ProjectConstants.QIAN_HONG_PAY_NOTIFYURL;//通知地址
        String pay_callbackurl=ProjectConstants.QIAN_HONG_PAY_CALLBACKURL;//回调地址
        String pay_amount=price;
        String pay_attach="";
        String pay_productname="众乐商宝";
        String pay_productnum="";
        String pay_productdesc="";
        String pay_producturl="";
        String stringSignTemp="pay_amount="+pay_amount+"&pay_applydate="+pay_applydate+"&pay_bankcode="+pay_bankcode+"&pay_callbackurl="+pay_callbackurl+"&pay_memberid="+pay_memberid+"&pay_notifyurl="+pay_notifyurl+"&pay_orderid="+pay_orderid+"&key="+keyValue+"";
        String pay_md5sign= MD5Util.md5(stringSignTemp);


        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pay_memberid", pay_memberid);
        resultMap.put("pay_orderid", pay_orderid);
        resultMap.put("pay_applydate", pay_applydate);
        resultMap.put("pay_bankcode", pay_bankcode);
        resultMap.put("pay_notifyurl", pay_notifyurl);
        resultMap.put("pay_callbackurl", pay_callbackurl);
        resultMap.put("pay_amount", pay_amount);
        resultMap.put("pay_reserved1", "");
        resultMap.put("pay_reserved2", "");
        resultMap.put("pay_reserved3", "");
        resultMap.put("pay_productname", pay_productname);
        resultMap.put("pay_productnum", pay_productnum);
        resultMap.put("pay_productdesc", pay_productdesc);
        resultMap.put("pay_producturl", pay_producturl);
        resultMap.put("pay_md5sign", pay_md5sign);
        return resultMap;
    }
}
