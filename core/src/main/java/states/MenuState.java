package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import render.AlienDemo;


public class MenuState extends State implements InputProcessor {
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //  camera.update();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(255, 0, 0, 1);
        //shapeRenderer.line(0, 0, Gdx.input.getX(), Gdx.input.getY());
        shapeRenderer.line(0, 0, 500, 500);

        //shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //  camera.update();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(255, 0, 0, 1);
        //shapeRenderer.line(0, 0, Gdx.input.getX(), Gdx.input.getY());
        shapeRenderer.line(0, 0, 500, 500);

        //shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
