package container;

import java.util.ArrayList;
import java.util.Random;
import map.Map;

/**
 * Először is úgy döntöttünk, hogy a Cistern is felvesszük Objektum katalógusba,
 * mert az ugyanolyan fontos szerepet játszik ebben a feladatban, mint a Pump.
 * A Cistern tehát a csőrendszer aktív elemei, amely víztároló tartályként működik.
 * Felelős a csövek készítéséért, a víz tárolásáért és a ciszternában lévő vízszint növeléséért.
 */
public class Cistern extends Container {

	/**
	 * Ez a bemeneti cső, innen érkezik a víz a ciszternába,
	 * ha ezen a csövön keresztül megy víz.
	 */
	private Pipe input;

	/**
	 * Itt tároljuk a ciszternába került víz mennyiségét.
	 */
	private int collectedWater;

	/**
	 * Itt tároljuk a mozgatható pumpát.
	 */
	private Pump freePump;

	/**
	 * Ez felel a csővek véletlenszerű időközönkénti készítéséért/létrehozásáért.
	 */
	private int randomPipeCreationTime;

	/**
	 * Itt tároljuk a ciszterna által készített csöveket.
	 */
	private ArrayList<Pipe> madePipes;

	/**
	 * Cistern osztály konstruktora
	 * @param input - A Cistern bemeneti csöve
	 * @param freePump - A szabadon mozgatható Pump
	 * @param randomPipeCreationTime - A csövek véletlenszerű időközönkénti létrehozását folyásolja be
	 */
	public Cistern(Pipe input, Pump freePump, int randomPipeCreationTime) {
        this.input = input;
        this.collectedWater = 0;
        this.freePump = freePump;
        this.madePipes = new ArrayList<Pipe>();
    }

	/**
	 * Ezzel a metódussal tudjuk növelni a ciszternában lévő víz mennyiségét.
	 */
	public void increaseCollectedWater()
	{
		collectedWater++;
	}
	
	
	/**
	 * Minden harmadik körben létrehoz egy csövet
	 * @param turnCount
	 */
	public void lifeCycle(int turnCount)
	{
		if(turnCount%3==0)
			madePipes.add(new Pipe());
	}

	
	/**
	 * Mindig true-val tér vissza, ugyanis a Cistern-re mindig rá lehet lépni,
	 * az ot lévő játékosok számától függetlenül
	 * @return boolean - true
	 */
	public boolean steppable(){
		return true;
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}

	/**
	 * Vissaztér a Cistern bemeneti csövével
	 * @return - A bemeneti cső
	 */
	public Pipe getInput() {
		return input;
	}

	/**
	 * Beállítja a Cistern bemeneti csövét
	 * @param input - A beállítani kívánt cső
	 */
	public void setInput(Pipe input) {
		this.input = input;
	}

	/**
	 * Visszatér a CollectedWater attribútum,
	 * vagyis a Cistern által gyűjtött víz értékével.
	 * @return int - A CollectedWater attribútum értéke
	 */
	public int getCollectedWater() {
		return collectedWater;
	}

	/**
	 * Beállítja a CollectedWater attribútum értékét
	 * @param collectedWater - A beállítani kívánt érték
	 */
	public void setCollectedWater(int collectedWater) {
		this.collectedWater = collectedWater;
	}

	/**
	 * Vissaztér a szabadon mozgatható Pump-al
	 * @return Pump - A szabadon mozgatható Pump
	 */
	public Pump getFreePump() {
		return freePump;
	}

	/**
	 * Beállítja a FreePump attribútumot a paraméterként átadott értékre
	 * @param freePump - Az beállítani kívánt Pump
	 */
	public void setFreePump(Pump freePump) {
		this.freePump = freePump;
	}

	/**
	 * Vissaztér a RandomPipeCreationTime attribútum értékével
	 * @return int - A RandomPipeCreationTime attribútum értéke
	 */
	public int getRandomPipeCreationTime() {
		return randomPipeCreationTime;
	}

	/**
	 * Beállítja a RandomPipeCreationTime attribútum értékét
	 * @param randomPipeCreationTime - A beállítani kívánt érték
	 */
	public void setRandomPipeCreationTime(int randomPipeCreationTime) {
		this.randomPipeCreationTime = randomPipeCreationTime;
	}

	/**
	 * Visszatér Cisternben keletkezett Pipe-ok ArrayList-jével
	 * @return ArrayList - A keletkezett Pipe-ok
	 */
	public ArrayList<Pipe> getMadePipes() {
		return madePipes;
	}

	/**
	 * Beállítja a MadePipes attribútum értékét
	 * @param madePipes - A beállítani kívánt Pipe ArrayList
	 */
	public void setMadePipes(ArrayList<Pipe> madePipes) {
		this.madePipes = madePipes;
	}
}
