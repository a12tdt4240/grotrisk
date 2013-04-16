package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.testgame.Models.Area;

public class AreaView extends Button implements Observer {
	private Area areaModel;
	private Area area;

	public AreaView(Area area) {
		super(area.getImage());
		this.area = area;

		setModel(area);
		setX(calculateXPosition());
		setY(calculateYPosition());
		changeEvent();
	}

	private float calculateXPosition() {
		float width = areaModel.getImage().getMinWidth() - 5;
		float value = 0;
		float leftBorder = 0;
		Gdx.app.log("ImageWidth", "" + width);
		int temp = area.getXPosition();
		switch (temp) {
		case 0:
			value = leftBorder;
			break;
		case 1:
			value = width + leftBorder;
			break;
		case 2:
			value = width * 2 + leftBorder;
			break;
		case 3:
			value = width * 3 + leftBorder;
			break;
		case 4:
			value = width * 4 + leftBorder;
			break;
		}
		return value;
	}

	private float calculateYPosition() {
		float width = areaModel.getImage().getMinWidth() - 5;
		float value = 0;
		float lowBorder = 90;
		Gdx.app.log("ImageHeight", "" + width);
		int temp = area.getYPosition();
		switch (temp) {
		case 0:
			value = lowBorder;
			break;
		case 1:
			value = lowBorder + width;
			break;
		case 2:
			value = lowBorder + width * 2;
			break;
		case 3:
			value = lowBorder + width * 3;
			break;
		case 4:
			value = lowBorder + width * 4;
			break;
		}
		return value;
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
