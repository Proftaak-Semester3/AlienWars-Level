package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Healthbar {

    private int widthhealth;

    private Vector3 position;
    private double scale = 1.3;
    private int totalwidth;
    private int number;

    private Texture barDamageTexture;
    private Texture barHealthTexture;

    public Healthbar(int x, int y, int health, int number)
    {
        this.number = number;
        position = new Vector3(x, y,0);
        barDamageTexture = new Texture("red bar.jpg");
        barHealthTexture = new Texture("green bar.jpg");
        double width = health * scale;
        totalwidth = (int) width;
        widthhealth = (int) width;
    }

    public void render(SpriteBatch batch)
    {

        batch.draw(barDamageTexture, position.x, position.y, totalwidth , 20);
        batch.draw(barHealthTexture, position.x, position.y, widthhealth,20);
    }

    public void update(float deltaTime)
    {
        if(widthhealth <= 0)
        {
        widthhealth = 0;
    }
    }

    public void damage(int health) {
        double width = health * scale;
        widthhealth = (int) width;
    }
    public void updatePosition(Vector3 update)
    {
        if(number == 0)
        {
            position.x = update.x - 60;
            position.y = update.y + 150;
            position.z = update.z;
        }
        else
        {
            position.x = update.x;
            position.y = update.y + 150;
            position.z = update.z;
        }
    }
}
