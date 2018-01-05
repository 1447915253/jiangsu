package com.rmkj.microcap.common.modules.pay.xinwenxin.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5PayCallBackCommonBean;
import com.rmkj.microcap.common.modules.pay.shande.service.ShanDeH5PayService;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WechatPayBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.service.WeCharService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by 123 on 2016/10/19.
 */
@RestController
@RequestMapping("${v1}/WeChat")
public class WeChatController extends BaseController {

    @Autowired
    private WeCharService weCharService;
    @Autowired
    private ShanDeH5PayService shanDeH5PayService;

    /**
     * 杉德支付 微信H5支付 异步回调
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public ResponseEntity callback(ShanDeH5PayCallBackCommonBean shanDeH5PayCallBackCommonBean){
        return shanDeH5PayService.callBack(shanDeH5PayCallBackCommonBean);
    }


}
