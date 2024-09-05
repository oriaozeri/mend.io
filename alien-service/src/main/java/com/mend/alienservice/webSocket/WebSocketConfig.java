package com.mend.alienservice.webSocket;

import com.mend.alienservice.webSocket.AlienWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(alienWebSocketHandler(), "/ws/aliens").setAllowedOrigins("*");
    }

    @Bean
    public AlienWebSocketHandler alienWebSocketHandler() {
        return new AlienWebSocketHandler();
    }
}
