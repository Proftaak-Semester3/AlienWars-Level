package websockets.messageCreator;

import org.json.JSONObject;
import websockets.websocket.WebsocketClient;


public class MessageCreator implements iJsonCreator {
    private WebsocketClient webSocket;
    public MessageCreator(WebsocketClient websocket)
    {
        this.webSocket = websocket;
    }

    public void close()
    {
        webSocket.closeConnection();
    }

    @Override
    public JSONObject bulletMessage(float x, float y, int horizontal, int vertical, boolean player1Turn) {
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
    }

    @Override
    public JSONObject startPositionMessage(int x1, int y1, int x2, int y2) {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "startposition");
            json.put("x1", x1);
            json.put("y1", y1);
            json.put("x2", x2);
            json.put("y2", y2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
