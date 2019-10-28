package com.martens.game.desktop.states;

import Bullet.Bullet;
import Bullet.CollisionRect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.martens.game.desktop.AlienDemo;
import com.martens.game.desktop.Objects.Player;

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
        player2 = new Player("Ornstein.jpg", (AlienDemo.WIDTH - (AlienDemo.WIDTH / 6)), (AlienDemo.HEIGHT / 7));
        cam.setToOrtho(false, AlienDemo.WIDTH /2, AlienDemo.HEIGHT /2);
        bg = new Texture("melkweg.jpg");
        bullets = new ArrayList<>();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                bullets.add(new Bullet(player1.getPosition().x, player1.getPosition().y, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
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
            if(player2.getCollisionRect().collidesWith(bullet.getCollisionRect()) && !bullet.hit)
            {
                player2.updateHP((int)bullet.GetVelocity().x / 50);
                bullet.hit = true;
                Vector3 vector3 = bullet.GetVelocity();
                Vector3 bounced = new Vector3();
                bounced.x = 0 - (vector3.x / 2);
                bounced.y = Math.round(vector3.y / 2) - 50;
                bounced.z = vector3.z;
                bullet.updateVelocity(bounced);
            }
        }
        player1.update(dt);
        player2.update(dt);

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
        player1.render(sb);
        player2.render(sb);

        for (Bullet bullet: bullets) {
            bullet.render(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
