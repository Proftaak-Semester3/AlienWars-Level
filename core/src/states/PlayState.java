package states;

import Bullet.Bullet;
import Render.AlienDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Objects.Player;

import java.util.ArrayList;

public class PlayState  extends State{


    private Player player1;
    private Player player2;
    private Texture bg;

    private int playerSize = 60;

    private ArrayList<Bullet> bullets;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player1 = new Player("Bird.png", AlienDemo.WIDTH / 20, AlienDemo.HEIGHT / 20);
        player2 = new Player("Ornstein.jpg", AlienDemo.WIDTH - (AlienDemo.WIDTH / 20), AlienDemo.HEIGHT / 20);
        cam.setToOrtho(false, AlienDemo.WIDTH /2, AlienDemo.HEIGHT /2);
        bg = new Texture("melkweg.jpg");
        bullets = new ArrayList<>();


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                bullets.add(new Bullet(player1.getXPosition(), player1.getYPosition(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        
        cam.update();

        removeBullets(dt);
    }

    public void removeBullets(float dt){
        ArrayList<Bullet> bulletToRemove = new ArrayList<>();

        for (Bullet bullet: bullets) {
            bullet.update(dt);
            if(bullet.remove)
            {
                bulletToRemove.add(bullet);
            }
        }

        bullets.removeAll(bulletToRemove);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();

        sb.draw(bg, 0, 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        sb.draw(player1.getTexture(), player1.getXPosition(), player2.getYPosition(), playerSize, playerSize);
        sb.draw(player2.getTexture(), player2.getXPosition(), player2.getYPosition(), playerSize, playerSize);

        for (Bullet bullet: bullets) {
            bullet.render(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
