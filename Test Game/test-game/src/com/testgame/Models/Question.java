package com.testgame.Models;

public class Question {

	private Category category;
	private String questionText;

	public Question() {}
	
	public Question(Category cat, String questionText) {
		this.category = cat;
		this.questionText = questionText;
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
	
	/**
	 * Provides the question text
	 * 
	 * @return String
	 */
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String text) {
		questionText = text;
	}
	

}
