package gameChecks;

import bullet.Bullets;
import bullet.TurnHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import render.AlienDemo;

import java.util.ArrayList;

public class CameraUpdate {
    public void cameraUpdate(OrthographicCamera cam, TurnHandler turnHandler, ArrayList<Bullets> bullets)
    {
        Vector3 camPosition = new Vector3();

        float camMinX = cam.viewportWidth / 2;
        float camMinY = cam.viewportHeight / 2;

        float camMaxX = AlienDemo.WIDTH - (cam.viewportWidth / 2);
        float camMaxY = AlienDemo.HEIGHT - (cam.viewportHeight / 2);

        if(!bullets.isEmpty()){
            cam.position.set(bullets.get(0).getPosition().x, bullets.get(0).getPosition().y, 0);
        }
        else{
            cam.position.set(turnHandler.getCurrentPlayer().getXPosition(), turnHandler.getCurrentPlayer().getYPosition(), 0);
        }

        camPosition.x = Math.min(camMaxX, Math.max(camMinX, cam.position.x));
        camPosition.y = Math.min(camMaxY, Math.max(camMinY, cam.position.y));

        cam.position.set(camPosition);
    }
}
