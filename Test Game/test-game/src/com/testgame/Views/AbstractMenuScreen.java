package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.MyGame;

public abstract class AbstractMenuScreen extends AbstractScreen {
	
	// Our NinePatches
	NinePatch panel;
	NinePatch background;
	
	
	
	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public AbstractMenuScreen(MyGame game) {
		super(game);
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
		panel.draw(
				batch,
				(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 0.8f) / 2,
				(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.8f) / 2,
				Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.8f);
		
		
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
		panel = new NinePatch(new TextureRegion(atlas.findRegion("panel")),
				215, 200, 140, 140);

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
		super.resize(width, height);

		initializeStyle(); // Sets the style of buttons etc.
		initializeButtons(); // Creates our buttons
		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from
		// this stage
	}

	/**
	 * Sets the style of UI elements.
	 */
	public void initializeStyle() {
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.getDrawable("buttonUp");
		buttonStyle.font = font;
	}

	public void initializeButtons() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.debug("testgame", "Disposing Main Menu");

		atlas.dispose();

		font.dispose();
		skin.dispose();
		batch.dispose();
		panel.getTexture().dispose();
		background.getTexture().dispose();
	}
}