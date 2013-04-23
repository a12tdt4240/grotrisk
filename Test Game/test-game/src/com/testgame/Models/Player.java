package com.testgame.Models;

import com.badlogic.gdx.graphics.Color;
import com.testgame.Constants;

public class Player {

	private Score score;
	private Color color;
	private String name;
	private int numeric;
	private QuestionPool questionPool;

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

	public void setQuestionPool(QuestionPool questions) {
		this.questionPool = questions;
	}
	
	public QuestionPool getQuestionPool() {
		return this.questionPool;
	}
}
