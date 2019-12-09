package websockets.handlers;

import org.json.JSONObject;
import states.GameStateManager;
import states.PlayState;

public class confirmHandler implements Handler {
    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        PlayState state = (PlayState) gsm.getCurrentState();
        boolean confirm = false;
        try {
            confirm = json.getBoolean("equal");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        state.confirmServer(confirm);
    }
}
