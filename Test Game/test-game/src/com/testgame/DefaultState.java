package com.testgame;

import com.testgame.Models.Area;
import com.testgame.Models.Player;
import com.testgame.Views.MapView;

public class DefaultState implements State {
	private Player currentPlayer;
	private Takeover game;
	
	public DefaultState(Takeover game) {
		this.game = game;
		currentPlayer = game.getCurrentPlayer();
	}
	
	@Override
	public void nextPlayer() {
		int pos = game.getPlayers().indexOf(currentPlayer);
		if (pos < game.getPlayers().size() - 1) {
			currentPlayer = game.getPlayers().get(pos + 1);
			game.setCurrentPlayer(currentPlayer);
		} else {
			currentPlayer = game.getPlayers().get(0);
			game.setCurrentPlayer(currentPlayer);
		}
	}

	@Override
	public void goToNextScreen() {
		if (game.getMapView() == null)
			game.setMapView(new MapView(game, game.getMapModel()));

		game.setScreen(game.getMapView());
	}

	@Override
	public void updateScore(Area area, boolean correctAnswer) {
		if(correctAnswer == true) {
			game.getCurrentPlayer().getScore().updateScore(area.getValueOfArea());
			area.setOwner(game.getCurrentPlayer());
		}
	}

}
