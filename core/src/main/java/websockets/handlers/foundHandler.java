package websockets.handlers;

import org.json.JSONObject;
import states.GameStateManager;
import states.QueueState;

public class foundHandler implements Handler {

    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        try {
            QueueState state = (QueueState) gsm.getCurrentState();
            boolean first = json.getBoolean("first");
            state.setFirstToFire(first);
            state.setMatchFound(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
