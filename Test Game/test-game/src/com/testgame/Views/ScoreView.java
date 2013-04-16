package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.testgame.MyGame;
import com.testgame.Models.Player;

public class ScoreView extends AbstractScreen implements Observer {

	// Graphics data
	private TextureAtlas atlas;
	private BitmapFont font;
	private SpriteBatch batch;
	private TextureRegion colorContainerImage;
	private LabelStyle labelStyle;

	private ArrayList<Player> players;
	private ArrayList<Label> names;
	private ArrayList<Label> scores;
	private ArrayList<Image> colorIcons;

	public ScoreView(MyGame game) {
		super(game);
		this.players = game.getPlayers();

		this.names = new ArrayList<Label>();
		this.scores = new ArrayList<Label>();
		this.colorIcons = new ArrayList<Image>();
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		Label score;
		Label name;
		batch.begin();
		for (int i = 0; i < players.size(); i++) {
			Image img = colorIcons.get(i);
			img.setColor(players.get(i).getColor());
			img.setPosition(0, i * 50);
			img.setWidth(50);
			img.setHeight(50);
			img.draw(batch, 1);

			score = scores.get(i);
			score.setX(50);
			score.setY(i * 50 - 10);
			score.draw(batch, 1.0f);

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
		
		
		atlas = new TextureAtlas("data/maps/map.atlas");
		skin = new Skin();
		skin.addRegions(atlas);
		
		colorContainerImage = skin.getRegion("area001");
		
		for (int i = 0; i < players.size(); i++) {
			colorIcons.add(new Image(colorContainerImage));
		}
		
		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		
		labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = new Color(1, 1, 1, 1.0f);

		for (int i = 0; i < this.players.size(); i++) {
			Label score = new Label("" + players.get(i).getScore().getScore(), labelStyle);
			Label name = new Label("" + players.get(i).getName(), labelStyle);
			score.setFontScale(0.6f);
			name.setFontScale(0.6f);
			players.get(i).getScore().addObserver(this);
			scores.add(score);
			names.add(name);
		}
		
		
		batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
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

	@Override
	public void changeEvent() {
		for (int i = 0; i < players.size(); i++) {
			Label score = new Label("" + players.get(i).getScore().getScore(), labelStyle);
			score.setFontScale(0.6f);
			scores.set(i, score);
		}
	}
}
