package com.testgame.Models;

public class Score {

	int score;

	/**
	 * Constructor.
	 */
	public Score() {
		this.score = 0;
	}

	/**
	 * Returns the score.
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Updates the score.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
