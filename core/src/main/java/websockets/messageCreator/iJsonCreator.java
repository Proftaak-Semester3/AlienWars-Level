package websockets.messageCreator;

import org.json.JSONObject;

public interface iJsonCreator extends iBulletMessage, iStartPostitionMessage {
    public JSONObject winMessage();
}
