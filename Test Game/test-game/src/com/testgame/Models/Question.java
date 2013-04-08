package com.testgame.Models;

public class Question {

	Category category;
	String questionText;
	Alternative alt1, alt2, alt3, alt4;

	// Les inn sp�rsm�l fra tekstfil (bruke en ferdig parser?).
	// Et sp�rsm�l tilh�rer en kategori, har en sp�rsm�ltekst
	// og har fire alternativer. Andre attributter?
	
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

	public Question(Category cat, String txt, Alternative alt1,
			Alternative alt2, Alternative alt3, Alternative alt4) {
		this.category = cat;
		this.questionText = txt;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
		this.alt4 = alt4;
	}
	
	/**
	 * Provides the category of the question
	 * 
	 * @return Category
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * Provides the question text
	 * 
	 * @return String
	 */
	public String getQuestionText() {
		return questionText;
	}

}
