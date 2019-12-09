package states;

import bullet.Bullets;
import bullet.TurnHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import gameChecks.CameraUpdate;
import gameChecks.CollisionChecks;
import objects.Player;
import render.AlienDemo;
import websockets.messageCreator;

import java.util.ArrayList;

public class PlayState extends State {

    Bullets currentBullet;
    private TurnHandler turnHandler;
    private Texture bg;
    private Texture textureship;
    private CollisionChecks collisionChecks;
    private CameraUpdate camUpdate;
    private OrthographicCamera cam;
    private GameStateManager gsm;
    private websockets.messageCreator messageCreator;
    private boolean justonce;
    private boolean waiting;
    private boolean yourTurn;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int playerSize = 60;
    private int playernumber;
    private ArrayList<Bullets> bullets;


    protected PlayState(GameStateManager gsm, boolean firstToFire, messageCreator messageCreator) {
        super(gsm);
        this.collisionChecks = new CollisionChecks();
        this.camUpdate = new CameraUpdate();
        this.messageCreator = messageCreator;
        this.gsm = gsm;
        justonce = true;
        turnHandler = new TurnHandler();
        cam = new OrthographicCamera(AlienDemo.WIDTH / 1.5F, AlienDemo.HEIGHT / 1.5F);
        cam.update();
        bg = new Texture("melkweg.jpg");
        textureship = new Texture("bottomtube.png");
        bullets = new ArrayList<>();
        if (firstToFire) {
            playernumber = 0;
            yourTurn = true;
        } else {
            playernumber = 1;
            yourTurn = false;
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Player currentPlayer = turnHandler.getCurrentPlayer();
                Vector3 convertedInputPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                if (bullets.isEmpty() && turnHandler.player1turn(playernumber)) {
                    bullets.add(new Bullets(currentPlayer.getXPosition(), currentPlayer.getYPosition(), (int) (convertedInputPosition.x - currentPlayer.getXPosition()), (int) (convertedInputPosition.y - currentPlayer.getYPosition()), yourTurn));
                    messageCreator.createBulletMessage(currentPlayer.getXPosition(), currentPlayer.getYPosition(), (int) (convertedInputPosition.x - currentPlayer.getXPosition()), (int) (convertedInputPosition.y - currentPlayer.getYPosition()), yourTurn);
                    turnHandler.switchTurn();
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            messageCreator.close();
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        cam.update();
        for (Bullets bullets : this.bullets) {
            collisionChecks.checkCollision(bullets, turnHandler);
        }
        turnHandler.getPlayer1().update(dt);
        turnHandler.getPlayer2().update(dt);

        if (turnHandler.getPlayer1().isDead() || turnHandler.getPlayer2().isDead()) {
            cam = new OrthographicCamera(AlienDemo.WIDTH, AlienDemo.HEIGHT);
            cam.update();
            gsm.set(new MenuState(gsm));
            dispose();
        }
        if (currentBullet != null) {
            bullets.add(currentBullet);
        }

        removeBullets(dt);
    }

    public void removeBullets(float dt) {
        ArrayList<Bullets> bulletsToRemove = new ArrayList<>();

        for (Bullets bullets : this.bullets) {
            bullets.update(dt);
            if (bullets.remove) {
                bulletsToRemove.add(bullets);
            }
        }

        bullets.removeAll(bulletsToRemove);
    }

    @Override
    public void render(SpriteBatch sb) {
        if (justonce) {
            x1 = (int) turnHandler.getPlayer1().getXPosition();
            y1 = (int) turnHandler.getPlayer1().getYPosition();
            x2 = (int) turnHandler.getPlayer2().getXPosition();
            y2 = (int) turnHandler.getPlayer2().getYPosition();
            justonce = false;
        }
        camUpdate.cameraUpdate(cam, turnHandler, bullets);

        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg, 0, 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        sb.draw(turnHandler.getPlayer1().getTexture(), turnHandler.getPlayer1().getXPosition(), turnHandler.getPlayer1().getYPosition(), -playerSize, playerSize * 1.8f);
        sb.draw(turnHandler.getPlayer2().getTexture(), turnHandler.getPlayer2().getXPosition(), turnHandler.getPlayer2().getYPosition(), playerSize, playerSize * 1.8f);
        turnHandler.getPlayer1().getHealthbar().render(sb);
        turnHandler.getPlayer2().getHealthbar().render(sb);
        for (Bullets bullets : this.bullets) {
            bullets.setTexture();
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

    public void enemyMove(float x, float y, int horizontal, int vertical, boolean player1Turn) {
        bullets.add(new Bullets(x, y, horizontal, vertical, player1Turn));
        turnHandler.switchTurn();
    }
}
