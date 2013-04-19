package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;
import com.testgame.Models.Constants;

public class MainMenuView extends AbstractPanelView {

	// Our Buttons
	private TextButton newGameButton;
	private CheckBox soundCheckBox;
	
	//Our additional styles
	private CheckBoxStyle checkBoxStyle;

	// constructor to keep a reference to the main Game class
	public MainMenuView(MyGame game) {
		super(game);
	}

	@Override
	public void resize(int width, int height) {
		this.checkBoxStyle = new CheckBoxStyle(
				skin.getDrawable("buttonUpGrey"), skin.getDrawable("buttonUp"),
				font, Color.WHITE);
		super.resize(width, height);
	}

	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {

		// Initialize buttons
		soundCheckBox = new CheckBox(Constants.MAINMENU_SOUND, checkBoxStyle);
		soundCheckBox.setChecked(true);
		soundCheckBox.setWidth(120);
		soundCheckBox.setHeight(88);
		soundCheckBox.setX(Gdx.graphics.getWidth() / 2
				- soundCheckBox.getWidth() / 2);
		soundCheckBox.setY(Gdx.graphics.getHeight() / 2.6f
				- soundCheckBox.getHeight() / 2);

		newGameButton = new TextButton(Constants.MAINMENU_BUTTON, buttonStyle);
		newGameButton.setWidth(458);
		newGameButton.setHeight(88);
		newGameButton.setX(Gdx.graphics.getWidth() / 2
				- newGameButton.getWidth() / 2);
		newGameButton.setY(Gdx.graphics.getHeight() / 1.8f
				- newGameButton.getHeight() / 2);

		// Add InputListeners to buttons
		newGameButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new PlayerView(game));
			}
		});

		soundCheckBox.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.changeSoundSetting();
			}
		});

		// Add buttons to stage
		this.stage.addActor(soundCheckBox);
		this.stage.addActor(newGameButton);
	}
}