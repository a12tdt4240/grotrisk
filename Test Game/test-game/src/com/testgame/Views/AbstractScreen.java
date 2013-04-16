package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.MyGame;

public abstract class AbstractScreen implements Screen {

	MyGame game;
	Stage stage;

	SpriteBatch batch;

	// Graphics data
	TextureAtlas atlas;
	BitmapFont font;
	Skin skin;
	TextButtonStyle buttonStyle;

	

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public AbstractScreen(MyGame game) {
		this.game = game;
		this.batch = new SpriteBatch();
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

		// Following should go in render?
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

		if (stage == null)
			stage = new Stage(width, height, false);

		stage.clear(); // clears the stage

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		font.dispose();
		skin.dispose();
		batch.dispose();
	}
}