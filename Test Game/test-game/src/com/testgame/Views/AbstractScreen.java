import com.badlogic.gdx.Screen;
 
public abstract class AbstractScreen implements Screen {

    Game game;
 
    // constructor to keep a reference to the main Game class
    public GameScreen(Game game){
        this.game = game;
    }
        
    @Override
    public void render(float delta) {
    }
 
    @Override
    public void resize(int width, int height) {
    }
 

    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();
    }
 

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }
 

    @Override
    public void pause() {
    }
 

    @Override
    public void resume() {
    }
 

    @Override
    public void dispose() {
        // never called automatically
    }
 }