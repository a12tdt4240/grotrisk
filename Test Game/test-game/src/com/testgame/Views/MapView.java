package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.testgame.MyGame;
import com.testgame.Models.Area;
import com.testgame.Models.Map;

public class MapView extends AbstractScreen {
	// Model of the map
	private Map mapModel;
	// All AreaViews belonging to the map
	private ArrayList<AreaView> areaViews;
	// Common listener for all areas
	private InputEventListener listener;
	// Background, often a sea image.
	private NinePatch background;
	
	
	public MapView(MyGame game, Map model) {
		super(game);
		setModel(model);
		listener = new InputEventListener();
		makeAreaViews();
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
		
		for(int i = 0; i < areas.size(); ++i) {
			areaViews.add(new AreaView(areas.get(i)));
		}
		
	}
	
	/**
	 * Adds areas to stage as actors
	 */
	private void addAreaViewsAsActors() {
		for(int i = 0; i < areaViews.size(); ++i) {
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
		ArrayList<Area> neighbors = getModel().getNeighborsByPlayer(game.getCurrentPlayer());
		
		// Add listener to neighbors
		for(int i = 0; i < neighbors.size(); ++i) {
			// Find matching view to model
			// UGLY!!
			for(int k = 0; k < areaViews.size(); ++k) {
				if(neighbors.get(i) == areaViews.get(k).getModel()) {
//					Gdx.app.log("ALG", i + ":" + k);
					areaViews.get(k).addListener(listener);
				}
			}
		}
	}
	
	/**
	 * Removes all listeners to make sure only neighbors have listener
	 */
	private void removeListeners() {
		for(int i = 0; i < areaViews.size(); ++i) {
			areaViews.get(i).removeListener(listener);
		}
	}
	
	public void resize(int width, int height) {
		super.resize(width, height);

		addListeners();
		addAreaViewsAsActors();
		
	}
	
	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		
		// Draws the background
		batch.begin();
		background.draw(batch,
						(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 1.0f) / 2,
						(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 1.0f) / 2,
						Gdx.graphics.getWidth() * 1.0f,
						Gdx.graphics.getHeight() * 1.0f);
		
		batch.end();
		
	}
	
	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	public void show() {
		super.show();
		
		stage = new Stage();

		atlas = new TextureAtlas("skins/mainmenu.atlas");

		skin = new Skin();
		skin.addRegions(atlas);

		background = new NinePatch(new TextureRegion(
				atlas.findRegion("background")), 190, 190, 114, 292);

		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		batch = new SpriteBatch();
		
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
			AreaView areaView = (AreaView) event.getTarget();
			// If area is already owned by other, initiate duel
			if(areaView.getModel().getOwner() != null) {
				game.getDuelState().initiateDuel(game.getCurrentPlayer(), areaView.getModel().getOwner(), areaView.getModel());
			}
			// Go to question screen
			game.setScreen(new QuestionScreen(game, areaView.getArea()));

		}
	}
}
