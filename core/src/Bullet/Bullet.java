package Bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Bullet {

    private static final int GRAVITY = -2;
    private static final int MOVEMENT = 0;

    private Vector3 position;
    private Vector3 velocity;
    private float velocityMultiplier = 3;

    private Texture texture;

    private int size = 30;

    CollisionRect rect;
    double down = 0.955;
    double Height = 1;

    public boolean remove = false;

    public Bullet(float x, float y, int horizontal, int vertical){
        position = new Vector3(x,y, 0);

        velocity = new Vector3(horizontal, vertical,0);

        this.rect = new CollisionRect(x, y, size, size);
        if(texture == null)
        {
            texture = new Texture("Tennisbal.png");
        }
    }

    public void update(float deltaTime){

        if(position.y > 0){
            velocity.add(0, velocityMultiplier * GRAVITY,0);}

        velocity.scl(deltaTime);
        position.add(velocityMultiplier * velocity.x, velocityMultiplier * 2 * velocity.y, 0);


        velocity.scl(1/deltaTime);

        if(position.y > Gdx.graphics.getHeight() || position.x > Gdx.graphics.getWidth() || position.x < 0 || position.y < -20)
        {
            remove = true;
        }
        rect.move(position.x, position.y);
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, position.x, position.y, size, size);
    }

    public CollisionRect getCollisionRect ()
    {
        return rect;
    }
}