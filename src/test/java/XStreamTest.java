import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.XStreamTool;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinResult;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderRespBean;
import com.rmkj.microcap.common.utils.Utils;
import com.thoughtworks.xstream.XStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 123 on 2016/10/28.
 */
public class XStreamTest {

    @Test
    public void t1(){
        XStream xStream = new XStream();

        xStream.alias("xml", UnifiedOrderRespBean.class);

        xStream.addDefaultImplementation(UnifiedOrderRespBean.class, WeiXinResult.class);

        UnifiedOrderRespBean respBean = (UnifiedOrderRespBean) xStream.fromXML("<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>");
        Assert.assertTrue(respBean.success());
    }

    @Test
    public void t2(){
        UnifiedOrderRespBean respBean = (UnifiedOrderRespBean) XStreamTool.toBean("<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>", UnifiedOrderRespBean.class);
        Assert.assertTrue(respBean.success());
    }

    @Test
    public void t3(){
        UnifiedOrderReqBean unifiedOrderReqBean = new UnifiedOrderReqBean();
        unifiedOrderReqBean.setAppid("11");
        unifiedOrderReqBean.setMch_id("121");
        unifiedOrderReqBean.setDevice_info("212");
        unifiedOrderReqBean.setNonce_str(Utils.uuid().toUpperCase());
        unifiedOrderReqBean.setBody("111");
        unifiedOrderReqBean.setOut_trade_no("2222222");
        unifiedOrderReqBean.setSpbill_create_ip("233333333333");

        String xml = XStreamTool.toXml(unifiedOrderReqBean, UnifiedOrderReqBean.class);
        System.out.println(xml);
    }

    @Test
    public void t4(){
        System.out.println(Utils.uuid());
    }

    @Test
    public void t5(){
        XStreamTool.toBean("<xml>\n</xml>", NotifyReqBean.class);

        UnifiedOrderRespBean respBean = XStreamTool.toBean("<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>", UnifiedOrderRespBean.class);
        Assert.assertTrue(respBean.success());
    }
}
