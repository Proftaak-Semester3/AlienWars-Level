package websockets.handlers;

import org.json.JSONObject;
import states.GameStateManager;

public interface Handler {
    void handleMessage(JSONObject json, GameStateManager gsm);
}
