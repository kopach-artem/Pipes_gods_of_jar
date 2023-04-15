package container;

import java.util.ArrayList;

public class Container {
	private ArrayList<Container> neighbors;
	private boolean[] InputState;
	
	
	public boolean seeifNeighbors(Container neighbor){

		return this.neighbors.contains(neighbor);
	}

	public void lifeCycle(){

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
