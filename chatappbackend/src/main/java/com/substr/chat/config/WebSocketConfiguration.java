package com.substr.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        // "/topic/messages"
        config.enableSimpleBroker("/topic");
        // "/app/chat"
        // server-side: @MessageingMapping("/chat)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // "/chat endpoint par connection aka establish hoga"
        registry.addEndpoint("/chat") // Connection Establishment
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }
}
