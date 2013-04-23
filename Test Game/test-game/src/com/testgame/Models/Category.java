package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Category implements Serializable {
	
	private String category;
	private Color visual;
	
	public Category() {}
	
	public Category(String category) {
		this.category = category;
		setVisual(category);
	}
	
	/**
	 * Name of category
	 * 
	 * @return String
	 */
	public String getName() {
		return category;
	}
	
	/**
	 * Color of category
	 * 
	 * @return Color
	 */
	public Color getCategoryColor() {
		return visual;
	}
	
	/**
	 * Sets color of category
	 * 
	 * @input Color
	 */
	public void setCategoryColor(Color color) {
		visual = color;
	}
	
	/**
	 * Method not used.
	 * @param category
	 */
	public void setVisual(String category) {
		if (category.equals("Sport")) {
			visual = Color.YELLOW;
		} else if (category.equals("Historie")) {
			visual = Color.RED;
		} else if (category.equals("Geografi")) {
			visual = Color.GREEN;
		}
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		// TODO Auto-generated method stub
		this.category = json.readValue("category", String.class, jsonData);
		this.setVisual(this.category);
	}
	
	public String toString() {
		return getName();
	}

}
