package com.rmkj.microcap.common.modules.pay.weifutong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 123 on 2016/12/12.
 */
@Controller
@RequestMapping("${v1}/weifutong/pay")
public class WeiFuTongPayController {

    @Autowired
    private WeiFuTongPayService weiFuTongPayService;

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public ResponseEntity notify(@RequestBody String xml){
        return weiFuTongPayService.notify(xml);
    }
}
