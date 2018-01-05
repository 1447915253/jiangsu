import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderReqBean;
import com.rmkj.microcap.common.utils.Utils;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 123 on 2016/10/28.
 */
public class JSONTest {

    @Test
    public void t1(){
        UnifiedOrderReqBean unifiedOrderReqBean = new UnifiedOrderReqBean();
        unifiedOrderReqBean.setAppid("app_id");
        unifiedOrderReqBean.setMch_id("mch_id");
        unifiedOrderReqBean.setDevice_info("WEB");
        unifiedOrderReqBean.setNonce_str(Utils.uuid().toUpperCase());
        unifiedOrderReqBean.setSign("sign");
        unifiedOrderReqBean.setBody("body");
        unifiedOrderReqBean.setOut_trade_no("out_trade_no");
        unifiedOrderReqBean.setSpbill_create_ip("spbill_create_ip");
        unifiedOrderReqBean.setTrade_type("trade_type");
        unifiedOrderReqBean.setNotify_url(ProjectConstants.WEI_XIN_PAY_CALLBACK_URL);
        String s = JSON.toJSONString(unifiedOrderReqBean);
        JSONObject o = JSON.parseObject(s);
        Set<String> set = new TreeSet<>(o.keySet());
        for (String key : set){
            System.out.println(key);
        }
    }

    @Test
    public void t2(){
        System.out.println(JSON.toJSONString(new NotifyReqBean()));
    }
}
