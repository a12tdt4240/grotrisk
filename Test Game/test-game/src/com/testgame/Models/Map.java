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
	}
	
	/**
	 * Filters areas by player
	 * 
	 * @param Player
	 * @return ArrayList<Area>
	 */
	public ArrayList<Area> getAreasByPlayer(Player player) {
		ArrayList<Area> areasPlayer = new ArrayList<Area>();
		
		for(int i = 0; i < areas.size(); ++i) {
			Area area = areas.get(i);
			if(area.getOwner() == player) {
				areasPlayer.add(area);
			}
		}
		
		return areasPlayer;
	}
}
