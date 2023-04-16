package container;

import exception.MyException;
import player.Player;
import player.Type;

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

	
	/** 
	 * @return String
	 */
	public String writeInputState(){

		return "MountainSpring inputStatjének első illetve második eleme: " + this + ": "+ inputState[0] + ',' + inputState[1];
	}

	@Override
	public void lifeCycle(int turnCount) {

	}

	@Override
	public void movedFrom() {

	}

	@Override
	public void alterPump(Player player, Pipe pi, Type t) throws MyException {

	}

	@Override
	public void mendPipe() throws MyException {

	}

	@Override
	public void mendPump() throws MyException {

	}

	@Override
	public void puncturePipe() throws MyException {

	}

	@Override
	public void insertPump(Player player) throws MyException {

	}

	@Override
	public void extractPipe(Player player, Pipe pi) throws MyException {

	}

	@Override
	public void insertPipe(Player player) throws MyException {

	}

	public void eval() {
		System.out.println("Before: "+ this.writeInputState());
		if(waterCapac != 0) {
			decreaseWaterAm();
			this.setInputState();
			output.setInputState();
		}
	}
	
	public void setInputState() {
		inputState[0] = true;
		inputState[1] = true;
		System.out.println("After: "+ this.writeInputState() + "Water remaining in Mountain Spring: " + waterCapac);
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
