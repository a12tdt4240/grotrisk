package com.testgame.Views;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.testgame.Takeover;
import com.testgame.Models.Area;
import com.testgame.Models.Map;
import com.testgame.Models.SkinSingleton;

public class MapView extends AbstractView {
	// Model of the map
	private Map mapModel;
	// All AreaViews belonging to the map
	private ArrayList<AreaView> areaViews;
	// Holds our score view
	private ScoreView scoreView;
	// Common listener for all areas
	private InputEventListener listener;
	// Background, often a sea image.
	private NinePatch background;

	private ArrayList<Image> attackImages;

	public MapView(Takeover game, Map model) {
		super(game);
		setModel(model);
		listener = new InputEventListener();
		this.attackImages = new ArrayList<Image>();
		makeAreaViews();
		this.scoreView = new ScoreView(game);
	}

	/**
	 * Sets the model of the view
	 * 
	 * @param Map
	 */
	public void setModel(Map model) {
		mapModel = model;
	}

	/**
	 * Gets current model of view
	 * 
	 * @return Map
	 */
	public Map getModel() {
		return mapModel;
	}

	/**
	 * Make area views from model
	 */
	private void makeAreaViews() {
		ArrayList<Area> areas = mapModel.getAreas();
		areaViews = new ArrayList<AreaView>();

		// Set initial ownership
		areas.get(0).setOwner(game.getPlayers().get(0));
		areas.get(areas.size() - 1).setOwner(game.getPlayers().get(1));

		for (int i = 0; i < areas.size(); ++i) {
			areaViews.add(new AreaView(areas.get(i)));
		}

	}

	/**
	 * Adds areas to stage as actors
	 */
	private void addAreaViewsAsActors() {
		for (int i = 0; i < areaViews.size(); ++i) {
			stage.addActor(areaViews.get(i));
		}
	}

	/**
	 * Adds listeners to correct areas
	 * 
	 * Could use some heavy rework
	 */
	private void addListeners() {
		// Remove all other listeners
		removeListeners();
		// Get list of all neighbors
		ArrayList<Area> neighbors = getModel().getNeighborsByPlayer(
				game.getCurrentPlayer());

		// Add listener to neighbors
		for (int i = 0; i < neighbors.size(); ++i) {
			// Find matching view to model
			// UGLY!!
			for (int k = 0; k < areaViews.size(); ++k) {
				if (neighbors.get(i) == areaViews.get(k).getModel()) {
					areaViews.get(k).addListener(listener);

					Image attackDrawable = new Image(SkinSingleton.getInstance().getAttackImage().getDrawable());
					attackDrawable.setScale((Gdx.graphics.getHeight() * 0.065f) / attackDrawable.getHeight());
					attackDrawable.setPosition(areaViews.get(k).getX(),
							areaViews.get(k).getY());
					attackImages.add(attackDrawable);
				}
			}
		}
	}

	/**
	 * Removes all listeners to make sure only neighbors have listener
	 */
	private void removeListeners() {
		attackImages.clear();
		for (int i = 0; i < areaViews.size(); ++i) {
			areaViews.get(i).removeListener(listener);
		}
	}

	public void resize(int width, int height) {
		super.resize(width, height);
		scoreView.resize(width, height);
		
		background = SkinSingleton.getInstance().getMenuBackground();
		batch = new SpriteBatch();
		
		
		addListeners();
		addAreaViewsAsActors();

		// Add scoreViews' actors to this stage
		Actor[] scoreActors = scoreView.stage.getActors().toArray();
		Gdx.app.log("TEST", "" + scoreActors.length);
		for (int i = 0; i < scoreActors.length; i++) {
			stage.addActor(scoreActors[i]);
		}

		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from
		// this stage
	}
	
	@Override
	public void hide() {
		super.hide();
		SkinSingleton.getInstance().resetFontSize();
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		super.render(delta);
		// Draws the background
		batch.begin();
		background
				.draw(batch,
						(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 1.0f) / 2,
						(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 1.0f) / 2,
						Gdx.graphics.getWidth() * 1.0f,
						Gdx.graphics.getHeight() * 1.0f);
		batch.end();
		
		// render ScoreView
		scoreView.render(delta);
		
		stage.act(delta);
		stage.draw();
		
		// Draw the attack icons
		batch.begin();
		for ( int i = 0; i < attackImages.size(); i++) {
			attackImages.get(i).draw(batch, 1);
		}
		batch.end();
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	public void show() {
		super.show();

		stage = new Stage();
		
		// show the score view
		scoreView.show();
	}

	/**
	 * Listens for touch
	 * 
	 */
	class InputEventListener extends InputListener {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			Gdx.app.log("CLICK", "clicked a land");
			Gdx.app.log("TAKEOVER-TIME", "START - Area click time: "+ new SimpleDateFormat("HH:mm:ss.SSSS").format(new Date()));
			AreaView areaView = (AreaView) event.getTarget();
			// If area is already owned by other, initiate duel
			if (areaView.getModel().getOwner() != null) {
				game.getDuelState().initiateDuel(game.getCurrentPlayer(),
						areaView.getModel().getOwner(), areaView.getModel());
			}

			// Increase the number of plays.
			game.increasePlaysCounter();

			game.setScreen(new QuizView(game, areaView.getArea()));
		}
	}
	
	@Override
	public void dispose() {
		Gdx.app.debug("testgame", "Disposing MapView");
		super.dispose();
		scoreView.dispose();
	}
}
