package com.testgame.Views;

import com.badlogic.gdx.Screen;
import com.testgame.MyGame;

public abstract class AbstractScreen implements Screen {

	MyGame game;

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public AbstractScreen(MyGame game) {
		this.game = game;
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	@Override
	public void hide() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// never called automatically
	}
}