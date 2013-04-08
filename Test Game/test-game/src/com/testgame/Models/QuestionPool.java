package com.testgame.Models;

import java.util.ArrayList;

/**
 * Class to hold categories and questions
 * 
 * Has a way of choosing a question upon request
 *
 */
public class QuestionPool {
	private ArrayList<Question> questions;
	
	public QuestionPool(){
	}
	
	/**
	 * Temporary method for testing purposes.
	 * @return
	 */
	public Question getRandomQuestion() {
		Category catOne = new Category("Historie");
		Alternative alt1 = new Alternative("17.mai 1905", false);
		Alternative alt2 = new Alternative("17.mai 1814", false);
		Alternative alt3 = new Alternative("7.juni 1905", true);
		Alternative alt4 = new Alternative("7.mai 1945", false);
		Question questionOne = new Quiz(catOne,
				"NŒr ble det moderne Norge uavhengig?", alt1, alt2, alt3, alt4);
		return questionOne;
	}
}
