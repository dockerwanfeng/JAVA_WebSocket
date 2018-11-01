package daomu.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket    //Enable将websocket管理进来
public class websocketconfig implements WebSocketConfigurer {

    @Autowired
    handshakeInterceptor handshakeInterceptor;
    @Autowired
    webSocketHandlerRegistry mywebSocketHandlerRegistry;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        WebSocketHandlerRegistration webSocketHandlerRegistration =
                webSocketHandlerRegistry.
                        addHandler(mywebSocketHandlerRegistry, "/websocket/*");

        webSocketHandlerRegistration.addInterceptors(handshakeInterceptor);
    }
}
