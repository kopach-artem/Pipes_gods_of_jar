package container;

public class Pipe extends Container {
	private boolean isLeaked;
	private boolean isOccupied;
	private boolean waterFlowing;
	
	public boolean steppable() {

		if(!isOccupied){
			return true;
		}
		else
			return false;
	}
	
	public void addPump(Pump pu) {
	}
	
	public void removePump(Pump pu) {
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

	public void setisLeaked(boolean b)
	{
		isLeaked=b;
	}
}
