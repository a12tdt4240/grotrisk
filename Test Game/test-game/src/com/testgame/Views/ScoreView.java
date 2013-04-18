package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.testgame.MyGame;
import com.testgame.Models.Constants;
import com.testgame.Models.Player;

public class ScoreView extends AbstractView implements Observer {

	// Graphics data
	private TextureRegion colorContainerImage;
	private LabelStyle labelStyle;
	private Skin menuSkin;
	private TextButton exitButton;
	
	// Player information data
	private ArrayList<Player> players;
	private ArrayList<Label> names;
	private ArrayList<Label> scores;
	private ArrayList<Image> colorIcons;
	
	
	public ScoreView(MyGame game) {
		super(game);

		this.colorIcons = new ArrayList<Image>();
		this.names = new ArrayList<Label>();
		this.scores = new ArrayList<Label>();
		// Load skins
		this.skin = new Skin();
		this.skin.addRegions(new TextureAtlas("data/maps/map.atlas"));
		
		this.menuSkin = new Skin();
		this.menuSkin.addRegions(new TextureAtlas("skins/mainmenu.atlas"));
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		Label score, name;
		Image img;
		
		batch.begin();
		
		// Add information about the players
		for (int i = 0; i < players.size(); i++) {
			
			// create the color icons
			img = colorIcons.get(i);
			img.setColor(players.get(i).getColor());
			img.setPosition(0, i * 50);
			img.setWidth(50);
			img.setHeight(50);
			img.draw(batch, 1);

			// create the score label
			score = scores.get(i);
			score.setX(50);
			score.setY(i * 50 - 10);
			score.draw(batch, 1.0f);

			// create the name label
			name = names.get(i);
			name.setX(150);
			name.setY(i * 50 - 10);
			name.draw(batch, 1.0f);

		}
		
		batch.end();
	}

	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	@Override
	public void show() {
		
		scores.clear();
		names.clear();
		// create a new stage to collect our actors.
		stage = new Stage();
		
		// get the players each time the view is shown
		players = game.getPlayers();
		
		// TextureRegion to use as color icon
		colorContainerImage = skin.getRegion("area001");
		
		// Create images of the TextureRegion
		for (int i = 0; i < players.size(); i++) {
			colorIcons.add(new Image(colorContainerImage));
		}
		
		// The font to use
		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		
		// The style to our labels
		labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = new Color(1, 1, 1, 1.0f);
		
		Label score, name;
		// Create our labels and set ScoreView as an observer to a Score
		for (int i = 0; i < this.players.size(); i++) {
			score = new Label("" + players.get(i).getScore().getScore(), labelStyle);
			name = new Label("" + players.get(i).getName(), labelStyle);
			score.setFontScale(0.6f);
			name.setFontScale(0.6f);
			players.get(i).getScore().addObserver(this);
			scores.add(score);
			names.add(name);
		}
		
		batch = new SpriteBatch();
		
		// Our exit button style
		TextButtonStyle exitButtonStyle = new TextButtonStyle();
		exitButtonStyle.up = menuSkin.getDrawable("buttonUp");
		exitButtonStyle.font = font;
		
		// initialize the exit button
		exitButton = new TextButton(Constants.EXIT_BUTTON, exitButtonStyle);
		exitButton.setWidth(Gdx.graphics.getWidth() * 0.2f);
		exitButton.setHeight(Gdx.graphics.getHeight() * 0.1f);
		exitButton.setX(Gdx.graphics.getWidth() - (float)(exitButton.getWidth() + 10));
		exitButton.setY(10f);
		
		// Set the font to scale according to the screen size.
		exitButtonStyle.font.setScale((exitButton.getWidth() * 4) / Gdx.graphics.getWidth());
		
		// Add listener to the exit button
		exitButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("grotrisk", "touchDown: avslutt");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("grotrisk", "touchUp: avslutt");
				game.resetGame();
				game.setScreen(new MainMenuView(game));
			}
		});
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		// add exit button to stage
		this.stage.addActor(exitButton);
	}

	/**
	 * Called when the current screen changes from this to a different screen.
	 * Remember to dispose objects.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		colorContainerImage.getTexture().dispose();
	}

	/**
	 * If a score updates, update label with the new score
	 */
	@Override
	public void changeEvent() {
		for (int i = 0; i < players.size(); i++) {
			scores.get(i).setText("" + players.get(i).getScore().getScore());
		}
	}
}
