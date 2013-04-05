package com.testgame.Views;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.testgame.Models.Area;

public class AreaView extends Button {

	public AreaView(Area area) {
		super(new ButtonStyle(area.getImage().getDrawable(),
				area.getImage().getDrawable(), area.getImage().getDrawable()));
	}
	
}
