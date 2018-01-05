package com.rmkj.microcap.common.modules.trademarket;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.modules.market.service.MarketService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 123 on 2016/11/1.
 */
@Component
public class MarketDebugServer {

    private static final Logger Log = Logger.getLogger(MarketDebugServer.class);

    // 变化
    int [] pp = new int[]{3130, 47010, 2300};

    //开盘最高最低
    int [] pp1 = new int[]{3030, 47111, 2290};
    int [] pp2 = new int[]{3130, 47100, 2299};
    int [] pp3 = new int[]{3430, 47200, 2399};
    int [] pp4 = new int[]{3030, 47000, 2199};

    Random random = new Random();

    public List<MarketPointBean> latest(String[] codes){
        List<MarketPointBean> list = new ArrayList<>(codes.length);
        MarketPointBean marketPointBean = null;

        // 测试
        int [] ii = new int[]{1, 1, -1};

        for (int i = 0; i < codes.length; i++){
            marketPointBean = new MarketPointBean();
            pp[i] += ii[random.nextInt(3)] * random.nextInt(2);
            marketPointBean.setPrice(pp[i] + "");
            marketPointBean.setClose(pp1[i] + "");
            marketPointBean.setOpen(pp2[i] + "");
            marketPointBean.setHigh(pp3[i] + "");
            marketPointBean.setLow(pp4[i] + "");
            marketPointBean.setCode(codes[i]);
            marketPointBean.setTime(new Date());

            marketPointBean.setTimestamp(marketPointBean.getTime().getTime()/1000);

            list.add(marketPointBean);
        }

        return list;
    }

    public Map<String, List<MarketPointBean>> latestKData(String interval, int rows, String[] codes){
        Map<String, List<MarketPointBean>> map = new HashMap<>();
        for(String code : codes){
            if(StringUtils.isBlank(code)){
                continue;
            }
            map.put(code, new ArrayList<>(rows));
        }

        Calendar[] calendars = new Calendar[codes.length];
        for (int i = 0; i < codes.length; i++) {
            calendars[i] = Calendar.getInstance();
        }

        List<MarketPointBean> list = null;
        MarketPointBean marketPointBean = null;
        int[] points = null;
        String code = null;
        Date date = null;
        int minute = 0;
        Calendar calendar = null;
        int[] offPoints = new int[]{20, 100, 10};
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < codes.length; j++){
                code = codes[j];
                if(StringUtils.isBlank(code)){
                    continue;
                }
                list = map.get(code);
                if(list == null){
                    Log.warn(JSON.toJSONString(map.keySet()).concat(" ,找不到").concat(code));
                    continue;
                }
                marketPointBean = new MarketPointBean();
                points = random(4, i == 0 ? pp1[j] : Integer.parseInt(list.get(i-1).getClose()), offPoints[j]);

                calendar = calendars[j];
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if("1".equals(interval)){
                    calendar.add(Calendar.MINUTE, -1);
                }else if("5".equals(interval)){
                    minute = calendar.get(Calendar.MINUTE);
                    calendar.add(Calendar.MINUTE, i == 0 ? -minute%5  : -5);
                }else if("15".equals(interval)){
                    minute = calendar.get(Calendar.MINUTE);
                    calendar.add(Calendar.MINUTE, i == 0 ? -minute%15  : -15);
                }else if("30".equals(interval)){
                    minute = calendar.get(Calendar.MINUTE);
                    calendar.add(Calendar.MINUTE, i == 0 ? -minute%30  : -30);
                }else if("60".equals(interval)){
                    minute = calendar.get(Calendar.MINUTE);
                    calendar.add(Calendar.MINUTE, i == 0 ? -minute%60  : -60);
                }else if("1440".equals(interval)){
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                }
                date = calendar.getTime();
                setPoint(marketPointBean, points, date);
                list.add(marketPointBean);
            }
        }

        for(String _code : map.keySet()){
            List<MarketPointBean> _list = map.get(_code);
            List<MarketPointBean> _temp = new ArrayList<>();
            for(int i = _list.size() - 1; i >= 0 ; i--){
                _temp.add(_list.get(i));
            }
            map.put(_code, _temp);
        }

        return map;
    }

    /**
     * 随机K线点
     * @param count
     * @param fromPoint
     * @param offPoint
     * @return
     */
    private int[] random(int count, int fromPoint, int offPoint){
        int[] is = new int[count];
        for (int i = 0; i < count; i++){
            is[i] = random.nextInt(offPoint) - offPoint/2 + fromPoint;
        }
        Arrays.sort(is);
        return is;
    }

    private void setPoint(MarketPointBean marketPointBean, int[] points, Date date){
        marketPointBean.setHigh(points[3] + "");
        marketPointBean.setLow(points[0] + "");

        if(random.nextInt(2) == 0){
            marketPointBean.setClose(points[1] + "");
            marketPointBean.setOpen(points[2] + "");
        }else {
            marketPointBean.setClose(points[2] + "");
            marketPointBean.setOpen(points[1] + "");
        }
        marketPointBean.setTime(date);
        marketPointBean.setTimestamp(marketPointBean.getTime().getTime());
    }

}
