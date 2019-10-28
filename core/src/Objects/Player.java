package Objects;

import com.badlogic.gdx.graphics.Texture;

public class Player {

    Texture Player;
    float XPosition;
    float YPosition;

    public Player(String File, float x, float y) {
        Player = new Texture(File);
        XPosition = x;
        YPosition = y;
    }

    public Texture getTexture() {
        return Player;
    }

    public float getXPosition() { return XPosition; }
    public float getYPosition() { return YPosition; }
}
