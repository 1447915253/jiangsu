package com.rmkj.microcap.common.modules.pay.baishi.service;

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiPrePayBean;
import com.rmkj.microcap.common.modules.pay.baishi.util.ConfigUtils;
import com.rmkj.microcap.common.modules.pay.baishi.util.SSLClient;
import com.rmkj.microcap.common.modules.pay.baishi.util.SignUtils;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by M on 2017/10/23.
 */
@Service
public class BaiShiPayService {

    private static final Logger Log = Logger.getLogger(BaiShiPayService.class);

    @Autowired
    private ParameterSetService parameterSetService;

    public String baiShiScanPay(MoneyRecord moneyRecord, BaiShiPrePayBean prePayBean,HttpServletRequest request) throws Exception {
        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());
        ParameterSetBean parameterSetBean= null;
        if( list != null && list.size()>0){
            parameterSetBean=list.get(0);
        }
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://pay.bisrec.com/aggpay/gateway/api/pay");
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("TradeType","bis.pay.submit"));
        //nvps.add(new BasicNameValuePair("OperatorID","MD201706230000000090"));
        nvps.add(new BasicNameValuePair("PayType",prePayBean.getType()));
        //nvps.add(new BasicNameValuePair("PayType","0207"));
        //nvps.add(new BasicNameValuePair("TerminalType","PC"));
        nvps.add(new BasicNameValuePair("MerchantID",ProjectConstants.BAI_SHI_MERCHANT_NO));
        nvps.add(new BasicNameValuePair("OrderID",moneyRecord.getSerialNo()));
        //nvps.add(new BasicNameValuePair("DeviceID","21"));
        //nvps.add(new BasicNameValuePair("OpenID","oVRDcjifg23KaZzaqmK6ktb-0lRM"));
        nvps.add(new BasicNameValuePair("Subject",null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName()));
        nvps.add(new BasicNameValuePair("MachineIP",prePayBean.getSpbillCreateIp()));
        nvps.add(new BasicNameValuePair("NotifyUrl",ProjectConstants.BAI_SHI_NOTIFY_URL));
        nvps.add(new BasicNameValuePair("ReturnUrl","http://"+request.getServerName()+"/front/wap/user"));
        nvps.add(new BasicNameValuePair("SubmitTime",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        //nvps.add(new BasicNameValuePair("CreditLimit","0"));
        nvps.add(new BasicNameValuePair("Amt",moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+""));
        //nvps.add(new BasicNameValuePair("Summary","支付测试"));
        nvps.add(new BasicNameValuePair("Sign", SignUtils.signData(nvps)));
        try {
            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse resp = httpClient.execute(postMethod);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
            int statusCode = resp.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                boolean signFlag = SignUtils.verferSignData(str);
                if (!signFlag) {
                    Log.info("百时扫码支付验签失败");
                    return null;
                }
                Log.info("百时扫码支付验签成功");
                JSONObject resObject = ConfigUtils.getJSON(str);
                String urlString = resObject.has("CodeImgUrl") ? resObject.getString("CodeImgUrl").trim():"";
                if (urlString.length()>0) {
                    java.net.URI uri = new java.net.URI(urlString);
                    Log.info("百时微信扫码支付url："+uri.toString());
                    return uri.toString();
                }
                return null;
            }
            Log.info("百时扫码支付返回错误码:" + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String baiShiWeChatPubPay(MoneyRecord moneyRecord, BaiShiPrePayBean prePayBean,HttpServletRequest request) throws Exception {
        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());
        ParameterSetBean parameterSetBean= null;
        if( list != null && list.size()>0){
            parameterSetBean=list.get(0);
        }
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://pay.bisrec.com/aggpay/gateway/api/pay");
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("TradeType","bis.pay.submit"));
        //nvps.add(new BasicNameValuePair("OperatorID","MD201706230000000090"));
        nvps.add(new BasicNameValuePair("PayType",prePayBean.getType()));
        //nvps.add(new BasicNameValuePair("TerminalType","PC"));
        nvps.add(new BasicNameValuePair("MerchantID",ProjectConstants.BAI_SHI_MERCHANT_NO));
        nvps.add(new BasicNameValuePair("OrderID",moneyRecord.getSerialNo()));
        //nvps.add(new BasicNameValuePair("DeviceID","21"));
        nvps.add(new BasicNameValuePair("Subject",null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName()));
        nvps.add(new BasicNameValuePair("MachineIP",prePayBean.getSpbillCreateIp()));
        nvps.add(new BasicNameValuePair("NotifyUrl",ProjectConstants.BAI_SHI_NOTIFY_URL));
        nvps.add(new BasicNameValuePair("ReturnUrl","http://"+request.getServerName()+"/front/wap/user"));
        nvps.add(new BasicNameValuePair("SubmitTime",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        //nvps.add(new BasicNameValuePair("CreditLimit","0"));
        nvps.add(new BasicNameValuePair("Amt",moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+""));
        //nvps.add(new BasicNameValuePair("Summary","支付测试"));
        nvps.add(new BasicNameValuePair("Sign", SignUtils.signData(nvps)));
        try {
            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse resp = httpClient.execute(postMethod);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
            int statusCode = resp.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                boolean signFlag = SignUtils.verferSignData(str);
                if (!signFlag) {
                    Log.info("百时微信公众号支付验签失败");
                    return null;
                }
                Log.info("百时微信公众号支付验签成功");
                JSONObject resObject = ConfigUtils.getJSON(str);
                String urlString = resObject.has("PayUrl") ? resObject.getString("PayUrl").trim():"";
                if (urlString.length()>0) {
                    java.net.URI uri = new java.net.URI(urlString);
                    Log.info("百时微信公众号支付url："+uri.toString());
                    return uri.toString();
                }
                return null;
            }
            Log.info("百时微信公众号支付返回错误码:" + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
