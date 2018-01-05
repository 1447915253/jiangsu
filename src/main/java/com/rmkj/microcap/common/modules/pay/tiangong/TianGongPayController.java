package com.rmkj.microcap.common.modules.pay.tiangong;

import com.rmkj.microcap.common.modules.pay.tiangong.bean.PayResultReqBean;
import com.rmkj.microcap.common.modules.pay.weifutong.WeiFuTongPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 123 on 2017/1/7.
 */
@Controller
@RequestMapping("${v1}/tiangong/pay")
public class TianGongPayController {

    @Autowired
    private TianGongPayService tianGongPayService;

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public ResponseEntity notify(PayResultReqBean payResultReqBean){
        return tianGongPayService.notify(payResultReqBean);
    }

}
