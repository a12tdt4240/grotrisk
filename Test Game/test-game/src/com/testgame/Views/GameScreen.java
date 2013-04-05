package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.testgame.MyGame;

public class GameScreen extends AbstractGameScreen {

	// constructor to keep a reference to the main Game class
	public GameScreen(MyGame game) {
		super(game);
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		super.show();
		game.setScreen(new NextPlayerScreen(game, 1));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	@Override
	public void hide() {
	}
}