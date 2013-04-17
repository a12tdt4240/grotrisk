package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.testgame.MyGame;

public class PlayerView extends AbstractMenuScreen {

	Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
						Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
						Color.PINK, Color.RED, Color.YELLOW};
	
	ArrayList<Button> colorButtons;
	ButtonStyle colorButtonStyle;
	float colorButtonWidth = 0;
	
	public PlayerView (MyGame game) {
		super(game);
		colorButtons = new ArrayList<Button>();
	}
	
	@Override
	public void resize(int width, int height) {
			super.resize(width, height);
	}
	
	@Override
	public void initializeStyle() {
		super.initializeStyle();
		
		colorButtonStyle = new ButtonStyle(skin.getDrawable("container"), skin.getDrawable("container"), skin.getDrawable("container"));
		colorButtonWidth = skin.getDrawable("container").getMinWidth();
	}
	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		for (int i = 0; i < colors.length; i++) {
			Button btn = new Button(colorButtonStyle);
			float scale = (Gdx.graphics.getWidth() * 0.1f) / colorButtonWidth;
			btn.setScale(scale);
			btn.setColor(colors[i]);
			btn.setX((Gdx.graphics.getWidth() - ((colorButtonWidth + 5) * colors.length)) / 2);
			btn.setY(Gdx.graphics.getWidth() / 2);
			colorButtons.add(btn);
			this.stage.addActor(btn);
		}

	}
}
