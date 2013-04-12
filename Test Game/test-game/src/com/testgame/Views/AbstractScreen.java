package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

	// Our NinePatches
	NinePatch background;

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

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		batch.begin();
		background.draw(batch,
						(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 1.0f) / 2,
						(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 1.0f) / 2,
						Gdx.graphics.getWidth() * 1.0f,
						Gdx.graphics.getHeight() * 1.0f);
		
		
		batch.end();

		stage.draw();
	}

	
	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {

		stage = new Stage();

		atlas = new TextureAtlas("skins/mainmenu.atlas");

		skin = new Skin();
		skin.addRegions(atlas);

		background = new NinePatch(new TextureRegion(
				atlas.findRegion("background")), 190, 190, 114, 292);

		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
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

		if (stage == null)
			stage = new Stage(width, height, false);

		stage.clear(); // clears the stage

		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from
											// this stage
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Gdx.app.debug("testgame", "Disposing Game Screen");

		atlas.dispose();
		stage.dispose();
		font.dispose();
		skin.dispose();
		batch.dispose();
		background.getTexture().dispose();
	}
}