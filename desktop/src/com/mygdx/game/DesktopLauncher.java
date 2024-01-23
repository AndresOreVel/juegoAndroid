package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.SpaceRace;

import utils.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("SpaceRace");
		config.setWindowedMode(Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT * 2);
		new Lwjgl3Application(new SpaceRace(), config);
	}
}
