package container;

import exception.MyException;
import player.Player;
import player.Type;

import java.io.Serializable;

/**
 * Ez a hegyi forrás, ebből származik a víz, amelyért a játékosok versengenek
 */
public class MountainSpring extends Container implements Serializable {

	/**
	 * Tárolja azt a csövet, amely kivezeti a vizet a forrásból.
	 */
	private Pipe output;

	/**
	 * A hegyi forrásvíz kapacitása, azaz mennyi vizet képes tárolni a hegyi forrás
	 */
	private int waterCapac = 50;

	public MountainSpring(Pipe output){
		this.output = output;
		this.neighbors.add(output);
		output.neighbors.add(this);
	}

	public MountainSpring(){
		output = null;
	}

	
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


	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void lifeCycle(int turnCount) {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void movedFrom() {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void alterPump(Player player, Pipe pi, Type t) throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void mendPipe() throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void mendPump() throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void puncturePipe() throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void insertPump(Player player) throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void extractPipe(Player player, int xCord, int yCord) throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void insertPipe(Player player) throws MyException {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void pipeGetsSlippery() {

	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean getIsSlippery() {
		return false;
	}

	@Override
	public void pipeGetsSticky() {

	}

	@Override
	public boolean getIsSticky() {
		return false;
	}

	/**
	 * A hegyi forráshoz tartozó kiértékelő függvény
	 * Ez a függvény egyszerűen csak egy feltételben megnézi, hogy van-e benne víz ha van megyünk mélyebbre
	 * Mélyebben meghívjuk benne a setInputState-et önmagára, illetve csökkentjük a víz tartalmát (mert folyik ki belőle víz)
	 * És legvégül az outputján lévő Pipe-ra meghívjuk a setInputState-et
	 */
	public void eval() {
		System.out.println("Before: "+ this.writeInputState());
		if(waterCapac != 0) {
			decreaseWaterAm();
			this.setInputState();
			output.setInputState();
		}
	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	public void setInputState() {
		inputState[0] = true;
		inputState[1] = true;
		System.out.println("After: "+ this.writeInputState() + " " + "Water remaining in Mountain Spring: " + waterCapac);
	}

	@Override
	public String consolePrint() {
		return "MS\t";
	}

	@Override
	public void damageContainer() {

	}

	@Override
	public boolean isLooseEnd() {
		return false;
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
