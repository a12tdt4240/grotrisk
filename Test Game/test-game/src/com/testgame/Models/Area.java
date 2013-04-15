package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.testgame.Views.Observer;

import java.util.ArrayList;

public class Area implements Serializable {
	
	private int id;
	
	// An area has an owner. Player 1, Player 2 or null;
	private Player owner;
	// An area holds a question.
	private Question question;
	// The visual representation of the area.
	private Drawable areaDrawable;
	
	private ArrayList<Observer> observers;

	// Holds color of area
	private Color color = Color.WHITE;

	private int value, xPosition, yPosition;
	// A list of neighboring areas. Areas that can be moved to in one move.
	private ArrayList<Area> neighbors;
	private ArrayList<Integer> neighborsMap;

	Skin skin;
	TextureAtlas atlas;
	
	public Area() {}
	
	/**
	 * Constructor.
	 * @param xPosition
	 * @param yPosition
	 * @param value
	 */
	public Area(int xPosition, int yPosition, int value) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.value = value;
		observers = new ArrayList<Observer>();
		loadAreaImage();
	}
	
	/**
	 * Helper method for loading the Image.
	 */
	protected void loadAreaImage() {
		atlas = new TextureAtlas("data/maps/map.atlas");

		skin = new Skin();
		skin.addRegions(atlas);
		areaDrawable = skin.getDrawable("area001");
	}
	
	/**
	 * Returns standard color if no users owns area, and
	 * returns the owners color if an owner exists
	 * 
	 * @return Color
	 */
	public Color getColor() {
		if(getOwner() == null) {
			return color;
		}
		return getOwner().getColor();
	}
	
	/**
	 * Sets provided player as owner
	 * 
	 * @param player
	 */
	public void setOwner(Player player) {
		owner = player;
		fireObserverEvent();
	}
	
	/**
	 * Provides the owner of the area
	 * 
	 * @return Player
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Sets provided question as question of area
	 * 
	 * @param question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	/**
	 * Provides the question of the area
	 * 
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}
	
	public int getValueOfArea() {
		return value;
	}

	public Drawable getImage() {
		return areaDrawable;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public ArrayList<Area> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * Add observer to list
	 * 
	 * @param Observer
	 */
	public void addObserver(Observer ob) {
		observers.add(ob);
	}
	
	private void fireObserverEvent() {
		for(int i = 0; i < observers.size(); ++i) {
			observers.get(i).changeEvent();
		}
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		// TODO Auto-generated method stub
		this.id = json.readValue("id", Integer.class, jsonData);
		this.xPosition = json.readValue("xPosition", Integer.class, jsonData);
		this.yPosition = json.readValue("yPosition", Integer.class, jsonData);
		this.value = json.readValue("value", Integer.class, jsonData);
		this.neighborsMap = json.readValue("neighbors", ArrayList.class, jsonData);
//		loadAreaImage();
	}
	
	public void initiateNeighbors(ArrayList<Area> areas) {
		for (Area area: areas) {
			if (this.neighborsMap.contains(area.id)) this.neighbors.add(area); 
		}
	}
}
