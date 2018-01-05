package com.rmkj.microcap.common.modules.trademarket.websocket;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renwp on 2017/4/19.
 */
@Lazy(false)
@Service
public class GaoMarketServer {

    private Logger Log = Logger.getLogger(GaoMarketServer.class);

    @Autowired
    private MarketServer marketServer;

    // 止损止盈平仓 线程池
    @Autowired
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor = null;

//    private WebSocketContainer container = ContainerProvider.getWebSocketContainer();

    private Session session = null;

//    @PostConstruct
//    public void start(){
//        threadPoolTaskExecutor.execute(() -> {
//            try {
//                Thread.sleep(10000);
//                _new();
//            } catch (Exception e) {
//                e.printStackTrace();
//                reStart();
//            }
//        });
//    }
//
//    @PreDestroy
//    public void end(){
//        try {
//            if(session != null){
//                session.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

/*    private void _new() throws IOException, URISyntaxException, DeploymentException {
        // 连接会话
        session = container.connectToServer(GaoMarketClient.class, new URI(ProjectConstants.MARKET_NEW_WEBSOCKET_URL));
        Map<String, String> map = new HashMap<>();

        String[] codes = marketServer._codes();
        String _codes = "";
        for(String code : codes){
            if(!"".equals(_codes)){
                _codes = _codes.concat(",");
            }
            _codes = _codes.concat(code);
        }
        map.put("Key", _codes);
        map.put("event", "REG");
        session.getBasicRemote().sendText(JSON.toJSONString(map));
    }*/

    /**
     * 6秒之后重启
     */
/*    public void reStart(){
        Log.info("最新行情WebSocket重连");
        threadPoolTaskExecutor.execute(() -> {
            try {
                Thread.sleep(6000);
                _new();
            } catch (Exception e) {
                e.printStackTrace();
                reStart();
            }
        });
    }*/

}
