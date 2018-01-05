package com.rmkj.microcap.common.modules.trademarket.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.trademarket.GaoMarket;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketPro;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

/**
 * Created by 123 on 2016/10/27.
 * 行情服务
 * 定时更新行情数据、K线图数据
 */
@Service
@Lazy(false)
public class GaoMarketServer extends AbstractHttpMarketServer {

    @Autowired
    private MarketServer marketServer;

    @HttpService
    protected GaoLastestApi gaoLastestApi;

    @HttpService
    protected GaoKDataApi gaoKDataApi;

    private MarketPro marketPro = new GaoMarket();

    /**
     * 每隔一秒都去获取最新行情
     */
    public void refreshNew(String code){
        Response<String> response;
        String body = null;
        JSONObject jsonObject = null;
        try {
            response = gaoLastestApi.getPrice(code).execute();
            if(response.isSuccessful()){
                body = response.body();
                if(!"No Stock Data".equals(body)){
                    jsonObject = JSON.parseObject(body);
                    Map<String, Integer> precisions = TradeService.getPrecisions();
                    // 高大总管数据
                    final MarketPointBean marketPointBean = marketPro.toMarketPointBean(jsonObject, precisions.get(code));
                    marketServer.putNew2Cache(marketPointBean, code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshKData(String code, String interval, int position){
        // 获取最新行情
        Response<String> response;
        String body = null;
        List<MarketPointBean> list = null;
        // http请求获取行情
        try {
             response = gaoKDataApi.getChart(code, interval, marketServer.getInterval_rows()[position]).execute();
            Map<String, Integer> precisions = TradeService.getPrecisions();

            if(response.isSuccessful()){
                body = response.body();
//                Log.info(new Date().toLocaleString().concat(" ").concat(interval+" ").concat(body));
                list = toList(body, precisions.get(code));
//                System.out.println(marketServer.getInterval_rows()[position] + " " + list.size());
                if(!list.isEmpty()){
                    marketServer.putKDate2Cache(code, interval, position, list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MarketPointBean> toList(String jsonStr, Integer precision){
        List<List> list = JSON.parseArray(jsonStr, List.class);
        List<MarketPointBean> l = new ArrayList<>();
        MarketPointBean m = null;
        List<Object> s = null;
        for (int i = 0; i < list.size(); i ++){
            s = list.get(i);
            m = new MarketPointBean();
            m.setTimestamp(Long.parseLong(s.get(0)+""));
            m.setOpen(marketPro.filterNumber(Double.parseDouble(s.get(1)+""), precision));
            m.setHigh(marketPro.filterNumber(Double.parseDouble(s.get(2)+""), precision));
            m.setLow(marketPro.filterNumber(Double.parseDouble(s.get(3)+""), precision));
            m.setClose(marketPro.filterNumber(Double.parseDouble(s.get(4)+""), precision));
            m.setTime(new Date(m.getTimestamp()));
            l.add(m);
        }
        return l;
    }

    @Override
    protected String[] reqIntervalsParams() {
        return marketPro.reqIntervalsParams();
    }
}
