package daomu.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class webSocketHandlerRegistry implements WebSocketHandler {

    private Map<Integer, WebSocketSession> map = new HashMap();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println(webSocketSession.getAttributes().get("name") + "连接建立");
        map.put(webSocketSession.hashCode(), webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        TextMessage textMessage = (TextMessage) webSocketMessage;
        String s = new String(textMessage.asBytes());
        System.out.println("收到消息 " + s);
        webSocketSession.sendMessage(textMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("发生异常");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("连接关闭");
        map.remove(webSocketSession.hashCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
