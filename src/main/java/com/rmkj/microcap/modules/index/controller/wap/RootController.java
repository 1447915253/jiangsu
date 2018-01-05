package com.rmkj.microcap.modules.index.controller.wap;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.bean.annotation.WeiXinLogin;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.cache.bean.CacheBean;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoPerBankCardBean;
import com.rmkj.microcap.common.modules.pay.mobao.service.MoBaoPayService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.dao.ParameterSetDao;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.index.service.RootService;
import com.rmkj.microcap.modules.money.entity.CashCoupon;
import com.rmkj.microcap.modules.money.service.CashCouponService;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 123 on 2016/10/19.
 */
@Controller
@RequestMapping("${wap}")
public class RootController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private RootService rootService;

    @Autowired
    private ParameterSetDao parameterSetDao;

    @Autowired
    private CashCouponService cashCouponService;

    @Autowired
    private MoBaoPayService moBaoPayService;

    @RequestMapping("/debug")
    public String debug(Model model){
        return "/wap/debug";
    }

    @RequestMapping("/invalid")
    public String invalid(Model model){
        return "/wap/invalid";
    }

    @RequestMapping("/error/404")
    public String error404(Model model){
        return "/wap/error/404";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg(RegBean regBean,Model model){

        if(StringUtils.isBlank(regBean.getAgentInviteCode()) && StringUtils.isBlank(regBean.getUserId())){
            regBean.setAgentInviteCode("");
            regBean.setUserId("");
        }else if(StringUtils.isBlank(regBean.getUserId())){
            regBean.setUserId("");
        }
        return "/wap/reg";
    }

    @RequestMapping(value = "/registProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String regInstuction(){
        return "/wap/user/registProcessIntroduce";
    }
    @RequestMapping(value = "/tradeProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String tradeProcessIntroduce(){
        return "/wap/user/tradeProcessIntroduce";
    }
    @RequestMapping(value = "/withdrawalsProcessIntroduce", method = RequestMethod.GET)
    @LoginAnnot
    public String withdrawalsProcessIntroduce(){
        return "/wap/user/withdrawalsProcessIntroduce";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String terminal,HttpServletRequest request){
        boolean flag = isWeiXin(request);
        if(flag == true && "4".equals(terminal)){
            return "/wap/loginTip";
        }
        return "/wap/login";
    }

    private boolean isWeiXin(HttpServletRequest request){
        String ua = request.getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            return true;
        }
        return false;
    }

    @RequestMapping("/home")
    @WeiXinLogin
    @LoginAnnot
    public String index(String tokenId,Model model){
        if(StringUtils.isNotBlank(tokenId) && !"renrenbao".equals(tokenId)){
            CacheFacade.delete(tokenId);
        }
        return rootService.toTrade(model);
    }

    /**
     * wap端注册注册
     * @param regBean
     * @param errors
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String reg(@Valid RegBean regBean, Errors errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", parseErrors(errors).getBody());
        } else {
//            String validateCode = (String) request.getSession().getAttribute("validateCode");
//            String code = regBean.getRandomCode();
//            if(!validateCode.equalsIgnoreCase(code)){
//                model.addAttribute("errors",  new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST).getBody());
//                return "/wap/reg";
//            }
            ResponseEntity responseEntity = userService.reg(regBean);

            if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                model.addAttribute("errors", responseEntity.getBody());
                model.addAttribute("regBean", regBean);
            } else {
                // 注册成功，写入客户端cookie
               AuthEntity user = (AuthEntity) responseEntity.getBody();
               Cookie cookie = null;
                try {
                    cookie = new Cookie(Constants.Http.AUTHORIZATION, URLEncoder.encode(JSON.toJSONString(user), "utf-8"));
                    cookie.setPath(request.getContextPath().concat("/"));
                    response.addCookie(cookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return "/wap/skip";
            }
        }
        return "/wap/reg";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid LoginBean loginBean, Errors errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        //判断会员二级域名
        System.out.println("RequestAddress:"+ request.getScheme() +"://" + request.getServerName() +":"+ request.getServerPort() +request.getRequestURI());
        String path = request.getScheme() +"://" + request.getServerName() +":"+ request.getServerPort() +request.getRequestURI();

        if (errors.hasErrors()) {
            model.addAttribute("errors", parseErrors(errors).getBody());
        } else {
            String validateCode = (String) request.getSession().getAttribute("validateCode");
            String code = loginBean.getRandomCode();
            if(StringUtils.isBlank(code) || !validateCode.equalsIgnoreCase(code)){
                model.addAttribute("errors",  new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST).getBody());
                model.addAttribute("mobile", loginBean.getMobile());
                return "/wap/login";
            }
            ResponseEntity responseEntity = userService.login(loginBean, path, model);
            if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                ResponseErrorEntity er = (ResponseErrorEntity) responseEntity;
                // 停用账号
                if(er.getCode().equals(StatusCode.USER_CLOSE)){
                    return "/wap/invalid";
                }
                model.addAttribute("errors", responseEntity.getBody());
                model.addAttribute("loginBean", loginBean);
            } else {
                // 登录成功，写入客户端cookie
                AuthEntity user = (AuthEntity) responseEntity.getBody();
                try {
                    Cookie cookie = null;
                    cookie = new Cookie(Constants.Http.AUTHORIZATION, URLEncoder.encode(JSON.toJSONString(user), "utf-8"));
                    cookie.setMaxAge(5*365*24*60*60);
                    cookie.setPath(request.getContextPath().concat("/"));
                    response.addCookie(cookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                AuthContext.setCurAuth(user);

                //查询最后一次登录时间。判断是否第一次使用 true：第一次
                if(true == userService.getUserLastLoginTime(user.getUserId())){
                    model.addAttribute("oneLogin", "0");
                }else{
                    model.addAttribute("oneLogin", "1");
                }
                userService.updateLogin(Utils.getClientIp(request));
//
                if(Constants.Terminal.TERMINAL_IOS.equals(user.getTerminal())
                        || Constants.Terminal.TERMINAL_ANDROID.equals(user.getTerminal()) || Constants.Terminal.TERMINAL_WEB.equals(user.getTerminal())){
                    return rootService.toTrade(model);
                }else{
                    return "/wap/skip";
                }
            }
        }
        return "/wap/login";
    }

    @RequestMapping(value = "/updateUserInfo")
    @LoginAnnot
    @WeiXinLogin(bind = true)
    public String updateUserInfo(Model model){
        return rootService.toTrade(model);
    }

    /**
     * 不登陆的状态下访问trade页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/home/nologin")
    public String toTrade(String tokenId,Model model){
        if(StringUtils.isNotBlank(tokenId)){
            CacheFacade.delete(tokenId);
        }
        return rootService.toTrade(model);
    }

    @RequestMapping(value = "/pay/recharge")
    @LoginAnnot
    public String recharge(String from, Model model, HttpServletRequest request){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        model.addAttribute("from", from);
        //天宫移动端微信支付回调
        String is_success = request.getParameter("is_success");
        if(StringUtils.isNotBlank(is_success)){
            if(Boolean.TRUE.toString().equals(is_success)){
                model.addAttribute("msg", "支付成功");
            }else{
                model.addAttribute("msg", "支付失败");
            }
        }
        // 兼容安卓h5
        model.addAttribute("terminal", AuthContext.getCurAuth().getTerminal());
        //支付默认类型
        ParameterSet parameterSet = parameterSetDao.findParameterSet();
        model.addAttribute("payType",parameterSet.getDefaultPayType());
        return "/wap/pay/recharge";
    }

    @RequestMapping(value = "/firstPartResetTradePassWord")
    public String modifyLoginPassWord(@RequestParam String mobile,Model model){
        model.addAttribute("mobile",mobile);
        return "/wap/firstPartResetTradePassWord";
    }
    @RequestMapping(value = "/secondPartResetTradePassWord")
    public String secondPartResetTradePassWord(@RequestParam String mobile,String validCode,Model model){
        model.addAttribute("mobile",mobile);
        model.addAttribute("validCode",validCode);
        return "/wap/secondPartResetTradePassWord";
    }


    @RequestMapping(value = "/toTrade")
    @LoginAnnot
    public String toTradePage(){
        return "/wap/trade";
    }

    @RequestMapping(value = "/account")
    @LoginAnnot
    public String toAccount(Model model){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        return "/wap/user/account";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = null;
        cookie = new Cookie(Constants.Http.AUTHORIZATION, "");
        cookie.setPath(request.getContextPath().concat("/"));
        response.addCookie(cookie);
        return "/wap/login";
    }

    @RequestMapping(value = "/yizhifu/pay/yiZhiFuReturn")
    @LoginAnnot
    public String yiZhiFuReturn(String from, Model model){
        return "wap/pay/recharge";
    }

    @RequestMapping(value = "/invite",method = RequestMethod.GET)
    public String invite(){
        return "/wap/invite";
    }

    /**
     * TODO 输出会员代理二维码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/displayQRCode")
    public void DisplayQRCodeImage(@Valid String mobile, HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream write = null;
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "No-cache");
        FileInputStream fis = null;
        try {
            //查询当前注册用户的会员单位
            String imageName = userService.getAgentInviteCode(mobile);
            System.out.println(imageName);
            File file = new File(ProjectConstants.AGENT_QRCODE, imageName + ProjectConstants.AGENT_QRCODE_TYPE);
            if(file.exists()){
                fis = new FileInputStream(file);
                byte[] bb = new byte[1024];

                int read = -1;
                write = response.getOutputStream();
                while((read = fis.read(bb)) != -1){
                    write.write(bb, 0 , read);
                }
                write.flush();
                write.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/notweixin", method = RequestMethod.GET)
    public String notWeiXin(){
        return "/wap/error/notweixin";
    }

    @RequestMapping("/pay/moBaoUnionPay")
    public String getFirstPage(Model model) {
        MoBaoPerBankCardBean moBaoPerBankCardBean=  moBaoPayService.moBaoPayQuery();
        model.addAttribute("moBaoPer",moBaoPerBankCardBean);
        model.addAttribute("money", "my name is money");
        return "/wap/pay/moBaoUnionPay";
    }

    @RequestMapping("/pay/moBaoUnionPaycode")
    public String getcodePage(Model model) {
        model.addAttribute("ymz", "my name is ymz");
        return "/wap/pay/moBaoUnionPaycode";
    }

    @RequestMapping("/pay/qianhongPay")
    public String personalPay(Model model) {
        model.addAttribute("money", "my name is money");
        return "/wap/pay/qianhongPay";
    }

    @RequestMapping("/pay/xionyunPay")
    public String xionyunPay(Model model) {
        model.addAttribute("money", "my name is money");
        return "/wap/pay/xionyunPay";
    }
}
