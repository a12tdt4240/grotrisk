package com.testgame.Models;

public class Score {

	int score;

	public Score() {
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateScore(int value) {
		this.score = score + value;
	}
}
