package com.testgame.Models;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Quiz extends Question implements Serializable {

	private String questionText;
	private Alternative alt1, alt2, alt3, alt4;

	public Quiz() {super();}	
	
	public Quiz(Category cat, String txt, Alternative alt1,
			Alternative alt2, Alternative alt3, Alternative alt4) {
		super(cat);
		this.questionText = txt;
		this.alt1 = alt1;
		this.alt2 = alt2;
		this.alt3 = alt3;
		this.alt4 = alt4;
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
	
	/**
	 * Provides the question text
	 * 
	 * @return String
	 */
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public void write(Json json) {
		// Does nothing
		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.questionText = json.readValue("questionText", String.class, jsonData);
		this.alt1 = json.readValue("alt1", Alternative.class, jsonData);
		this.alt2 = json.readValue("alt2", Alternative.class, jsonData);
		this.alt3 = json.readValue("alt3", Alternative.class, jsonData);
		this.alt4 = json.readValue("alt4", Alternative.class, jsonData);
		this.setCategory(json.readValue("category", Category.class, jsonData));
	}
	
	public String toString() {
		return getQuestionText();
	}

}

