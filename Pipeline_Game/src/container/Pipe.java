package container;

import player.Player;
import exception.*;

public class Pipe extends Container {
	private boolean isLeaked;
	private boolean isOccupied;
	private boolean waterFlowing;

	public boolean steppable() {

		if(!isOccupied){
			return true;
		} else
			return false;

	}

	public void insertPump(Player player) throws MyException{

		//Create new Pipe for the attachement
		Pipe split1 = new Pipe();

		//Initialize pumps
		Pump atPu = player.getCarriedPump();
		Container pump2 = this.neighbors.get(1);

		//Adding pumps to split Pipe
		split1.addPump((Pump) pump2, 1);
		split1.addPump(atPu, 0);

		//Removing the Pump that now connects to split1 Pipe but was connected to the base Pipe, and Adding the new Pump
		this.removePump(1);
		this.addPump(atPu, 1);

		//Add Pipes to Pumps too
		atPu.addPipe(split1);
		atPu.addPipe(this);
		((Pump) pump2).addPipe(split1);
		((Pump) pump2).removePipe(this);

	}

	public void addPump(Pump pu, int index) throws MyException {

		if(!(this.neighbors.size() == 2))
			this.neighbors.add(index, pu);
		else
			throw new MyException("Add pump failed");
	}

	public void removePump(int index) throws MyException {

		if(!(this.neighbors.isEmpty()))
			this.neighbors.remove(index);
		else
			throw new MyException("Remove Pump failed");
	}

	public void eval() {
	}

	public void setInputState() {
	}

	public boolean isLooseEnd() {

		if(getNeighbors().size()==1)
			return true;
		else
			return false;
	}

	public boolean isLeaked() {
		return isLeaked;
	}

	public void setLeaked(boolean isLeaked) {
		this.isLeaked = isLeaked;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isWaterFlowing() {
		return waterFlowing;
	}

	public void setWaterFlowing(boolean waterFlowing) {
		this.waterFlowing = waterFlowing;
	}

	public boolean getWaterFlowing() {
		return false;
	}

	public void setisLeaked(boolean b) {
		isLeaked=b;
	}
}
