package websockets;

import org.json.JSONObject;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class WebsocketClient {
    private final static String uri = "ws://145.93.160.187:8096/alien/";
    private Session session;
    private messageCreator messageCreator;

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
    }

    void sendMessage(JSONObject message) {
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

    public messageCreator getMessageCreator()
    {
        return messageCreator;
    }
}
