package com.testgame.Models;

<<<<<<< .merge_file_QMCrdq
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
=======
import java.util.ArrayList;

>>>>>>> .merge_file_cm6xhI
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Area {
	
	private Player owner;
	private Question question;
<<<<<<< .merge_file_QMCrdq
	private int value;
	private Image image;

	public Area(int value) {
=======
	private int value, xPosition, yPosition;
	private ArrayList<Area> neighbors;
	private Image areaImage;
	
	public Area(int xPosition, int yPosition, int value) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
>>>>>>> .merge_file_cm6xhI
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
<<<<<<< .merge_file_QMCrdq
	
	public Image getImage() {
		return image;
=======

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
>>>>>>> .merge_file_cm6xhI
	}
}
