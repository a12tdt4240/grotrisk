package com.testgame.Models;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
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
	
	public static final String DEFAULT_QUESTIONS = "data/json/questions.json";
		
	private Question[] questions;
	private ArrayList<Question> randomSelected;
	
	public QuestionPool(){
		this.loadQuestions();
		this.randomSelected = new ArrayList<Question>();
		Gdx.app.log("QuestionPool", "construct");
		
		Gdx.app.log("QP",this.randomByCategory(new Category("Geografi")).toString());
	}
	
	private void loadQuestions() {
		Gdx.app.log("QuestionPool", "QuestionPool:loadQuestion");
		Json json = new Json();
		json.setElementType(Quiz.class, "alt1", Alternative.class);
		json.setElementType(Quiz.class, "alt2", Alternative.class);
		json.setElementType(Quiz.class, "alt3", Alternative.class);
		json.setElementType(Quiz.class, "alt4", Alternative.class);
		json.setElementType(Quiz.class, "category", Category.class);
		json.setElementType(Category.class, "visual", Color.class);
		
		FileHandle handle = null;
		try {
			handle = Gdx.files.getFileHandle(DEFAULT_QUESTIONS, FileType.Internal);
			Gdx.app.log("QuestionPool","exists: " + handle.exists());
		} catch (Exception e) {
			Gdx.app.error("QuestionPool read from file", e.getMessage());
		}
		
		try {
			this.questions = json.fromJson(Quiz[].class, handle);
		} catch (NullPointerException e) {
			Gdx.app.error("QuestionPool tojson", e.getMessage());
		}
		
		Gdx.app.log("QuestionPool","Questions loaded: " + this.questions.length);
	}
	
	public Question[] all() {
		Gdx.app.log("QuestionPool", "QuestionPool:all");
		return questions;
	}
	
	public ArrayList<Question> allByCategory(Category category) {
		Gdx.app.log("QuestionPool", "QuestionPool:allByCategory");
		ArrayList<Question> listByCategory = new ArrayList<Question>(); 
		
		for (int i = 0; i < questions.length; i++) {
			Question question = questions[i];
			if (question.getCategory().getName().equals(category.getName())) {
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
		
		Question random = random();
		if (!random.getCategory().getName().equals(category.getName())) {
			return this.randomByCategory(category); 
		}
		return random;
	}
	
	public Question random() {
		Gdx.app.log("QuestionPool", "QuestionPool:random");
		Random randomIndex = new Random(); 
		Question random = questions[randomIndex.nextInt(questions.length)];
		
		if (this.randomSelected.contains(random) && (this.randomSelected.size() <= this.questions.length)) {
			this.randomSelected.add(random);
		} else {
			this.randomSelected.clear();
			this.randomSelected.add(random);
		}
		
		return random;
	}
}
