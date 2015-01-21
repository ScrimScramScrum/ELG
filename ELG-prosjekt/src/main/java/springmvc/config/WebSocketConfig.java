package springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.*;



// @Configuration
@Controller
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "springmvc.controller")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
  //@Autowired
  // private SessionRepository sessionRepository;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/chat").withSockJS();
  }
}
