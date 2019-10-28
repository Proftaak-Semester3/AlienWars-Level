package states;

import Bullet.*;
import Render.AlienDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Objects.Player;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class PlayState  extends State{

    private TurnHandler turnHandler;
    private Texture bg;
    private OrthographicCamera cam;

    private int playerSize = 60;

    private ArrayList<Bullet> bullets;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        turnHandler = new TurnHandler();
        cam = new OrthographicCamera(AlienDemo.WIDTH /1.2F , AlienDemo.HEIGHT / 1.2F);
        cam.update();
        bg = new Texture("melkweg.jpg");
        bullets = new ArrayList<>();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Player currentPlayer = turnHandler.getCurrentPlayer();

                Vector3 convertedInputPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                if(bullets.size() <= 0){
                    bullets.add(new Bullet(currentPlayer.getXPosition(), currentPlayer.getYPosition(), (int) (convertedInputPosition.x - currentPlayer.getXPosition()), (int) (convertedInputPosition.y - currentPlayer.getYPosition()), turnHandler.player1turn()));
                    turnHandler.SwitchTurn();
                }
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

    private void UpdateCameraPosition(){
        Vector3 camPosition = new Vector3();

        float camMinX = cam.viewportWidth / 2;
        float camMinY = cam.viewportHeight / 2;

        float camMaxX = AlienDemo.WIDTH - (cam.viewportWidth / 2);
        float camMaxY = AlienDemo.HEIGHT - (cam.viewportHeight / 2);

        if(bullets.size() != 0){
            cam.position.set(bullets.get(0).getPosition().x, bullets.get(0).getPosition().y, 0);
        }
        else{
            cam.position.set(turnHandler.getCurrentPlayer().getXPosition(), turnHandler.getCurrentPlayer().getYPosition(), 0);
        }

        camPosition.x = Math.min(camMaxX, Math.max(camMinX, cam.position.x));
        camPosition.y = Math.min(camMaxY, Math.max(camMinY, cam.position.y));

        cam.position.set(camPosition);
    }

    @Override
    public void render(SpriteBatch sb) {

        UpdateCameraPosition();

        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, AlienDemo.WIDTH , AlienDemo.HEIGHT);
        sb.draw(turnHandler.getPlayer1().getTexture(), turnHandler.getPlayer1().getXPosition(), turnHandler.getPlayer1().getYPosition(), -playerSize, playerSize * 1.8f);
        sb.draw(turnHandler.getPlayer2().getTexture(), turnHandler.getPlayer2().getXPosition(), turnHandler.getPlayer2().getYPosition(), playerSize, playerSize * 1.8f);

        for (Bullet bullet: bullets) {
            bullet.render(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
