import com.rmkj.microcap.common.constants.RegexpConstants;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.utils.Digests;
import com.rmkj.microcap.common.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by 123 on 2016/10/18.
 */
public class DTest {
    @Test
    public void t1(){
        String str = "https://localhost:6060/xx/wap/loginPage";
        System.out.println(str.substring(str.indexOf("/", str.indexOf("//")+2)));
    }

    @Test
    public void t2(){
        Random random = new Random();
        System.out.println(new Date().getTime()+"_"+String.format("%1$03d",random.nextInt(1000)));
    }

    @Test
    public void t3(){
        Assert.assertTrue(Pattern.matches(RegexpConstants.WX_TRADE_TYPE, "JSAPI"));
    }

    @Test
    public void t4(){
        Date d = new Date();
        System.out.println(d.getTime());
        System.out.println(d.getTime()/1000);
    }

    @Test
    public void t5(){
        try {
            System.out.println(URLEncoder.encode("我们", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t6(){
        MarketPointBean marketPointBean = new MarketPointBean();
        marketPointBean.setCode("22222");

        marketPointBean = xxx(marketPointBean);
        System.out.println(marketPointBean.getCode());

        System.out.println(new BigDecimal(1.00).toString());
    }

    private MarketPointBean xxx(MarketPointBean marketPointBean){
        MarketPointBean marketPointBean1 = new MarketPointBean();
        marketPointBean1.setCode("1111");

        return marketPointBean;
    }

    @Test
    public void t7(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(instance.getTime().toLocaleString());

        Calendar instance1 = Calendar.getInstance();
        if(instance1.get(Calendar.DAY_OF_WEEK) == 1){
            instance1.add(Calendar.DAY_OF_WEEK, -1);
        }
        instance1.set(Calendar.DAY_OF_WEEK, 2);
        System.out.println(instance1.getTime().toLocaleString());
    }

    @Test
    public void t8(){
        System.out.println(new BigDecimal(434.33).subtract(new BigDecimal(1.0/100)));
    }

    @Test
    public void t9(){
        System.out.println(Boolean.toString(true));

        String s = Utils.InvitationCodeUtils.generateCode(6);
        System.out.println(s);
        System.out.println(Digests.backPassword("123456"));
    }

    @Test
    public void t10(){
        long l = System.currentTimeMillis();
        System.out.println("time:".concat(String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", new Date(1489528264000l))).concat("."+(l%1000)));



    }
}
