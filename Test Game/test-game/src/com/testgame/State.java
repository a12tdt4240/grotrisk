package com.testgame;

import com.testgame.Models.Area;

public interface State {
	public void nextPlayer();
	public void goToNextScreen();
	public void updateScore(Area area, boolean correctAnswer);
}
