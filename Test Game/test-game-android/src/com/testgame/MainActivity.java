package com.testgame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.testgame.Takeover;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
	Takeover game;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;
		cfg.useGL20 = false;
		
		initialize(new Takeover(), cfg);
	}
}