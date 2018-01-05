package com.rmkj.microcap.common.modules.trademarket.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.trademarket.HaiJieMarket;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketPro;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Response;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by 123 on 2016/10/27.
 * 行情服务
 * 定时更新行情数据、K线图数据
 */
//@Service
//@Lazy(false)
public class HaiJieMarketServer extends AbstractHttpMarketServer {

    private Logger Log = Logger.getLogger(HaiJieMarketServer.class);

    @Autowired
    private MarketServer marketServer;

    @Value("${market_icairon_uName}")
    protected String _uName;

    @Value("${market_icairon_uPass}")
    protected String _uPass;

    @HttpService
    protected MarketHttpApi marketHttpApi;

    private MarketPro marketPro = new HaiJieMarket();

    public void refreshNew(String code){
        Response<String> response;
        String body = null;
        JSONObject jsonObject = null;

        try {
            response = marketHttpApi.getPrice(_uName, _uPass, code).execute();
            if(response.isSuccessful()){
                body = response.body();
                jsonObject = JSON.parseObject(body);
                Map<String, Integer> precisions = TradeService.getPrecisions();
                if("1".equals(jsonObject.getString("Status"))){
                    final MarketPointBean marketPointBean = marketPro.toMarketPointBean(jsonObject.getJSONObject("Data"), precisions.get(code));
                    marketServer.putNew2Cache(marketPointBean, code);
                }else {
                    Log.error("行情：".concat(URLDecoder.decode(body, "utf-8")));
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
        JSONObject jsonObject = null;
        List<MarketPointBean> list = null;
        // http请求获取行情
        try {
            response = marketHttpApi.getChart(_uName, _uPass, code, interval, marketServer.getInterval_rows()[position]).execute();

            Map<String, Integer> precisions = TradeService.getPrecisions();

            if(response.isSuccessful()){
                body = response.body();
                if(body != null && body.length() > 2){
                    body = body.substring(1, body.length()-1);
                }
                list = toList(body, precisions.get(code));
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
        for (int i = 0; i < list.size(); i++){
            s = list.get(i);
            m = new MarketPointBean();
            m.setTimestamp(Long.parseLong(s.get(0)+""));
            m.setOpen(marketPro.filterNumber(s.get(1).toString(), precision));
            m.setHigh(marketPro.filterNumber(s.get(2).toString(), precision));
            m.setLow(marketPro.filterNumber(s.get(3).toString(), precision));
            m.setClose(marketPro.filterNumber(s.get(4).toString(), precision));
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
