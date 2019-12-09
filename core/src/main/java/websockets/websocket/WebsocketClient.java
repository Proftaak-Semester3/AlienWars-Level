package websockets.websocket;

import org.json.JSONObject;
import websockets.messageCreator.iJsonCreator;
import websockets.messageCreator.messageCreator;
import websockets.messageSender.MessageBroadcaster;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class WebsocketClient {
    private final static String uri = "ws://145.93.160.169:8096/alien/";
    private Session session;
    private iJsonCreator messageCreator;

 /*   public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                Session session = container.connectToServer(WebsocketC.class, new URI(uri));
                // Send a message
                messageCreator handler = new messageCreator();
                session.getBasicRemote().sendObject(handler.createBulletMessage(5,5,10,10,true));
                // Close session
                Thread.sleep(10000);
                session.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }*/

    public WebsocketClient() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                session = container.connectToServer(WebsocketC.class, new URI(uri));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Exception t) {
            t.printStackTrace();
        }
        messageCreator = new messageCreator(this);
        MessageBroadcaster broadcaster = new MessageBroadcaster(session);
    }

    public void sendMessage(JSONObject message) {
        try {
            session.getBasicRemote().sendObject(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public iJsonCreator getMessageCreator()
    {
        return messageCreator;
    }
}
