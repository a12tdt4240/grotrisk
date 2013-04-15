package com.testgame.Views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.testgame.MyGame;
import com.testgame.Models.Player;

public class ScoreView extends AbstractScreen {

	// Graphics data
	private TextureAtlas atlas;
	private BitmapFont font;
	private SpriteBatch batch;
	private TextureRegion colorContainerImage;
	private LabelStyle labelStyle;

	private ArrayList<Player> players;
	private ArrayList<Label> names;
	private ArrayList<Label> scores;

	public ScoreView(MyGame game) {
		super(game);
		this.players = game.getPlayers();

		this.names = new ArrayList<Label>();
		this.scores = new ArrayList<Label>();

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
			batch.draw(colorContainerImage, 0, i * 50);

			score = scores.get(i);
			score.setX(50);
			score.setY(i * 50);
			score.draw(batch, 1.0f);

			name = names.get(i);
			name.setX(150);
			name.setY(i * 50);
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

		colorContainerImage = atlas.findRegion("area001");
		
		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		
		labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = new Color(0, 0, 0, 1.0f);

		for (int i = 0; i < this.players.size(); i++) {
			scores.add(new Label("" + players.get(i).getScore().getScore(),
					labelStyle));
			names.add(new Label("" + players.get(i).getName(), labelStyle));
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
	public void hide() {
		batch.dispose();
	}

}
