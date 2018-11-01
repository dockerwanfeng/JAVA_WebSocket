package daomu.test;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/webSocketServer/{name}")
public class webSocketServer {

    {
        System.out.println("受到触发");
    }

    private Map<Integer, webSocketServer> map = new HashMap();
    private Session session;
    private String name;

    @OnOpen
    public void onOpen(@PathParam("name") String name, Session session) {
        this.session = session;
        this.name = name;
        map.put(session.hashCode(), this);
        System.out.println("建立连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到消息 message " + message);
        webSocketServer webSocketServer = map.get("14614891331014");
        RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
        System.out.println("自己: " + asyncRemote);
        asyncRemote.sendText(message);
    }

    @OnClose
    public void close() {
        map.remove(name);
        System.out.println("连接关闭");
    }

    @OnError
    public void onError(Session session, Throwable e) {
        System.out.println("收到" + map.get(session.hashCode()).name + "触发异常");
        e.printStackTrace();
    }
}
