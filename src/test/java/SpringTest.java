import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.modules.money.out.service.JiuBaOutService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 123 on 2016/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class SpringTest {
    @Test
    public void te1(){
        System.out.println("1111111111");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar createTimeCalendar = Calendar.getInstance();
        createTimeCalendar.setTime(new Date());
        createTimeCalendar.add(Calendar.DAY_OF_MONTH,30);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,29);

        System.out.printf("00000000000000000000"+sdf.format(createTimeCalendar.getTime())+"00000000000000000000");
        System.out.printf("11111111111111111111111"+sdf.format(calendar.getTime())+"11111111111111111111");
    }

    @Before
    public void before(){
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
    }

    @Autowired
    JiuBaOutService jiuBaOutService;

    @Test
    public void te222(){
        System.out.println(JSON.toJSONString(jiuBaOutService.checkBankNo("6214835313565408")));
    }

}
