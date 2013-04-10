package com.testgame.Models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class Area {
	
	// An area has an owner. Player 1, Player 2 or null;
	private Player owner;
	// An area holds a question.
	private Question question;
	// The visual representation of the area.
	private Image image;
	
	private Color color;

	private int value, xPosition, yPosition;
	// A list of neighboring areas. Areas that can be moved to in one move.
	private ArrayList<Area> neighbors;

	/**
	 * Constructor.
	 * @param xPosition
	 * @param yPosition
	 * @param value
	 */
	public Area(int xPosition, int yPosition, int value) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.value = value;
		loadAreaImage();
	}
	
	/**
	 * Helper method for loading the Image.
	 */
	protected void loadAreaImage() {
		image = new Image(new Texture( new FileHandle("data/maps/mo/area001.png")));
	}
	
	/**
	 * Returns standard color if no users owns area, and
	 * returns the owners color if an owner exists
	 * 
	 * @return Color
	 */
	public Color getColor() {
		if(getOwner() == null) {
			return color;
		}
		return getOwner().getColor();
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
	
	public int getValueOfArea() {
		return value;
	}
	
	public Image getImage() {
		return image;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public ArrayList<Area> getNeighbors() {
		return neighbors;
	}
}
