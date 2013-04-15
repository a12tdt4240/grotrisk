package com.testgame.Models;

import java.util.ArrayList;

public class Map {

	private ArrayList<Area> areas;

	public ArrayList<Area> getAreas() {
		return areas;
	}

	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}

	public Map(ArrayList<Area> areas) {
		this.areas = areas;
		this.initiateAreNeighbors();
	}
	
	private void initiateAreNeighbors() {
		for (Area area: this.areas) {
			area.initiateNeighbors(this.areas);
		}
	}
}
