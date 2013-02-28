import com.badlogic.gdx.*;
 
public class MainMenuScreen extends AbstractScreen {

    TextureRegion mainMenu;
	SpriteBatch batch;
	float time = 0;
 
    // constructor to keep a reference to the main Game class
    public MainMenuScreen(Game game){
        super(game);
    }
        
	/**
	*	Updates and draws objects.
	**/
    @Override
    public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mainMenu, 0, 0);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.justTouched()) {
				game.setScreen(new GameScreen(game));
			}
		}
		
        // if (Gdx.input.justTouched())
        //    game.setScreen(game.GameScreen);
    }
	
	/**
	* This is called when this screen is set as the screen with game.setScreen();
	** /
    @Override
    public void show() {
		mainMenu = new TextureRegion(new Texture(Gdx.files.internal("data/menu.png")), 0, 0, 480, 320);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
    }
 
	/**
	* This is called when the current screen changes from this to a different screen.
	**/
    @Override
    public void hide() {
		Gdx.app.debug("testgame", "Disposing Main Menu");
		mainMenu.getTexture().dispose();
		batch.dispose();
    }
 }