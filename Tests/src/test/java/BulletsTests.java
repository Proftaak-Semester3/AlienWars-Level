import Bullet.Bullets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BulletsTests {
    @Test
    void justAnExample() {
        Bullets bullets = new Bullets(5, 5, 5, 5, true);
        assertEquals(5, bullets.getPosition().x);
        assertEquals(5, bullets.getPosition().y);
    }
}