package com.testgame.Views;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.MyGame;
import com.testgame.Models.Alternative;
import com.testgame.Models.DuelState;
import com.testgame.Models.Quiz;
import com.testgame.Views.QuestionScreen.InputEventListener;

public class AlternativeGroupView {
	
	private Boolean advantageEnabled;
	private Quiz currentQuestion;
	private TextButtonStyle buttonStyle;
	private MyGame game;
	
	public AlternativeGroupView(Quiz question, TextButtonStyle buttonStyle, MyGame game) {
		this.advantageEnabled = false;
		this.currentQuestion = question;
		this.game = game;
		this.buttonStyle = buttonStyle;
	}

	public ArrayList<AlternativeView> getList() {
		TextButton alt1Button, alt2Button, alt3Button, alt4Button;
		AlternativeView alt1View, alt2View, alt3View, alt4View;
		
		// Create buttons
		// Scaling text to fit the buttons.
		buttonStyle.font.setScale(0.7f);

		// Alternative 1
		alt1Button = new TextButton(currentQuestion.getAlt1().getName(),
				buttonStyle);
		alt1Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt1Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt1Button
				.setX(Gdx.graphics.getWidth() / 2 - alt1Button.getWidth() - 5);
		alt1Button
				.setY((Gdx.graphics.getHeight() / 2 - alt1Button.getHeight() / 2));
		alt1View = new AlternativeView(currentQuestion.getAlt1(), alt1Button);
		
		// Alternative 2
		alt2Button = new TextButton(currentQuestion.getAlt2().getName(),
				buttonStyle);
		alt2Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt2Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt2Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt2Button
				.setY((Gdx.graphics.getHeight() / 2 - alt2Button.getHeight() / 2));
		alt2View = new AlternativeView(currentQuestion.getAlt2(), alt2Button);
		
		// Alternative 3
		alt3Button = new TextButton(currentQuestion.getAlt3().getName(),
				buttonStyle);
		alt3Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt3Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt3Button
				.setX(Gdx.graphics.getWidth() / 2 - alt3Button.getWidth() - 5);
		alt3Button.setY((Gdx.graphics.getHeight() / 4));
		alt3View = new AlternativeView(currentQuestion.getAlt3(), alt3Button);
		
		// Alternative 4
		alt4Button = new TextButton(currentQuestion.getAlt4().getName(),
				buttonStyle);
		alt4Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt4Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt4Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt4Button.setY((Gdx.graphics.getHeight() / 4));
		alt4View = new AlternativeView(currentQuestion.getAlt4(), alt4Button);

		
		ArrayList<AlternativeView> alternativeViews = new ArrayList(Arrays.asList(alt1View, alt2View, alt3View, alt4View));
		if (this.game.getDuelState().isDuel() &&
			(this.game.getCurrentPlayer() == this.game.getDuelState().getDefendant())) {
			for (AlternativeView av : alternativeViews) {
				if (!this.advantageEnabled && !av.getModel().isCorrectAnswer()) {
					av.getView().setDisabled(true);
					this.advantageEnabled = true;
				}
			}
		}
		return alternativeViews;
	}
	
}
