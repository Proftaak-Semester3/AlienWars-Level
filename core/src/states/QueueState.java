package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QueueState extends State {
    private BitmapFont font;
    protected QueueState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        font.draw(sb, "Waiting for opponent", Gdx.graphics.getHeight() /2, (Gdx.graphics.getWidth()/2) - 30);
    }

    @Override
    public void dispose() {

    }


}
