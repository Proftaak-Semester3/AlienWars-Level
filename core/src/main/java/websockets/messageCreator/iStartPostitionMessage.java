package websockets.messageCreator;

import org.json.JSONObject;

public interface iStartPostitionMessage {
    JSONObject startPositionMessage(int x1, int y1, int x2, int y2);
}
