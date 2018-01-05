package com.rmkj.microcap.common.modules.pay.xinwenxin.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.baishi.bean.BaiShiCallBackBean;
import com.rmkj.microcap.common.modules.pay.baishi.service.BaiShiCallBackService;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanCallBackBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WebScanRespBean;
import com.rmkj.microcap.common.modules.pay.xinwenxin.utils.MD5Utils;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/13.
 */
@Service
public class WebScanPayService {

    private final static Logger Log = Logger.getLogger(WebScanPayService.class);

    @Autowired
    private MoneyService moneyService;

    /**
     * 新Web 扫码支付下单
     * @param moneyRecord
     */
    public String WebScanPay(MoneyRecord moneyRecord,HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        Map<String, String> map = new TreeMap<>();
        map.put("service", "pay.web"); //接口类型
        map.put("mch_id", ProjectConstants.NEW_WEB_MERCHANT_NO);//商户号
        map.put("total_fee",moneyRecord.getMoney().multiply(new BigDecimal(100)).toString());//金额
        map.put("out_trade_no", moneyRecord.getSerialNo()); //外部交易号
        map.put("callback_url","http://"+request.getServerName()+"/front/wap/user");
        map.put("body", "body"); //商品描述
        map.put("notify_url", ProjectConstants.NEW_WEB_NOTIFY_URL); //回调url
        String secret = ProjectConstants.NEW_WEB_KEY;
        String sign= MD5Utils.sign(map,secret,"UTF-8");
        Log.info("sign:"+sign);
        sb.append("<xml>\n" +
                "\t\t<service>pay.web</service>\n" +
                "\t\t<mch_id>"+ProjectConstants.NEW_WEB_MERCHANT_NO+"</mch_id>\n" +
                "\t<total_fee>"+moneyRecord.getMoney().multiply(new BigDecimal(100)).toString()+"</total_fee>\n" +
                "\t<callback_url>"+"http://"+request.getServerName()+"/front/wap/user"+"</callback_url>\n" +
                "\t<out_trade_no>" + moneyRecord.getSerialNo() + "</out_trade_no>\n" +
                "\t<notify_url>"+ProjectConstants.NEW_WEB_NOTIFY_URL+"</notify_url>\n" +
                "\t<body>body</body>\n" +
                "\t\t<sign>" + sign + "</sign>\n" +
                "</xml>\n");
        String data = sb.toString();
      //  System.out.print(data);
        Log.info("新Web扫码支付传递参数："+data);
        PostMethod post = new PostMethod("http://hxpay.coincard.cc/gate/com/index");//请求地址
        post.setRequestBody(data);//添加xml字符串

        // 指定请求内容的类型
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();//创建 HttpClient 的实例
        int result;
        String resp = null;
        try {
            result = httpclient.executeMethod(post);
            if (200==result){
                System.out.print("-------------" + post);
                System.out.println("Response status code: " + result);//返回200为成功
                System.out.println("getResponseBodyAsString()"+post.getResponseBodyAsString());//返回的内容
                resp=post.getResponseBodyAsString();
                post.releaseConnection();//释放连接

                JSONObject json=xmlString2Json(resp);
                JSONObject xmlJson= (JSONObject) json.get("xml");
                String xmlJsonStr=JSON.toJSONString(xmlJson);
                WebScanRespBean webScanRespBean=com.alibaba.fastjson.JSONObject.parseObject(xmlJsonStr,WebScanRespBean.class);
                String resultCode=webScanRespBean.getResult_code();
                resultCode = resultCode.substring(2,resultCode.length()-2);
                if("0".equals(resultCode)){
                    Log.info("新Web预支付返回参数："+xmlJsonStr);

                    String resultInfo=webScanRespBean.getResult_info();
                    Log.info("新Web支付resultInfo："+resultInfo);
                    String codeUrlstr = resultInfo.substring(3,resultInfo.length()-3);
                    String[] arr= codeUrlstr.split("\"");
                    String codeUrl=arr[2];
                    Log.info("新Web支付codeUrl:"+codeUrl);
                    java.net.URI uri = new java.net.URI(codeUrl);
                    return uri.toString();
                }
                return null;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * xml string字符串转换成JSON
     * @param xmlStr
     * @return
     */
    public static JSONObject xmlString2Json(String xmlStr){
        JSONObject json = new JSONObject();
        try {
            InputStream is = new ByteArrayInputStream(xmlStr.getBytes("utf-8"));
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(is);
            Element root = doc.getRootElement();
            json.put(root.getName(), iterateElement(root));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map  iterateElement(Element element) {
        List jiedian = element.getChildren();
        Element et = null;
        Map obj = new HashMap();
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }

    public ResponseEntity callBack(WebScanCallBackBean webScanCallBackBean){
        Log.info("新Web支付回调参数:"+ JSON.toJSONString(webScanCallBackBean));
        String resultCode = webScanCallBackBean.getResult_code();
        Log.info("新Web支付回调resultCode:"+resultCode);
        if("SUCCESS".equals(resultCode)){
            if("SUCCESS".equals(webScanCallBackBean.getReturn_code())){
                String orderID = webScanCallBackBean.getTransaction_id();
                Boolean flag = moneyService.WebScanCallBack(webScanCallBackBean,orderID);
                if(flag){
                    Log.info("新Web支付回调成功");
                    return new ResponseEntity(1, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(0, HttpStatus.OK);
    }
}
