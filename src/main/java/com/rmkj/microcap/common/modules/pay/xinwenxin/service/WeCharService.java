package com.rmkj.microcap.common.modules.pay.xinwenxin.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.modules.pay.shande.utils.SignUtil;
import com.rmkj.microcap.common.modules.pay.xinwenxin.bean.WeChatPubRespBean;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/11/13.
 */
@Service
public class WeCharService {

    private final static Logger Log = Logger.getLogger(WeCharService.class);

    /**
     * 微信支付 扫码支付下单
     * @param prePayBean
     * @param moneyRecord
     * @param openId
     */
    public String newPublicPay(WeChatPubRespBean prePayBean, MoneyRecord moneyRecord, String openId) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>\n" +
                "\t\t<service>pay.weixin.native</service>\n" +
                "\t\t <mch_id>197</mch_id>\n" +
                "\t <total_fee>" +101+ "</total_fee>\n" +
                "\t<out_trade_no>" + moneyRecord.getSerialNo() + "</out_trade_no>\n" +
                "\t<openid>"+openId+"</openid>\n" +
                "\t<body>body</body>  <notify_url>http://api.coincard.cc/pay_notify</notify_url>\n" +
                "\t\t<sign>" + queryOrderPostMd5(prePayBean, moneyRecord,openId) + "</sign>\n" +
                "</xml>\n");
        String data = sb.toString();
      //  System.out.print(data);

        PostMethod post = new PostMethod("http://demo2.novaszco.com/gate/com/index");//请求地址
        post.setRequestBody(data);//添加xml字符串

        // 指定请求内容的类型
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();//创建 HttpClient 的实例
        int result;
        String resp = null;
        try {
            result = httpclient.executeMethod(post);
            System.out.print("-------------" + post);
            System.out.println("Response status code: " + result);//返回200为成功
            System.out.println("getResponseBodyAsString()"+post.getResponseBodyAsString());//返回的内容
            resp=post.getResponseBodyAsString();

            post.releaseConnection();//释放连接
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }



    /**
     * 获取第三方订单号
     *
     * @return
     */
    public String queryOrderPostMd5(WeChatPubRespBean prePayBean, MoneyRecord moneyRecord, String openId) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "pay.weixin.native"); //接口类型
        map.put("mch_id", "197");//商品id
        map.put("total_fee","101");//金额
        map.put("out_trade_no", moneyRecord.getSerialNo()); //外部交易号
        map.put("openid", openId);
        map.put("body", "body"); //商品描述
        map.put("notify_url", "http://api.coincard.cc/pay_notify"); //回调url
        String secret = "8934e7d15453e97507ef794cf7b05196";
        String sign = SignUtil.createSign(map, secret);
        map.put("sign", sign);
        return sign;
    }

}
