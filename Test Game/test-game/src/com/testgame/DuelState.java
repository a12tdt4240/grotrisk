package com.testgame;

import com.testgame.Models.Area;
import com.testgame.Views.QuizView;

public class DuelState implements State {

	private Takeover game;
	
	public DuelState(Takeover game) {
		this.game = game;
	}
	
	@Override
	public void nextPlayer() {
		if (game.getDuel().getDefendant() == game.getCurrentPlayer()) {
			game.setCurrentPlayer(game.getDuel().getInitiator());
		} else {
			game.setCurrentPlayer(game.getDuel().getDefendant());
		}
	}

	@Override
	public void goToNextScreen() {
		game.setScreen(new QuizView(game, game.getDuel().getArea()));
	}

	@Override
	public void updateScore(Area area, boolean correctAnswer) {
		if(correctAnswer) {
			if (game.getCurrentPlayer() == game.getDuel().getDefendant()) {
				game.getDuel().increaseDefenantScore();
			} else {
				game.getDuel().increaseInitiatorScore();
			}
		}
		// Check if duel is finished, and handle it appropriately
		handleDuel(area);
	}
	
	protected void handleDuel(Area area) {
		if (game.getDuel().isFinished()
				&& (game.getCurrentPlayer() == game.getDuel().getDefendant())) {
			if (game.getDuel().getWinner() == game.getDuel().getInitiator()) {
				// Update score of winner
				game.getDuel().getWinner().getScore().updateScore(area.getValueOfArea());
				// Update owner of area
				area.setOwner(game.getDuel().getWinner());
			}
			// Clean up after duel
			game.getDuel().finishDuel();
			// Go back to normal mode
			game.switchState();
		}
	}

}
