package com.testgame.Models;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class MapFactory {
	
	public static final String DEFAULT_MAP = "data/json/map.default.json";
	
	public MapFactory() {}
	
	public Map createDefaultMap() {
		Gdx.app.log("MapFactory", "createDefaultMap");
		Json json = new Json();
		
		json.setElementType(Area.class, "xPosition", Integer.class);
		json.setElementType(Area.class, "yPosition", Integer.class);
		json.setElementType(Area.class, "value", Integer.class);
		
		FileHandle handle = null;
		try {
			handle = Gdx.files.getFileHandle(DEFAULT_MAP, FileType.Internal);
			Gdx.app.log("MapFactory","exists: " + handle.exists());
		} catch (Exception e) {
			Gdx.app.error("MapFactory read from file", e.getMessage());
		}
		
		try {
			return new Map(new ArrayList<Area>(Arrays.asList(json.fromJson(Area[].class, handle))));
		} catch (NullPointerException e) {
			Gdx.app.error("QuestionPool tojson", e.getMessage());
		}
		return null;
	}
}
