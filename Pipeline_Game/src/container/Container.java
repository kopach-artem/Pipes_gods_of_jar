package container;

import java.util.ArrayList;

import exception.MyException;
import player.*;

public abstract class Container {
	protected ArrayList<Container> neighbors;
	private boolean[] inputState;
	
	
    	public Container() {
        	this.neighbors = new ArrayList<>();
        	this.inputState = new boolean[2]; // default to false for both inputs
    	}
	
	
	/** 
	 * @param neighbor
	 * @return boolean
	 */
	public boolean seeifNeighbors(Container neighbor){

		return this.neighbors.contains(neighbor);
	}

	public void lifeCycle(){

	}

	public void insertPump(Player player) throws MyException {

	}

	public void extractPipe(Player player, Pipe pi) throws MyException {

	}

	public void insertPipe(Player player) throws MyException {

	}
	
	public boolean steppable(){
		return false;
	}

	public void eval() {
	}
	
	public void setInputState() {
	}
	
	public void makeHistory() {
	}

	public ArrayList<Container> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Container> neighbors) {
		this.neighbors = neighbors;
	}


}
