package container;

public class MountainSpring extends Container {
	
	private Pipe output;
	private int waterCapac;
	
	public void flowStart() {
	}

	
	/** 
	 * @return boolean
	 */
	public boolean steppable(){
		return false;
	}

	public void decreaseWaterAm() {
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}

	public int getWaterCapac() {
		return waterCapac;
	}

	public void setWaterCapac(int waterCapac) {
		this.waterCapac = waterCapac;
	}
}
