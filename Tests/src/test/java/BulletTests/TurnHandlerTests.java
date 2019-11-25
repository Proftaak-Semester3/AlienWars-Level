package BulletTests;

import Bullet.TurnHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnHandlerTests {

    @Test
    void SwitchTurnToPlayer1Test() {
        //Arrange
        TurnHandler turnHandler = new TurnHandler();
        //Act
        turnHandler.switchTurn();
        turnHandler.switchTurn();
        //Assert
        assertEquals(turnHandler.getCurrentPlayer(), turnHandler.getPlayer1());
    }

    @Test
    void SwitchTurnToPlayer2Test() {
        //Arrange
        TurnHandler turnHandler = new TurnHandler();
        //Act
        turnHandler.switchTurn();
        //Assert
        assertEquals(turnHandler.getCurrentPlayer(), turnHandler.getPlayer2());
    }
}
