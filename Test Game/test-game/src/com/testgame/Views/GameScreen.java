package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.testgame.MyGame;
import com.testgame.Models.Area;

public class GameScreen extends AbstractGameScreen {

	// Vi trenger et bakgrunnsbilde som vises her. Et blått hav eller en
	// havtekstur.
	// Oppå det blå havet tegner vi landområder, Area, via AreaViews.

	MapView mapView;
	AreaView areaView1, areaView2, areaView3;

	// constructor to keep a reference to the main Game class
	public GameScreen(MyGame game) {
		super(game);

		// Create dummy areaViews
		areaView1 = new AreaView(new Area((2 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		areaView2 = new AreaView(new Area((3 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		areaView3 = new AreaView(new Area((4 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		
		// Add listeners to dummy areas
		areaView1.addListener(new InputEventListener());
		areaView2.addListener(new InputEventListener());
		areaView3.addListener(new InputEventListener());
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
	public void resize(int width, int height) {
		super.resize(width, height);
		this.stage.addActor(areaView1);
		this.stage.addActor(areaView2);
		this.stage.addActor(areaView3);

	}

	/**
	 * Unified InputListener
	 * 
	 */
	class InputEventListener extends InputListener {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			AreaView areaView = (AreaView) event.getTarget();
			game.setScreen(new QuestionScreen(game, areaView.getArea()));

		}
	}
}