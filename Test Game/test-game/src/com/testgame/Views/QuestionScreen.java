package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.testgame.MyGame;
import com.testgame.Models.Quiz;

public class QuestionScreen extends AbstractScreen {

	// private final Sound correct;
	// private final Sound wrong;
	Quiz currentQuestion;
	TextButton alt1Button, alt2Button, alt3Button, alt4Button;
	Label questionText, responseText;

	/**
	 * Constructor to keep a reference to the main Game class
	 * 
	 * @param game
	 */
	public QuestionScreen(MyGame game) {
		super(game);
		// correct = Gdx.audio.newSound(Gdx.files.internal("data/correct.wav"));
		// wrong = Gdx.audio.newSound(Gdx.files.internal("data/wrong.wav"));
	}

	/**
	 * Updates and draws stuff.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();
		questionText.draw(batch, 1.0f);
		batch.end();
	}

	/**
	 * Button initialization
	 */
	public void initializeButtons() {
		// get question
		currentQuestion = (Quiz) game.getQuestionPool().getRandomQuestion();

		// create buttons
		// Vi kan skalere tekstst¿rrelsen til Œ passe i knappene.
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

		// add input listener
		alt1Button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (currentQuestion.getAlt1().isCorrectAnswer()) {
					// Answer was correct. 
					// 1. Update score  
					game.getCurrentPlayer().updateScore(100);
					game.switchCurrentPlayer();
					// 2. TODO: Show that answer was correct
					
					// 3. Move on to the nextplayerscreen.
					game.setScreen(new NextPlayerScreen(game, 1));
				} else {
					// Answer was wrong.
					// 1. Show that answer was wrong 2. Move on to the nextplayerscreen.
					
				}
			}

		});
		alt2Button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (currentQuestion.getAlt2().isCorrectAnswer())
					game.setScreen(new NextPlayerScreen(game, 1));
			}
		});
		alt3Button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (currentQuestion.getAlt3().isCorrectAnswer())
					game.setScreen(new NextPlayerScreen(game, 1));
			}
		});
		alt4Button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (currentQuestion.getAlt4().isCorrectAnswer())
					game.setScreen(new NextPlayerScreen(game, 1));
			}
		});

		// Question text field
		LabelStyle labelStyleHeader = new LabelStyle();
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
}