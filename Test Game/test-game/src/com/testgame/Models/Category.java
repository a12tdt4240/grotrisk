package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;

public class Category {

	// Vi vil ha muligheten til � sp�rre et Question om hvilken kategori det tilh�rer.
	// En kategori skal ha en visuell gjenkjenning som kan vises p� kart/gamescreen. Farge?
	
	String category;
	Color visual;
	
	public Category(String category) {
		this.category = category;
		setVisual(category);
	}
	
	
	/**
	 * JSON-parseren skal ta seg av � sette categori-fargen? Jeg lar metodenskallet st� til eventuell bruk.
	 * @param category
	 */
	public void setVisual(String category) {
		if (category.equals("Sport")) {
			visual = Color.YELLOW;
		} else if (category.equals("Historie"))
			visual = Color.RED;
	}

}
