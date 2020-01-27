import bullet.Bullets;
import com.badlogic.gdx.math.Vector3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class bulletTest {

    Bullets bullet = new Bullets(10,10,10,10, true);

    @Before
    public void init(){
        System.out.println("Starting Bullet test");
        Vector3 position = new Vector3();
        position.x = 10;
    }

    //posistion
    @Test
    public void getPosition() {
        Vector3 position = new Vector3();
        position.x = 10;
        position.y = 10;
        position.z = 0;
        assertEquals(bullet.getPosition(), position);
    }

    //Hit
    @Test
    public void  setHitasTrue(){
        bullet.setHit(true);

        assertEquals(true, bullet.isHit() );
    }

    @Test
    public void  setHitasFalse(){
        bullet.setHit(false);

        assertEquals(false, bullet.isHit());
    }




//    @Test
//    public void testtest()
//    {
//        assertEquals("yes", "yes");
//        //hi ruben
//    }
}
