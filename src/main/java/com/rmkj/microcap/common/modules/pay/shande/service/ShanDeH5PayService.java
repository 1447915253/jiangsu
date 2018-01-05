package com.rmkj.microcap.common.modules.pay.shande.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.shande.bean.*;
import com.rmkj.microcap.common.modules.pay.shande.http.ShanDeH5Api;
import com.rmkj.microcap.common.modules.pay.shande.utils.Constants;
import com.rmkj.microcap.common.modules.pay.shande.utils.HttpClientUtil;
import com.rmkj.microcap.common.modules.pay.shande.utils.SignUtil;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.ShanDePrePayBean;
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
import java.util.*;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service
public class ShanDeH5PayService {

    private final static Logger Log = Logger.getLogger(ShanDeH5PayService.class);

    @HttpService
    private ShanDeH5Api shanDeH5Api;

    @Autowired
    private MoneyService moneyService;

    /**
     * 杉德H5支付 去网页授权
     */
    public String shanDeH5AuthService(ShanDePrePayBean shanDePrePayBean, HttpServletRequest request){

        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id",ProjectConstants.SHAN_DE_MERCHANT_NO);
        map.put("method","openapi.payment.auth.auth");
        map.put("format","json");
        map.put("sign_method","md5");
        map.put("nonce",UUID.randomUUID().toString());
        map.put("version","1.0");
        HashMap<String, Object> content = new HashMap<String, Object>();
        //content.put("merchant_order_sn",serialNo());
        //content.put("total_fee",shanDePrePayBean.getMoney());
        content.put("url","http://"+request.getServerName()+"/front/wap/money/getOpenId?money="+shanDePrePayBean.getMoney());
        //content.put("store_id","191929");
        //content.put("mchid",ProjectConstants.SHAN_DE_MCHID);
        String bizContent = JSON.toJSONString(content);
        map.put("biz_content",bizContent);
        String secret=ProjectConstants.SHAN_DE_SECRET;
        String sign = SignUtil.createSign(map, secret);
        map.putIfAbsent("sign",sign);
        Log.info("杉德授权传入的参数："+JSON.toJSONString(map));
        String post = HttpClientUtil.post(Constants.url, JSON.toJSONString(map));
        ShanDeH5CommonResBean shanDeH5CommonResBean = null;
        shanDeH5CommonResBean = JSON.parseObject(post,ShanDeH5CommonResBean.class);
            if("200".equals(shanDeH5CommonResBean.getResult_code()) && "成功".equals(shanDeH5CommonResBean.getResult_message())){
                ShanDeH5AuthResBean authUrlBean = JSON.parseObject(shanDeH5CommonResBean.getData(), ShanDeH5AuthResBean.class);
                Log.info("杉德授权返回URL："+authUrlBean.getAuthUrl());
                return authUrlBean.getAuthUrl();
            }else{
                return null;
            }
    }

    /**
     * 杉德微信，支付宝扫码支付
     */
    /*public ShanDeScanCodePayBean createScanCodePageService(MoneyRecord moneyRecord, ShanDePrePayBean shanDePrePayBean){
        ShanDeH5CommonReqBean shanDeH5CommonReqBean = new ShanDeH5CommonReqBean();
        try {
            shanDeH5CommonReqBean.setSign_method("md5");
            shanDeH5CommonReqBean.setNonce(UUID.randomUUID().toString());
            shanDeH5CommonReqBean.setMethod("openapi.payment.order.scan");
            shanDeH5CommonReqBean.setFormat("json");
            shanDeH5CommonReqBean.setApp_id(ProjectConstants.SHAN_DE_MERCHANT_NO);
            ShanDePayResultResBean shanDePayResultResBean = new ShanDePayResultResBean();
            shanDePayResultResBean.setMerchant_order_sn(moneyRecord.getSerialNo());
            shanDePayResultResBean.setType(Integer.parseInt(shanDePrePayBean.getType()));
            shanDePayResultResBean.setTotal_fee(Float.parseFloat(shanDePrePayBean.getMoney()));
            shanDeH5CommonReqBean.setBiz_content(JSON.toJSONString(shanDePayResultResBean));
            shanDeH5CommonReqBean.setSign(Utils.md5(Utils.param(shanDeH5CommonReqBean).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase());
            Response<String> execute = shanDeH5Api.shanDeH5Pay(shanDeH5CommonReqBean).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                ShanDeH5CommonResBean shanDeH5CommonResBean = JSON.parseObject(xmlResult,ShanDeH5CommonResBean.class);
                ShanDeScanCodePayBean shanDeScanCodePayBean = JSON.parseObject(shanDeH5CommonResBean.getData(),ShanDeScanCodePayBean.class);
                shanDeScanCodePayBean.setResult_code(shanDeH5CommonResBean.getResult_code());
                shanDeScanCodePayBean.setStatus("0");
                return  shanDeScanCodePayBean;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return  null;
    }*/
    public ShanDeScanCodePayBean createScanCodePageService(MoneyRecord moneyRecord, ShanDePrePayBean shanDePrePayBean){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id",ProjectConstants.SHAN_DE_MERCHANT_NO);
        map.put("method","openapi.payment.order.routescan");
        map.put("format","json");
        map.put("sign_method","md5");
        map.put("nonce",UUID.randomUUID().toString());
        map.put("version","1.0");
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("merchant_order_sn", moneyRecord.getSerialNo());
        content.put("total_fee",moneyRecord.getMoney());
        content.put("store_id","228953");
        content.put("mchid",ProjectConstants.SHAN_DE_MCHID);
        String bizContent = JSON.toJSONString(content);
        map.put("biz_content",bizContent);
        String secret=ProjectConstants.SHAN_DE_SECRET;
        String sign = SignUtil.createSign(map, secret);
        map.putIfAbsent("sign",sign);
        String post = HttpClientUtil.post(Constants.url, JSON.toJSONString(map));
        ShanDeH5CommonResBean shanDeH5CommonResBean = JSON.parseObject(post,ShanDeH5CommonResBean.class);
        if("200".equals(shanDeH5CommonResBean.getResult_code()) && "成功".equals(shanDeH5CommonResBean.getResult_message())){
            ShanDeScanCodePayBean shanDeScanCodePayBean = JSON.parseObject(shanDeH5CommonResBean.getData(),ShanDeScanCodePayBean.class);
            shanDeScanCodePayBean.setResult_code(shanDeH5CommonResBean.getResult_code());
            shanDeScanCodePayBean.setStatus("0");
            return  shanDeScanCodePayBean;
        }else{
            return null;
        }
    }

    public ShanDeScanCodePayBean createQQScanCodePageService(MoneyRecord moneyRecord, ShanDePrePayBean shanDePrePayBean){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id",ProjectConstants.SHAN_DE_MERCHANT_NO);
        map.put("method","openapi.payment.qqpay.scan");
        map.put("format","json");
        map.put("sign_method","md5");
        map.put("nonce",UUID.randomUUID().toString());
        map.put("version","1.0");
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("merchant_order_sn", moneyRecord.getSerialNo());
        content.put("total_fee",moneyRecord.getMoney());
        content.put("store_id","228953");
        content.put("mchid",ProjectConstants.SHAN_DE_MCHID);
        String bizContent = JSON.toJSONString(content);
        map.put("biz_content",bizContent);
        String secret=ProjectConstants.SHAN_DE_SECRET;
        String sign = SignUtil.createSign(map, secret);
        map.putIfAbsent("sign",sign);
        String post = HttpClientUtil.post(Constants.url, JSON.toJSONString(map));
        ShanDeH5CommonResBean shanDeH5CommonResBean = JSON.parseObject(post,ShanDeH5CommonResBean.class);
        if("200".equals(shanDeH5CommonResBean.getResult_code()) && "成功".equals(shanDeH5CommonResBean.getResult_message())){
            ShanDeScanCodePayBean shanDeScanCodePayBean = JSON.parseObject(shanDeH5CommonResBean.getData(),ShanDeScanCodePayBean.class);
            shanDeScanCodePayBean.setResult_code(shanDeH5CommonResBean.getResult_code());
            shanDeScanCodePayBean.setStatus("0");
            return  shanDeScanCodePayBean;
        }else{
            return null;
        }
    }

    /**
     * 杉德H5支付，去公众号支付
     */
    /*public ShanDeH5PayParamBean shanDeH5PayService(ShanDeH5OpenIdBean shanDeH5OpenIdBean, String serialNo){

        ShanDeH5CommonReqBean shanDeH5CommonReqBean = new ShanDeH5CommonReqBean();
        try {
            shanDeH5CommonReqBean.setSign_method("md5");
            shanDeH5CommonReqBean.setNonce(UUID.randomUUID().toString());
            shanDeH5CommonReqBean.setMethod("openapi.payment.order.h5pay");
            //shanDeH5CommonReqBean.setMethod("openapi.payment.public.auth");
            shanDeH5CommonReqBean.setFormat("json");
            shanDeH5CommonReqBean.setApp_id(ProjectConstants.SHAN_DE_MERCHANT_NO);
            ShanDeH5PayReqBean shanDeH5PayReqBean = new ShanDeH5PayReqBean();
            shanDeH5PayReqBean.setMerchant_order_sn(serialNo);
            shanDeH5PayReqBean.setOpen_id(shanDeH5OpenIdBean.getOpen_id());
            shanDeH5PayReqBean.setSub_open_id(shanDeH5OpenIdBean.getSub_open_id());
            shanDeH5PayReqBean.setTotal_fee(shanDeH5OpenIdBean.getMoney());
            shanDeH5CommonReqBean.setBiz_content(JSON.toJSONString(shanDeH5PayReqBean));
            shanDeH5CommonReqBean.setSign(Utils.md5(Utils.param(shanDeH5CommonReqBean).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase());
            Response<String> execute = shanDeH5Api.shanDeH5Pay(shanDeH5CommonReqBean).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                ShanDeH5CommonResBean shanDeH5CommonResBean = JSON.parseObject(xmlResult,ShanDeH5CommonResBean.class);
                ShanDeH5PayParamBean shanDeH5PayParamBean = JSON.parseObject(shanDeH5CommonResBean.getData(),ShanDeH5PayParamBean.class);
                return shanDeH5PayParamBean;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/
    public ShanDeH5PayParamBean shanDeH5PayService(ShanDeH5OpenIdBean shanDeH5OpenIdBean, String serialNo,HttpServletRequest request){

        ShanDeH5CommonReqBean shanDeH5CommonReqBean = new ShanDeH5CommonReqBean();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id",ProjectConstants.SHAN_DE_MERCHANT_NO);
        map.put("method","openapi.payment.public.auth");
        map.put("format","json");
        map.put("sign_method","md5");
        map.put("nonce",UUID.randomUUID().toString());
        map.put("version","1.0");
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("merchant_order_sn",serialNo);
        content.put("total_fee",shanDeH5OpenIdBean.getMoney());
        content.put("url","http://"+request.getServerName()+"/front/wap/user");
        content.put("store_id","228953");
        content.put("mchid",ProjectConstants.SHAN_DE_MCHID);
        String bizContent = JSON.toJSONString(content);
        map.put("biz_content",bizContent);
        String secret=ProjectConstants.SHAN_DE_SECRET;
        String sign = SignUtil.createSign(map, secret);
        map.putIfAbsent("sign",sign);
        Log.info("杉德支付的传入参数："+JSON.toJSONString(map));
        String post = HttpClientUtil.post(Constants.url, JSON.toJSONString(map));
        Log.info("杉德支付的返回参数："+post);
        ShanDeH5CommonResBean shanDeH5CommonResBean = null;
        shanDeH5CommonResBean = JSON.parseObject(post,ShanDeH5CommonResBean.class);
        if("200".equals(shanDeH5CommonResBean.getResult_code()) && "成功".equals(shanDeH5CommonResBean.getResult_message())){
            ShanDeH5AuthResBean authUrlBean = JSON.parseObject(shanDeH5CommonResBean.getData(), ShanDeH5AuthResBean.class);
            Log.info("杉德授权返回URL："+authUrlBean.getAuthUrl());
            ShanDeH5PayParamBean shanDeH5PayParamBean=new ShanDeH5PayParamBean();
            shanDeH5PayParamBean.setUrl(authUrlBean.getAuthUrl());
            return shanDeH5PayParamBean;
        }else{
            return null;
        }
    }

    /**
     * 杉德异步回调
     */
    public ResponseEntity callBack(ShanDeH5PayCallBackCommonBean shanDeH5PayCallBackCommonBean){
        Log.info("杉微信H5支付回调"+JSON.toJSONString(shanDeH5PayCallBackCommonBean));
        if(StringUtils.isEmpty(shanDeH5PayCallBackCommonBean.getData())){
            return new ResponseEntity("success",HttpStatus.OK);
        }
        if("200".equals(shanDeH5PayCallBackCommonBean.getResult_code()) && "成功".equals(shanDeH5PayCallBackCommonBean.getResult_message())){
            //验签,去除sign
            String callSign = shanDeH5PayCallBackCommonBean.getSign();
            shanDeH5PayCallBackCommonBean.setSign(null);
            String sign = Utils.md5(Utils.param(shanDeH5PayCallBackCommonBean).concat(ProjectConstants.SHAN_DE_SECRET)).toUpperCase();
            Log.info("本地构造签名 "+sign);
            if(!callSign.equals(sign)){
                return new ResponseEntity("fail", HttpStatus.OK);
            }
            ShanDeH5PayCallBackResBean shanDeH5PayCallBackResBean = JSON.parseObject(shanDeH5PayCallBackCommonBean.getData(),ShanDeH5PayCallBackResBean.class);
            //判断能否根据第三方流水号查询到充值记录
            String orderSn=queryOrderPost(shanDeH5PayCallBackResBean.getMerchant_order_sn());
            Boolean flag = moneyService.shanDeCallBack(shanDeH5PayCallBackResBean,orderSn);
            if(flag){
                return new ResponseEntity("success", HttpStatus.OK);
            }
        }
        return new ResponseEntity("fail", HttpStatus.OK);
    }

        /**
         * 获取第三方订单号
         * @return
         */
        public String queryOrderPost(String serialNo) {

            TreeMap<String, String> map = new TreeMap<>();
            map.put("app_id",ProjectConstants.SHAN_DE_MERCHANT_NO);
            map.put("method","openapi.payment.order.query");
            map.put("format","json");
            map.put("sign_method","md5");
            map.put("nonce",UUID.randomUUID().toString());
            map.put("version","1.0");

            HashMap<String, Object> content = new HashMap<String, Object>();
            content.put("merchant_order_sn",serialNo);
            //content.put("order_sn","20170613215411319531");
            //  content.put("trade_no","");
            map.put("biz_content", JSON.toJSONString(content));

            String secret=ProjectConstants.SHAN_DE_SECRET;
            String sign = SignUtil.createSign(map, secret);
            map.put("sign",sign);

            String queryOrderStr = HttpClientUtil.post(Constants.url, JSON.toJSONString(map));
            Log.info("杉德查询订单返回："+queryOrderStr);
            ShanDeH5CommonResBean shanDeH5CommonResBean = null;
            shanDeH5CommonResBean = JSON.parseObject(queryOrderStr,ShanDeH5CommonResBean.class);
            if("200".equals(shanDeH5CommonResBean.getResult_code()) && "成功".equals(shanDeH5CommonResBean.getResult_message())){
                ShanDeQueryOrderBean shanDeQueryOrderBean=JSON.parseObject(shanDeH5CommonResBean.getData(), ShanDeQueryOrderBean.class);
                return shanDeQueryOrderBean.getOrder_sn();
            }else{
                return null;
            }
        }
}
