package com.rmkj.microcap.modules.money.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiPrePayBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightGatewayOpenPayReqBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayReqBean;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoCodeReqBean;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoPrePayBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengDoBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanPayBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.service.WebScanPayService;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayRequestParamBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.UnionPayRequestParam;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.*;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 123 on 2016/10/19.
 */
@RestController
@RequestMapping("${v1}/money")
public class MoneyController extends BaseController {

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private WebScanPayService weCharService;

    @RequestMapping(value = "/out", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity out(@RequestBody @Valid MoneyOutBean moneyOutBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyOut(moneyOutBean);
    }

    private Map<String, SoftReference<Object>> lockMap = new HashedMap();

    /**
     *
     * @param moneyOutBean
     * @return
     */
    public ResponseEntity moneyOut(MoneyOutBean moneyOutBean){
        String userId = AuthContext.getUserId();
        Object lock;
        synchronized (this){
            SoftReference<Object> objectSoftReference = lockMap.get(userId);
            if(objectSoftReference == null || (lock = objectSoftReference.get()) == null){
                lock = new Object();
                lockMap.put(userId, new SoftReference<>(lock));
            }
        }
        synchronized (lock){
            return "0".equals(moneyOutBean.getType()) ? moneyService.out(moneyOutBean) : moneyService.noReturnMoneyOut();
        }
    }

    @RequestMapping(value = "/prepay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity prePay(@RequestBody @Valid PrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.prePayOfWeiFuTong(prePayBean);
    }

    /**
     * TODO 通联扫码支付
     * @param prePayBean
     * @param errors
     * @param request
     * @return
     */
    @RequestMapping(value = "/tongLianScan", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity tongLianScanCodePay(@RequestBody @Valid PrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.tongLianScanCodePay(prePayBean);
    }

    @RequestMapping(value = "/tiangong/prepay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity tiangongPrePay(@RequestBody @Valid TianGongPrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyService.prePayOfTianGong(prePayBean);
    }

    @RequestMapping(value = "/prepay/del/{thirdSerialNo}", method = RequestMethod.DELETE)
    @LoginAnnot
    public ResponseEntity deletePrePay(@PathVariable String thirdSerialNo){
        return moneyService.deletePrePay(thirdSerialNo);
    }

    //首信易支付下单
    @RequestMapping(value = "/createPayOrder", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity createPayOrder(@RequestBody PayRequestParamBean requestParamBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyService.shouXinYinCreateOrder(requestParamBean);
    }

    /**
     * 首信易支付 银联支付下单
     */
    @RequestMapping(value = "/yizhifu/unionPay/createPayOrder",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity createUnionPayOrder(@RequestBody UnionPayRequestParam unionPayRequestParam){
        return moneyService.createUnionPayOrder(unionPayRequestParam);
    }

    /**
     * 98支付 快捷支付下单
     */
    @RequestMapping(value = "/nineeight/quickPay/createPayOrder",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity quickPayOrder(@RequestBody NineEightPrePayBean nineEightPrePayBean,HttpServletRequest request){
        nineEightPrePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.createNineEightQuickPayOrder(nineEightPrePayBean);
    }

    /**
     * App下单成功之后，调用该接口，在数据库中记录一条数据
     */
    @RequestMapping(value = "/moneyRecordFromApp", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity moneyRecordFromApp(FromAppReqBean fromAppReqBean){
        logger.info("money:"+fromAppReqBean.getMoney()+"serialNo:"+fromAppReqBean.getSerialNo()+"thirdSerialNo:"+fromAppReqBean.getThirdSerialNo()+"userId:"+fromAppReqBean.getUserId());
        return moneyService.moneyRecordFromApp(fromAppReqBean);
    }
    /**
     * App完成支付之后，调用该接口，更新状态
     */
    @RequestMapping(value = "/updateMoneyFromApp", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity updateMoneyFromApp(FromAppReqBean fromAppReqBean){
        logger.info("serialNo:"+fromAppReqBean.getSerialNo()+"status:"+fromAppReqBean.getStatus());
        boolean isAddMoney = moneyService.toAddMoney(fromAppReqBean.getSerialNo(),fromAppReqBean.getStatus());
        if(isAddMoney == true){
            return new ResponseEntity("success",HttpStatus.OK);
        }else{
            return new ResponseEntity("fail",HttpStatus.OK);
        }
    }

    /**
     * 威鹏支付-微信扫码下单
     * @param weiPengDoBean
     * @param errors
     * @return
     */
    @RequestMapping(value = "/weiPengScanQrcode", method = RequestMethod.POST)
    public ResponseEntity WeiPengscanQrcodePay(@RequestBody @Valid WeiPengDoBean weiPengDoBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyService.weiPengScanQrcodePayOrder(weiPengDoBean);
    }

    /**
     * 威鹏支付-微信H5扫码下单
     * @param weiPengDoBean
     * @param errors
     * @return
     */
    @RequestMapping(value = "/weiPengH5Pay", method = RequestMethod.POST)
    public ResponseEntity WeiPengH5Pay(@RequestBody @Valid WeiPengDoBean weiPengDoBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyService.weiPengH5Pay(weiPengDoBean);
    }

    /**
     * 支付宝扫码支付
     * @param prePayBean
     * @param errors
     * @param request
     * @return
     */
    @RequestMapping(value = "/aLiScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity aLiScanCodePay(@RequestBody @Valid PrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.aLiScanCodePay(prePayBean);
    }
    /**
     * 98支付 扫码支付
     */
    @RequestMapping(value = "/nineEightScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity nineEightScanPay(@RequestBody @Valid NineEightPrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.nineEightScanPay(prePayBean);
    }


    /**
     * 衫德支付 扫码支付
     */
    @RequestMapping(value = "/shanDeScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity shanDeScanPay(@RequestBody @Valid ShanDePrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.ShanDeScanPay(prePayBean);
    }

    /**
     * 衫德支付 QQ扫码支付
     */
    @RequestMapping(value = "/shanDeQQScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity shanDeQQScanPay(@RequestBody @Valid ShanDePrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.ShanDeQQScanPay(prePayBean);
    }

    /**
     * 百时扫码支付
     */
    @RequestMapping(value = "/baiShiScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity baiShiScanPay(@RequestBody @Valid BaiShiPrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.baiShiScanPay(prePayBean,request);
    }

    /**
     * 摩宝银联预支付
     */
    @RequestMapping(value = "/moBaoUnionPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity moBaoUnionPay(@RequestBody @Valid MoBaoPrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        //prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.moBaoUnionPay(prePayBean,request);
    }

    /**
     * 摩宝银联完成支付
     */
    @RequestMapping(value = "/moBaoUnionCodePay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity moBaoUnionCodePay(@RequestBody @Valid MoBaoCodeReqBean moBaoCodeReqBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return moneyService.moBaoUnionCodePay(moBaoCodeReqBean);
    }

    /**
     * 百时公众号支付
     */
    @RequestMapping(value = "/baiShiWeChatPubtPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity baiShiWeChatPubtPay(@RequestBody @Valid BaiShiPrePayBean prePayBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.baiShiWeChatPubPay(prePayBean,request);
    }

    /**
     * 58支付 扫码支付
     */
    @RequestMapping(value = "/fiveEightOpenPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity fiveEightOpenPay(@RequestBody @Valid FiveEightOpenPayReqBean fiveEightOpenPayReqBean, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        fiveEightOpenPayReqBean.setMchCreateIp(Utils.getClientIp(request));
        return moneyService.fiveEightOpenPay(fiveEightOpenPayReqBean);
    }

    /**
     * 58支付 微信网关模式支付下单
     */
    @RequestMapping(value = "/fiveeight/gateWayOpenPay/createGateWayPayOrder",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity createGateWayPayOrder(@RequestBody FiveEightGatewayOpenPayReqBean fiveEightGatewayOpenPayReqBean, HttpServletRequest request){
        return moneyService.createGateWayPayOrder(fiveEightGatewayOpenPayReqBean,request);
    }

    /**
     * 58支付 微信网关模式支付下单 接口模式提交
     */
    @RequestMapping(value = "/fiveeight/gateWayOpenPay/createGateWayPayOrderByService",method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity createGateWayPayOrderByService(@RequestBody @Valid FiveEightPrePayBean fiveEightPrePayBean, Errors errors,HttpServletRequest request){
        return moneyService.createGateWayPayOrderByService(fiveEightPrePayBean,request);
    }

    /**
     * 新web 扫码支付
     */
    @RequestMapping(value = "/xinWebScanPay", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity newPublicPay(@RequestBody @Valid WebScanPayBean prePayBean, Errors errors, HttpServletRequest request) {

        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        //prePayBean.setSpbillCreateIp(Utils.getClientIp(request));

        return moneyService.xinWebScanPay(prePayBean,request);
    }

    /**
     * 衫德扫码支付2
     */
    @RequestMapping(value = "/shanDeScanPay2", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity shanDeScanPay2(@RequestBody @Valid ShanDePrePayBean2 prePayBean2, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        prePayBean2.setSpbillCreateIp(Utils.getClientIp(request));
        return moneyService.ShanDeScanPay2(prePayBean2);
    }

    /**
     *  千红支付
     * @param price
     * @param istype
     * @return
     */
    @RequestMapping(value = "/qianhongPagePay", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> personalPay(String price, int istype) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = moneyService.qianhongScanPay(price,istype);
        return resultMap;
    }

    /**
     *  熊云支付
     * @param xionyunPayBean
     * @return
     */
    @RequestMapping(value = "/xionyunPagePay", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> xionyunalPay(@RequestBody @Valid XionyunPayBean xionyunPayBean) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = moneyService.xionyunScanPay(xionyunPayBean);
        return resultMap;
    }

    /**
     * 汇智通qq支付
     * @return
     */
    @RequestMapping(value = "/huizhitongPay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity huizhitongQQPay(@RequestBody @Valid WebScanPayBean prePayBean, Errors errors, HttpServletRequest request) {
        return moneyService.huizhitongQQScanPay(prePayBean);
    }

}
