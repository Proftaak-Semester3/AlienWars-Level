package bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Bullets {

    private static final int GRAVITY = -3;

    private Vector3 position;
    private Vector3 velocity;

    private boolean hit = false;
    private boolean player1turn;

    private float velocityMultiplier = 3f;

    private Texture texture;

    private int size = 30;

    CollisionRect rect;

    private boolean remove = false;

    public Bullets(float x, float y, int horizontal, int vertical, boolean player1Turn){
        this.player1turn = player1Turn;
        int horizontalSpeed = 0;
        int verticalSpeed = Math.min(vertical, 330);
        if(player1Turn){
            horizontalSpeed = Math.min(horizontal, 550);
        }
        else{
            horizontalSpeed = Math.max(horizontal, -550);
        }

        position = new Vector3(x,y, 0);

        velocity = new Vector3(horizontalSpeed, verticalSpeed,0);

        this.rect = new CollisionRect(x, y, size, size);
    }

    public void update(float deltaTime){

        if(position.y > 0){
            velocity.add(0, velocityMultiplier* GRAVITY,0); }

        velocity.scl(deltaTime);
        position.add(velocityMultiplier* velocity.x, velocityMultiplier* velocity.y, 0);
        position.add(0, velocity.y, 0);

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
    public Vector3 getVelocity() { return velocity; }
    public void updateVelocity(Vector3 velocity) { this.velocity = velocity; }
    public boolean isPlayer1turn() { return  player1turn; }
    public CollisionRect getCollisionRect ()
    {
        return rect;
    }
    public boolean isHit() { return hit; }
    public void setHit(boolean hit) { this.hit = hit; }
    public Vector3 getPosition() {
        return position;
    }
    public void setTexture()
    {
        if(texture == null)
        {
            texture = new Texture("NormalBullet.png");
        }
    }
    public boolean isRemove() { return remove; }
    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
