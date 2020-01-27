package objects;

import bullet.Bullets;
import bullet.CollisionRect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class BlackHole {
    private Vector3 position;
    private Vector3 innerPosition;
    private Texture hole;
    private Texture full;
    private CollisionRect rect;
    private Random random;
    public BlackHole(int x, int y)
    {
        Vector3 start = new Vector3(x,y,0);
        position = start;
        innerPosition.x = start.x + 193;
        innerPosition.y = start.y - 265;
        rect = new CollisionRect(innerPosition.x, innerPosition.y, 206, 216);
        random = new Random();
    }
    public void render(SpriteBatch sb)
    {
        sb.draw(full, position.x, position.y);
        sb.draw(hole, innerPosition.x, innerPosition.y);

    }
    public void updatePosition()
    {
        Vector3 update = new Vector3(540, 500 + random.nextInt(200), 0);
        position = update;
        innerPosition.x = update.x + 193;
        innerPosition.y = update.y - 265;
        rect.move(innerPosition.x, innerPosition.y);
    }
    public void loadTexture() { hole = new Texture("Hole.png"); full = new Texture("BlackFull.png"); }
    public void checkBulletHit(Bullets bullet)
    {
        if(bullet.getCollisionRect().collidesWith(rect)) {
            Vector3 newpos = new Vector3(760 + random.nextInt(400), 200 + random.nextInt(880), 0);
            bullet.setPosition(newpos);
        }
    }
}
