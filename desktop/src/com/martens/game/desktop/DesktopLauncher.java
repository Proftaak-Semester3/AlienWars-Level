package com.martens.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import render.AlienDemo;

public class  DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = AlienDemo.WIDTH;
		config.height = AlienDemo.HEIGHT;
		config.fullscreen = true;
		config.title = AlienDemo.TITLE;
		new LwjglApplication(new AlienDemo(), config);
	}
}
