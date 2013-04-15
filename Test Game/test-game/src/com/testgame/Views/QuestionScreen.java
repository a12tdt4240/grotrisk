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
import com.testgame.Models.Alternative;
import com.testgame.Models.Area;
import com.testgame.Models.Quiz;

public class QuestionScreen extends AbstractMenuScreen {

	// private final Sound correct;
	// private final Sound wrong;
	Quiz currentQuestion;
	TextButton alt1Button, alt2Button, alt3Button, alt4Button;
	Label questionText, responseText, countDownText;
	LabelStyle labelStyleHeader;
	// How many seconds you get to answer a question.
	int countDownTime = 20;
	int currentTime = 0;
	Area area;

	boolean hasAnswered = false;

	/**
	 * Constructor keeping a reference to the main Game class
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

	/**
	 * The count down timer.
	 */
	private void startTimer() {
		Timer.schedule(new Task() {

			@Override
			public void run() {
				if (!hasAnswered) {
					if (countDownTime - currentTime != 0) {
						currentTime++;
						startTimer();
					} else {
						wrongAnswer();
					}
				}
			}

		}, 1);
	}

	/**
	 * Helper method setting the next screen. If the game is considered finished
	 * it sets the end game screen.
	 */
	private void nextPlayer() {
		if (isGameFinished()) {
			game.setScreen(new EndGameScreen(game));
		} else {
			game.setScreen(new NextPlayerScreen(game));
		}
	}

	/**
	 * Currently: Asks if the game has run for # moves.
	 * 
	 * @return
	 */
	private boolean isGameFinished() {
		if (game.getPlaysCounter() >= 20) {
			return true;
		}
		return false;
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
		// Retrieve question from question pool
		currentQuestion = (Quiz) game.getQuestionPool().random();

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

		// Add listeners to each button.
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

		// Add the buttons to the stage.
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
		// Stop the count down clock. Not 100% sure if this is working
		// correctly.
		Timer.instance.clear();

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
			Label altButton = (Label) event.getTarget();
			String altName = altButton.getText().toString();

			if (altName.equals(currentQuestion.getAlt1().getName())) {
				handleEvent(altButton, currentQuestion.getAlt1());
			} else if (altName.equals(currentQuestion.getAlt2().getName())) {
				handleEvent(altButton, currentQuestion.getAlt2());
			} else if (altName.equals(currentQuestion.getAlt3().getName())) {
				handleEvent(altButton, currentQuestion.getAlt3());
			} else if (altName.equals(currentQuestion.getAlt4().getName())) {
				handleEvent(altButton, currentQuestion.getAlt4());
			}
		}

	}

	/**
	 * Takes an alternative, checks if it is correct and handles the given
	 * situation appropriately
	 * 
	 * @param Alternative
	 */
	private void handleEvent(Label lab, Alternative alt) {
		hasAnswered = true;

		if (alt.isCorrectAnswer()) {
			lab.setColor(Color.GREEN);

			Timer.schedule(new Task() {

				@Override
				public void run() {
					correctAnswer();
				}

			}, 2);

		} else {
			lab.setColor(Color.RED);

			Timer.schedule(new Task() {

				@Override
				public void run() {
					wrongAnswer();
				}

			}, 2);

		}
	}

	/**
	 * Called when the answer was correct.
	 */
	private void correctAnswer() {
		// Update score and set the new owner of the area.
		game.getCurrentPlayer().getScore().updateScore(area.getValueOfArea());
		area.setOwner(game.getCurrentPlayer());
		// Move on to the next player screen.
		game.switchCurrentPlayer();
		nextPlayer();
	}

	/**
	 * Called when the answer was wrong.
	 */
	private void wrongAnswer() {
		// Move on to the next player screen.
		game.switchCurrentPlayer();
		nextPlayer();
	}
}
