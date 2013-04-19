package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.testgame.Views.Observer;

import java.util.ArrayList;
import java.util.Arrays;

public class Area implements Serializable {
	
	private int id;
	
	// An area has an owner. Player 1, Player 2 etc. or null;
	private Player owner;
	// The visual representation of the area.
	private Drawable areaDrawable;
	
	private Image attackImage;
	
	private ArrayList<Observer> observers;

	// Holds color of area
	private Color color = Color.WHITE;

	private int value, xPosition, yPosition;
	// A list of neighboring areas. Areas that can be moved to in one move.
	private ArrayList<Area> neighbors;
	private Integer[] neighborsMap;
	
	public Area() {
		observers = new ArrayList<Observer>();
		neighbors = new ArrayList<Area>();
		neighborsMap = new Integer[0];
		loadAreaImage();
	}
	
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
		neighbors = new ArrayList<Area>();
		neighborsMap = new Integer[0];
		loadAreaImage();
	}
	
	/**
	 * Helper method for loading the Image.
	 */
	protected void loadAreaImage() {
		areaDrawable = SkinSingleton.getInstance().getAreaImage();
		attackImage = SkinSingleton.getInstance().getAttackImage();
		
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
		this.neighborsMap = json.readValue("neighbors", Integer[].class, jsonData);
//		loadAreaImage();
	}
	
	public void initiateNeighbors(ArrayList<Area> areas) {
		ArrayList<Integer> neighborsList = new ArrayList<Integer>(Arrays.asList(this.neighborsMap));
		for (Area area: areas) {
			if (neighborsList.contains(area.id)) this.neighbors.add(area); 
		}
	}
	
	public Image getAttackImage() {
		return attackImage;
	}
}
