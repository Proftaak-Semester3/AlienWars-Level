package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import render.AlienDemo;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private OrthographicCamera cam;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("Alien Wars Background.png");
        playBtn = new Texture("playBtn.png");
        cam = new OrthographicCamera(AlienDemo.WIDTH , AlienDemo.HEIGHT);
        cam.update();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched())
        {
            gsm.set(new LoginState(gsm));
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
        cam.position.set(new Vector3((float)((float)AlienDemo.WIDTH / 2d), ((int)((float)AlienDemo.HEIGHT / 2d)), 0));
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0 , 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        sb.draw(playBtn, (((int)((float)AlienDemo.WIDTH / 2d)) - ((int)((float)playBtn.getWidth() / 2d))), ((int)((float)AlienDemo.HEIGHT / 2d)) + 100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
