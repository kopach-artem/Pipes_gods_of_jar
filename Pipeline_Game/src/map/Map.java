package map;

import java.util.ArrayList;
import java.util.stream.Stream;

import player.*;
import container.*;

public class Map {
	private int leakedWater;
	private int turnCount;
	
	private ArrayList<Player> players;
	
	private ArrayList<Container> containers;

	public static int getTurnCount(){
		return turnCount;
	}

	public void checkLeakage() {
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
