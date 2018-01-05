package com.rmkj.microcap.common.modules.trademarket.http;

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.trademarket.AbstractMarketService;
import com.rmkj.microcap.common.modules.trademarket.MarketDebugServer;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by renwp on 2017/4/19.
 * 最新行情通过webSocket获取
 */
public abstract class AbstractHttpMarketServer {

    @Autowired
    private MarketServer marketServer;


    @Autowired
    protected MarketDebugServer marketDebugServer;

    @Autowired
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor = null;

    @Autowired
    protected AbstractMarketService marketService;

    /**
     * 每隔一秒都去获取最新行情
     */
    @Scheduled(initialDelay = 10000, fixedRate = 100)
    private void _refreshNew(){
        // debug模式
        if(ProjectConstants.MARKET_DEBUG){
            String[] codes = marketServer._codes();
            if(codes.length != 0){
                List<MarketPointBean> latest = marketDebugServer.latest(codes);
                for (MarketPointBean point : latest){
                    marketServer.putNew2Cache(point, point.getCode());
                }
            }
        }else {
            // http请求获取行情
            for (String code : marketServer._codes()){
                threadPoolTaskExecutor.execute(() ->  {
                    refreshNew(code);
                });
            }
            if(marketServer.isInitNew()){
                marketServer.setInitNew(false);
            }
        }
    }

    /**
     * 获取最新行情数据
     */
    public abstract void refreshNew(String code);

    /**
     * 每隔一秒都去判断是否获取最新K线图，满足条件才发送请求
     * 分线图，秒=0
     * 5分K线图，秒=1，分%5=0
     * 15分K线图，秒=2，分%15=0
     * 30分K线图，秒=3，分%30=0
     * 1小时K线图，秒=4，分=0
     * 1天K线图，秒=5，分=0，时=0
     */
    @Scheduled(initialDelay = 10000, fixedRate = 1000)
    private void refreshKData(){
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String[] intervalsParams = reqIntervalsParams();
        boolean[] interval_init = marketServer.getInterval_init();
        // 分线图
        if(!interval_init[0] || second == 3){
            _refreshKData(intervalsParams[0], 0);
        }
        // 5分钟
        if(!interval_init[1] || (second == 4 && minute%5 == 0)){
            _refreshKData(intervalsParams[1], 1);
        }
        // 15分钟
        if(!interval_init[2] || (second == 5 && minute%15 == 0)){
            _refreshKData(intervalsParams[2], 2);
        }
        // 30分钟
        if(!interval_init[3] || (second == 6 && minute%30 == 0)){
            _refreshKData(intervalsParams[3], 3);
        }
        // 1小时
        if(!interval_init[4] || (second == 7 && minute == 0)){
            _refreshKData(intervalsParams[4], 4);
        }
        // 1天
        if(!interval_init[5] || (second == 8 && minute == 0 && hour == 0)){
            _refreshKData(intervalsParams[5], 5);
        }
        if(marketServer.isInitKData()){
            marketServer.setInitKData(false);
        }
    }

    private void _refreshKData(String interval, int position){
        // debug模式
        if(ProjectConstants.MARKET_DEBUG){
            String[] codes = marketServer._codes();
            if(codes.length != 0){
                Map<String, List<MarketPointBean>> codeListMap = marketDebugServer.latestKData(interval, Integer.parseInt(marketServer.getInterval_rows()[position]), codes);
                for (String code : codeListMap.keySet()){
                    marketServer.putKDate2Cache(code, interval, position, codeListMap.get(code));
                }
                marketServer.getInterval_init()[position] = true;
            }
        }else{
            // http请求获取行情
            for (String code : marketServer._codes()){
                threadPoolTaskExecutor.execute(() -> refreshKData(code, interval, position));
            }
        }
    }

    /**
     * 获取K线图数据
     * @param interval
     * @param position
     */
    public abstract void refreshKData(String code, String interval, int position);

    protected abstract String[] reqIntervalsParams();

}
