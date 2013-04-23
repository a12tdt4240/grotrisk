package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.testgame.Constants;
import com.testgame.SkinSingleton;
import com.testgame.Takeover;

public class EndGameView extends AbstractPanelView {

	private TextButton mainMenuButton;
	private Label infoLabel;
	private Label winnerLabel;

	/**
	 * Constructor keeping a reference to the main Game class.
	 * 
	 * @param game
	 */
	public EndGameView(Takeover game) {
		super(game);
	}

	/**
	 * Updates and draws stuff.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
	
		batch.begin();

		infoLabel.draw(batch, 1.0f);
		winnerLabel.draw(batch, 1.0f);
		batch.end();
	}
	
	public void hide() {
		super.hide();
		SkinSingleton.getInstance().resetFontSize();
	}
	

	/**
	 * Button initialization.
	 */
	public void initializeButtons() {

		// Button initialization
		mainMenuButton = new TextButton(Constants.ENDGAME_MAINBUTTON, buttonStyle);
		mainMenuButton.setWidth(458);
		mainMenuButton.setHeight(88);
		mainMenuButton.setX(Gdx.graphics.getWidth() / 2
				- mainMenuButton.getWidth() / 2);
		mainMenuButton.setY((Gdx.graphics.getHeight() / 2 - mainMenuButton
				.getHeight() / 2) - Gdx.graphics.getHeight() * 0.15f);

		mainMenuButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// The following method resets the players with scores, areas
				// with owners, and the
				// play counter.
				game.resetGame();
				game.setScreen(new MainMenuView(game));
			}
		});

		// TextField initialization.
		LabelStyle labelStyle = SkinSingleton.getInstance().getLabelStyle();
		infoLabel = new Label(Constants.ENDGAME_INFOLABEL, labelStyle);
		infoLabel.setFontScale(0.9f);
		infoLabel.setAlignment(2);
		infoLabel.setColor(0.647059f, 0.164706f, 0.164706f, 1.0f);
		infoLabel.setX(Gdx.graphics.getWidth() / 2 - infoLabel.getWidth() / 2);
		infoLabel.setY(Gdx.graphics.getHeight() / 2 - infoLabel.getHeight() / 2
				+ Gdx.graphics.getHeight() * 0.19f);
		

		winnerLabel = new Label(Constants.ENDGAME_WINNERLABEL
				+ game.getWinningPlayer().getName(), labelStyle);
		winnerLabel.setFontScale(0.8f);
		winnerLabel.setAlignment(2);
		winnerLabel.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		winnerLabel.setX(Gdx.graphics.getWidth() / 2 - winnerLabel.getWidth()
				/ 2);
		winnerLabel.setY(Gdx.graphics.getHeight() / 2 - winnerLabel.getHeight()
				/ 2 + Gdx.graphics.getHeight() * 0.045f);
		
		
		this.stage.addActor(mainMenuButton);
	}
}