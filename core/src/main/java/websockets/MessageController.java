package websockets;

import org.json.JSONObject;
import states.GameStateManager;
import states.PlayState;
import states.QueueState;

public final class MessageController {

    private static GameStateManager gsm;
    public MessageController(GameStateManager gsm)
    {
      MessageController.gsm = gsm;
    }

    public static void handleMessage(JSONObject json)
    {
        try {
            if (json.getString("task").equals("found")) {
                QueueState state = (QueueState) gsm.getCurrentState();
                boolean first = json.getBoolean("first");
                state.setFirstToFire(first);
                state.setMatchFound(true);
            }
            else if(json.getString("task").equals("bullet"))
            {
                PlayState playState = (PlayState) gsm.getCurrentState();
                int x = json.getInt("x");
                int y = json.getInt("y");
                int horizontal = json.getInt("horizontal");
                int vertical = json.getInt("vertical");
                boolean player1turn = json.getBoolean("player1turn");
                playState.enemyMove(x,y,horizontal,vertical,player1turn);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
