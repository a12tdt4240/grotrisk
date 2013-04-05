package com.testgame.Models;

public class Alternative {

	// Et alternativ har en tekst og holder informasjon om det er det riktige svaret.
	
	String text;
	boolean isCorrectAnswer;
	
	public Alternative(String text, boolean isCorrectAnswer) {
		this.text = text;
		this.isCorrectAnswer = isCorrectAnswer;
	}
	
}
