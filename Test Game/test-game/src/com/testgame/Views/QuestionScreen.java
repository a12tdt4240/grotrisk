package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.testgame.MyGame;

public class QuestionScreen extends AbstractScreen {

//	private final Sound correct;
//	private final Sound wrong;
	
	// constructor to keep a reference to the main Game class
	public QuestionScreen(MyGame game) {
		super(game);
//		correct = Gdx.audio.newSound(Gdx.files.internal("data/correct.wav"));
//		wrong = Gdx.audio.newSound(Gdx.files.internal("data/wrong.wav"));
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
	}

	/**
	 * Called when the current screen changes from this to a different
	 * screen. Remember to dispose objects.
	 */
	public void hide() {
		// called when current screen changes from this to a different screen
//		correct.dispose();
//		wrong.dispose();
	}
}