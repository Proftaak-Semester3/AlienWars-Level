package com.martens.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.martens.game.desktop.states.GameStateManager;
import com.martens.game.desktop.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;

    public static final String TITLE = "test";
    private GameStateManager gsm;

    private SpriteBatch batch;


    @Override
    public void create () {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1,0,0,1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }
}
