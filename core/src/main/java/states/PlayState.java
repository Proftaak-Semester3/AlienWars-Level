package states;

import animation.RandomObstacle;
import animation.WalkBackAnimation;
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
import websockets.messageCreator.iJsonCreator;
import websockets.messageSender.MessageBroadcaster;

import java.util.ArrayList;

public class PlayState extends State {

    Bullets currentBullet;
    private TurnHandler turnHandler;
    private Texture bg;
    private CollisionChecks collisionChecks;
    private CameraUpdate camUpdate;
    private OrthographicCamera cam;
    private iJsonCreator messageCreator;
    private WalkBackAnimation walkBackAnimation;
    //private RandomObstacle obstacle;
    private boolean serverconfirm;
    private boolean yourTurn;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int playerSize = 60;
    private int playernumber;
    private ArrayList<Bullets> bullets;


    protected PlayState(GameStateManager gsm, boolean firstToFire, iJsonCreator messageCreator) {
        super(gsm);
        this.camUpdate = new CameraUpdate();
        this.messageCreator = messageCreator;
        this.gsm = gsm;
        serverconfirm = false;
        turnHandler = new TurnHandler();
        this.collisionChecks = new CollisionChecks();
        this.walkBackAnimation = new WalkBackAnimation(gsm, turnHandler);
        cam = new OrthographicCamera(AlienDemo.WIDTH / 1.5F, AlienDemo.HEIGHT / 1.5F);
        cam.update();
        bg = new Texture("Alien wars playfield background.png");
        bullets = new ArrayList<>();
        //obstacle = new RandomObstacle(gsm);
        if (firstToFire) {
            playernumber = 0;
            yourTurn = true;
        } else {
            playernumber = 1;
            yourTurn = false;
        }
        x1 = (int) turnHandler.getPlayer1().getXPosition();
        y1 = (int) turnHandler.getPlayer1().getYPosition();
        x2 = (int) turnHandler.getPlayer2().getXPosition();
        y2 = (int) turnHandler.getPlayer2().getYPosition();
        MessageBroadcaster.broadcast(messageCreator.startPositionMessage(x1, y1, x2, y2));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() && (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))) {
            Player currentPlayer = turnHandler.getCurrentPlayer();
            Vector3 convertedInputPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (bullets.isEmpty() && turnHandler.player1turn(playernumber)) {
                MessageBroadcaster.broadcast(messageCreator.bulletMessage(currentPlayer.getXPosition(), currentPlayer.getYPosition(), (int) (convertedInputPosition.x - currentPlayer.getXPosition()), (int) (convertedInputPosition.y - currentPlayer.getYPosition()), yourTurn));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        if (serverconfirm) {
            handleInput();
            cam.update();
            for (Bullets bullet : bullets) {
                collisionChecks.checkCollision(bullet, turnHandler);
                bullet.update(dt);
                //obstacle.updateBullet(bullet);
/*                if(bullet.isHit())
                {
                    turnHandler.switchTurn();
                    System.out.println("bullet did hit - switch turn");
                }*/
            }
            turnHandler.getPlayer1().update(dt);
            turnHandler.getPlayer2().update(dt);
            if (turnHandler.getPlayer1().isDead() || turnHandler.getPlayer2().isDead()) {
                MessageBroadcaster.broadcast(messageCreator.winMessage());
                cam = new OrthographicCamera(AlienDemo.WIDTH, AlienDemo.HEIGHT);
                cam.update();
                gsm.set(new MenuState(gsm));
                dispose();
            }
            if (currentBullet != null) {
                bullets.add(currentBullet);
            }

            removeBullets();

            if (!turnHandler.getPlayer1().isMoving() && turnHandler.getPlayer1().isHit() || !turnHandler.getPlayer2().isMoving() && turnHandler.getPlayer2().isHit()) {
                walkBackAnimation.update(dt);
            }
        }
    }

    private void removeBullets() {
        ArrayList<Bullets> bulletsToRemove = new ArrayList<>();

        for (Bullets bullet : this.bullets) {
            if (bullet.isRemove()) {
                bulletsToRemove.add(bullet);
                //obstacle.nextTurn();
                turnHandler.switchTurn();
            }
        }

        bullets.removeAll(bulletsToRemove);
    }

    @Override
    public void render(SpriteBatch sb) {
        camUpdate.cameraUpdate(cam, turnHandler, bullets);
        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg, 0, 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        sb.draw(turnHandler.getPlayer1().getTexture(), turnHandler.getPlayer1().getXPosition(), turnHandler.getPlayer1().getYPosition(), -playerSize, playerSize * 1.8f);
        sb.draw(turnHandler.getPlayer2().getTexture(), turnHandler.getPlayer2().getXPosition(), turnHandler.getPlayer2().getYPosition(), playerSize, playerSize * 1.8f);
        turnHandler.getPlayer1().getHealthbar().render(sb);
        turnHandler.getPlayer2().getHealthbar().render(sb);
        for (Bullets bullet : this.bullets) {
            bullet.setTexture();
            bullet.render(sb);
        }
        turnHandler.getPlayer1().draw(sb);
        turnHandler.getPlayer2().draw(sb);
        //obstacle.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }

    public void enemyMove(float x, float y, int horizontal, int vertical, boolean player1Turn) {
        bullets.add(new Bullets(x, y, horizontal, vertical, player1Turn));
    }

    public void confirmServer(boolean confirm) {
        if (!confirm) {
            gsm.push(new MenuState(gsm));
        } else {
            serverconfirm = true;
        }
    }

    public void win()
    {
        gsm.push(new QueueState(gsm));
    }
}
