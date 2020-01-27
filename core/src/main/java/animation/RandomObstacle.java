package animation;

import bullet.Bullets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import objects.BlackHole;
import states.GameStateManager;
import states.State;

public class RandomObstacle extends State {
    private BlackHole obstacle;
    public RandomObstacle(GameStateManager gsm) {
        super(gsm);
        obstacle = new BlackHole(780, 500);
        obstacle.loadTexture();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        obstacle.render(sb);
    }

    @Override
    public void dispose() {

    }

    public void updateBullet(Bullets bullet)
    {
        obstacle.checkBulletHit(bullet);
    }

    public void nextTurn()
    {
        obstacle.updatePosition();
    }
}
