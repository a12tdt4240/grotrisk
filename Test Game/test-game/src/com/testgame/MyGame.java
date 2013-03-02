package com.testgame;

import com.badlogic.gdx.ApplicationListener;
import com.testgame.Views.EndGameScreen;
import com.testgame.Views.GameScreen;
import com.testgame.Views.MainMenuScreen;
import com.testgame.Views.QuestionScreen;
import com.badlogic.gdx.Game;

public class MyGame extends Game implements ApplicationListener  {
	
	public MainMenuScreen mainMenuScreen;
//    public GameScreen gameScreen;
//	public QuestionScreen questionScreen;
//	public EndGameScreen endGameScreen;
	
	@Override
	public void create() {		
		mainMenuScreen = new MainMenuScreen(this);
//      gameScreen = new GameScreen(this);
//		questionScreen = new QuestionScreen(this);
//		endGameScreen = new EndGameScreen(this);
		
        setScreen(mainMenuScreen); 
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
