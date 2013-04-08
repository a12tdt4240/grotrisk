package com.testgame.Models;

public class Quiz extends Question {
	private String questionText;
	private Alternative alt1, alt2, alt3, alt4;
	
	public Quiz(Category cat, String txt, Alternative alt1,
			Alternative alt2, Alternative alt3, Alternative alt4) {
		super(cat);
		this.questionText = txt;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
		this.alt4 = alt4;
	}
	
	/**
	 * Provides the question text
	 * 
	 * @return String
	 */
	public String getQuestionText() {
		return questionText;
	}
	
	public Alternative getAlt1() {
		return alt1;
	}

	public Alternative getAlt2() {
		return alt2;
	}

	public Alternative getAlt3() {
		return alt3;
	}

	public Alternative getAlt4() {
		return alt4;
	}
}
