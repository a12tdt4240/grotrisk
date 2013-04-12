package com.testgame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.testgame.MyGame;
import com.testgame.Models.Area;

public class GameScreen extends AbstractScreen {

	// The game screen has a blue background or sea texture.
	// On top of this sea we draw land areas (AreaViews), which are clickable.
	Stage stage;
	SpriteBatch batch;

	// Graphics data
	TextureAtlas atlas;
	BitmapFont font;
	Skin skin;
	TextButtonStyle buttonStyle;

	// Our NinePatches
	NinePatch background;
	
	MapView mapView;
	AreaView areaView1, areaView2, areaView3;
	boolean timeToRePaint;

	/**
	 * Constructor keeping a reference to the main Game class
	 * @param game
	 */
	public GameScreen(MyGame game) {
		super(game);
		
		// Create dummy areaViews
		areaView1 = new AreaView(new Area((2 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		areaView2 = new AreaView(new Area((3 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		areaView3 = new AreaView(new Area((4 * Gdx.graphics.getWidth()) / 6, Gdx.graphics.getHeight() / 2, 100));
		
		setTimeToRePaint(false);
		
		// Add listeners to dummy areas
		areaView1.addListener(new InputEventListener());
		areaView2.addListener(new InputEventListener());
		areaView3.addListener(new InputEventListener());
	}

	/**
	 * Updates and draws objects.
	 **/
	@Override
	public void render(float delta) {
		super.render(delta);
		
		stage.act(delta);

		batch.begin();
		background.draw(batch,
						(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 1.0f) / 2,
						(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 1.0f) / 2,
						Gdx.graphics.getWidth() * 1.0f,
						Gdx.graphics.getHeight() * 1.0f);
		
		
		batch.end();

		stage.draw();
		
		// Ask a variable if areas needs to be updated.
				if (timeToRePaint) rePaintAreaViews();
		
	}
	
	/**
	 * Paints all area views.
	 */
	private void rePaintAreaViews() {
		// Currently the dummies.
		areaView1.setColor(areaView1.getArea().getColor());
		areaView2.setColor(areaView2.getArea().getColor());
		areaView3.setColor(areaView3.getArea().getColor());
		
		// Reset the timeToRePaint variable.
		setTimeToRePaint(false);
	}
	
	/**
	 * Controls if it is time to repaint the area views.
	 * @param val
	 */
	public void setTimeToRePaint(boolean val) {
		timeToRePaint = val;
	}


	/**
	 * Called when this screen is set as the screen with game.setScreen();
	 */
	public void show() {
		super.show();
		
		stage = new Stage();

		atlas = new TextureAtlas("skins/mainmenu.atlas");

		skin = new Skin();
		skin.addRegions(atlas);

		background = new NinePatch(new TextureRegion(
				atlas.findRegion("background")), 190, 190, 114, 292);

		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		batch = new SpriteBatch();
		
		setTimeToRePaint(true);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

		if (stage == null)
			stage = new Stage(width, height, false);

		stage.clear(); // clears the stage

		Gdx.input.setInputProcessor(stage); // sets gdx to listen to input from
											// this stage
		
		this.stage.addActor(areaView1);
		this.stage.addActor(areaView2);
		this.stage.addActor(areaView3);
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
			AreaView areaView = (AreaView) event.getTarget();
			game.setScreen(new QuestionScreen(game, areaView.getArea()));

		}
	}
	
	@Override
	public void dispose() {
		Gdx.app.debug("testgame", "Disposing Game Screen");

		atlas.dispose();
		stage.dispose();
		font.dispose();
		skin.dispose();
		batch.dispose();
		background.getTexture().dispose();
	}
}