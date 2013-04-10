package com.testgame.Models;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;

/**
 * Class to hold categories and questions
 * 
 * Has a way of choosing a question upon request
 *
 */
public class QuestionPool {
	private Question[] questions;
	private ArrayList<Question> randomSelected;
	
	public QuestionPool(){
//		this.loadQuestions();
//		Gdx.app.log("QuestionPool", "construct");
	}
	
	private void loadQuestions() {
		Gdx.app.log("QuestionPool", "QuestionPool:loadQuestion");
		Json json = new Json();
		json.setElementType(Question.class, "alt1", Alternative.class);
		json.setElementType(Question.class, "alt2", Alternative.class);
		json.setElementType(Question.class, "alt3", Alternative.class);
		json.setElementType(Question.class, "alt4", Alternative.class);
		json.setElementType(Question.class, "category", Category.class);
		json.setElementType(Category.class, "visual", Color.class);
		
		FileHandle handle = null;
		try {
			handle = Gdx.files.internal("data/json/questions.json");
			Gdx.app.log("QuestionPool","exists: " + handle.exists());
		} catch (Exception e) {
			Gdx.app.error("QuestionPool read from file", e.getMessage());
		}
		
		try {
			this.questions = json.fromJson(Question[].class, handle);
		} catch (NullPointerException e) {
			Gdx.app.error("QuestionPool tojson", e.getMessage());
		}
		
		Gdx.app.log("QuestionPool","Questions loaded: " + this.questions.length);
	}
	
	public Question[] all() {
		Gdx.app.log("QuestionPool", "QuestionPool:all");
		return this.questions;
	}
	
	public ArrayList<Question> allByCategory(Category category) {
		Gdx.app.log("QuestionPool", "QuestionPool:allByCategory");
		ArrayList<Question> listByCategory = new ArrayList<Question>(); 
		
		for (int i = 0; i < this.questions.length; i++) {
			Question question = this.questions[i];
			if (question.category.equals(category)) {
				listByCategory.add(question);
			}
		} 
		
		return listByCategory;
	}
	
	/**
	 * RandomByCategory
	 * Returns a random question from the dataset with the given category.
	 * 
	 * @param category
	 * @throws NullPointerException
	 * @return
	 */
	public Question randomByCategory(Category category) {
		Gdx.app.log("QuestionPool", "QuestionPool:randomByCategory");
		if (this.allByCategory(category).size() == 0) {
			Gdx.app.log("QuestionPool", "QuestionPool:randomByCategory return null");
			return null;
		}
		
		Question random = this.random();
		if (!random.category.equals(category)) {
			return this.randomByCategory(category); 
		}
		return random;
	}
	
	public Question random() {
		Gdx.app.log("QuestionPool", "QuestionPool:random");
		Random randomIndex = new Random(); 
		Question random = this.questions[randomIndex.nextInt(this.questions.length)];
		
		if (this.randomSelected.contains(random) && (this.randomSelected.size() < this.questions.length)) {
			this.randomSelected.add(random);
		} else {
			this.randomSelected.clear();
			this.randomSelected.add(random);
		}
		
		return random;
	}
	
	/**
	 * Temporary method for testing purposes. Don't delete, ok please?
	 * @return
	 */
	public Question getTestQuestion() {
		Category catOne = new Category("Historie");
		Alternative alt1 = new Alternative("17.mai 1905", false);
		Alternative alt2 = new Alternative("17.mai 1814", false);
		Alternative alt3 = new Alternative("7.juni 1905", true);
		Alternative alt4 = new Alternative("7.mai 1945", false);
		Quiz questionOne = new Quiz(catOne,
				"NŒr ble det moderne Norge uavhengig?", alt1, alt2, alt3, alt4);
		return questionOne;
	}
}
