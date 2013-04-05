package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;

public class Category {

	// Vi vil ha muligheten til å spørre et Question om hvilken kategori det tilhører.
	// En kategori skal ha en visuell gjenkjenning som kan vises på kart/gamescreen. Farge?
	
	String category;
	Color visual;
	
	public Category(String category) {
		this.category = category;
		setVisual(category);
	}
	
	
	/**
	 * JSON-parseren skal ta seg av å sette categori-fargen? Jeg lar metodenskallet stå til eventuell bruk.
	 * @param category
	 */
	public void setVisual(String category) {
		if (category.equals("Sport")) {
			visual = Color.YELLOW;
		} else if (category.equals("Historie"))
			visual = Color.RED;
	}

}
