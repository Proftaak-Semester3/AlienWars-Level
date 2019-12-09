package websockets.websocket;

import org.json.JSONObject;
import states.GameStateManager;
import websockets.handlers.Handler;
import websockets.handlers.bulletHandler;
import websockets.handlers.confirmHandler;
import websockets.handlers.foundHandler;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class handlerContext {
    private static Map<String, Handler> handlers;
    private static GameStateManager gsm;

    static {
        handlers = new HashMap<>();
        handlers.put("bullet", new bulletHandler());
        handlers.put("startposition", new confirmHandler());
        handlers.put("found", new foundHandler());
    }

    public handlerContext(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    public static void processMessage(JSONObject json) {
        String handler = null;
        try {
            handler = json.getString("task");
        } catch (Exception e) {
            e.printStackTrace();
        }
        handlers.get(handler).handleMessage(json, gsm);
    }
}

