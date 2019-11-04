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
    private Texture textureship;
    private OrthographicCamera cam;
    private GameStateManager gsm;
    private boolean justonce;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private int playerSize = 60;

    private ArrayList<Bullets> bullets;
    Bullets currentBullets;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        justonce = true;
        turnHandler = new TurnHandler();
        cam = new OrthographicCamera(AlienDemo.WIDTH /1.5F , AlienDemo.HEIGHT / 1.5F);
        cam.update();
        bg = new Texture("melkweg.jpg");
        textureship = new Texture("bottomtube.png");
        bullets = new ArrayList<>();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Player currentPlayer = turnHandler.getCurrentPlayer();

                Vector3 convertedInputPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                if(bullets.isEmpty()){
                    bullets.add(new Bullets(currentPlayer.getXPosition(), currentPlayer.getYPosition(), (int) (convertedInputPosition.x - currentPlayer.getXPosition()), (int) (convertedInputPosition.y - currentPlayer.getYPosition()), turnHandler.player1turn()));
                    turnHandler.switchTurn();
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
        
        cam.update();
        for (Bullets bullets : this.bullets) {
            if(turnHandler.getPlayer2().getCollisionRect().collidesWith(bullets.getCollisionRect()) && bullets.isPlayer1turn() && !bullets.isHit())
            {
                turnHandler.getPlayer2().updateHP((int) bullets.getVelocity().x / 50);
                bullets.setHit(true);
                Vector3 bounced = new Vector3();
                bounced.x = 0 - (bullets.getVelocity().x / 2);
                bounced.y = (Math.round(bullets.getVelocity().y / 2) - 50);
                bounced.z = bullets.getVelocity().z;
                Vector3 playerspeed = bounced;
                playerspeed.x = bullets.getVelocity().x / 10;
                bullets.updateVelocity(bounced);
                turnHandler.getPlayer2().updateVelocity(playerspeed);
                turnHandler.getPlayer2().setHit(true);
            }
            else if(turnHandler.getPlayer1().getCollisionRect().collidesWith(bullets.getCollisionRect()) && !bullets.isPlayer1turn() && !bullets.isHit())
            {
                turnHandler.getPlayer1().updateHP(0 - ((int) bullets.getVelocity().x / 50));
                bullets.setHit(true);
                Vector3 bounced = new Vector3();
                bounced.x = 0 - (bullets.getVelocity().x / 2);
                bounced.y = (Math.round(bullets.getVelocity().y / 2) - 50);
                bounced.z = bullets.getVelocity().z;
                Vector3 playerspeed = bounced;
                playerspeed.x = bullets.getVelocity().x / 10;
                bullets.updateVelocity(bounced);
                turnHandler.getPlayer1().updateVelocity(playerspeed);
                turnHandler.getPlayer1().setHit(true);
            }
        }
        turnHandler.getPlayer1().update(dt);
        turnHandler.getPlayer2().update(dt);

        if(turnHandler.getPlayer1().isDead() || turnHandler.getPlayer2().isDead())
        {
            cam = new OrthographicCamera(AlienDemo.WIDTH , AlienDemo.HEIGHT);
            cam.update();
            gsm.set(new MenuState(gsm));
            dispose();
        }

        removeBullets(dt);
    }

    public void removeBullets(float dt){
        ArrayList<Bullets> bulletsToRemove = new ArrayList<>();

        for (Bullets bullets : this.bullets) {
            bullets.update(dt);
            if(bullets.remove)
            {
                bulletsToRemove.add(bullets);
            }
        }

        bullets.removeAll(bulletsToRemove);
    }

    private void updateCameraPosition(){
        Vector3 camPosition = new Vector3();

        float camMinX = cam.viewportWidth / 2;
        float camMinY = cam.viewportHeight / 2;

        float camMaxX = AlienDemo.WIDTH - (cam.viewportWidth / 2);
        float camMaxY = AlienDemo.HEIGHT - (cam.viewportHeight / 2);

        if(!bullets.isEmpty()){
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
        if(justonce) {
            x1 = (int) turnHandler.getPlayer1().getXPosition();
            y1 = (int) turnHandler.getPlayer1().getYPosition();
            x2 = (int) turnHandler.getPlayer2().getXPosition();
            y2 = (int) turnHandler.getPlayer2().getYPosition();
            justonce = false;
        }
        updateCameraPosition();

        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, AlienDemo.WIDTH , AlienDemo.HEIGHT);
        sb.draw(turnHandler.getPlayer1().getTexture(), turnHandler.getPlayer1().getXPosition(), turnHandler.getPlayer1().getYPosition(), -playerSize, playerSize * 1.8f);
        sb.draw(turnHandler.getPlayer2().getTexture(), turnHandler.getPlayer2().getXPosition(), turnHandler.getPlayer2().getYPosition(), playerSize, playerSize * 1.8f);
        turnHandler.getPlayer1().getHealthbar().render(sb);
        turnHandler.getPlayer2().getHealthbar().render(sb);
        for (Bullets bullets : this.bullets) {
            bullets.render(sb);
        }
        sb.draw(textureship, (x1 - 60), (y1 - 20), 100, 10);
        sb.draw(textureship, (x2 - 60), (y2 - 20), 100, 10);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
