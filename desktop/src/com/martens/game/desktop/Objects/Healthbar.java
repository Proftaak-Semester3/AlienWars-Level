package com.martens.game.desktop.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Healthbar {

    private int widthhealth;

    private int x;
    private int y;

    Texture damagebar;
    Texture healthbar;

    public Healthbar(int x, int y, int health)
    {
        this.x = x;
        this.y = y;
        damagebar = new Texture("red bar.jpg");
        healthbar = new Texture("green bar.jpg");
        widthhealth = health * 2;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(damagebar, x, y, 300, 20);
        batch.draw(healthbar, x, y, widthhealth,20);
    }

    public void update(float Deltatime)
    {
        if(widthhealth <= 0)
        {
            widthhealth = 0;
        }
    }

    public void damage(int health)
    {
        widthhealth = health * 2;
    }
}
