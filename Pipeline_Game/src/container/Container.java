package container;

import java.util.ArrayList;

public class Container {
	private ArrayList<Container> negighbors;
	private boolean[] InputState;
	
	
	public boolean seeifNeighbors(Container neighbor, Container c) {
		return true;
	}
	
	public boolean steppable() {
		return false;
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}
	
	public void makeHistory() {
	}

	public ArrayList<Container> getNegighbors() {
		return negighbors;
	}

	public void setNegighbors(ArrayList<Container> negighbors) {
		this.negighbors = negighbors;
	}


}
