package com.rmkj.microcap.common.modules.trademarket.websocket;

import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.handler.SpringContextHolder;
import com.rmkj.microcap.common.modules.trademarket.GaoMarket;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketPro;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.apache.log4j.Logger;
import javax.websocket.*;
import java.util.Map;

/**
 * Created by renwp on 2017/4/19.
 */
@ClientEndpoint
public class GaoMarketClient {

    private Logger Log = Logger.getLogger(GaoMarketClient.class);

    private MarketServer marketServer;

    private MarketPro marketPro = new GaoMarket();

    @OnOpen
    public void onOpen(Session session) {
        Log.info("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject body = jsonObject.getJSONObject("body");
//        Log.info("webSocket: ".concat(body.toString()));
        String code = body.getString("StockCode");
        Map<String, Integer> precisions = TradeService.getPrecisions();
        // 高大总管数据
        final MarketPointBean marketPointBean = marketPro.toMarketPointBean(body, precisions.get(code));
        marketServer().putNew2Cache(marketPointBean, code);
    }

    private MarketServer marketServer(){
        if(marketServer == null){
            marketServer = SpringContextHolder.getBean(MarketServer.class);
        }
        return marketServer;
    }

/*    @OnClose
    public void onClose(){
        // 重启机制
        SpringContextHolder.getBean(GaoMarketServer.class).reStart();
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
        // 重启机制
        SpringContextHolder.getBean(GaoMarketServer.class).reStart();
    }*/

}
