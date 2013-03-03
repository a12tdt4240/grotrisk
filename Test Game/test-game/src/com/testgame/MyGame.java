package com.testgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.testgame.Views.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;

public class MyGame extends Game implements ApplicationListener {

	MainMenuScreen mainMenuScreen;
	private Music music;

	@Override
	public void create() {
		// Load music and start playing:
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle(
				"data/Riskspillet.mp3", FileType.Internal));
		music.setLooping(true);
		music.play();
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
	}

	@Override
	public void dispose() {
		music.dispose();
	}
}
