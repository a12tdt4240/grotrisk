package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.testgame.MyGame;
import com.testgame.Models.Constants;

public class PlayerView extends AbstractMenuScreen {

	Color[] colors = { Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY,
			Color.MAGENTA, Color.ORANGE, Color.YELLOW };

	TextField nameField;
	TextFieldStyle nameFieldStyle;
	TextButton okButton;
	ArrayList<Button> colorButtons;
	ButtonStyle colorButtonStyle;
	Drawable colorButton;
	Drawable colorButtonChecked;
	Color selectedColor;

	ButtonGroup buttonGroup;

	float colorButtonWidth = 0;
	float scale;

	public PlayerView(MyGame game) {
		super(game);
		this.colorButtons = new ArrayList<Button>();
		this.selectedColor = colors[0];
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void initializeStyle() {
		super.initializeStyle();

		colorButton = skin.getDrawable("container");
		colorButtonChecked = skin.getDrawable("container2"); // to fix

		colorButtonStyle = new ButtonStyle(colorButton, colorButton,
				colorButtonChecked);
		colorButtonWidth = colorButton.getMinWidth();
		font.setColor(Color.BLACK);
		nameFieldStyle = new TextFieldStyle(font, Color.WHITE,
				skin.getDrawable("cursor"), skin.getDrawable("selected"),
				skin.getDrawable("background"));
	}

	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		buttonGroup = new ButtonGroup();
		buttonGroup.setMaxCheckCount(1);
		buttonGroup.setUncheckLast(true);
		nameField = new TextField("skriv inn navn", nameFieldStyle);
		nameField.setWidth(Gdx.graphics.getWidth() * 0.7f);
		nameField.setX((Gdx.graphics.getWidth() - nameField.getWidth()) / 2);
		nameField.setY((Gdx.graphics.getHeight() / 2) + nameField.getHeight());
		nameField.setMaxLength(15);

		this.stage.addActor(nameField);

		okButton = new TextButton(Constants.OK_BUTTON, buttonStyle);
		okButton.setWidth(458);
		okButton.setHeight(88);
		okButton.setX(Gdx.graphics.getWidth() / 2 - okButton.getWidth() / 2);
		okButton.setY(Gdx.graphics.getHeight() / 3 - okButton.getHeight() / 2);

		okButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.getPlayers().get(0).setName(nameField.getText());
				game.getPlayers().get(0).setColor(selectedColor);
				game.setScreen(new NextPlayerScreen(game));
			}
		});

		this.stage.addActor(okButton);

		for (int i = 0; i < colors.length; i++) {
			Button btn = new Button(colorButtonStyle);
			scale = (Gdx.graphics.getWidth() * 0.055f) / colorButtonWidth;
			btn.setHeight(scale * colorButtonWidth);
			btn.setWidth(scale * colorButtonWidth);
			btn.setColor(colors[i]);
			// btn.setX(100);
			btn.setX(((Gdx.graphics.getWidth() - ((colorButtonWidth * scale + 5) * colors.length)) / 2)
					+ (colorButtonWidth * scale + 5) * i);
			btn.setY(Gdx.graphics.getHeight() / 2);
			btn.addListener(new InputEventListener());
			buttonGroup.add(btn);
			colorButtons.add(btn);
			this.stage.addActor(btn);
		}
	}

	class InputEventListener extends InputListener {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			Gdx.app.log("CLICK", "clicked a color");
			Button colorPick = (Button) event.getTarget();
			selectedColor = colorPick.getColor();
		}
	}
}
