package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;

public class MainMenuScreen extends AbstractScreen {
	
	// Our Buttons
	TextButton newGameButton;

	// constructor to keep a reference to the main Game class
	public MainMenuScreen(MyGame game) {
		super(game);
	}
	
	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		newGameButton = new TextButton("Nytt spill", buttonStyle);
		newGameButton.setWidth(458);
		newGameButton.setHeight(88);
		newGameButton.setX(Gdx.graphics.getWidth() / 2 - newGameButton.getWidth() / 2);
		newGameButton.setY(Gdx.graphics.getHeight() / 2 - newGameButton.getHeight() / 2);
		
		newGameButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new NextPlayerScreen(game));
			}
		});
		this.stage.addActor(newGameButton);
	}
}