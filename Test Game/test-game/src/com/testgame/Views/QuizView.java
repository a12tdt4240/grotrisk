package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.testgame.MyGame;
import com.testgame.Models.Alternative;
import com.testgame.Models.Quiz;

public class QuizView extends QuestionView {
	private Quiz currentQuiz;
	private InputEventListener listener;
	private ArrayList<AlternativeView> alternatives; 
	
	public QuizView(MyGame game) {
		super(game);
		listener = new InputEventListener();
	}
	
	public void initializeButtons() {
		super.initializeButtons();
		currentQuiz = (Quiz) getCurrentQuestion();
		
		AlternativeGroupView altGroup = new AlternativeGroupView(
				currentQuiz, buttonStyle, game);
		alternatives = altGroup.getList();
		
		// Add the buttons to the stage.
		for (AlternativeView av : alternatives) {
			if (!av.getView().isDisabled()) {
				av.getView().addListener(listener);
			}
			this.stage.addActor(av.getView());
		}
	}
	
	public void removeEventListeners() {
		for(int i = 0; i < alternatives.size(); i++) {
			alternatives.get(i).getView().removeListener(listener);
		}
	}
	
	/**
	 * Takes an alternative, checks if it is correct and handles the given
	 * situation appropriately
	 * 
	 * @param Alternative
	 */
	private void handleEvent(Label lab, Alternative alt) {
		setAnswerd(true);

		if (alt.isCorrectAnswer()) {
			// Change label color.
			lab.setColor(Color.GREEN);
			// Play the correct sound.
			if (game.getMusic().isPlaying())
				playCorrectAnswerSound();
			Timer.schedule(new Task() {

				@Override
				public void run() {
					correctAnswer();
				}

			}, 2);

		} else {
			// Change the label color.
			lab.setColor(Color.RED);
			// Play the wrong sound.
			if (game.getMusic().isPlaying())
				playWrongAnswerSound();
			Timer.schedule(new Task() {

				@Override
				public void run() {
					wrongAnswer();
				}

			}, 2);

		}
	}


	/**
	 * Unified InputListener
	 * 
	 */
	class InputEventListener extends InputListener {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			Label altButton = (Label) event.getTarget();
			String altName = altButton.getText().toString();
			removeEventListeners();
			if (altName.equals(currentQuiz.getAlt1().getName())) {
				handleEvent(altButton, currentQuiz.getAlt1());
			} else if (altName.equals(currentQuiz.getAlt2().getName())) {
				handleEvent(altButton, currentQuiz.getAlt2());
			} else if (altName.equals(currentQuiz.getAlt3().getName())) {
				handleEvent(altButton, currentQuiz.getAlt3());
			} else if (altName.equals(currentQuiz.getAlt4().getName())) {
				handleEvent(altButton, currentQuiz.getAlt4());
			}
		}

	}
}
