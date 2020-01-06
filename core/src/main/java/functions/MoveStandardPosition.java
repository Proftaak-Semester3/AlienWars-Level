package functions;

import bullet.TurnHandler;
import com.badlogic.gdx.math.Vector3;
import objects.Player;

public class MoveStandardPosition implements Runnable {
    private Player player;
    private boolean whatPlayer;
    private TurnHandler turnHandler;
    public MoveStandardPosition(TurnHandler turnHandler)
    {
        this.turnHandler = turnHandler;
    }

    public void updatePlayer(Player player, boolean whatPlayer, TurnHandler turnHandler)
    {
        this.player = player;
        this.whatPlayer = whatPlayer;
        this.turnHandler = turnHandler;
    }

    @Override
    public void run() {
        Vector3 original = player.getOriginalPosition();
        Vector3 now = player.getPosition();
        float change = 0;
        if(whatPlayer)
        {
            change = original.x - now.x;
            for (int i = 1; i < change; i++)
            {
                Vector3 newPosition = new Vector3();
                newPosition.x = player.getXPosition() - i;
                newPosition.y = player.getYPosition();
                newPosition.z = 0;
                player.setPosition(newPosition);
            }
        }
        else
        {
            change = now.x - original.x;
            for (int i = 1; i < change; i++)
            {
                Vector3 newPosition = new Vector3();
                newPosition.x = player.getXPosition() + i;
                newPosition.y = player.getYPosition();
                newPosition.z = 0;
                player.setPosition(newPosition);
            }
        }
        player.setHit(false);
        turnHandler.switchTurn();
    }
}
