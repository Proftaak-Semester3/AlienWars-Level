package websockets.messageSender;

import org.json.JSONObject;

import javax.websocket.Session;

public class MessageBroadcaster {
    private static Session session;

    public MessageBroadcaster(Session session) {
        this.session = session;
    }

    public static void broadcast(JSONObject json) {
        try {
            session.getBasicRemote().sendText(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
