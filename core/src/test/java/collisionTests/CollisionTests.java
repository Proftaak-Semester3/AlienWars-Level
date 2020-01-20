package collisionTests;

import bullet.CollisionRect;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionTests {
    private CollisionRect first;
    @Before
    public void beforeTests()
    {
        first = new CollisionRect(100, 1000, 10, 500);
    }
    @Test
    public void hit()
    {
        CollisionRect second = new CollisionRect(50, 1000, 70, 70);
        assertTrue(first.collidesWith(second));
    }
    @Test
    public void notHit()
    {
        CollisionRect second = new CollisionRect(50, 1000, 20, 70);
        assertFalse(first.collidesWith(second));
    }
    @Test
    public void moveCollisionForHit()
    {
        CollisionRect second = new CollisionRect(50, 1000, 20, 70);
        second.move(80, 1000);
        assertTrue(first.collidesWith(second));
    }
}
