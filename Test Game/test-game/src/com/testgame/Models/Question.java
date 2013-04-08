package com.testgame.Models;

public class Question {

	Category category;

	public Question(Category cat) {
		this.category = cat;
	}
	
	/**
	 * Provides the category of the question
	 * 
	 * @return Category
	 */
	public Category getCategory() {
		return category;
	}

}
