package com.testgame.Models;

public class Alternative {

	// Et alternativ har en tekst og holder informasjon om det er det riktige svaret.
	
	String text;
	boolean isCorrectAnswer;
	
	public Alternative(String text, boolean isCorrectAnswer) {
		this.text = text;
		this.isCorrectAnswer = isCorrectAnswer;
	}
	
	/**
	 * Provides the textual version of the alternative
	 * 
	 * @return String
	 */
	public String getName() {
		return text;
	}
	
	/**
	 * Knows if the alternative is correct or not
	 * 
	 * @return boolean
	 */
	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}
}
