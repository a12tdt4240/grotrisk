package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.testgame.MyGame;

public class MainMenuScreen extends AbstractScreen {

	TextureRegion mainMenu;
	SpriteBatch batch;
	float time = 0;

	// constructor to keep a reference to the main Game class
	public MainMenuScreen(MyGame game) {
		super(game);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		mainMenu = new TextureRegion(new Texture(
				Gdx.files.internal("data/mainmenu.png")), 0, 0, 480, 320);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mainMenu, 0, 0);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.justTouched()) {
				game.setScreen(new GameScreen(game));
			}
		}
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	@Override
	public void hide() {
		Gdx.app.debug("testgame", "Disposing Main Menu");
		mainMenu.getTexture().dispose();
		batch.dispose();
	}
}