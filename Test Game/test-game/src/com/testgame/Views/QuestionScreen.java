package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.testgame.MyGame;
import com.testgame.Models.Question;

public class QuestionScreen extends AbstractScreen {

	// private final Sound correct;
	// private final Sound wrong;
	Question currentQuestion;
	TextButton alt1Button, alt2Button, alt3Button, alt4Button;
	Label questionText;

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

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	/**
	 * Sets the style of UI elements.
	 */
	public void initializeStyle() {
		super.initializeStyle();
	}

	/**
	 * Button initialization
	 */
	public void initializeButtons() {
		// get question
		currentQuestion = game.getQuestionPool().getRandomQuestion();
		
		// create buttons
		// Alternative 1
		alt1Button = new TextButton(currentQuestion.getAlt1().getAlternativeText(), buttonStyle);
		alt1Button.setWidth(200);
		alt1Button.setHeight(100);
		alt1Button
				.setX(Gdx.graphics.getWidth() / 2 - alt1Button.getWidth() / 2);
		alt1Button
				.setY((Gdx.graphics.getHeight() / 4 - alt1Button.getHeight() / 4)
						- Gdx.graphics.getHeight() * 0.15f);
		// Alternative 2
		alt2Button = new TextButton(currentQuestion.getAlt2().getAlternativeText(), buttonStyle);
		alt2Button.setWidth(200);
		alt2Button.setHeight(100);
		alt2Button
				.setX(Gdx.graphics.getWidth() / 2 - alt2Button.getWidth() / 2);
		alt2Button
				.setY((Gdx.graphics.getHeight() / 2 - alt2Button.getHeight() / 2)
						- Gdx.graphics.getHeight() * 0.15f);

		// add input listener
		alt1Button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new NextPlayerScreen(game, 1));
			}
		});

		// Question text field
		LabelStyle labelStyleHeader = new LabelStyle();
		labelStyleHeader.font = font;
		labelStyleHeader.fontColor = new Color(0.647059f, 0.164706f, 0.164706f,
				1.0f);
		questionText = new Label(currentQuestion.getQuestionText(), labelStyleHeader);
		questionText.setX(Gdx.graphics.getWidth() / 2 - questionText.getWidth()
				/ 2);
		questionText.setY(Gdx.graphics.getHeight() / 2
				- questionText.getHeight() / 2 + Gdx.graphics.getHeight()
				* 0.19f);
		questionText.setWidth(0.6f * Gdx.graphics.getWidth());

		this.stage.addActor(alt1Button);
		this.stage.addActor(alt2Button);
		// this.stage.addActor(alt3Button);
		// this.stage.addActor(alt4Button);
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		super.show();
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	public void hide() {
		// correct.dispose();
		// wrong.dispose();
	}
}