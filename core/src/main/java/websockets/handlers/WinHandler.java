package websockets.handlers;

import org.json.JSONObject;
import states.GameStateManager;
import states.PlayState;

public class WinHandler implements Handler {
    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        PlayState playState = (PlayState) gsm.getCurrentState();
        try {
            boolean win = json.getBoolean("win");
            if(win)
            {
                playState.win();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
