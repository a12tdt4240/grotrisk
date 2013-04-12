package com.testgame.Views;

import java.util.ArrayList;

import com.testgame.MyGame;
import com.testgame.Models.Player;

public class ScoreView {
	
	private MyGame game;
	
	private ArrayList<Player> players;
	
	public ScoreView(MyGame game) {
		this.game = game;
		this.players = game.getPlayers();
	}
	
}
