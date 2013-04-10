package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		super.show();
		game.setScreen(new NextPlayerScreen(game, 1));
	}
}