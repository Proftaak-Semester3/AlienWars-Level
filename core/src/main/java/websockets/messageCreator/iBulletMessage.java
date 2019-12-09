package websockets.messageCreator;

import org.json.JSONObject;

public interface iBulletMessage {
    JSONObject bulletMessage(float x, float y, int horizontal, int vertical, boolean player1Turn);
}
