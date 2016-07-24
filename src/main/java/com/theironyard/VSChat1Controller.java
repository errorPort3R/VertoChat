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

    @Autowired
    UserRepository users;


    static SimpMessagingTemplate messenger;

    @Autowired
    public VSChat1Controller(SimpMessagingTemplate messenger)
    {
        this.messenger = messenger;
    }

    @MessageMapping("/topic/chat")
    @SendTo("/chat")
    public Message sendMessage(HttpSession session, Message msg)
    {
        String username = (String)session.getAttribute("username");
        User user = users.findByUsername(username);
        //Message mess = new Message(new String((byte[]) msg.getPayload()));
        //messages.save(mess);
        return msg;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Model model, @RequestParam String username, @RequestParam String password) throws Exception {

        User user = users.findByUsername(username);
        if (user == null) {
            user = new User(username, password);
            users.save(user);
        }
        else if (!password.equals(user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "";
    }
}