package Websockets;

import Websockets.WebsocketClient;
import org.json.JSONObject;


public class messageCreator {
    private WebsocketClient webSocket;
    public messageCreator(WebsocketClient websocket)
    {
        this.webSocket = websocket;
    }

    public void createBulletMessage(float x, float y, int horizontal, int vertical, boolean player1Turn)
    {
        JSONObject json = new JSONObject();
        try
        {
            json.put("task", "bullet");
            json.put("x", x);
            json.put("y", y);
            json.put("horizontal", horizontal);
            json.put("vertical", vertical);
            json.put("player1turn", player1Turn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        webSocket.sendMessage(json);
    }

    public void close()
    {
        webSocket.closeConnection();
    }

/*    private void sendMessage(JsonObject json)
    {
        webSocket.sendMessage(json);
    }*/
}
