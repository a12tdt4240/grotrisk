package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.testgame.Observer;
import com.testgame.Models.Area;
import com.testgame.Models.SkinSingleton;

public class AreaView extends Button implements Observer {
	
	private Area areaModel;
	private Label value;
	private float wantedLength;

	public AreaView(Area area) {
		super(area.getImage());

		// wantedLength is the height of the screen divided by the
		// number of area views + 1 in the y direction.
		this.wantedLength = Gdx.graphics.getHeight() / 6;

		// set the GUI properties of the AreaView
		setModel(area);
		setHeight(wantedLength);
		setWidth(wantedLength);
		setX(calculateXPosition());
		setY(calculateYPosition());
		
		value = new Label("" + getModel().getValueOfArea(), SkinSingleton.getInstance().getLabelStyle());
		value.setColor(Color.WHITE);
		value.setFontScale(0.75f);
		value.setTouchable(Touchable.disabled);
		add(value);
		
		changeEvent();
	}

	/**
	 * Helper for placing the area view in the x plane.
	 * 
	 * @return
	 */
	private float calculateXPosition() {
		float width = (wantedLength - 5);
		// leftBorder scales so that we get the same border on each side of the
		// map of area views. This is hard coded using the number of area views
		// in the x direction.
		float leftBorder = (Gdx.graphics.getWidth() - width * 7) / 2;
		int temp = getModel().getXPosition();
		
		return width * temp + leftBorder;
	}

	/**
	 * Helper for placing the area view in the y direction.
	 * 
	 * @return
	 */
	private float calculateYPosition() {
		float width = wantedLength - 5;
		float lowBorder = 90;
		int temp = getModel().getYPosition();
		
		return lowBorder + width * temp;
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
