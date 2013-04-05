package com.testgame.Views;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;

public class NextPlayerScreen extends AbstractScreen {

	TextButton nextPlayerButton;
	Label nextPlayerInfo;
	Label nextPlayerLabel;
	
	int player;
	
	// constructor to keep a reference to the main Game class
	public NextPlayerScreen(MyGame game, int player) {
		super(game);
		this.player = player;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		
		nextPlayerInfo.draw(batch, 1.0f);
		nextPlayerLabel.draw(batch, 1.0f);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	@Override
	public void show() {
		super.show();
	}
	
	/**
	 * Sets the style of UI elements.
	 */
	public void initializeStyle() {
		super.initializeStyle();
	}
	
	public void initializeButtons() {
		
		// Button initalization
		nextPlayerButton = new TextButton("Ok", buttonStyle);
		nextPlayerButton.setWidth(458);
		nextPlayerButton.setHeight(88);
		nextPlayerButton.setX(Gdx.graphics.getWidth() / 2 - nextPlayerButton.getWidth() / 2);
		nextPlayerButton.setY((Gdx.graphics.getHeight() / 2 - nextPlayerButton.getHeight() / 2) - Gdx.graphics.getHeight() * 0.15f);
		
		nextPlayerButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
		});
		
		// TextField init
		LabelStyle labelStyleHeader = new LabelStyle();
		labelStyleHeader.font = font;
		labelStyleHeader.fontColor = new Color(0.5f, 0.5f, 0.5f, 1.0f);
		nextPlayerInfo = new Label("Send enheten til:", labelStyleHeader);
		nextPlayerInfo.setX(Gdx.graphics.getWidth() / 2 - nextPlayerInfo.getWidth() / 2);
		nextPlayerInfo.setY(Gdx.graphics.getHeight() / 2 - nextPlayerInfo.getHeight() / 2 + Gdx.graphics.getHeight() * 0.19f);
		nextPlayerInfo.setWidth(0.6f * Gdx.graphics.getWidth());
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		nextPlayerLabel = new Label("spiller " + player, labelStyle);
		nextPlayerLabel.setX(Gdx.graphics.getWidth() / 2 - nextPlayerLabel.getWidth() / 2);
		nextPlayerLabel.setY(Gdx.graphics.getHeight() / 2 - nextPlayerLabel.getHeight() / 2 + Gdx.graphics.getHeight() * 0.045f);
		nextPlayerLabel.scale(0.8f, 0.8f);

		
		this.stage.addActor(nextPlayerButton);
	}

	@Override
	public void hide() {
		super.hide();
	}
}