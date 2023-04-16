package map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

import exception.MyException;
import player.*;
import container.*;

public class Map {
	private int leakedWater;

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Container> containers = new ArrayList<Container>();

	public Map(){

		leakedWater = 0;

	}

	public void connectPumpToPipe(Pump pu, Pipe pi) throws MyException {

		pu.addPipe(pi);
		pi.addPump(pu);

	}

	public void increaseLeakedWater() {
	}
	
	
	/** 
	 * @param c
	 */
	public void removeElement(Container c) {
	}
	
	public void addElement(Container c) {
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
