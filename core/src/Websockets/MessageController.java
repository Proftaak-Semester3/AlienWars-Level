package Websockets;

import org.json.JSONObject;
import states.GameStateManager;
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

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
