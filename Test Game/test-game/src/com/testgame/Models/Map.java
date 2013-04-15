package com.testgame.Models;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	/**
	 * Takes a player and returns a list of all the areas that are neighbors
	 * to the areas he owns
	 * 
	 * @param Player
	 * @return ArrayList<Area>
	 */
	public ArrayList<Area> getNeighborsByPlayer(Player player) {
		// Use set to avoid duplicates
		HashSet<Area> neighbors = new HashSet<Area>();
		// Get areas owned by player
		ArrayList<Area> ownedByPlayer = getAreasByPlayer(player);
		for(int i = 0; i < ownedByPlayer.size(); ++i) {
			neighbors.addAll(ownedByPlayer.get(i).getNeighbors());
		}
		// Remove all areas owned by player
		neighbors.removeAll(ownedByPlayer);
		
		return new ArrayList<Area>(neighbors);
	}
}
