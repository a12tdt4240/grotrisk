package com.testgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.testgame.Models.Area;
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

	public static final String SPRITE = "skins/mainmenu.atlas";
	
	private GameScreen gameScreen;
	private Map map;
	private Music music;
	private QuestionPool questionPool; 
	private ArrayList<Player> players;
	private Player currentPlayer;
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

		// Get default Map model
		map = new MapFactory().createDefaultMap();

		// Create players.
		players = createPlayers(2);
		// Set player colors TO BE UPDATED WITH COLOR CHOOSER VIEW ETC
		players.get(0).setColor(Color.BLACK);
		players.get(1).setColor(Color.ORANGE);
		// Set the current player. A random player.
		setCurrentPlayer(players.get((int)Math.floor(Math.random() * players.size())));
		playsCounter = 0;
		
		// Give out initial areas
		setInitialOwnership();

		duel = new DuelState(this);

		// Create the game screen with selected map model
		gameScreen = new GameScreen(this, map);

		// Create and launch main menu screen.
		setScreen(new MainMenuScreen(this));
	}
	
	// Set initial ownership
	private void setInitialOwnership() {
		map.getAreas().get(0).setOwner(getPlayers().get(0));
		map.getAreas().get(map.getAreas().size() - 1).setOwner(getPlayers().get(1));
	}

	/**
	 * Pauses or restarts the music.
	 */
	public void changeSoundSetting() {
		if (music.isPlaying())
			music.pause();
		else
			music.play();
	}
	
	public Music getMusic() {
		return music;
	}

	/**
	 * Resets the game. This is called when the main menu button is pressed on
	 * the end game screen.
	 */
	public void resetGame() {
		// Overwrite the old players with new players.
		players = createPlayers(2);
		players.get(0).setColor(Color.BLACK);
		players.get(1).setColor(Color.ORANGE);
		setCurrentPlayer(players.get((int)Math.floor(Math.random() * players.size())));
		// Reset area owners
		for (Area area : map.getAreas()) {
			area.setOwner(null);
		}
		// Reset playsCounter
		playsCounter = 0;
		setInitialOwnership();
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
			int pos = players.indexOf(currentPlayer);
			if (pos < players.size() - 1) {
				setCurrentPlayer(players.get(pos + 1));
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

		for (int i = 0; i < numberOfPlayers; i++) {
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
