package com.martens.game.desktop.states;

import Bullet.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.martens.game.desktop.FlappyDemo;
import com.martens.game.desktop.Objects.Bird;
import com.martens.game.desktop.Objects.Player;

import java.util.ArrayList;

public class PlayState  extends State{


    private Bird bird;
    private Player player1;
    private Player player2;
    private Texture bg;

    private ArrayList<Bullet> bullets;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50 ,200);
        player1 = new Player("Bird.png", FlappyDemo.WIDTH / 20, FlappyDemo.HEIGHT / 20);
        player2 = new Player("Ornstein.jpg", FlappyDemo.WIDTH - (FlappyDemo.WIDTH / 20), FlappyDemo.HEIGHT / 20);
        cam.setToOrtho(false, FlappyDemo.WIDTH /2, FlappyDemo.HEIGHT /2);
        bg = new Texture("melkweg.jpg");
        bullets = new ArrayList<>();


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                bullets.add(new Bullet(0, 2, Gdx.input.getX(), Gdx.input.getY()));
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

        cam.position.x = bird.getPosition().x + 80;

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();

        sb.draw(bg, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        sb.draw(player1.getTexture(), player1.getXPosition(), player2.getYPosition(), 40, 40);
        sb.draw(player2.getTexture(), player2.getXPosition(), player2.getYPosition(), 40, 40);


        sb.end();
    }

    @Override
    public void dispose() {

    }
}
