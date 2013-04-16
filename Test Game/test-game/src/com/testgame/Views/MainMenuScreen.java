package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;
import com.testgame.Models.Constants;

public class MainMenuScreen extends AbstractMenuScreen {

	// Our Buttons
	TextButton newGameButton, soundButton;

	// constructor to keep a reference to the main Game class
	public MainMenuScreen(MyGame game) {
		super(game);
	}

	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		soundButton = new TextButton(Constants.MAINMENU_SOUND, buttonStyle);
		soundButton.setWidth(200);
		soundButton.setHeight(88);
		soundButton.setX(Gdx.graphics.getWidth() / 2 - soundButton.getWidth() / 2);
		soundButton.setY(Gdx.graphics.getHeight() / 2.4f - soundButton.getHeight() / 2);
		
		newGameButton = new TextButton(Constants.MAINMENU_BUTTON, buttonStyle);
		newGameButton.setWidth(458);
		newGameButton.setHeight(88);
		newGameButton.setX(Gdx.graphics.getWidth() / 2 - newGameButton.getWidth() / 2);
		newGameButton.setY(Gdx.graphics.getHeight() / 1.8f - newGameButton.getHeight() / 2);
		
		newGameButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new NextPlayerScreen(game));
			}
		});
		soundButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.changeSoundSetting();
			}
		});
		this.stage.addActor(soundButton);
		this.stage.addActor(newGameButton);
	}
}