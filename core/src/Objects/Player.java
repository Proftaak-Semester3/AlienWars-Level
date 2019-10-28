package Objects;

import com.badlogic.gdx.graphics.Texture;

public class Player {

    Texture Player;
    float XPosition;
    float YPosition;
    int PlayerNumber;

    public Player(String File, float x, float y, int number) {
        Player = new Texture(File);
        XPosition = x;
        YPosition = y;
        PlayerNumber = number;
    }

    public Texture getTexture() {
        return Player;
    }

    public float getXPosition() { return XPosition; }
    public float getYPosition() { return YPosition; }

    public int getPlayerNumber() {
        return PlayerNumber;
    }
}
