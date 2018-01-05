package com.rmkj.microcap.common.modules.pay.mobao.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoCallBackBean;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by M on 2017/10/25.
 */
@Service
public class MoBaoCallBackService {

    private static final Logger Log = Logger.getLogger(MoBaoCallBackService.class);

    @Autowired
    private MoneyService moneyService;

    public ResponseEntity callBack(MoBaoCallBackBean moBaoCallBackBean){
        Log.info("摩宝支付回调参数:"+ JSON.toJSONString(moBaoCallBackBean));
        String refCode = moBaoCallBackBean.getRefCode();
        if("00".equals(refCode)){
            String orderID = moBaoCallBackBean.getOrderId();

            Boolean flag = moneyService.MoBaoCallBack(moBaoCallBackBean,orderID);
            if(flag){
                Log.info("摩宝支付回调成功");
                return new ResponseEntity("OK", HttpStatus.OK);
            }
        }
        else if("02".equals(refCode)){
            return new ResponseEntity("FAIL", HttpStatus.OK);
        }
        return new ResponseEntity("FAIL", HttpStatus.OK);

    }
}
