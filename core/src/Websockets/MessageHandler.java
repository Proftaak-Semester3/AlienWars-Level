package Websockets;

import Websockets.WebsocketClient;
import org.json.JSONObject;


public class MessageHandler {
    private WebsocketClient webSocket;
    public MessageHandler(/*WebsocketClient websocket*/)
    {
        /*this.webSocket = websocket;*/
    }

    public JSONObject createBulletMessage(float x, float y, int horizontal, int vertical, boolean player1Turn)
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

        return json;
       /* sendMessage(json);*/
    }

/*    private void sendMessage(JsonObject json)
    {
        webSocket.sendMessage(json);
    }*/
}
