package map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

import player.*;
import container.*;

public class Map {
	private int leakedWater;

	private ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Container> containers = new ArrayList<Container>();

	public Map(){

	
	/** 
	 * @return int
	 */
		leakedWater = 0;

	}

	public void increaseLeakedWater() {
	}
	
	public void removeElement(Container c) {
	}
	
	public void addElement(Container c) {
	}

	public static ArrayList<Container> getContainers() {
		return containers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
