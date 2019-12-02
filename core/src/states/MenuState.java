package states;

import Render.AlienDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private OrthographicCamera cam;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("sea.jpg");
        playBtn = new Texture("playBtn.png");
        cam = new OrthographicCamera(AlienDemo.WIDTH , AlienDemo.HEIGHT);
        cam.update();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched())
        {
            gsm.set(new QueueState(gsm));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        cam.position.set(new Vector3((float)((double)AlienDemo.WIDTH / 2), (AlienDemo.HEIGHT / 2), 0));
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0 , 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        sb.draw(playBtn, ((AlienDemo.WIDTH / 2) - (playBtn.getWidth() / 2)), (AlienDemo.HEIGHT / 2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
