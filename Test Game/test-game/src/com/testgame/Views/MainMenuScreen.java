package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.MyGame;

public class MainMenuScreen extends AbstractScreen {
	
	Stage stage;
	SpriteBatch batch;
	
	// Graphics data
	TextureAtlas atlas;
	BitmapFont font;
	Skin skin;
	TextButtonStyle buttonStyle;
	
	// Our NinePatches
	NinePatch panel;
	NinePatch background;

	// Our Buttons
	TextButton newGameButton;

	// constructor to keep a reference to the main Game class
	public MainMenuScreen(MyGame game) {
		super(game);
	}

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		
		if (stage == null)
			stage = new Stage(width, height, false);
		
		stage.clear(); // clears the stage
		
		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from this stage
		
		initializeStyle(); // Sets the style of buttons etc.
		initializeButtons(); // Creates our buttons
	}
	
	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		
		stage = new Stage();
		
		atlas = new TextureAtlas("skins/mainmenu.atlas");
		
		skin = new Skin();
		skin.addRegions(atlas);
		
		background  = new NinePatch(new TextureRegion(atlas.findRegion("background")),190, 190, 114, 292);
		panel = new NinePatch(new TextureRegion(atlas.findRegion("panel")),215, 200, 140, 140);
		
		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		
		batch = new SpriteBatch();
		
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
		background.draw(batch,
				(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 1.0f) / 2,
				(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 1.0f) / 2,
				Gdx.graphics.getWidth() * 1.0f,
				Gdx.graphics.getHeight() * 1.0f
				);
		panel.draw(batch,
				(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 0.8f) / 2,
				(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.8f) / 2,
				Gdx.graphics.getWidth() * 0.8f,
				Gdx.graphics.getHeight() * 0.8f
				);
		batch.end();
		
		stage.draw();
		
	}
	
	/**
	 * Sets the style of UI elements.
	 */
	public void initializeStyle() {
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.getDrawable("buttonUp");
		buttonStyle.font = font;
	}
	
	/**
	 * Creates the buttons for drawing.
	 */
	public void initializeButtons() {
		newGameButton = new TextButton("Nytt spill", buttonStyle);
		newGameButton.setWidth(458);
		newGameButton.setHeight(88);
		newGameButton.setX(Gdx.graphics.getWidth() / 2 - newGameButton.getWidth() / 2);
		newGameButton.setY(Gdx.graphics.getHeight() / 2 - newGameButton.getHeight() / 2);
		
		newGameButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
		});
		stage.addActor(newGameButton);
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	@Override
	public void hide() {
		Gdx.app.debug("testgame", "Disposing Main Menu");
		
		atlas.dispose();
		stage.dispose();
		font.dispose();
		skin.dispose();
		batch.dispose();
		panel.getTexture().dispose();
		background.getTexture().dispose();
	}
}