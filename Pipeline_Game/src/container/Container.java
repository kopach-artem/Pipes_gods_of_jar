package container;

import java.util.ArrayList;

import exception.MyException;
import player.*;

/**
 * Abstract osztály.
 * Egy általános tárolót valósít meg (Pipe, Pump, Mountain Spring, Cistern)
 */
public abstract class Container {
	/**
	 * A Container mellett található Containerek ArrayList-je
	 */
	protected ArrayList<Container> neighbors = new ArrayList<Container>();

	/**
	 * A Bemenetek állapotai
	 */
	private boolean[] inputState;

	/**
	 * Container osztály konstruktora
	 */
    	public Container() {
        	this.neighbors = new ArrayList<>();
        	this.inputState = new boolean[2]; // default to false for both inputs
    	}


	/**
	 * Megnézi, hogy apraméterként kapott Container a szomszédja-e a Cotainernek
	 * @param neighbor - A megvizsgálni kívánt Container
	 * @return boolean - Szomszédja-e vagy sem
	 */
	public boolean seeifNeighbors(Container neighbor){

		return this.neighbors.contains(neighbor);
	}

	
	/** 
	 * @param turnCount
	 */
	public void lifeCycle(int turnCount){

	}

	/**
	 *
	 * @param player
	 * @throws MyException
	 */
	public void insertPump(Player player) throws MyException {

	}

	/**
	 *
	 * @param player
	 * @param pi
	 * @throws MyException
	 */
	public void extractPipe(Player player, Pipe pi) throws MyException {

	}

	/**
	 *
	 * @param player
	 * @throws MyException
	 */
	public void insertPipe(Player player) throws MyException {

	}

	/**
	 *
	 * @return
	 */
	public boolean steppable(){
		return false;
	}

	/**
	 *
	 */
	public void eval() {
	}

	/**
	 *
	 */
	public void setInputState() {
	}

	/**
	 *
	 */
	public void makeHistory() {
	}

	/**
	 * Visszatér a neighbors attribúmmal
	 * @return ArrayList - neighbors attribútum
	 */
	public ArrayList<Container> getNeighbors() {
		return neighbors;
	}

	/**
	 * Beállítja a neigbors attribútumot a paraméterként kapottra
	 * @param neighbors - A beállítani kívánt attribútum
	 */
	public void setNeighbors(ArrayList<Container> neighbors) {
		this.neighbors = neighbors;
	}


}
