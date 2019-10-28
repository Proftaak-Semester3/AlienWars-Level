package com.martens.game.desktop.Objects;

import Bullet.CollisionRect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Player {

    private static final int GRAVITY = -7;

    Texture Player;

    Healthbar healthbar;

    private Vector3 position;
    private Vector3 velocity;

    private CollisionRect ship;

    private int health;
    public boolean onship = false;

    CollisionRect rect;

    public Player(String File, float x, float y) {
        Player = new Texture(File);
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        rect = new CollisionRect(x,y, 60, 60);
        health = 150;
        healthbar = new Healthbar((int)position.x, (int)(position.y + 100), health);
        ship = new CollisionRect(x, y - 20, 100, 10);
    }

    public void update(float deltaTime)
    {
        if(rect.collidesWith(ship))
        {
            onship = true;
            velocity = new Vector3(0, 0, 0);
        }
        if(position.y > 0 && !onship){
            velocity.add(0, GRAVITY,0);}

        velocity.scl(deltaTime);
        position.add(velocity.x, velocity.y, 0);
        position.add(0, velocity.y, 0);


        velocity.scl(1/deltaTime);

        rect.move(position.x, position.y);

        healthbar.update(deltaTime);

        onship = false;
    }

    public void render (SpriteBatch batch)
    {
        batch.draw(Player, position.x, position.y, 60, 60);
        healthbar.render(batch);
    }

    public Texture getTexture() {
        return Player;
    }

    public Vector3 getPosition() { return position; }

    public CollisionRect getCollisionRect ()
    {
        return rect;
    }

    public void updateHP(int damage) {
        health = health - damage;
        healthbar.damage(health);
    }

    public int getHealth() {  return health;  }

    public void updateVelocity(Vector3 update)
    {
        velocity = update;
    }
}
