package websocket;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

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
public class WebSocketTest {

    @Test
    public void t1(){
        // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://data.ronmei.com:8888/ws";
        // 连接会话
        Session session = null;
        try {
            session = container.connectToServer(Client.class, new URI(uri));
            Map<String, String> map = new HashMap<>();
            map.put("Key", "YKCMASTER2");
            map.put("event", "REG");
            System.out.println(JSON.toJSONString(map));
            // 发送文本消息
            session.getBasicRemote().sendText(JSON.toJSONString(map));

            Thread.sleep(10000);
            session.close();
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
