package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Dan on 7/22/16.
 */


@RestController
public class VSChat1Controller
{

    static SimpMessagingTemplate messenger;

    @Autowired
    public VSChat1Controller(SimpMessagingTemplate messenger)
    {
        this.messenger = messenger;
    }

    @MessageMapping("/topic/chat")
    @SendTo("/chat")
    public Message sendMessage(Message msg)
    {
        //String username = (String)session.getAttribute("username");
        //User user = users.findByUsername(username);
        //Message mess = new Message(new String((byte[]) msg.getPayload()));
        //messages.save(mess);

        System.out.println(new String((byte[]) msg.getPayload()));
        return msg;
    }
}