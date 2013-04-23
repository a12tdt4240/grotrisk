package com.testgame.Views;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.SkinSingleton;
import com.testgame.Takeover;
import com.testgame.Models.Quiz;

public class AlternativeGroup {
	
	private Boolean advantageEnabled;
	private Quiz currentQuestion;
	private TextButtonStyle buttonStyle1, buttonStyle2, buttonStyle3, buttonStyle4;
	private Takeover game;
	private BitmapFont font1, font2, font3, font4;
	
	public AlternativeGroup(Quiz question, TextButtonStyle buttonStyle, Takeover game) {
		this.game = game;
		this.advantageEnabled = false;
		this.currentQuestion = question;
		buttonStyle1 = new TextButtonStyle(buttonStyle);
		buttonStyle2 = new TextButtonStyle(buttonStyle);
		buttonStyle3 = new TextButtonStyle(buttonStyle);
		buttonStyle4 = new TextButtonStyle(buttonStyle);
		font1 = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		font2 = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		font3 = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		font4 = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);

	}

	public ArrayList<AlternativeView> getList() {
		TextButton alt1Button, alt2Button, alt3Button, alt4Button;
		AlternativeView alt1View, alt2View, alt3View, alt4View;
		
		// Create buttons
		// Scaling text to fit the buttons.

		// Alternative 1
		alt1Button = new TextButton(currentQuestion.getAlt1().getName(),
				buttonStyle1);
		alt1Button = setLayoutProperties(font1, alt1Button, buttonStyle1);
		alt1Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt1Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt1Button
				.setX(Gdx.graphics.getWidth() / 2 - alt1Button.getWidth() - 5);
		alt1Button
				.setY((Gdx.graphics.getHeight() / 2 - alt1Button.getHeight() / 2));
		alt1View = new AlternativeView(currentQuestion.getAlt1(), alt1Button);
		
		// Alternative 2
		alt2Button = new TextButton(currentQuestion.getAlt2().getName(),
				buttonStyle2);
		alt2Button.getStyle().font = font2;
		alt2Button = setLayoutProperties(font2, alt2Button, buttonStyle2);
		alt2Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt2Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt2Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt2Button
				.setY((Gdx.graphics.getHeight() / 2 - alt2Button.getHeight() / 2));
		alt2View = new AlternativeView(currentQuestion.getAlt2(), alt2Button);
		
		// Alternative 3
		alt3Button = new TextButton(currentQuestion.getAlt3().getName(),
				buttonStyle3);
		alt3Button = setLayoutProperties(font3, alt3Button, buttonStyle3);
		alt3Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt3Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt3Button
				.setX(Gdx.graphics.getWidth() / 2 - alt3Button.getWidth() - 5);
		alt3Button.setY((Gdx.graphics.getHeight() / 4));
		alt3View = new AlternativeView(currentQuestion.getAlt3(), alt3Button);
		
		// Alternative 4
		alt4Button = new TextButton(currentQuestion.getAlt4().getName(),
				buttonStyle4);
		
		alt4Button = setLayoutProperties(font4, alt4Button, buttonStyle4);
		alt4Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt4Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt4Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt4Button.setY((Gdx.graphics.getHeight() / 4));
		
		alt4View = new AlternativeView(currentQuestion.getAlt4(), alt4Button);

		
		// Give advantage to defendant
		ArrayList<AlternativeView> alternativeViews = new ArrayList<AlternativeView>(Arrays.asList(alt1View, alt2View, alt3View, alt4View));
		if (this.game.getDuel().isDuel() &&
			(this.game.getCurrentPlayer() == this.game.getDuel().getDefendant())) {
			for (AlternativeView av : alternativeViews) {
				if (!this.advantageEnabled && !av.getModel().isCorrectAnswer()) {
					
					av.getView().setStyle(SkinSingleton.getInstance().getDisabledButtonStyle());
					av.getView().setDisabled(true);
					
					this.advantageEnabled = true;
				}
			}
		}
		return alternativeViews;
	}

	private TextButton setLayoutProperties(BitmapFont font, TextButton button, TextButtonStyle style) {
		if ((60 * button.getText().toString().length()) > button.getWidth()) {
			font.setScale(button.getWidth() / (60 * button.getText().toString().length()));
			style.font = font;
			button.getStyle().font = font;
//			button.getStyle().font.setScale(button.getWidth() / (100 * button.getText().toString().length()));
		} else {
			font.setScale(button.getWidth() / (9 * 60));
			style.font = font;
			
//			button.getStyle().font.setScale(button.getWidth() / (12 * 100));
		}
		button.setStyle(style);
		return button;
	}
	
	
	
	public void dispose() {
		font1.dispose();
		font2.dispose();
		font3.dispose();
		font4.dispose();
	}
	
}
