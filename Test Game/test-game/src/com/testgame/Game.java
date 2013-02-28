package com.testgame;

import com.badlogic.gdx.ApplicationListener;

public class Game implements ApplicationListener {
	
	MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;
	QuestionScreen questionScreen;
	EndGameScreen endGameScreen;
	
	@Override
	public void create() {		
		mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
		questionScreen = new QuestionScreen(this);
		endGameScreen = new EndGameScreen(this);
		
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
