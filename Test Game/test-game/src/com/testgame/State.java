package com.testgame;

import com.badlogic.gdx.Screen;

public interface State {
	public void nextPlayer();
	public Screen getNextScreen();
}
