package com.testgame.Models;

public class Question {

	private Category category;

	public Question() {}
	
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
	
	public void setCategory(Category cat) {
		category = cat;
	}

}
