package com.testgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.testgame.Models.DuelState;
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
	ArrayList<Player> players;
	Player currentPlayer;
	private DuelState duel;
	private int playsCounter;

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
		players = createPlayers(2);
		// Set player colors TO BE UPDATED WITH COLOR CHOOSER VIEW ETC
		players.get(0).setColor(Color.BLACK);
		players.get(1).setColor(Color.ORANGE);
		// Set the current player.
		setCurrentPlayer(players.get(0));
		playsCounter = 0;

		duel = new DuelState(this);

		// Create the game screen.
		gameScreen = new GameScreen(this, map);
		
		// Create and launch main menu screen.
		setScreen(new MainMenuScreen(this));
		// setScreen(new EndGameScreen(this));
	}

	/**
	 * Switches which player is the current player.
	 */
	public void switchCurrentPlayer() {
		// If duel is active, switch between the two duelist
		if (getDuelState().isDuel()) {
			if (getDuelState().getDefendant() == getCurrentPlayer()) {
				setCurrentPlayer(getDuelState().getInitiator());
			} else {
				setCurrentPlayer(getDuelState().getDefendant());
			}
			// Else, follow normal procedure
		} else {
			if (currentPlayer.getNumeric() < players.size()) {
				setCurrentPlayer(players.get(currentPlayer.getNumeric()));
			} else {
				setCurrentPlayer(players.get(0));
			}
		}
	}

	/**
	 * Currently returns the player with the highest score. Doesn't handle ties
	 * and sets player 1 as the winner if the game was a tie.
	 * 
	 * @return
	 */
	public Player getWinningPlayer() {
		ArrayList<Player> players = getPlayers();
		Player winner = players.get(0);
		for (Player player : players) {
			if (player.getScore().getScore() > winner.getScore().getScore())
				winner = player;
		}
		return winner;
	}

	/**
	 * Returns the state object for duels
	 * 
	 * @return DuelState
	 */
	public DuelState getDuelState() {
		return duel;
	}

	/**
	 * Returns the current player.
	 * 
	 * @return
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Player> createPlayers(int numberOfPlayers) {
		ArrayList<Player> playersCreated = new ArrayList<Player>();

		for (int i = 1; i < numberOfPlayers + 1; i++) {
			playersCreated.add(new Player(i));
		}

		return playersCreated;
	}

	/**
	 * Sets the current player.
	 * 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Returns the question pool.
	 * 
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

	public void increasePlaysCounter() {
		playsCounter++;
	}

	public int getPlaysCounter() {
		return playsCounter;
	}
}
