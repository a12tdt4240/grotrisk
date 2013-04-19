package com.testgame.Models;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SkinSingleton {
	private static final SkinSingleton instance = new SkinSingleton();

	private Skin mapSkin;
	private TextureAtlas mapAtlas;
	private Drawable areaDrawable;
	private Image attackImage;
	
	private SkinSingleton() {
		mapAtlas = new TextureAtlas("data/maps/map.atlas");

		mapSkin = new Skin();
		mapSkin.addRegions(mapAtlas);
		
		areaDrawable = mapSkin.getDrawable("area001");
		attackImage = new Image(mapSkin.getRegion("duel"));
	
	}

	public static SkinSingleton getInstance() {
		return instance;
	}
	
	public Skin getMapSkin() {
		return mapSkin;
	}
	
	public Image getAttackImage() {
		return attackImage;
	}
	
	public Drawable getAreaImage() {
		return areaDrawable;
	}
}
