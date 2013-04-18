package com.testgame.Models;

import java.util.ArrayList;

import com.testgame.Views.Observer;

public class Score {

	private int score;
	private ArrayList<Observer> observers;

	public Score() {
		this.score = 0;
		this.observers = new ArrayList<Observer>();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		fireObserverEvent();
	}
	
	public void updateScore(int value) {
		setScore(score + value);
	}
	
	/**
	 * Add observer to list
	 * 
	 * @param Observer
	 */
	public void addObserver(Observer ob) {
		observers.add(ob);
	}
	
	private void fireObserverEvent() {
		for(int i = 0; i < observers.size(); ++i) {
			observers.get(i).changeEvent();
		}
	}

	
}
