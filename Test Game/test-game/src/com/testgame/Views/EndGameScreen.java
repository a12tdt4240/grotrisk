package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.testgame.MyGame;

public class EndGameScreen extends AbstractScreen {

	// constructor to keep a reference to the main Game class
	public EndGameScreen(MyGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		// update and draw stuff
		if (Gdx.input.justTouched())
			game.setScreen(game.mainMenuScreen);
	}

	@Override
	public void show() {
		// called when this screen is set as the screen with game.setScreen();
	}

	@Override
	public void hide() {
		// called when current screen changes from this to a different screen
	}
}