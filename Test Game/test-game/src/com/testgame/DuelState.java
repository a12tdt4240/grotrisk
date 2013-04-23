package com.testgame;

import com.testgame.Views.QuizView;

public class DuelState implements State {

	private Takeover game;
	
	public DuelState(Takeover game) {
		this.game = game;
	}
	
	@Override
	public void nextPlayer() {
		if (game.getDuelState().getDefendant() == game.getCurrentPlayer()) {
			game.setCurrentPlayer(game.getDuelState().getInitiator());
		} else {
			game.setCurrentPlayer(game.getDuelState().getDefendant());
		}
	}

	@Override
	public void goToNextScreen() {
		game.setScreen(new QuizView(game, game.getDuelState().getArea()));
	}

}
