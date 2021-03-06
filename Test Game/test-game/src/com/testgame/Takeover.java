package com.testgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.testgame.Models.Area;
import com.testgame.Models.Duel;
import com.testgame.Models.Map;
import com.testgame.Models.MapFactory;
import com.testgame.Models.Player;
import com.testgame.Models.QuestionPool;
import com.testgame.Views.MainMenuView;
import com.testgame.Views.MapView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

public class Takeover extends Game implements ApplicationListener {

	public static final String MENU_ATLAS_PATH = "skins/mainmenu.atlas";
	public static final String MAP_ATLAS_PATH = "data/maps/map.atlas";

	private MapView mapView;
	private Map map;
	private Music music;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Duel duel;
	private int playsCounter;
	private State currentState;
	private State duelState;
	private State defaultState;

	@Override
	public void create() {
		// for the dumb Android VM!!
		SkinSingleton.getInstance().resetSingleton();
		// Load and start playing music.
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle(
				"data/Riskspillet.mp3", FileType.Internal));
		music.setLooping(true);
		music.play();

		// Start at turn zero
		playsCounter = 0;

		// Get default Map model
		map = new MapFactory().createDefaultMap();

		// Create players.
		players = createPlayers(2);
		
		// Set player colors TO BE UPDATED WITH COLOR CHOOSER VIEW ETC
		players.get(0).setColor(Color.DARK_GRAY);
		players.get(1).setColor(Color.ORANGE);

		// Set the current player. A random player to start.
		setCurrentPlayer(players.get((int) Math.floor(Math.random()
				* players.size())));

		// Give out initial areas
		setInitialOwnership();

		// create a DuelState for duels
		duel = new Duel(this);
		
		// Initialize states
		defaultState = new DefaultState(this);
		duelState = new DuelState(this);
		currentState = defaultState;

		// Create and launch main menu screen.
		setScreen(new MainMenuView(this));
	}

	/**
	 * Set initial ownership
	 */
	public void setInitialOwnership() {
		map.getAreas().get(0).setOwner(getPlayers().get(0));
		map.getAreas().get(map.getAreas().size() - 1)
				.setOwner(getPlayers().get(1));
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

	/**
	 * Getting access to the music variable.
	 * 
	 * @return
	 */
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
		setCurrentPlayer(players.get((int) Math.floor(Math.random()
				* players.size())));
		// Reset area owners
		for (Area area : map.getAreas()) {
			area.setOwner(null);
		}
		// Reset playsCounter
		playsCounter = 0;
		setInitialOwnership();
		// Clean up after duel just in case.
		getDuel().finishDuel();
	}

	/**
	 * Switches which player is the current player.
	 */
	public void switchCurrentPlayer() {
		// If duel is active, switch between the two duelist
		if (getDuel().isDuel()) {
			if (getDuel().getDefendant() == getCurrentPlayer()) {
				setCurrentPlayer(getDuel().getInitiator());
			} else {
				setCurrentPlayer(getDuel().getDefendant());
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
	public Duel getDuel() {
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

	/**
	 * Returns the current players
	 * 
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Creates a list of x Players
	 * 
	 * @param numberOfPlayers
	 * @return a list of players created
	 */
	public ArrayList<Player> createPlayers(int numberOfPlayers) {
		ArrayList<Player> playersCreated = new ArrayList<Player>();
		Player newPlayer;

		for (int i = 0; i < numberOfPlayers; i++) {
			newPlayer = new Player(i);
			newPlayer.setQuestionPool(new QuestionPool());
			playersCreated.add(newPlayer);
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
		return this.currentPlayer.getQuestionPool();
	}

	/**
	 * Gets current MapView object
	 * 
	 * @return
	 */
	public Screen getMapView() {
		return mapView;
	}

	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}
	
	public Map getMapModel() {
		return map;
	}
	
	/**
	 * Increase the turn value
	 */
	public void increasePlaysCounter() {
		playsCounter++;
	}

	/**
	 * Returns current turn value
	 * 
	 * @return
	 */
	public int getPlaysCounter() {
		return playsCounter;
	}
	
	// Switches between the to states
	public void switchState() {
		if(currentState == defaultState) {
			currentState = duelState;
		} else {
			currentState = defaultState;
		}
	}
	
	public State getState() {
		return currentState;
	}

	@Override
	public void dispose() {
		music.dispose();
		
		if (mapView != null)
			mapView.dispose();
		SkinSingleton.getInstance().dispose();
	}
}
