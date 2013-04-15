package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.testgame.MyGame;
import com.testgame.Models.Map;

public class GameScreen extends AbstractScreen {

	// Holds our current Map View
	MapView mapView;

	// Holds our score view
	ScoreView scoreView;

	/**
	 * Constructor keeping a reference to the main Game class
	 * 
	 * @param game
	 */
	public GameScreen(MyGame game, Map map) {
		super(game);
		this.mapView = new MapView(game, map);
		this.scoreView = new ScoreView(game);
	}

	@Override
	public void dispose() {
		Gdx.app.debug("testgame", "Disposing Game Screen");
		super.dispose();
		
		mapView.dispose();
		scoreView.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		mapView.resize(width, height);
		scoreView.resize(width, height);

		Array<Actor> mapActors = mapView.stage.getActors();

		for (int i = 0; i < mapActors.size; i++) {
			stage.addActor(mapActors.get(i));
		}

		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from
		// this stage
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		super.render(delta);
		mapView.render(delta);
		scoreView.render(delta);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		mapView.show();
		scoreView.show();
	}
}