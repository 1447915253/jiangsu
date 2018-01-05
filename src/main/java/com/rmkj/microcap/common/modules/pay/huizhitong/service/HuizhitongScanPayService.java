package com.rmkj.microcap.common.modules.pay.huizhitong.service;

import com.rmkj.microcap.common.modules.pay.huizhitong.util.HttpUtils;
import com.rmkj.microcap.common.modules.pay.huizhitong.util.MD5;
import com.rmkj.microcap.common.modules.pay.qianhongPay.util.PayUtil;
import com.rmkj.microcap.common.modules.pay.xinwenxin.service.WebScanPayService;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * Created by Administrator on 2018/1/2.
 */
@Service
public class HuizhitongScanPayService {
    private final static Logger Log = Logger.getLogger(HuizhitongScanPayService.class);
    //测试商户号秘钥
    private String mchIdTest = "10000036";
    private String keyTest = "fo73wefm424hd4zzt695avg1frrc0bm6";

    //测试环境接口
    private String url_dopayTest = "https://r630.speedpos.cn:17630/urlapi/scpay/dopayTest";
    private String url_orderqueryTest = "https://r630.speedpos.cn:17630/urlapi/scpay/orderqueryTest";

    public String HuizhitongQQPay(MoneyRecord moneyRecord) {



        String str = HttpUtils.httpSend(url_dopayTest,buildDoPay());
        System.out.println("str"+str);
      /*  post 请求的 map参数：{biz_content={"bill_create_ip":"127.0.0.1","body":"春季服装","mch_id":"1","notify_url":"http://wcmini.com/notify.php","out_order_no":"1514958437884","pay_platform":"SQPAY","payment_fee":"1000","remark":"测试"}, sign_type=MD5, signature=2174DBAF29757357CE3F91F4ECE3BE09}
*/
        return str;
    }

    /**
     * 构造支付参数
     *
     * @return
     */
    private TreeMap<String, Object> buildDoPay() {

        TreeMap<String, Object> map = new TreeMap<>();
        TreeMap<String, Object> map2 = new TreeMap<>();
        map.put("mch_id", mchIdTest);  //商户号
        map.put("out_order_no", PayUtil.generateOrderId());
        map.put("pay_platform", "SQPAY");  //支付平台
        map.put("payment_fee",1);  //总金额
        map.put("body", "春季服装");  //商品名称
        map.put("notify_url", "http://www.thqsj.cn/front/wap/user");  //通知地址
        map.put("bill_create_ip", "192.168.1.1");//正式交易请用正式外网IP地址   //终端IP
        String biz_content = JSONObject.fromObject(map).toString();
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
