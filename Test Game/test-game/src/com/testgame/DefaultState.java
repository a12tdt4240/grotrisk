package com.testgame;

import com.testgame.Models.Player;
import com.testgame.Views.MapView;

public class DefaultState implements State {
	private Player currentPlayer;
	private Takeover game;
	
	public DefaultState(Takeover game) {
		this.game = game;
	}
	
	@Override
	public void nextPlayer() {
		int pos = game.getPlayers().indexOf(currentPlayer);
		if (pos < game.getPlayers().size() - 1) {
			game.setCurrentPlayer(game.getPlayers().get(pos + 1));
		} else {
			game.setCurrentPlayer(game.getPlayers().get(0));
		}
	}

	@Override
	public void goToNextScreen() {
		if (game.getMapView() == null)
			game.setMapView(new MapView(game, game.getMapModel()));

		game.setScreen(game.getMapView());
	}

}
