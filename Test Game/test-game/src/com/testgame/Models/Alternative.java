package com.testgame.Models;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Alternative implements Serializable {

	// Et alternativ har en tekst og holder informasjon om det er det riktige svaret.
	
	String text;
	boolean isCorrectAnswer;
	
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
	public String getAlternativeText() {
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
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		// TODO Auto-generated method stub
		this.text = json.readValue("text", String.class, jsonData);
		this.isCorrectAnswer = json.readValue("isCorrectAnswer", Boolean.class, jsonData);
	}
}
