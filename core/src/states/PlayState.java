package states;

import Bullet.*;
import Render.AlienDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Objects.Player;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class PlayState  extends State{

    private TurnHandler turnHandler;
    private Texture bg;

    private int playerSize = 60;

    private ArrayList<Bullet> bullets;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        turnHandler = new TurnHandler();
        cam.setToOrtho(false, AlienDemo.WIDTH /2, AlienDemo.HEIGHT /2);
        bg = new Texture("melkweg.jpg");
        bullets = new ArrayList<>();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Player currentPlayer = turnHandler.GetCurrentPlayer();
                bullets.add(new Bullet(currentPlayer.getXPosition(), currentPlayer.getYPosition(), Gdx.input.getX() - (int)currentPlayer.getXPosition(), Gdx.graphics.getHeight() - Gdx.input.getY() - (int)currentPlayer.getYPosition(), turnHandler.player1turn()));
                turnHandler.SwitchTurn();
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
        for (Bullet bullet: bullets) {
/*            else if(player1.getCollisionRect().collidesWith(bullet.getCollisionRect()))
            {
                player1.updateHP((int)bullet.GetVelocity().x / 5);
            }*/
            if(turnHandler.getPlayer2().getCollisionRect().collidesWith(bullet.getCollisionRect()) && bullet.isPlayer1turn() && !bullet.hit)
            {
                turnHandler.getPlayer2().updateHP((int)bullet.GetVelocity().x / 50);
                bullet.hit = true;
                Vector3 vector3 = bullet.GetVelocity();
                Vector3 bounced = new Vector3();
                bounced.x = 0 - (vector3.x / 2);
                bounced.y = Math.round(vector3.y / 2) - 50;
                bounced.z = vector3.z;
                bullet.updateVelocity(bounced);
            }
            else if(turnHandler.getPlayer1().getCollisionRect().collidesWith(bullet.getCollisionRect()) && !bullet.isPlayer1turn() && !bullet.hit)
            {
                turnHandler.getPlayer1().updateHP(0 - ((int)bullet.GetVelocity().x / 50));
                bullet.hit = true;
                Vector3 vector3 = bullet.GetVelocity();
                Vector3 bounced = new Vector3();
                bounced.x = 0 - (vector3.x / 2);
                bounced.y = Math.round(vector3.y / 2) - 50;
                bounced.z = vector3.z;
                bullet.updateVelocity(bounced);
            }
        }
        turnHandler.getPlayer1().update(dt);
        turnHandler.getPlayer2().update(dt);

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
        sb.draw(turnHandler.getPlayer1().getTexture(), turnHandler.getPlayer1().getXPosition(), turnHandler.getPlayer1().getYPosition(), -playerSize, playerSize * 1.8f);
        sb.draw(turnHandler.getPlayer2().getTexture(), turnHandler.getPlayer2().getXPosition(), turnHandler.getPlayer2().getYPosition(), playerSize, playerSize * 1.8f);
        turnHandler.getPlayer1().getHealthbar().render(sb);
        turnHandler.getPlayer2().getHealthbar().render(sb);
        for (Bullet bullet: bullets) {
            bullet.render(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
