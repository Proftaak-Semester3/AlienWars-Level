package com.martens.game.desktop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.martens.game.desktop.FlappyDemo;
import com.martens.game.desktop.Sprites.Bird;
import com.martens.game.desktop.Sprites.Player;
import com.martens.game.desktop.Sprites.Tube;

import java.util.ArrayList;

public class PlayState  extends State{


    private Bird bird;
    private Player player1;
    private Player player2;
    private Texture bg;

    float Object1X = FlappyDemo.WIDTH / 20;
    float Object1Y = FlappyDemo.HEIGHT / 20;

    float Object2X = FlappyDemo.WIDTH - (FlappyDemo.WIDTH / 20);
    float Object2Y = FlappyDemo.HEIGHT / 20;

    private ArrayList<Tube> tubes;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50 ,200);
        player1 = new Player("Bird.png");
        player2 = new Player("Ornstein.jpg");
        cam.setToOrtho(false, FlappyDemo.WIDTH /2, FlappyDemo.HEIGHT /2);
        bg = new Texture("melkweg.jpg");


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        //bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();

        sb.draw(bg, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        sb.draw(player1.getTexture(),Object1X, Object1Y, 40, 40);
        sb.draw(player2.getTexture(),Object2X, Object2Y, 40, 40);


        sb.end();
    }

    @Override
    public void dispose() {

    }
}
