package com.rmkj.microcap.common.modules.pay.shande2.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeScanCodePayBean;
import com.rmkj.microcap.common.modules.pay.shande2.bean.*;
import com.rmkj.microcap.common.modules.pay.shande2.http.ShanDeH5Api2;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean2;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service
public class ShanDeH5PayService2 {

    private final static Logger Log = Logger.getLogger(ShanDeH5PayService2.class);

    @HttpService
    private ShanDeH5Api2 shanDeH5Api2;

    @Autowired
    private MoneyService moneyService;

    /**
     * 杉德H5支付 去网页授权
     */
    public String shanDeH5AuthService(ShanDePrePayBean2 shanDePrePayBean2, HttpServletRequest request){
        ShanDeH5CommonResBean2 shanDeH5CommonResBean2 = null;

        try {
            ShanDeH5CommonReqBean2 shanDeH5CommonReqBean2 = new ShanDeH5CommonReqBean2();
            shanDeH5CommonReqBean2.setApp_id(ProjectConstants.SHAN_DE_MERCHANT_NO);
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","http://"+request.getServerName()+"/front/wap/money/getOpenId2?money="+shanDePrePayBean2.getMoney());
            String jsonUrl = JSON.toJSONString(map);
            shanDeH5CommonReqBean2.setBiz_content(jsonUrl);
            shanDeH5CommonReqBean2.setFormat("json");
            shanDeH5CommonReqBean2.setMethod("openapi.payment.auth.auth");
            shanDeH5CommonReqBean2.setNonce(UUID.randomUUID().toString());
            shanDeH5CommonReqBean2.setSign_method("md5");
            shanDeH5CommonReqBean2.setSign(Utils.md5(Utils.param(shanDeH5CommonReqBean2).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase());
            Log.info(JSON.toJSONString(shanDeH5CommonReqBean2));
            Response<String> execute = shanDeH5Api2.createShanDeH5Order(shanDeH5CommonReqBean2).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("杉德微信H5微信授权返回："+execute.body());
                shanDeH5CommonResBean2 = JSON.parseObject(xmlResult,ShanDeH5CommonResBean2.class);
                if("200".equals(shanDeH5CommonResBean2.getResult_code()) && "成功".equals(shanDeH5CommonResBean2.getResult_message())){
                    ShanDeH5AuthResBean2 authUrlBean = JSON.parseObject(shanDeH5CommonResBean2.getData(), ShanDeH5AuthResBean2.class);
                    return authUrlBean.getAuthUrl();
                }else{
                    return null;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 杉德微信，支付宝扫码支付
     */
    public ShanDeScanCodePayBean2 createScanCodePageService2(MoneyRecord moneyRecord, ShanDePrePayBean2 shanDePrePayBean2){
        ShanDeH5CommonReqBean2 shanDeH5CommonReqBean2 = new ShanDeH5CommonReqBean2();
        try {
            shanDeH5CommonReqBean2.setSign_method("md5");
            shanDeH5CommonReqBean2.setNonce(UUID.randomUUID().toString());
            shanDeH5CommonReqBean2.setMethod("openapi.payment.order.scan");
            shanDeH5CommonReqBean2.setFormat("json");
            shanDeH5CommonReqBean2.setApp_id(ProjectConstants.SHAN_DE_MERCHANT_NO);
            ShanDePayResultResBean2 shanDePayResultResBean2 = new ShanDePayResultResBean2();
            shanDePayResultResBean2.setMerchant_order_sn(moneyRecord.getSerialNo());
            shanDePayResultResBean2.setType(Integer.parseInt(shanDePrePayBean2.getType()));
            shanDePayResultResBean2.setTotal_fee(Float.parseFloat(shanDePrePayBean2.getMoney()));
            shanDeH5CommonReqBean2.setBiz_content(JSON.toJSONString(shanDePayResultResBean2));
            shanDeH5CommonReqBean2.setSign(Utils.md5(Utils.param(shanDeH5CommonReqBean2).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase());
            Response<String> execute = shanDeH5Api2.shanDeH5Pay(shanDeH5CommonReqBean2).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                ShanDeH5CommonResBean2 shanDeH5CommonResBean2 = JSON.parseObject(xmlResult,ShanDeH5CommonResBean2.class);
                ShanDeScanCodePayBean2 shanDeScanCodePayBean2 = JSON.parseObject(shanDeH5CommonResBean2.getData(),ShanDeScanCodePayBean2.class);
                shanDeScanCodePayBean2.setResult_code(shanDeH5CommonResBean2.getResult_code());
                shanDeScanCodePayBean2.setStatus("0");
                return  shanDeScanCodePayBean2;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return  null;
    }

    /**
     * 杉德H5支付，去公众号支付
     */
    public ShanDeH5PayParamBean2 shanDeH5PayService2(ShanDeH5OpenIdBean2 shanDeH5OpenIdBean2, String serialNo){

        ShanDeH5CommonReqBean2 shanDeH5CommonReqBean2 = new ShanDeH5CommonReqBean2();
        try {
            shanDeH5CommonReqBean2.setSign_method("md5");
            shanDeH5CommonReqBean2.setNonce(UUID.randomUUID().toString());
            shanDeH5CommonReqBean2.setMethod("openapi.payment.order.h5pay");
            shanDeH5CommonReqBean2.setFormat("json");
            shanDeH5CommonReqBean2.setApp_id(ProjectConstants.SHAN_DE_MERCHANT_NO);
            ShanDeH5PayReqBean2 shanDeH5PayReqBean2 = new ShanDeH5PayReqBean2();
            shanDeH5PayReqBean2.setMerchant_order_sn(serialNo);
            shanDeH5PayReqBean2.setOpen_id(shanDeH5OpenIdBean2.getOpen_id());
            shanDeH5PayReqBean2.setSub_open_id(shanDeH5OpenIdBean2.getSub_open_id());
            shanDeH5PayReqBean2.setTotal_fee(shanDeH5OpenIdBean2.getMoney());
            shanDeH5CommonReqBean2.setBiz_content(JSON.toJSONString(shanDeH5PayReqBean2));
            shanDeH5CommonReqBean2.setSign(Utils.md5(Utils.param(shanDeH5CommonReqBean2).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase());
            Response<String> execute = shanDeH5Api2.shanDeH5Pay(shanDeH5CommonReqBean2).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                ShanDeH5CommonResBean2 shanDeH5CommonResBean2 = JSON.parseObject(xmlResult,ShanDeH5CommonResBean2.class);
                ShanDeH5PayParamBean2 shanDeH5PayParamBean2 = JSON.parseObject(shanDeH5CommonResBean2.getData(),ShanDeH5PayParamBean2.class);
                return shanDeH5PayParamBean2;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 杉德异步回调
     */
    public ResponseEntity callBack(ShanDeH5PayCallBackCommonBean2 shanDeH5PayCallBackCommonBean2){
        Log.info("杉微信H5支付回调"+JSON.toJSONString(shanDeH5PayCallBackCommonBean2));
        if(StringUtils.isEmpty(shanDeH5PayCallBackCommonBean2.getData())){
            return new ResponseEntity("success",HttpStatus.OK);
        }
        if("200".equals(shanDeH5PayCallBackCommonBean2.getResult_code()) && "成功".equals(shanDeH5PayCallBackCommonBean2.getResult_message())){
            //验签,去除sign
            String callSign = shanDeH5PayCallBackCommonBean2.getSign();
            shanDeH5PayCallBackCommonBean2.setSign(null);
            String sign = Utils.md5(Utils.param(shanDeH5PayCallBackCommonBean2).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase();
            Log.info("本地构造签名 "+sign);
            if(!callSign.equals(sign)){
                Log.info("验证签名不通过！"+callSign);
                return new ResponseEntity("fail", HttpStatus.OK);
            }
            ShanDeH5PayCallBackResBean2 shanDeH5PayCallBackResBean2 = JSON.parseObject(shanDeH5PayCallBackCommonBean2.getData(),ShanDeH5PayCallBackResBean2.class);
            //判断能否根据第三方流水号查询到充值记录
            Boolean flag = moneyService.shanDeCallBack2(shanDeH5PayCallBackResBean2);
            if(flag){
                Log.info("flag:"+flag);
                return new ResponseEntity("success", HttpStatus.OK);
            }
        }
        return new ResponseEntity("fail", HttpStatus.OK);
    }
}
