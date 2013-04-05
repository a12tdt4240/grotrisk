package com.testgame.Models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Area {
	private Player owner;
	private Question question;
	private int value;
	private Image image;

	public Area(int value) {
		this.value = value;
		
		loadAreaImage();
	}
	
	protected void loadAreaImage() {
		image = new Image(new Texture( new FileHandle("data/maps/mo/area001.png")));
	}
	
	/**
	 * Sets provided player as owner
	 * 
	 * @param player
	 */
	public void setOwner(Player player) {
		owner = player;
	}
	
	/**
	 * Provides the owner of the area
	 * 
	 * @return Player
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Sets provided question as question of area
	 * 
	 * @param question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	/**
	 * Provides the question of the area
	 * 
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}
	
	/**
	 * The value of the area
	 * 
	 * @return int
	 */
	public int getValueOfArea() {
		return value;
	}
	
	public Image getImage() {
		return image;
	}
}
