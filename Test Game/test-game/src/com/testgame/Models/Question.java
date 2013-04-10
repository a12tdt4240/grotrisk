package com.testgame.Models;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Question implements Serializable {

	Category category;
	String questionText;
	Alternative alt1, alt2, alt3, alt4;

	// Les inn sp�rsm�l fra tekstfil (bruke en ferdig parser?).
	// Et sp�rsm�l tilh�rer en kategori, har en sp�rsm�ltekst
	// og har fire alternativer. Andre attributter?
	public Question() {}	
	
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

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		// TODO Auto-generated method stub
		this.questionText = json.readValue("questionText", String.class, jsonData);
		this.alt1 = json.readValue("alt1", Alternative.class, jsonData);
		this.alt2 = json.readValue("alt2", Alternative.class, jsonData);
		this.alt3 = json.readValue("alt3", Alternative.class, jsonData);
		this.alt4 = json.readValue("alt4", Alternative.class, jsonData);
		this.category = json.readValue("category", Category.class, jsonData);
	}

}
