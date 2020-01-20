package objects;

import bullet.CollisionRect;
import com.badlogic.gdx.graphics.Texture;

public class Platform {

    CollisionRect collider;
    Texture texture;
    float xPosition;
    float yPosition;
    int width;
    int height;

    public CollisionRect getCollider() {
        return collider;
    }

    public Texture getTexture(){
        return texture;
    }

    public float getXPosition(){
        return xPosition;
    }

    public float getYPosition(){
        return yPosition;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Platform(float xPos, float yPos, int width, int height, String file){
        collider = new CollisionRect(xPos, yPos, width, height);
        texture = new Texture(file);

        xPosition = xPos;
        yPosition = yPos;
        this.width = width;
        this.height = height;
    }

}
