package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Category implements Serializable {

	// Vi vil ha muligheten til � sp�rre et Question om hvilken kategori det tilh�rer.
	// En kategori skal ha en visuell gjenkjenning som kan vises p� kart/gamescreen. Farge?
	
	String category;
	Color visual;
	
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
	public String getCategoryName() {
		return category;
	}
	
	/**
	 * Colour of category
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
	 * JSON-parseren skal ta seg av � sette categori-fargen? Jeg lar metodenskallet st� til eventuell bruk.
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

}
