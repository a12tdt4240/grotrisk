package com.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SkinSingleton {
	private static final SkinSingleton instance = new SkinSingleton();

	private Skin mapSkin;
	private Skin menuSkin;
	private TextureAtlas mapAtlas;
	private TextureAtlas menuAtlas;
	private Drawable areaDrawable;
	private Image attackImage;
	private NinePatch menuBackground;
	private NinePatch panel;
	private BitmapFont font;
	private TextButtonStyle disabledButtonStyle;
	private TextButtonStyle defaultButtonStyle;
	private LabelStyle labelStyle;
	private CheckBoxStyle checkBoxStyle;
	private ButtonStyle radioButtonStyle;
	private TextFieldStyle textFieldStyle;
	private Image boxImage;

	
	private SkinSingleton() {
		init();

	}
	
	private void init() {
		mapAtlas = new TextureAtlas(Takeover.MAP_ATLAS_PATH);
		menuAtlas = new TextureAtlas(Takeover.MENU_ATLAS_PATH);

		mapSkin = new Skin();
		menuSkin = new Skin();
		mapSkin.addRegions(mapAtlas);
		menuSkin.addRegions(menuAtlas);
		
		areaDrawable = mapSkin.getDrawable("area001");
		attackImage = new Image(mapSkin.getRegion("duel"));
		
		boxImage = new Image(mapSkin.getRegion("area001"));
		
		menuBackground = new NinePatch(menuSkin.getRegion("background"), 190, 190, 114, 292);
		panel = new NinePatch(menuSkin.getRegion("panel"), 215, 200, 140, 140);
		
		font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
	
		//Button styles
		defaultButtonStyle = new TextButtonStyle();
		defaultButtonStyle.up = menuSkin.getDrawable("buttonUp");
		defaultButtonStyle.font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false);
		
		disabledButtonStyle = new TextButtonStyle(defaultButtonStyle);
		disabledButtonStyle.up = menuSkin.getDrawable("buttonUpGrey");
		
		// Label styles
		labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false); 
		
		// CheckboxStyle
		checkBoxStyle = new CheckBoxStyle(
				menuSkin.getDrawable("buttonUpGrey"), menuSkin.getDrawable("buttonUp"),
				new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false), Color.WHITE);
		
		// RadioButtonStyle
		radioButtonStyle = new ButtonStyle(menuSkin.getDrawable("container"), menuSkin.getDrawable("container"),
				menuSkin.getDrawable("container2"));
		
		// TextFieldStyle
		textFieldStyle = new TextFieldStyle(new BitmapFont(Gdx.files.internal("skins/fonts.fnt"), false), Color.BLACK,
				menuSkin.getDrawable("cursor"), menuSkin.getDrawable("selected"),
				menuSkin.getDrawable("textFieldBg"));
	}

	public static SkinSingleton getInstance() {
		return instance;
	}
	
	public void dispose() {
		mapSkin.dispose();
		menuSkin.dispose();
		mapAtlas.dispose();
		menuAtlas.dispose();
		menuBackground.getTexture().dispose();
		panel.getTexture().dispose();
		font.dispose();
	}
	
	public Skin getMapSkin() {
		return mapSkin;
	}
	
	public Skin getMenuSkin() {
		return menuSkin;
	}
	
	public Image getAttackImage() {
		return attackImage;
	}
	
	public Drawable getAreaImage() {
		return areaDrawable;
	}
	
	public NinePatch getMenuBackground() {
		return menuBackground;
	}
	
	public NinePatch getPanelImage() {
		return panel;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public TextButtonStyle getDefaultButtonStyle() {
		return defaultButtonStyle;
	}
	
	public TextButtonStyle getDisabledButtonStyle() {
		return disabledButtonStyle;
	}
	
	public LabelStyle getLabelStyle() {
		return labelStyle;
	}
	
	public CheckBoxStyle getCheckBoxStyle() {
		return checkBoxStyle;
	}
	
	public ButtonStyle getRadioButtonStyle() {
		return radioButtonStyle;
	}
	
	public TextFieldStyle getTextFieldStyle() {
		return textFieldStyle;
	}
	
	public Image getBoxImage() {
		return boxImage;
	}
	
	public void resetFontSize() {
		font.setScale(1);
		defaultButtonStyle.font.setScale(1);	
		disabledButtonStyle.font.setScale(1);
		labelStyle.font.setScale(1);
		checkBoxStyle.font.setScale(1);
		textFieldStyle.font.setScale(1);
	}
	
	// For the dumb android VM!!!
	public void resetSingleton() {
		init();
	}
}
