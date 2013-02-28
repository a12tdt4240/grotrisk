import com.badlogic.gdx.Screen;
 
public class EndGameScreen extends AbstractScreen {

 
    // constructor to keep a reference to the main Game class
    public EndGameScreen(Game game){
        super(game);
    }
        
    @Override
    public void render(float delta) {
        // update and draw stuff
        if (Gdx.input.justTouched())
            game.setScreen(game.MainMenuScreen);
    }

    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();
    }
 

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }
 }