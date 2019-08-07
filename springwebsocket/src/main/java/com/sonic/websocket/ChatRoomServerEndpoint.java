package com.sonic.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ChatRoomServerEndpoint
 *
 * @author Sonic
 * @since 2019/8/8
 */
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        sendText(session, "Welcome + [ " + username + "]");
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, Session session, String message) {
        sendText(session, "user + [ " + username + "] : " + message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //TODO
    }

    private void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();

        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
