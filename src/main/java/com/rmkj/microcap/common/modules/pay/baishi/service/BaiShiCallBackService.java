package com.rmkj.microcap.common.modules.pay.baishi.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiCallBackBean;
import com.rmkj.microcap.common.modules.pay.baishi.util.ConfigUtils;
import com.rmkj.microcap.common.modules.pay.baishi.util.SignUtils;
import com.rmkj.microcap.modules.money.service.MoneyService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by M on 2017/10/25.
 */
@Service
public class BaiShiCallBackService {

    private static final Logger Log = Logger.getLogger(BaiShiCallBackService.class);

    @Autowired
    private MoneyService moneyService;

    public ResponseEntity callBack(BaiShiCallBackBean baiShiCallBackBean){
        Log.info("百时支付回调参数:"+ JSON.toJSONString(baiShiCallBackBean));
        String retStatus = baiShiCallBackBean.getRetStatus();
        if("0".equals(retStatus)){
            if("02".equals(baiShiCallBackBean.getStatus())){
                String orderID = baiShiCallBackBean.getOrderID();
                Boolean flag = moneyService.baiShiCallBack(baiShiCallBackBean,orderID);
                if(flag){
                    Log.info("百时支付回调成功");
                    return new ResponseEntity("SUCCESS", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity("FAIL", HttpStatus.OK);
    }
}
