package map;

import java.util.ArrayList;

import player.*;
import container.*;

public class Map {
	private int leakedWater;
	
	private ArrayList<Player> players;
	
	private ArrayList<Container> containers;
	
	public void checkLeakage() {
	}
	
	public void increaseLeakedWater() {
	}
	
	public void removeElement(Container c) {
	}
	
	public void addElement(Container c) {
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}

	public void setContainers(ArrayList<Container> containers) {
		this.containers = containers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
