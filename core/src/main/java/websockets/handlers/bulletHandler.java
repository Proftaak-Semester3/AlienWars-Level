package websockets.handlers;

import org.json.JSONObject;
import states.GameStateManager;
import states.PlayState;

public class bulletHandler implements Handler {

    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        try {
            PlayState playState = (PlayState) gsm.getCurrentState();
            int x = json.getInt("x");
            int y = json.getInt("y");
            int horizontal = json.getInt("horizontal");
            int vertical = json.getInt("vertical");
            boolean player1turn = json.getBoolean("player1turn");
            playState.enemyMove(x, y, horizontal, vertical, player1turn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
