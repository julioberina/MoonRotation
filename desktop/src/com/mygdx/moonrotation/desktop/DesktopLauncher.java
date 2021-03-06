package com.mygdx.moonrotation.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.moonrotation.MoonRotation;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 1280;
                config.height = 630;
                config.resizable = false;
                config.title = "Moon Rotation";
		new LwjglApplication(new MoonRotation(), config);
	}
}
