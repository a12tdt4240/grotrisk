package com.testgame.Views;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.testgame.Models.Area;

public class AreaView extends Button {
	private Area areaModel;

	public AreaView(Area area) {
//		super(new ButtonStyle(area.getImage(), area.getImage()
//				, area.getImage()));
		super(area.getImage());
		
		this.areaModel = area;
		
		setX(area.getXPosition());
		setY(area.getYPosition());
		
	}

	public void setModel(Area model) {
		areaModel = model;
	}

	public Area getArea() {
		return areaModel;
	}
}
