package com.rmkj.microcap.common.modules.pay.mobao.controller;

import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoCallBackBean;
import com.rmkj.microcap.common.modules.pay.mobao.service.MoBaoCallBackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by M on 2017/10/23.
 */
@RequestMapping("${v1}/moBaoUnionPay")
@RestController
public class MoBaoPayController {
    private static final Logger Log = Logger.getLogger(MoBaoPayController.class);

    @Autowired
    private MoBaoCallBackService moBaoCallBackService;
    /**
     * 摩宝支付异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(MoBaoCallBackBean moBaoCallBackBean){
        return moBaoCallBackService.callBack(moBaoCallBackBean);
    }
}
