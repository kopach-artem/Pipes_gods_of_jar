package container;

public class Pipe extends Container {
	private boolean isLeaked;
	private boolean isOccupied;
	private boolean waterFlowing;
	
	public boolean steppable() {
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

    public boolean getisDamaged()
	{
		return isLeaked;
    }

	public void setisDamaged(boolean b)
	{
		isLeaked=b;
	}
}
