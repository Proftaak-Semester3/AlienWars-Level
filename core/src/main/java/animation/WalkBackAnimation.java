package animation;

import bullet.TurnHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import states.GameStateManager;
import states.State;

public class WalkBackAnimation extends State {

    private TurnHandler turnHandler;

    public WalkBackAnimation(GameStateManager gsm, TurnHandler turnHandler) {
        super(gsm);
        this.turnHandler = turnHandler;
        this.gsm = gsm;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if(turnHandler.getPlayer1().getOriginalPosition().x > turnHandler.getPlayer1().getXPosition())
        {
            turnHandler.getPlayer1().setInAnimation(true);
            Vector3 newPos = new Vector3();
            newPos.x = turnHandler.getPlayer1().getXPosition() + 1;
            newPos.y = turnHandler.getPlayer1().getYPosition();
            newPos.z = 0;
            turnHandler.getPlayer1().setPosition(newPos);

        }
        else if(turnHandler.getPlayer2().getOriginalPosition().x < turnHandler.getPlayer2().getXPosition())
        {
            turnHandler.getPlayer2().setInAnimation(true);
            Vector3 newPos = new Vector3();
            newPos.x = turnHandler.getPlayer2().getXPosition() - 1;
            newPos.y = turnHandler.getPlayer2().getYPosition();
            newPos.z = 0;
            turnHandler.getPlayer2().setPosition(newPos);
        }
        else
        {
            turnHandler.getPlayer1().setInAnimation(false);
            turnHandler.getPlayer1().setHit(false);
            turnHandler.getPlayer2().setInAnimation(false);
            turnHandler.getPlayer2().setHit(false);
        }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
