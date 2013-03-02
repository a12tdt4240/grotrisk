package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.testgame.MyGame;

public class GameScreen extends AbstractScreen {

	// constructor to keep a reference to the main Game class
	public GameScreen(MyGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		// touch brings main menu
		if (Gdx.input.justTouched())
			game.setScreen(new MainMenuScreen(game));
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