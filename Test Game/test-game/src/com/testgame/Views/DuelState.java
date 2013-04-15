package com.testgame.Views;

import com.testgame.MyGame;
import com.testgame.Models.Player;

public class DuelState {
	private boolean isDuel = false;
	private int scoreInitiator = 0;
	private int scoreDefendant = 0;
	private Player initiator;
	private Player defendant;
	private MyGame game;
	
	public DuelState(MyGame game) {
		this.game = game;
	}
	
	/**
	 * Is a duel in place
	 * 
	 * @return boolean
	 */
	public boolean isDuel() {
		return isDuel;
	}
	
	/**
	 * Knows if game is finished
	 * Never to be called before the player does his turn, but after
	 * 
	 * @return boolean
	 */
	public boolean isFinished() {
		if((game.getCurrentPlayer() == defendant) && (scoreInitiator != scoreDefendant)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns Player if a winner exists, null otherwise
	 * 
	 * @return Player
	 */
	public Player getWinner() {
		if(isFinished()) {
			if(scoreDefendant > scoreInitiator) {
				return defendant;
			} else {
				return initiator;
			}
		}
		return null;
	}
	
	/**
	 * Returns defendant of the duel
	 * 
	 * @return Player defendant
	 */
	public Player getDefendant() {
		return defendant;
	}
	
	/**
	 * Initiates duel by setting the initiator and the defendant
	 * Duel is marked as in action
	 * 
	 * @param Player initiator
	 * @param Player defendant
	 */
	public void initiateDuel(Player initiator, Player defendant) {
		this.initiator = initiator;
		this.defendant = defendant;
		isDuel = true;
	}
	
	/**
	 * Clean up after duel
	 */
	public void finishDuel() {
		isDuel = false;
		scoreInitiator = 0;
		scoreDefendant = 0;
		initiator = null;
		defendant = null;
	}
}
