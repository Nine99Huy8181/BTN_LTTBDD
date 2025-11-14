/*
 * @ (#) WebSocketConfig.java     1.0    29-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import iuh.fit.fashionshop_be.config.CustomUserDetailsService;
import iuh.fit.fashionshop_be.config.AuthHandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:29-Oct-25
 * @version: 1.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final CustomUserDetailsService userDetailsService;
    private final String jwtSecret;

    public WebSocketConfig(CustomUserDetailsService userDetailsService, @Value("${jwt.secret}") String jwtSecret) {
        this.userDetailsService = userDetailsService;
        this.jwtSecret = jwtSecret;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // Gửi đến client
        config.setApplicationDestinationPrefixes("/app"); // Client gửi đến server
        config.setUserDestinationPrefix("/user"); // Gửi riêng cho user
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Use a custom handshake handler so we can authenticate based on the JWT sent during the SockJS/WebSocket handshake.
        AuthHandshakeHandler handshakeHandler = new AuthHandshakeHandler(userDetailsService, jwtSecret);

        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(handshakeHandler)
                .withSockJS();
    }
}