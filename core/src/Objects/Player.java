package Objects;

import com.badlogic.gdx.graphics.Texture;

public class Player {

    Texture PlayerTexture;
    float XPosition;
    float YPosition;
    int PlayerNumber;

    public Player(String File, float x, float y, int number) {
        PlayerTexture = new Texture(File);
        XPosition = x;
        YPosition = y;
        PlayerNumber = number;
    }

    public Texture getTexture() {
        return PlayerTexture;
    }

    public float getXPosition() { return XPosition; }
    public float getYPosition() { return YPosition; }
}
