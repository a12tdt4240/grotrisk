package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;

public class Player {

	Score score;
	Color color;
	String name;

	// Players lages i oppstarten av spillet (klassen MyGame ?). Der holdes det
	// styr på hvilken Player sin tur det er.
	// En Player har en Score poengsum, en Color farge som vises på
	// kart/gamescreen, og et String navn.

	public Player() {
		score = new Score();
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
