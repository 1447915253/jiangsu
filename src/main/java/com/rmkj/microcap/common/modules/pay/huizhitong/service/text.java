package com.rmkj.microcap.common.modules.pay.huizhitong.service;

import com.rmkj.microcap.common.modules.pay.huizhitong.util.HttpUtils;
import com.rmkj.microcap.common.modules.pay.huizhitong.util.MD5;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.PayUtil;
import net.sf.json.JSONObject;

import java.util.TreeMap;

/**
 * Created by Administrator on 2018/1/3.
 */
public class text {
    //测试商户号秘钥
    private String mchIdTest ="1";
    private String keyTest ="tjzs4aomh4i7rf80k09fbjy6w7ffu7kd";


    //正式环境接口
    private String url_dopay ="https://r630.speedpos.cn:17630/urlapi/scpay/dopay";
    private String url_orderquery ="https://r630.speedpos.cn:17630/urlapi/scpay/orderquery";



    //测试环境接口
    private String url_dopayTest = "https://r630.speedpos.cn:17630/urlapi/scpay/dopayTest";
    private String url_orderqueryTest = "https://r630.speedpos.cn:17630/urlapi/scpay/orderqueryTest";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        text main = new text();
        // 支付测试
        String str = HttpUtils.httpSend(main.url_dopay,main.buildDoPay());
        System.out.println(str);


    }

    /**
     * 构造支付参数
     *
     * @return
     */
    private TreeMap<String, Object> buildDoPay() {
        TreeMap<String, Object> map = new TreeMap<>();
        TreeMap<String, Object> map2 = new TreeMap<>();
        map.put("mch_id", mchIdTest);
        map.put("out_order_no", PayUtil.generateOrderId());
        map.put("pay_platform", "SQPAY");
        map.put("payment_fee",1);
        map.put("body", "春季服装");
        map.put("notify_url", "http://www.thqsj.cn/front/wap/user");
        map.put("bill_create_ip", "192.168.1.1");//正式交易请用正式外网IP地址
        String biz_content = JSONObject.fromObject(map).toString();
        map2.put("biz_content", biz_content);
        map2.put("signature", buildSign(map));
        map2.put("sign_type", "MD5");
        System.out.println("post 请求的 map参数：" + map2.toString());
        return map2;
    }

    /**
     * 构造查询参数
     *
     * @param out_order_no
     * @return
     */
    private TreeMap<String, Object> buildOrderquery(String out_order_no) {
        TreeMap<String, Object> map = new TreeMap<>();
        TreeMap<String, Object> map2 = new TreeMap<>();
        map.put("mch_id", mchIdTest);
        map.put("out_order_no", out_order_no);
        String biz_content = JSONObject.fromObject(map).toString();
        System.out.println("biz_content------------->" + biz_content);
        map2.put("biz_content", biz_content);
        map2.put("signature", buildSign(map));
        map2.put("sign_type", "MD5");
        System.out.println("post 请求的 map参数：" + map2.toString());
        return map2;
    }

    /**
     * 生成签名字段
     *
     * @param map
     * @return
     */
    private String buildSign(TreeMap<String, Object> map) {
        JSONObject dataobj = JSONObject.fromObject(map);
        String strPre = "biz_content=" + dataobj.toString() + "&key=" + keyTest;
        System.out.println("md5前字段---------->：" + strPre);
        String str = MD5.MD5Encode(strPre).toUpperCase();
        System.out.println("md5后字段---------->：" + str);
        return str;
    }

}
