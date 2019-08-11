package com.sonic.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatRoomServerEndpoint
 *
 * @author Sonic
 * @since 2019/8/8
 */
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    private static Map<String, Session> liveSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        String sessionId = session.getId();
        liveSessions.put(sessionId, session);
        sendTextAll("Welcome + [ " + username + "]");
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, Session session, String message) {
//        sendText(session, "user + [ " + username + "] : " + message);
        sendTextAll("user + [ " + username + "] : " + message);
    }


    private void sendTextAll(String message) {
        liveSessions.forEach((sessionId, session)->{
            sendText(session,message);

        });
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        String sessionId = session.getId();
        //当前session移除
        liveSessions.remove(sessionId);
        //并且通知其他人当前用户已经离开聊天室了
        sendTextAll("user + [ " + username + "] 已经离开聊天室了");
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
