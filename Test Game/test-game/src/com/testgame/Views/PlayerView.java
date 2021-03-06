package com.testgame.Views;

import java.util.ArrayList;
import java.util.Collections;

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
import com.testgame.Constants;
import com.testgame.SkinSingleton;
import com.testgame.Takeover;
import com.testgame.Models.Player;

public class PlayerView extends AbstractPanelView {

	private ArrayList<Color> colors;

	private TextField nameField;
	private TextFieldStyle nameFieldStyle;
	private TextButton okButton;
	private ArrayList<Button> colorButtons;
	private ButtonStyle colorButtonStyle;
	private Color selectedColor;

	private ButtonGroup buttonGroup;

	private int currentPlayer;
	private ArrayList<Player> players;

	private float colorButtonWidth = 0;
	private float scale;

	public PlayerView(Takeover game) {
		super(game);
		this.colors = new ArrayList<Color>();
		Collections.addAll(colors, Color.RED, Color.DARK_GRAY, Color.GREEN, Color.BLUE,
				Color.ORANGE, Color.YELLOW);
		this.selectedColor = null;
		this.colorButtons = new ArrayList<Button>();
		this.players = game.getPlayers();
		this.currentPlayer = 0;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void initializeStyle() {
		super.initializeStyle();

		colorButtonStyle = SkinSingleton.getInstance().getRadioButtonStyle();
		colorButtonWidth = colorButtonStyle.up.getMinWidth();
		nameFieldStyle = SkinSingleton.getInstance().getTextFieldStyle();
	}

	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		selectedColor = colors.get(0);
		buttonGroup = new ButtonGroup();
		buttonGroup.setMaxCheckCount(1);
		buttonGroup.setUncheckLast(true);
		nameField = new TextField(players.get(currentPlayer).getName(),
				nameFieldStyle);
		nameField.setWidth(Gdx.graphics.getWidth() * 0.6f);
		nameField.setX((Gdx.graphics.getWidth() - nameField.getWidth()) / 2);
		nameField.setY(Gdx.graphics.getHeight() / 2 - nameField.getHeight() / 2
				+ Gdx.graphics.getHeight() * 0.19f);
		nameField.setMaxLength(15);

		this.stage.addActor(nameField);

		okButton = new TextButton(Constants.OK_BUTTON, buttonStyle);
		okButton.setWidth(458);
		okButton.setHeight(88);
		okButton.setX(Gdx.graphics.getWidth() / 2 - okButton.getWidth() / 2);
		okButton.setY((Gdx.graphics.getHeight() / 2 - okButton.getHeight() / 2)
				- Gdx.graphics.getHeight() * 0.15f);

		okButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				players.get(currentPlayer).setName(nameField.getText());
				players.get(currentPlayer).setColor(selectedColor);
				colors.remove(selectedColor);
				// Give out initial areas
				if (currentPlayer < players.size() - 1) {
					currentPlayer++;
					stage.clear();
					initializeButtons();
				} else {
					game.setInitialOwnership();

					game.setScreen(new NextPlayerView(game));
				}
			}
		});

		this.stage.addActor(okButton);

		for (int i = 0; i < colors.size(); i++) {
			Button btn = new Button(colorButtonStyle);
			scale = (Gdx.graphics.getWidth() * 0.055f) / colorButtonWidth;
			btn.setHeight(scale * colorButtonWidth);
			btn.setWidth(scale * colorButtonWidth);
			btn.setColor(colors.get(i));
			// btn.setX(100);
			btn.setX(((Gdx.graphics.getWidth() - ((colorButtonWidth * scale + 5) * colors
					.size())) / 2) + (colorButtonWidth * scale + 5) * i);
			btn.setY(Gdx.graphics.getHeight() / 2 - btn.getHeight() / 2
					+ Gdx.graphics.getHeight() * 0.045f);
			btn.addListener(new InputEventListener());
			buttonGroup.add(btn);
			colorButtons.add(btn);
			this.stage.addActor(btn);
		}
	}

	// EventListener for color buttons
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
