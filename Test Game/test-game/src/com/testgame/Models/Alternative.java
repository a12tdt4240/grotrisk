package com.testgame.Models;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * An alternative that contains the alternatives' text and information about 
 * if it's the right answer or not.
 */
public class Alternative implements Serializable {
	
	private String text;
	private boolean isCorrectAnswer;
	
	public Alternative() {}
	
	public Alternative(String text, boolean isCorrectAnswer) {
		this.text = text;
		this.isCorrectAnswer = isCorrectAnswer;
	}
	
	/**
	 * Provides the textual version of the alternative
	 * 
	 * @return String
	 */
	public String getName() {
		return text;
	}
	
	/**
	 * Knows if the alternative is correct or not
	 * 
	 * @return boolean
	 */
	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}

	@Override
	public void write(Json json) {}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.text = json.readValue("text", String.class, jsonData);
		this.isCorrectAnswer = json.readValue("isCorrectAnswer", Boolean.class, jsonData);
	}
}
