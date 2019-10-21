package com.martens.game.desktop.Sprites;

import com.badlogic.gdx.graphics.Texture;

public class Player {

    Texture Player;

    public Player(String File) {
        Player = new Texture(File);
    }

    public Texture getTexture() {
        return Player;
    }
}
