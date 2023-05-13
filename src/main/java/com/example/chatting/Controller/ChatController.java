package com.example.chatting.Controller;

import org.springframework.stereotype.Controller;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/chat")
public class ChatController extends Socket {
    private static final List<Session> session = new ArrayList<Session>();

    @OnOpen
    public void open(Session newUser){
        System.out.println("connected");
        session.add(newUser);
        System.out.println(newUser.getId());
    }

    @OnMessage
    public void getMsg(Session recieveSession, String msg){
        for(int i = 0; i< session.size(); i++){
            if(!recieveSession.getId().equals(session.get(i).getId())){
                try{
                    session.get(i).getBasicRemote().sendText("상대 : "+msg);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                try{
                    session.get(i).getBasicRemote().sendText("나 : "+msg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
