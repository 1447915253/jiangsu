package com.rmkj.microcap.common.modules.pay.tonglian.service;/**
 * Created by Administrator on 2017/3/20.
 */

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.tonglian.bean.WeiFuTongScanCodeBean;
import com.rmkj.microcap.common.modules.pay.tonglian.bean.WeiFuTongScanResultBean;
import com.rmkj.microcap.common.modules.pay.tonglian.http.WeiFuTongScanPayApi;
import com.rmkj.microcap.common.modules.pay.weifutong.WeiFuTongPayService;
import com.rmkj.microcap.common.modules.pay.weifutong.bean.PrePayReqBean;
import com.rmkj.microcap.common.modules.pay.weifutong.bean.PrePayRespBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.utils.XStreamTool;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author k
 * @create -03-20-15:39
 **/
@Service
public class WeiFuTongScanPayService {
    private static final Logger Log = Logger.getLogger(WeiFuTongScanPayService.class);

    @HttpService
    private WeiFuTongScanPayApi weiFuTongScanPayApi;


    @Autowired
    private ParameterSetService parameterSetService;

    /**
     * 通联支付宝支付
     * @param moneyRecord
     * @param ip
     */
    public WeiFuTongScanResultBean WeiFuTongALiScanCodePay(MoneyRecord moneyRecord, String ip){

        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){

            parameterSetBean=list.get(0);
        }
        //封装扫码请求参数
        WeiFuTongScanCodeBean weiFuTongScanCodeBean = new WeiFuTongScanCodeBean();
        weiFuTongScanCodeBean.setService("pay.alipay.native");
        weiFuTongScanCodeBean.setVersion("2.0");
        weiFuTongScanCodeBean.setCharset("UTF-8");
        weiFuTongScanCodeBean.setMch_id(null == parameterSetBean.getWftBusinessNumber()? ProjectConstants.WEI_FU_TONG_PAY_MCH_ID:parameterSetBean.getWftBusinessNumber());   //商户号， 由威富通分配
        weiFuTongScanCodeBean.setOut_trade_no(moneyRecord.getSerialNo()); //商户系统内部的订单号
        weiFuTongScanCodeBean.setBody(null == parameterSetBean.getProductName() ? "四海国际":parameterSetBean.getProductName()); //商品描述
        weiFuTongScanCodeBean.setTotal_fee(moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue());
        weiFuTongScanCodeBean.setMch_create_ip(ip);
        weiFuTongScanCodeBean.setNotify_url(ProjectConstants.WEI_FU_TONG_PAY_NOTIFY_URL); //通知地址
        weiFuTongScanCodeBean.setNonce_str(Utils.uuid()); //随机字符串
        String sign = Utils.md5(Utils.param(weiFuTongScanCodeBean).concat("&key=").concat(null == parameterSetBean.getWftBusinessKey()? ProjectConstants.WEI_FU_TONG_PAY_KEY:parameterSetBean.getWftBusinessKey())).toUpperCase();
        weiFuTongScanCodeBean.setSign(sign);
        String reqXml = XStreamTool.toXml(weiFuTongScanCodeBean, WeiFuTongScanResultBean.class);
        Log.info("通联支付宝扫码请求" + reqXml);
        WeiFuTongScanResultBean resultBean = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(ProjectConstants.WEI_FU_TONG_PAY_URL);
            StringEntity entityParams = new StringEntity(reqXml, "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                resultBean = com.rmkj.microcap.common.modules.weixin.XStreamTool.toBean(new String(EntityUtils.toByteArray(response.getEntity()), "utf-8"), WeiFuTongScanResultBean.class);
            }
            // resultBean = geteway(reqXml);
        }catch (Exception e){
            e.printStackTrace();
            Log.error(reqXml);
            resultBean = new WeiFuTongScanResultBean();
        }
        return resultBean;
    }

    /**
     * 威富通扫码下单请求
     * @param moneyRecord
     * @param ip
     * @return
     */
    public WeiFuTongScanResultBean weiFuTongScanCodeRequest(MoneyRecord moneyRecord, String ip){

        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){

            parameterSetBean=list.get(0);
        }

        //封装扫码请求参数
        WeiFuTongScanCodeBean weiFuTongScanCodeBean = new WeiFuTongScanCodeBean();
        weiFuTongScanCodeBean.setService("pay.weixin.native");
        weiFuTongScanCodeBean.setVersion("2.0");
        weiFuTongScanCodeBean.setCharset("UTF-8");
        weiFuTongScanCodeBean.setMch_id(parameterSetBean==null? ProjectConstants.WEI_FU_TONG_PAY_MCH_ID:parameterSetBean.getWftBusinessNumber());   //商户号， 由威富通分配
        weiFuTongScanCodeBean.setOut_trade_no(moneyRecord.getSerialNo()); //商户系统内部的订单号
        weiFuTongScanCodeBean.setBody(parameterSetBean ==null?"四海国际":parameterSetBean.getProductName()); //商品描述

        weiFuTongScanCodeBean.setTotal_fee(moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue());
        weiFuTongScanCodeBean.setMch_create_ip(ip);
        weiFuTongScanCodeBean.setNotify_url(ProjectConstants.WEI_FU_TONG_PAY_NOTIFY_URL); //通知地址
        weiFuTongScanCodeBean.setNonce_str(Utils.uuid()); //随机字符串
       // weiFuTongScanCodeBean.setLimit_credit_pay("1"); //1微信支付时禁用信用卡支付

        String sign = Utils.md5(Utils.param(weiFuTongScanCodeBean).concat("&key=").concat(parameterSetBean==null? ProjectConstants.WEI_FU_TONG_PAY_KEY:parameterSetBean.getWftBusinessKey())).toUpperCase();
        weiFuTongScanCodeBean.setSign(sign);
        String reqXml = XStreamTool.toXml(weiFuTongScanCodeBean, WeiFuTongScanCodeBean.class);
        Log.info("通联微信扫码请求" + reqXml);
        WeiFuTongScanResultBean resultBean = null;

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(ProjectConstants.WEI_FU_TONG_PAY_URL);
            StringEntity entityParams = new StringEntity(reqXml, "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                resultBean = com.rmkj.microcap.common.modules.weixin.XStreamTool.toBean(new String(EntityUtils.toByteArray(response.getEntity()), "utf-8"), WeiFuTongScanResultBean.class);
            }
           // resultBean = geteway(reqXml);
        }catch (Exception e){
            e.printStackTrace();
            Log.error(reqXml);
            resultBean = new WeiFuTongScanResultBean();
        }
        return resultBean;
    }

    public WeiFuTongScanResultBean geteway(String reqXml){
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        WeiFuTongScanResultBean weiFuTongScanResultBean = null;
        try {
            HttpPost httpPost = new HttpPost(ProjectConstants.WEI_FU_TONG_PAY_URL);
            StringEntity entityParams = new StringEntity(reqXml, "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                weiFuTongScanResultBean = XStreamTool.toBean(new String(EntityUtils.toByteArray(response.getEntity()), "utf-8"), WeiFuTongScanResultBean.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(reqXml);
            weiFuTongScanResultBean = new WeiFuTongScanResultBean();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return weiFuTongScanResultBean;
    }
}
