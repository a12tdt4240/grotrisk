package com.testgame.Views;

import com.testgame.MyGame;

public class GameScreen extends AbstractGameScreen {

	// Vi trenger et bakgrunnsbilde som vises her. Et blått hav eller en havtekstur.
	// Oppå det blå havet tegner vi landområder, Area, via AreaViews.
	
	// constructor to keep a reference to the main Game class
	public GameScreen(MyGame game) {
		super(game);
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		super.render(delta);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		super.show();
		//game.setScreen(new NextPlayerScreen(game));
	}
}