package com.testgame.Views;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.testgame.Models.Area;

public class AreaView extends Button implements Observer {
	private Area areaModel;

	public AreaView(Area area) {
		super(area.getImage());
		
		setModel(area);
		
		setX(area.getXPosition());
		setY(area.getYPosition());
		
	}

	public void setModel(Area model) {
		areaModel = model;
		areaModel.addObserver(this);
	}
	
	public Area getModel() {
		return areaModel;
	}

	public Area getArea() {
		return areaModel;
	}

	@Override
	public void changeEvent() {
		setColor(getModel().getColor());
		
	}
}
