package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;

public class Player {

	Score score;
	Color color;
	String name;
	int numeric;

	// Players lages i oppstarten av spillet (klassen MyGame ?). Der holdes det
	// styr på hvilken Player sin tur det er.
	// En Player har en Score poengsum, en Color farge som vises på
	// kart/gamescreen, et String navn og en numerisk verdi.

	public Player(int n) {
		score = new Score();
		numeric = n;
		this.name = Constants.PLAYER_TEXT + (numeric + 1);
	}

	public int getNumeric() {
		return numeric;
	}
	
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
