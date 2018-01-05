package com.rmkj.microcap.modules.sms;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.modules.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2016/10/18.
 */
@RestController
@RequestMapping("${v1}/sms")
public class SmsController extends BaseController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("/validatecode/{type}_{mobile}")
    public ResponseEntity validateCode(@PathVariable String type, @PathVariable String mobile){
        return smsService.sendSmsValidateCode(mobile, type);
    }

    @RequestMapping("/user/validatecode/{type}_{mobile}")
    @LoginAnnot
    public ResponseEntity userValidateCode(@PathVariable String type, @PathVariable String mobile){
        if("mtpwd".equals(type) || "reg".equals(type)){
            return smsService.sendSmsValidateCode(mobile, type);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
