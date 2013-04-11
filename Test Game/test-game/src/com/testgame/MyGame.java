package com.testgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.testgame.Models.Map;
import com.testgame.Models.MapFactory;
import com.testgame.Models.Player;
import com.testgame.Models.QuestionPool;
import com.testgame.Views.GameScreen;
import com.testgame.Views.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

public class MyGame extends Game implements ApplicationListener {

	GameScreen gameScreen;
	private Music music;
	QuestionPool questionPool;
	Player player1, player2, currentPlayer;

	@Override
	public void create() {
		// Load and start playing music.
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle(
				"data/Riskspillet.mp3", FileType.Internal));
		music.setLooping(true);
		music.play();
		
		// Generate question pool.
		questionPool = new QuestionPool();
		
		Map map = new MapFactory().createDefaultMap();
		
		// Create players.
		player1 = new Player(1);
		player2 = new Player(2);
		// Set player colors
		player1.setColor(Color.BLACK);
		player2.setColor(Color.ORANGE);
		// Set the current player.
		setCurrentPlayer(player1);
		
		// Create the game screen.
		gameScreen = new GameScreen(this);
		
		// Create and launch main menu screen.
		setScreen(new MainMenuScreen(this));
	}
	
	/**
	 * Switches which player is the current player.
	 */
	public void switchCurrentPlayer() {
		switch(currentPlayer.getNumeric()) {
		case 1:
			setCurrentPlayer(player2);
			break;
		case 2:
			setCurrentPlayer(player1);
			break;
		}
	}
	
	/**
	 * Returns the current player.
	 * @return
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player.
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Returns the question pool.
	 * @return
	 */
	public QuestionPool getQuestionPool() {
		return questionPool;
	}

	@Override
	public void dispose() {
		music.dispose();
	}

	public Screen getGameScreen() {
		return gameScreen;
	}
}
