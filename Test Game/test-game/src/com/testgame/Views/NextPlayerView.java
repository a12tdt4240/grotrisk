package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;
import com.testgame.Models.Constants;
import com.testgame.Models.SkinSingleton;

public class NextPlayerView extends AbstractPanelView {

	// Our GUI elements
	private TextButton nextPlayerButton;
	private Label nextPlayerInfo;
	private Label nextPlayerLabel;

	/**
	 * Constructor keeping a reference to the main Game class.
	 * 
	 * @param game
	 */
	public NextPlayerView(MyGame game) {
		super(game);
	}

	/**
	 * Updates and draws stuff.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();

		nextPlayerInfo.draw(batch, 1.0f);
		nextPlayerLabel.draw(batch, 1.0f);
		batch.end();
	}

	/**
	 * Button initialization.
	 */
	@Override
	public void initializeButtons() {

		// Button initialization
		nextPlayerButton = new TextButton(Constants.NEXTPLAYER_BUTTON,
				buttonStyle);
		nextPlayerButton.setWidth(458);
		nextPlayerButton.setHeight(88);
		nextPlayerButton.setX(Gdx.graphics.getWidth() / 2
				- nextPlayerButton.getWidth() / 2);
		nextPlayerButton.setY((Gdx.graphics.getHeight() / 2 - nextPlayerButton
				.getHeight() / 2) - Gdx.graphics.getHeight() * 0.15f);

		nextPlayerButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				setCorrectScreen();
			}
		});

		// TextField initialization.
		LabelStyle labelStyle = SkinSingleton.getInstance().getLabelStyle();

		nextPlayerInfo = new Label(Constants.NEXTPLAYER_INFO, labelStyle);
		nextPlayerInfo.setFontScale(0.9f);
		nextPlayerInfo.setAlignment(2);
		nextPlayerInfo.setColor(0.647059f, 0.164706f, 0.164706f, 1.0f);
		nextPlayerInfo.setX(Gdx.graphics.getWidth() / 2
				- nextPlayerInfo.getWidth() / 2);
		nextPlayerInfo.setY(Gdx.graphics.getHeight() / 2
				- nextPlayerInfo.getHeight() / 2 + Gdx.graphics.getHeight()
				* 0.19f);
		
		

		nextPlayerLabel = new Label(game.getCurrentPlayer().getName(),
				labelStyle);
		nextPlayerLabel.setFontScale(0.8f);
		nextPlayerLabel.setAlignment(2);
		nextPlayerLabel.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		nextPlayerLabel.setX(Gdx.graphics.getWidth() / 2
				- nextPlayerLabel.getWidth() / 2);
		nextPlayerLabel.setY(Gdx.graphics.getHeight() / 2
				- nextPlayerLabel.getHeight() / 2 + Gdx.graphics.getHeight()
				* 0.045f);
		
		// Add buttons to stage
		this.stage.addActor(nextPlayerButton);
	}
	@Override
	public void show() {
		super.show();
		
	}
	@Override
	public void hide() {
		super.hide();
		SkinSingleton.getInstance().resetFontSize();
	}
	/**
	 * Checks if duel is in action and displays correct screen accordingly
	 * 
	 */
	private void setCorrectScreen() {
		if (game.getDuelState().isDuel()) {
			// Go to question screen
			if (game.getQuestionView() == null)
				game.setQuestionView(new QuizView(game));

			game.getQuestionView().setArea(game.getDuelState().getArea());
			game.setScreen(game.getQuestionView());
		} else {
			if (game.getMapView() == null)
				game.setMapView(new MapView(game, game.getMapModel()));

			game.setScreen(game.getMapView());
		}
	}
}
