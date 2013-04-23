package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.testgame.SkinSingleton;
import com.testgame.Takeover;

public abstract class AbstractPanelView extends AbstractView {

	// Our NinePatches
	NinePatch panel;
	NinePatch background;

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public AbstractPanelView(Takeover game) {
		super(game);
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		super.render(delta);

		stage.act(delta);

		batch.begin();
		background
				.draw(batch,
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

		background = SkinSingleton.getInstance().getMenuBackground();
		panel = SkinSingleton.getInstance().getPanelImage();

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
		buttonStyle = SkinSingleton.getInstance().getDefaultButtonStyle();
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
	}
}