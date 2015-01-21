package springmvc.controller;

import java.util.Date;

import org.slf4j.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import springmvc.domain.NewMessage;

@Controller
public class ChatController {
  /*
  @Autowired private SimpMessagingTemplate simpMessagingTemplate;
  
  @MessageMapping("/topic/message.borgar")
  public NewMessage filterMessage(@Payload NewMessage message) {
      System.out.println("from: " + message.getFrom());
      message.setFrom("abc123123");
      System.out.println("from: " + message.getFrom());
      return message;
    }
  
  @MessageMapping("/chat.{username}")
  public void sendMessage(@Payload NewMessage message, @DestinationVariable("username") String username) {
        System.out.println("from: " + message.getFrom());
        message.setFrom("borgar");
        System.out.println("from: " + message.getFrom());
        simpMessagingTemplate.convertAndSend("/topic/message." + username, message);
    }
  
  @MessageMapping("/chat.OnlineUsers")
  public void getOnlineUsers(@Payload String person) {
        simpMessagingTemplate.convertAndSend("/topic/OnlineUsers", person);
    }
  
  */
    
    @RequestMapping(value = "getuser" , method=RequestMethod.GET)
    public String getuser() {
        System.out.println("get USER");
        return "getuser";
    }
    
}

