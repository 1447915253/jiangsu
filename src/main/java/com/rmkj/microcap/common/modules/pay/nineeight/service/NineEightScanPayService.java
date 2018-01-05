package com.rmkj.microcap.common.modules.pay.nineeight.service;/**
 * Created by Administrator on 2017/3/20.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.nineeight.bean.*;
import com.rmkj.microcap.common.modules.pay.nineeight.utils.MD5Utils;
import com.rmkj.microcap.common.modules.pay.tonglian.bean.WeiFuTongScanResultBean;
import com.rmkj.microcap.common.modules.pay.tonglian.http.WeiFuTongScanPayApi;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.UnionPayOrderReq;
import com.rmkj.microcap.common.modules.pay.yizhifu.utils.XStreamTool;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.DateUtils;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.NineEightPrePayBean;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author k
 * @create -03-20-15:39
 **/
@Service
public class NineEightScanPayService {
    private static final Logger Log = Logger.getLogger(NineEightScanPayService.class);

    @Autowired
    private ParameterSetService parameterSetService;

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private UserService userService;

    /**
     * 98支付 微信扫码支付
     * @param moneyRecord
     */
    public NineEightScanPayResBean nineEightScanPayOrder(MoneyRecord moneyRecord, NineEightPrePayBean nineEightPrePayBean){

        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){

            parameterSetBean=list.get(0);
        }
        Map<String, String> param = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildRequestParam(false);
        param.put("service", "v1_scan_pay");
        param.put("version","1.0");
        param.put("charset","UTF-8");
        param.put("mch_no", ProjectConstants.NINE_EIGHT_PAY_MCH_ID);
        param.put("out_trade_no", moneyRecord.getSerialNo());
        param.put("order_subject", null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName());// 1-支付宝 2-微信
        param.put("total_fee", moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+"");
        param.put("notify_url",ProjectConstants.NINE_EIGHT_PAY_NOTIFY_URL);
        param.put("client_ip", nineEightPrePayBean.getSpbillCreateIp());
        param.put("acquirer_type", nineEightPrePayBean.getTradeType());
        param.put("nonce_str",Utils.uuid());
        param.put("req_time", DateUtils.formatDate(moneyRecord.getCreateTime(),"yyyyMMddHHmmss"));
        param.put("order_time",DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        Map<String, String> reqParam = new HashMap<String, String>();
        try {
            reqParam = MD5Utils.signMap(param, "MD5", ProjectConstants.NINE_EIGHT_PAY_KEY, "UTF-8");
            String url = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildUrl("scan/pay/gateway", reqParam);
            String result = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.receiveBySend(url, "utf-8");
            Log.info("充值下单".concat(result));
            return JSON.parseObject(result,NineEightScanPayResBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 98支付 微信扫码回调
     */
    public synchronized ResponseEntity getScanPayResult(NineEightScanPayResultResBean payResultReqBean) {
        Log.info("98微信扫码异步通知：".concat(JSON.toJSONString(payResultReqBean)));
//        NineEightScanPayResultResBean payResultReqBean = JSON.parseObject(resultStr,NineEightScanPayResultResBean.class);
        // 校验签名是否正确
        String reqSign = payResultReqBean.getSign();
        Map<String, String> param = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildRequestParam(false);
        param.put("version",payResultReqBean.getVersion());
        param.put("charset",payResultReqBean.getCharset());
        param.put("device_info",payResultReqBean.getDevice_info());
        param.put("resp_msg",payResultReqBean.getResp_msg());
        param.put("sign_type",payResultReqBean.getSign_type());
        param.put("trade_state",payResultReqBean.getTrade_state());
        param.put("out_trade_no",payResultReqBean.getOut_trade_no());
        param.put("trade_no",payResultReqBean.getTrade_no());
        param.put("mch_no",payResultReqBean.getMch_no());
        param.put("acquirer_type",payResultReqBean.getAcquirer_type());
        param.put("amount",payResultReqBean.getAmount());
        param.put("currency",payResultReqBean.getCurrency());
        param.put("attach",payResultReqBean.getAttach());
        param.put("resp_time",payResultReqBean.getResp_time());
        param.put("bank_trade_no",payResultReqBean.getBank_trade_no());
        param.put("trade_time",payResultReqBean.getTrade_time());
        param.put("resp_code",payResultReqBean.getResp_code());
        param.put("extend",payResultReqBean.getExtend());
        payResultReqBean.setSign(null);
        String sign = MD5Utils.signStr(param,"MD5",ProjectConstants.NINE_EIGHT_PAY_KEY,"UTF-8");
        Log.info("本地签名：" + sign + "    98微信扫码支付签名：" + reqSign);
        if(!reqSign.equals(sign)){
            Log.error("98微信扫码支付验签失败：".concat(payResultReqBean.toString()));
            return new ResponseEntity("fail", HttpStatus.OK);
        }else if(!moneyService.payResultOfNineEight(payResultReqBean)) {// 处理业务
            return new ResponseEntity("fail", HttpStatus.OK);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * 98支付 快捷银联支付
     */
    public QuickPayOrderResBean createQuickPayOrder(MoneyRecord moneyRecord, NineEightPrePayBean nineEightPrePayBean) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){

            parameterSetBean=list.get(0);
        }

        Map<String, String> param = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildRequestParam(false);
        param.put("service", "api.sd.quickPay");
        param.put("version", "2.0");
        param.put("merchant_no", ProjectConstants.NINE_EIGHT_PAY_MCH_ID);
        param.put("out_trade_no", moneyRecord.getSerialNo());
        param.put("body", null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName());
        param.put("total_fee", moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+"");
        param.put("notify_url",
                ProjectConstants.NINE_EIGHT_QUICK_PAY_NOTIFY_URL);
        param.put("client_ip",
                nineEightPrePayBean.getSpbillCreateIp());
        param.put("return_url","http://rrb.guantuanwang.com/front/wap/yizhifu/pay/yiZhiFuReturn");
        param.put("order_time", simpleDateFormat.format(new Date()));
        param.put("charset","UTF-8");
        param.put("time_expire","30");
        Map<String, String> reqParam = new HashMap<String, String>();
        String url = null;
        try {
            reqParam = MD5Utils.signMap(param, "MD5", ProjectConstants.NINE_EIGHT_PAY_KEY, "UTF-8");
            url = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildUrl("pay/gateway.shtml", reqParam);
            String result = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.receiveBySend(url, "UTF-8");
            Log.info("银联请求返回："+result);
            QuickPayOrderResBean quickPayOrderResBean = JSON.parseObject(result,QuickPayOrderResBean.class);
            return quickPayOrderResBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 98支付 银联快捷支付回调
     */
    public synchronized ResponseEntity getQuickPayResult(QuickPayOrderResBean payResultReqBean) {
        Log.info("98银联快捷支付异步通知：".concat(JSON.toJSONString(payResultReqBean)));

        // 校验签名是否正确
        String reqSign = payResultReqBean.getSign();
        Map<String, String> param = com.rmkj.microcap.common.modules.pay.nineeight.utils.Utils.buildRequestParam(false);
        param.put("version",payResultReqBean.getVersion());
        param.put("charset",payResultReqBean.getCharset());
        param.put("resp_code",payResultReqBean.getResp_code());
        param.put("resp_msg",payResultReqBean.getResp_msg());
        param.put("out_trade_no",payResultReqBean.getOut_trade_no());
        param.put("order_no",payResultReqBean.getOrder_no());
        param.put("total_amount",payResultReqBean.getTotal_amount());
        param.put("pay_amount",payResultReqBean.getPay_amount());
        param.put("disc_amount",payResultReqBean.getDisc_amount());
        param.put("pay_time",payResultReqBean.getPay_time());
        param.put("sign_type",payResultReqBean.getSign_type());
        payResultReqBean.setSign(null);
        String sign = MD5Utils.signStr(param,"MD5",ProjectConstants.NINE_EIGHT_PAY_KEY,"UTF-8");
        Log.info("本地签名：" + sign + "   98银联快捷支付签名：" + reqSign);
        if(!reqSign.equals(sign)){
            Log.error("98银联快捷支付验签失败：".concat(payResultReqBean.getSign()));
            return new ResponseEntity("fail", HttpStatus.OK);
        }else if(!moneyService.payResultOfNineEightQuickPay(payResultReqBean)) {// 处理业务
            return new ResponseEntity("fail", HttpStatus.OK);
        }

        return new ResponseEntity("success", HttpStatus.OK);
    }



}