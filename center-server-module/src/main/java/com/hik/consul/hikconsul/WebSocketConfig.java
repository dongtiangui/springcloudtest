package com.hik.consul.hikconsul;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Bean
  public SocketIOServer socketIOServer() {
    com.corundumstudio.socketio.Configuration configuration =
        new com.corundumstudio.socketio.Configuration();
    configuration.setPort(8087);
    return new SocketIOServer(configuration);
  }

  @Bean
  public SpringAnnotationScanner springAnnotationScanner() {
    return new SpringAnnotationScanner(socketIOServer());
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setUserDestinationPrefix("/user");
    registry.enableSimpleBroker("/topic","/user");
  }
}
