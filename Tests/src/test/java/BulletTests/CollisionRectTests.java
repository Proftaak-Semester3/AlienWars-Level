package BulletTests;

import Bullet.CollisionRect;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionRectTests {
    @Test
    void CollidesWithPositiveTest() {
        //Arrange
        CollisionRect firstCollider = new CollisionRect(0,0,10,10);
        CollisionRect secondCollider = new CollisionRect(0,0,10,10);
        //Act
        //Assert
        assertEquals(firstCollider.collidesWith(secondCollider), true);
    }

    @Test
    void CollidesWithNegativeTest() {
        //Arrange
        CollisionRect firstCollider = new CollisionRect(0,0,10,10);
        CollisionRect secondCollider = new CollisionRect(20,20,10,10);
        //Act
        //Assert
        assertEquals(firstCollider.collidesWith(secondCollider), false);
    }
}
