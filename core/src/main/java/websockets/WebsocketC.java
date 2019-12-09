package websockets;

import org.json.JSONObject;

import javax.websocket.*;

@ClientEndpoint
public class WebsocketC {

    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("[Received]: " + message);
        JSONObject json = null;
        try {
            json = new JSONObject(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        MessageController.handleMessage(json);
    }
    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("[Closed]: " + reason);
    }
    @OnError
    public void onWebSocketError(Throwable cause) {
        System.out.println("[ERROR]: " + cause.getMessage());
    }

}
