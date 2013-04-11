package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.testgame.MyGame;
import com.testgame.Models.Area;
import com.testgame.Models.Quiz;

public class QuestionScreen extends AbstractScreen {

	// private final Sound correct;
	// private final Sound wrong;
	Quiz currentQuestion;
	TextButton alt1Button, alt2Button, alt3Button, alt4Button;
	Label questionText, responseText, countDownText;
	LabelStyle labelStyleHeader;
	int countDownTime = 20;
	int currentTime = 0;
	Area area;

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public QuestionScreen(MyGame game, Area area) {
		super(game);
		this.area = area;
		// correct = Gdx.audio.newSound(Gdx.files.internal("data/correct.wav"));
		// wrong = Gdx.audio.newSound(Gdx.files.internal("data/wrong.wav"));
		startTimer();
	}

	private void startTimer() {
		Timer.schedule(new Task() {

			@Override
			public void run() {
				if (countDownTime - currentTime != 0) {
					currentTime++;
					startTimer();
				} else {
					wrongAnswer();
				}
			}

		}, 1);
	}

	/**
	 * Helper method setting the next screen.
	 */
	private void nextPlayer() {
		game.setScreen(new NextPlayerScreen(game));
	}

	/**
	 * Updates and draws stuff.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);

		// Count down label must update each render
		countDownText = new Label("" + (countDownTime - currentTime),
				labelStyleHeader);
		countDownText.setX((4 * Gdx.graphics.getWidth()) / 5);
		countDownText.setY((3 * Gdx.graphics.getHeight()) / 4);

		batch.begin();
		questionText.draw(batch, 1.0f);
		countDownText.draw(batch, 1.0f);
		batch.end();
	}

	/**
	 * Button initialization
	 */
	public void initializeButtons() {
		// get question
		currentQuestion = (Quiz) game.getQuestionPool().random();
		// currentQuestion = (Quiz) game.getQuestionPool().getTestQuestion();

		// create buttons
		// Vi kan skalere tekststørrelsen til å passe i knappene.
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
		// Alternative 2
		alt2Button = new TextButton(currentQuestion.getAlt2().getName(),
				buttonStyle);
		alt2Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt2Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt2Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt2Button
				.setY((Gdx.graphics.getHeight() / 2 - alt2Button.getHeight() / 2));
		// Alternative 3
		alt3Button = new TextButton(currentQuestion.getAlt3().getName(),
				buttonStyle);
		alt3Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt3Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt3Button
				.setX(Gdx.graphics.getWidth() / 2 - alt3Button.getWidth() - 5);
		alt3Button.setY((Gdx.graphics.getHeight() / 4));
		// Alternative 4
		alt4Button = new TextButton(currentQuestion.getAlt4().getName(),
				buttonStyle);
		alt4Button.setWidth(Gdx.graphics.getWidth() * 0.3f);
		alt4Button.setHeight(Gdx.graphics.getHeight() * 0.15f);
		alt4Button.setX(Gdx.graphics.getWidth() / 2 + 5);
		alt4Button.setY((Gdx.graphics.getHeight() / 4));

		alt1Button.addListener(new InputEventListener());
		alt2Button.addListener(new InputEventListener());
		alt3Button.addListener(new InputEventListener());
		alt4Button.addListener(new InputEventListener());

		// Question text field
		labelStyleHeader = new LabelStyle();
		labelStyleHeader.font = font;
		labelStyleHeader.fontColor = new Color(0.647059f, 0.164706f, 0.164706f,
				1.0f);
		questionText = new Label(currentQuestion.getQuestionText(),
				labelStyleHeader);
		questionText.setX(Gdx.graphics.getWidth() / 2 - questionText.getWidth()
				/ 2);
		questionText.setY(Gdx.graphics.getHeight() / 2
				- questionText.getHeight() / 2 + Gdx.graphics.getHeight()
				* 0.19f);
		questionText.setWidth(0.6f * Gdx.graphics.getWidth());

		this.stage.addActor(alt1Button);
		this.stage.addActor(alt2Button);
		this.stage.addActor(alt3Button);
		this.stage.addActor(alt4Button);
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	public void hide() {
		super.hide();

		// correct.dispose();
		// wrong.dispose();
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
			Label alt = (Label) event.getTarget();
			String altName = alt.getText().toString();

			if (altName.equals(currentQuestion.getAlt1().getName())) {
				if (currentQuestion.getAlt1().isCorrectAnswer()) {
					correctAnswer();
				} else {
					wrongAnswer();
				}
			} else if (altName.equals(currentQuestion.getAlt2().getName())) {
				if (currentQuestion.getAlt2().isCorrectAnswer()) {
					correctAnswer();
				} else {
					wrongAnswer();
				}
			} else if (altName.equals(currentQuestion.getAlt3().getName())) {
				if (currentQuestion.getAlt3().isCorrectAnswer()) {
					correctAnswer();
				} else {
					wrongAnswer();
				}
			} else if (altName.equals(currentQuestion.getAlt4().getName())) {
				if (currentQuestion.getAlt4().isCorrectAnswer()) {
					correctAnswer();
				} else {
					wrongAnswer();
				}
			}
		}

	}

	/**
	 * Called when the answer was correct.
	 */
	private void correctAnswer() {
		// 1. Update score, currently with 100 points.
		game.getCurrentPlayer().updateScore(area.getValueOfArea());
		// 2. TODO: Show that answer was correct

		// 3. Move on to the nextplayerscreen.
		game.switchCurrentPlayer();
		nextPlayer();
	}

	private void wrongAnswer() {
		// 1. TODO: Show that answer was wrong.

		// 2. Move on to the nextplayerscreen.
		game.switchCurrentPlayer();
		// nextPlayer();
	}
}
