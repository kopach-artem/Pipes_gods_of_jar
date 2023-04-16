package container;

/**
 * Ez a hegyi forrás, ebből származik a víz, amelyért a játékosok versengenek
 */
public class MountainSpring extends Container {

	/**
	 * Tárolja azt a csövet, amely kivezeti a vizet a forrásból.
	 */
	private Pipe output;

	/**
	 * A hegyi forrásvíz kapacitása, azaz mennyi vizet képes tárolni a hegyi forrás
	 */
	private int waterCapac = 50;

	
	/**
	 * Mindig false-al tér vissza, ugyanis a MountainSpring-re nem lépheetnek rá a játékosok.
	 * @return boolean
	 */
	public boolean steppable(){
		return false;
	}

	/**
	 * A hegyi forrásban lévő víz mennyiségét csökkenti
	 */
	public void decreaseWaterAm() {
		waterCapac--;
	}

	public String writeInputState(){

		return "MountainSpring inputStatjének első illetve második eleme" + this + ": "+ inputState[0] + inputState[1];
	}
	
	public void eval() {
		System.out.println("Before: "+ this.writeInputState());
		if(waterCapac != 0) {
			this.setInputState();
			decreaseWaterAm();
			output.setInputState();
		}
	}
	
	public void setInputState() {
		inputState[0] = true;
		inputState[1] = true;
		System.out.println("After: "+ this.writeInputState());
	}

	
	/**
	 * Visszatér a waterCapac attribűtum értékével
	 * @return int - A waterCapac attribútum értéke
	 */
	public int getWaterCapac() {
		return waterCapac;
	}

	/**
	 * Beállítja a waterCapac attribútum értékét a paraméterként kapottra
	 * @param waterCapac - A beállítani kívánt érték
	 */
	public void setWaterCapac(int waterCapac) {
		this.waterCapac = waterCapac;
	}

	/**
	 * Beállítja az output attribútum értékét a paraméterként kapottra
	 * @param output - A beállítani kívánt érték
	 */
	public void setOutput(Pipe output){
		this.output = output;
	}
}
