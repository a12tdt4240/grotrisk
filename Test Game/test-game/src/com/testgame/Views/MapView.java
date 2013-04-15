package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
		
		addAreaViewsAsActors();
		addListeners();
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
