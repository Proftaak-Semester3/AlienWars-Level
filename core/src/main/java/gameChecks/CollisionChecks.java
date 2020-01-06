package gameChecks;

import bullet.Bullets;
import bullet.TurnHandler;
import com.badlogic.gdx.math.Vector3;
import functions.MoveStandardPosition;

public class CollisionChecks {

    public void checkCollision(Bullets bullets, TurnHandler turnHandler)
    {
        if(turnHandler.getPlayer2().getCollisionRect().collidesWith(bullets.getCollisionRect()) && bullets.isPlayer1turn() && !bullets.isHit())
        {
            turnHandler.getPlayer2().updateHP((int) bullets.getVelocity().x / 50);
            bullets.setHit(true);
            Vector3 bounced = new Vector3();
            bounced.x = 0 - (bullets.getVelocity().x / 2);
            bounced.y = (Math.round(bullets.getVelocity().y / 2) - 50);
            bounced.z = bullets.getVelocity().z;
            Vector3 playerspeed = bounced;
            playerspeed.x = bullets.getVelocity().x / 10;
            bullets.updateVelocity(bounced);
            turnHandler.getPlayer2().updateVelocity(playerspeed);
            turnHandler.getPlayer2().setHit(true);
            turnHandler.getPlayer2().setMoving(true);
        }
        else if(turnHandler.getPlayer1().getCollisionRect().collidesWith(bullets.getCollisionRect()) && !bullets.isPlayer1turn() && !bullets.isHit())
        {
            turnHandler.getPlayer1().updateHP(0 - ((int) bullets.getVelocity().x / 50));
            bullets.setHit(true);
            Vector3 bounced = new Vector3();
            bounced.x = 0 - (bullets.getVelocity().x / 2);
            bounced.y = (Math.round(bullets.getVelocity().y / 2) - 50);
            bounced.z = bullets.getVelocity().z;
            Vector3 playerspeed = bounced;
            playerspeed.x = bullets.getVelocity().x / 10;
            bullets.updateVelocity(bounced);
            turnHandler.getPlayer1().updateVelocity(playerspeed);
            turnHandler.getPlayer1().setHit(true);
            turnHandler.getPlayer2().setMoving(true);
        }
    }
}
