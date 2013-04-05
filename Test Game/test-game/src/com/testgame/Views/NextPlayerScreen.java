package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.testgame.MyGame;

public class NextPlayerScreen extends AbstractScreen {

	int player = 1;
	
	// constructor to keep a reference to the main Game class
	public NextPlayerScreen(MyGame game, int player) {
		super(game);
		this.player = player;
	}
	
	// default constructor to keep a reference to the main Game class
		public NextPlayerScreen(MyGame game) {
			super(game);
		}

	@Override
	public void render(float delta) {
		// update and draw stuff
		if (Gdx.input.justTouched())
			game.setScreen(new GameScreen(game));
	}

	@Override
	public void show() {
		// called when this screen is set as the screen with game.setScreen();
		
		// display text showing which player is next
		// display a button 
	}

	@Override
	public void hide() {
		// called when current screen changes from this to a different screen
		// dispose
	}
}