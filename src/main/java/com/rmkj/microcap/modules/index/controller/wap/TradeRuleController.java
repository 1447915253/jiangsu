package com.rmkj.microcap.modules.index.controller.wap;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.bean.annotation.WeiXinLogin;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.service.RootService;
import com.rmkj.microcap.modules.user.bean.LoginBean;
import com.rmkj.microcap.modules.user.bean.RegBean;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 123 on 2016/10/19.
 */
@Controller
@RequestMapping("${wap}/tradeRule")
public class TradeRuleController extends BaseController{

    @RequestMapping(value = "/registProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String regInstuction(){
        return "/wap/user/trade_rule/registProcessIntroduce";
    }
    @RequestMapping(value = "/tradeProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String tradeProcessIntroduce(){
        return "/wap/user/trade_rule/tradeProcessIntroduce";
    }
    @RequestMapping(value = "/withdrawalsProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String withdrawalsProcessIntroduce(){
        return "/wap/user/trade_rule/withdrawalsProcessIntroduce";
    }


}
