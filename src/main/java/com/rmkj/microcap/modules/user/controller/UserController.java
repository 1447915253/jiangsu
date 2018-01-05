package com.rmkj.microcap.modules.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.PageEntity;
import com.rmkj.microcap.common.bean.PagerBean;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinShare;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.CashCouponService;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.user.bean.*;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.entity.UserMessage;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 123 on 2016/10/18.
 */
@RestController
@RequestMapping("${v1}/users")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private CashCouponService cashCouponService;

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private MoneyService moneyService;
    /**
     * 注册
     * @param regBean
     * @param errors
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity reg(@Valid RegBean regBean, Errors errors, Model model){
        if(errors.hasErrors()){
           return parseErrors(errors);
        }
        return userService.reg(regBean);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity get(){
        return userService.get();
    }

    @RequestMapping(value = "/tradeList", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity tradeList(PageEntity pageEntity){
        PagerBean<Trade> pb = userService.tradeList(pageEntity);
        return new ResponseEntity(pb, HttpStatus.OK);
    }

    @RequestMapping(value = "/moneyRecordList", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity moneyRecordList(PageEntity pageEntity){
        PagerBean<MoneyRecord> pb = userService.moneyRecordList(pageEntity);
        return new ResponseEntity(pb, HttpStatus.OK);
    }

    @RequestMapping(value = "/moneyOutInfo", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity moneyOutInfo(MoneyRecord moneyRecord){
        return new ResponseEntity(userService.moneyOutInfo(moneyRecord), HttpStatus.OK);
    }

    @RequestMapping(value = "/modifyChnName", method = RequestMethod.PUT)
    @LoginAnnot
    public ResponseEntity modifyChnName(@RequestBody(required = false) @Valid ModifyChnNameBean modifyChnNameBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.modifyChnName(modifyChnNameBean);
    }

    @RequestMapping(value = "/modifyMobile", method = RequestMethod.PUT)
    @LoginAnnot
    public ResponseEntity modifyMobile(@RequestBody(required = false) @Valid ModifyMobile modifyMobile, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.modifyMobile(modifyMobile);
    }

    @RequestMapping(value = "/modifyTradePwd", method = RequestMethod.PUT)
    @LoginAnnot
    public ResponseEntity modifyTradePwd(@RequestBody(required = false) @Valid ModifyTradePwd modifyTradePwd, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.modifyTradePwd(modifyTradePwd);
    }

    @RequestMapping(value = "/firstPartResetTradePassWord",method = RequestMethod.POST)
    public ResponseEntity resetTradePassWord(@RequestBody @Valid FirstPartResetTradePwd firstPartResetTradePwd, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.validateMobileCode(firstPartResetTradePwd);
    }
    @RequestMapping(value = "/secondPartResetTradePassWord",method = RequestMethod.POST)
    public ResponseEntity secondPartResetTradePassWord(@RequestBody @Valid SecondPartResetTradePwd secondPartResetTradePwd, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.restTradePass(secondPartResetTradePwd);
    }

    @RequestMapping(value = "/messageList", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity messageList(UserMessage userMessage){
        PagerBean<UserMessage> pb = userService.messageList(userMessage);
        return ResponseEntity.ok(pb);
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity newMessage(){
        long count = userService.countNewMessage();
        return ResponseEntity.ok(new JSONObject().put("total", count).toString());
    }

    @RequestMapping(value = "/readMessage/{id}", method = RequestMethod.PUT)
    @LoginAnnot
    public ResponseEntity readMessage(@PathVariable String id){
        return userService.readMessage(id);
    }

    @RequestMapping(value = "/addBankCard", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity addBankCard(@RequestBody @Valid AddBankCard addBankCard, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.addBankCard(addBankCard);
    }

    /**
     * TODO 威鹏支付绑定银行卡
     * @param addBankCard
     * @param errors
     * @return
     */
    @RequestMapping(value = "/addBank", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity addBank(@RequestBody @Valid AddBankCard addBankCard, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.addBank(addBankCard);
    }

    /*
    * 佣金转换成余额
    *
    * */
    @RequestMapping(value = "/commissionToMoney", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity commissionToMoney(   ){

        return moneyService.commissionToMoney();
    }

    /**
     * TODO 修改银行卡信息
     * @param addBankCard
     * @param errors
     * @return
     */
    @RequestMapping(value = "/updateBankCard", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity updateBankCard(@RequestBody @Valid AddBankCard addBankCard, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return userService.updateBankCard(addBankCard);
    }

    @RequestMapping(value = "/getCashCoupon",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity getCashCoupon(){
        cashCouponService.giveCashCoupon(AuthContext.getUserId(), Constants.CashCoupon.GIVE_MONEY,Constants.CashCoupon.MIN_MONEY, Constants.CashCoupon.interval,Constants.CashCoupon.counts);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * TODO 获取用户money
     * @return
     */
    @RequestMapping(value = "/getUserMoney")
    @ResponseBody
    public Map<String, Object> getUserMoney(@Valid String tradeId){
        Map<String, Object> result = new HashedMap();
        User user = userService.getUserMoney(tradeId);
        if(null != user) {
            result.put("money", user.getMoney());
            result.put("returnMoney", user.getReturnMoney());
        }else{
            result.put("money", "-1");
        }
        return result;
    }

    @RequestMapping(value = "/getWeiXinShare", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getWeiXinShare(Model model){
        Map<String,Object> result = new HashedMap();
        result.put("result",weiXinService.getWeixinShare());
        return result;
    }

    /**
     * TODO 查询支行信息--模糊查询提示
     * @param mainWord
     * @return
     */
    @RequestMapping(value = "/getOpenBankName",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity getOpenBankName(String mainWord){
        return new ResponseEntity(userService.findOpenBankName(mainWord),HttpStatus.OK);
    }

    @RequestMapping(value = "/getMoneyRecord", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity findMoneyRecordStatusByUserId(){
        return new ResponseEntity(moneyService.findMoneyRecordStatusByUserId(), HttpStatus.OK);
    }

}
