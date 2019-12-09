package bullet;

import com.badlogic.gdx.math.Vector2;

public class CollisionRect {
    private float x;
    private float y;
    private int width;
    private int height;
    private Vector2 center;

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Vector2 getCenter(){
        return center;
    }

    public void setCenter(Vector2 newCenter){
        x = newCenter.x - width / 2;
        y = newCenter.y - height / 2;
        center = newCenter;
    }

    public CollisionRect(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        center = new Vector2(x + width / 2, y + height / 2);
    }

    public void move (float x, float y)
    {
       this.x = x;
       this.y = y;
    }

    public boolean collidesWith(CollisionRect rect)
    {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }




}
