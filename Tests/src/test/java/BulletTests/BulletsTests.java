package java.BulletTests;

import Bullet.Bullets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.math.Vector3;

public class BulletsTests {
    @Test
    void BulletInstantiationTest() {
        //Arrange
        Bullets bullets = new Bullets(5, 5, 5, 5, true);
        //Act
        //Assert
        assertEquals(5, bullets.getPosition().x);
        assertEquals(5, bullets.getPosition().y);
    }

    @Test
    void UpdatePositionTest() {
        //Arrange
        Bullets bullets = new Bullets(5, 5, 5, 5, true);
        //Act
        bullets.update(1);
        //Assert
        assertEquals(15, bullets.getPosition().x);
        assertEquals(20, bullets.getPosition().x);
    }
}
