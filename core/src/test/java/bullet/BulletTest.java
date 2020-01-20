package bullet;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class BulletTest {
    // This is our "test" application
    private static Application application;
    private Bullets bullet;
    private static SpriteBatch batch;


    // Before running any tests, initialize the application with the headless backend
    @BeforeClass
    public static void init() {
        // Note that we don't need to implement any of the listener's methods
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() { }
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        // Use Mockito to mock the OpenGL methods since we are running headlessly
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.graphics.setWindowedMode(1920, 1080);
        Gdx.gl = Gdx.gl20;
    }
    @Before
    public void beforeTests()
    {
        bullet = new Bullets(10, 10, 20, 20, true);
    }

    @Test
    public void getPosition() {
        Vector3 position = new Vector3();
        position.x = 10;
        position.y = 10;
        position.z = 0;
        assertEquals((int) bullet.getPosition().x, (int) position.x);
        assertEquals((int) bullet.getPosition().y, (int) position.y);
    }

    @Test
    public void getVelocity() {
        Vector3 velocity = new Vector3();
        velocity.x = 20;
        velocity.y = 20;
        velocity.z = 0;
        assertEquals((int) bullet.getVelocity().x, (int) velocity.x);
        assertEquals((int) bullet.getVelocity().y, (int) velocity.y);
    }

    @Test
    public void updateVelocity() {
        bullet.updateVelocity(new Vector3(5,5,0));
        Vector3 velocity = new Vector3();
        velocity.x = 5;
        velocity.y = 5;
        velocity.z = 0;
        assertEquals((int) bullet.getVelocity().x, (int) velocity.x);
        assertEquals((int) bullet.getVelocity().y, (int) velocity.y);

    }

    @Test
    public void isPlayer1turn() {
        assertTrue(bullet.isPlayer1turn());
    }

    @Test
    public void getCollisionRect() {
        CollisionRect collision = new CollisionRect(20,20,100,100);
        assertTrue(bullet.getCollisionRect().collidesWith(collision));
    }

    @Test
    public void dontRemoveBullet()
    {
        Vector3 vector3 = new Vector3(1, 1, 1);
        bullet.updateVelocity(vector3);
        bullet.update(0.015f);
        assertFalse(bullet.isRemove());
    }

    @Test
    public void removeBullet()
    {
        Vector3 vector3 = new Vector3(0, -20000, 1);
        bullet.updateVelocity(vector3);
        bullet.update(0.015f);
        assertTrue(bullet.isRemove());
    }

    @Test
    public void bulletIsHit()
    {
        bullet.setHit(true);
        assertTrue(bullet.isHit());
    }

    @Test
    public void bulletIsNotHit()
    {
        assertFalse(bullet.isHit());
    }

    @Test(expected = com.badlogic.gdx.utils.GdxRuntimeException.class)
    public void noTextureAndNoSpriteBatch()
    {
        bullet.setTexture();
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void renderNoSpriteBatchTexture()
    {
        bullet.render(null);
    }

    @AfterClass
    public static void cleanUp() {
        // Exit the application first
        application.exit();
        application = null;
    }
}
