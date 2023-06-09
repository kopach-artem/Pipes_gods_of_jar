package container;

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
	 * @param pipe
	 */
	@Override
	public void setInput(Pipe pipe) {

	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean amIGettingDeatched() {
		return false;
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

	@Override
	public void getsOccupied() {

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
	public void alterPump(int x, int y, Type t) {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void mendPipe() {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void mendPump() {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void puncturePipe() {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void insertPump(Player player) {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void extractPipe(Player player, int xCord, int yCord) {

	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	@Override
	public void insertPipe(Player player, int xCord, int yCord) {

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
	public void takePipeFromCs(Player player) {

	}

	@Override
	public void takePumpFromCs(Player player) {

	}


	/** 
	 * @return boolean
	 */
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
		if(waterCapac != 0) {
			decreaseWaterAm();
			this.setInputState();
			if(output != null)
				output.setInputState();
		}
		else{
			inputState[0] = false;
			inputState[1] = false;
		}
	}

	@Override
	public int hasPipes() {
		return -1;
	}

	@Override
	public boolean hasPump() {
		return false;
	}

	/**
	 * A Mountain Spring ezt a függvényt nem valósítja meg, úgyhogy erről többet nem is beszélek
	 */
	public void setInputState() {
		inputState[0] = true;
		inputState[1] = true;
	}

	
	/** 
	 * @return String
	 */
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

	@Override
	public boolean isDamaged() {
		return false;
	}

	@Override
	public Container getInput() {
		return null;
	}

	@Override
	public Pipe getOutput() {
		return output;
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

	@Override
	public void setBreakOff(int rng) {
		
	}

	@Override
	public int queryCistern() {
		return -1;
	}

	@Override
	public int mountainSpringQuery() {
		return waterCapac;
	}

	@Override
	public String myIconPath() {
		return "file:resources/container_components/ms.png";
	}
}
