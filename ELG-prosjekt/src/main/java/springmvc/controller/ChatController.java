package springmvc.controller;

import java.util.Date;

import org.slf4j.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springmvc.domain.NewMessage;
import springmvc.domain.User;

@Controller
public class ChatController {
  
  @Autowired private SimpMessagingTemplate simpMessagingTemplate;
  
  //@Autowired
    //private HttpServletRequest context;
  
  /*
  @MessageMapping("/topic/message.borgar")
  public NewMessage filterMessage(@Payload NewMessage message) {
      System.out.println("from: " + message.getFrom());
      message.setFrom("abc123123");
      System.out.println("from: " + message.getFrom());
      return message;
    }
  */
  
  @MessageMapping("/chat.{username}")
  public void sendMessage(@Payload NewMessage message, @DestinationVariable("username") String username, SimpMessageHeaderAccessor headers) {  
        Map<String, Object> sessionHeaders = headers.getSessionAttributes();
	User u = (User) sessionHeaders.get("httpSession.user");
        message.setFrom(u.getEmail());
        simpMessagingTemplate.convertAndSend("/topic/message." + username, message);
    }
  
  @MessageMapping("/chat.OnlineUsers")
  public void getOnlineUsers(@Payload String person) {
        simpMessagingTemplate.convertAndSend("/topic/OnlineUsers", person);
    }
  
  
    
    @RequestMapping(value = "getuser" , method=RequestMethod.GET)
    public String getuser() {
        System.out.println("get USER");
        return "getuser";
    }
    
}

