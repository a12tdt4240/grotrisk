package com.testgame.Views;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.Models.Alternative;

public class AlternativeView {
	private TextButton view;
	private Alternative model;
	
	public AlternativeView(Alternative model, TextButton view) {
		this.model = model;
		this.view = view;
	}

	public TextButton getView() {
		return view;
	}

	public void setView(TextButton view) {
		this.view = view;
	}

	public Alternative getModel() {
		return model;
	}

	public void setModel(Alternative model) {
		this.model = model;
	}
	
}
