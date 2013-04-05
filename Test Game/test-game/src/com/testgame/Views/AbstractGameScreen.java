package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgame.MyGame;

public abstract class AbstractGameScreen implements Screen {

	MyGame game;
	
	SpriteBatch batch;

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public AbstractGameScreen(MyGame game) {
		this.game = game;
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
		batch = new SpriteBatch();
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
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

	}
	
	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Gdx.app.debug("testgame", "Disposing Main Menu");

		batch.dispose();

	}
}