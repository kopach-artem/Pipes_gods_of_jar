package container;

import java.io.Serializable;
import java.util.ArrayList;

import exception.MyException;
import player.*;

/**
 * Abstract osztály.
 * Egy általános tárolót valósít meg (Pipe, Pump, Mountain Spring, Cistern)
 */
public abstract class Container implements Serializable {
	/**
	 * A Container mellett található Containerek ArrayList-je
	 */
	protected ArrayList<Container> neighbors = new ArrayList<Container>();

	/**
	 * Ez az attribútum felelős a víz mozgásáért
	 * Ebben az attribútumban tárolunk két értéket:
	 * 								-0.index-en tároljuk az előző evaluation kimenetét
	 * 								-1.index-en tároljuk a mostani evaluation kimenetét
	 * Ez az attribútum egy kis naplóként értelmezhető, mivel tároljuk, hogy előző körben mi történt, amellett, hogy most mi történik
	 * A false (hamis) érték azt jelenti, hogy a Containerhez nem jutott víz
	 * Míg a true (igaz) érték azt jelenti, hogy a Containerhez jutott víz
	 * A víz mozgásának részletes leírása a Controller waterFlow() függvényénél található meg (ide túl sok lett volna)
	 */
	protected boolean[] inputState = { false, false };

	/**
	 * Megnézi, hogy apraméterként kapott Container a szomszédja-e a Cotainernek
	 * @param neighbor - A megvizsgálni kívánt Container
	 * @return boolean - Szomszédja-e vagy sem
	 */
	public boolean seeifNeighbors(Container neighbor){

		return this.neighbors.contains(neighbor);
	}

	
	/**
	 * Ez a függvény felelős a waterFlow() függvényhez kapcsolódó kiiratásokra
	 * @return String
	 */
	public String writeInputState(){

		return null;

	}

	
	/** 
	 * @param c
	 */
	/**
	 * Ezt a függvényt implementálja a Pump és Cistern osztályok
	 * A függvény azért felelős, hogy egy adott idő után megtörténjen valami a container objektumunkkal
	 * @param turnCount
	 */
	public abstract void lifeCycle(int turnCount);

	/**
	 * Ezt a függvényt implementálja a Pump és Cistern osztályok
	 * A függvény egy boolean értéket ad vissza abból adódóan, hogy az argumentumban megadott Container inputja-e az adott Containernek (this)
	 * @param c
	 * @return
	 */
	public boolean amInput(Container c){
		return false;
	}

	/**
	 *  Ezt a függvényt implementálja a Pipe osztály
	 *	A függvény azt a feladatot látja el, hogy amikor a játékos ellép egy Container-ről (Pipe-ról), beállítsa, hogy a Pipe nem occupied
	 */
	public abstract void movedFrom();

	/**
	 * Ezt a függvényt a Pump osztály valósítja meg
	 * Ez a függvény felelős a pumpa output illetve inputjának átállításáért
	 * @param player
	 * @param pi
	 * @param t
	 * @throws MyException
	 */
	public abstract void alterPump(Player player, Pipe pi, Type t) throws MyException;

	/**
	 * Ezt a függvényt a Pipe osztály valósítja meg
	 * Ez a függvény felelős a cső megjavításáért
	 * @throws MyException
	 */
	public abstract void mendPipe() throws MyException;

	/**
	 * Ezt a függvényt a Pump osztály valósítja meg
	 * Ez a függvény felelős a pumpa megjavításáért
	 * @throws MyException
	 */
	public abstract void mendPump() throws MyException;

	/**
	 * Ezt a függvényt a Pipe osztály valósítja meg
	 * Ez a függvény felelős a cső meglékeléséért
	 * @throws MyException
	 */
	public abstract void puncturePipe() throws MyException;

	/**
	 * Ezt az függvényt a Pipe osztály valósítja meg
	 * Ez az függvény felelős pumpa csőhöz való illesztéséért
	 * @param player
	 * @throws MyException
	 */
	public abstract void insertPump(Player player) throws MyException;

	/**
	 * Ezt a függvényt a Pump osztály valósítja meg
	 * Ez a függvény felelős a csőhöz tartoző szabadvégű csőnek leillesztéséért
	 * @param player
	 * @param pi
	 * @throws MyException
	 */
	public abstract void extractPipe(Player player, int xCord, int yCord) throws MyException;

	/**
	 * Ezt a függvényt a Pump osztály valósítja meg
	 * Ez a függvény felelős a cső pumpához illesztéséért
	 *
	 * @param player
	 * @param xCord
	 * @param yCord
	 * @throws MyException
	 */
	public abstract void insertPipe(Player player, int xCord, int yCord) throws MyException;

	/**
	 * Ezt a függvényt a Pipe osztály valósítja meg. Ez a függvény felelős a cső csúszóssá válik
	 */
	public abstract void pipeGetsSlippery();

	/**
	 * Ezt a függvényt a Pipe (és Pump (false default))osztályok valósulnak meg. Ez a függvény felelős visszaadni csúszóssá-e Pipe (getter)
	 * @return
	 */

	/*
	 * Ezt a függvényt a Pipe osztály valósítja meg.
	 * Ez a függvény felelős visszaadni csúszóssá-e Pipe (getter)
	 */
	public abstract boolean getIsSlippery();

	/*
	 * Ezt a függvényt a Pipe osztály valósítja meg. 
	 * Ez a függvény felelős a cső  ragadóssá válik
	 */
	public abstract void pipeGetsSticky();

	/*
	 * Ezt a függvényt a Pipe osztály valósítja meg.
	 *  Ez a függvény felelős visszaadni ragadóssá-e Pipe (getter)
	 */
	public abstract boolean getIsSticky();

	/**
	 * Ezt a függvényt megvalósítja a Pump, Pipe, Cistern illetve MountaiSpring osztályok
	 * Ez a függvény visszaad egy boolean értéket az alapján, hogy az adott Container-re lehet-e lépni
	 * @return
	 */
	public boolean steppable(){
		return false;
	}

	/**
	 * Ezt a függvényt megvalósítja a Pipe, Cistern, MountainSpring
	 * Ez a függvény felelős a víz mozgásáért való kiértékelésekért
	 */
	public abstract void eval();

	/**
	 * Ezt a függvényt megvalósítja a Pump, Pipe, Cistern illetve MountaiSpring osztályok
	 * Ez a függvény felelős az inputState megváltoztatásáért, ez felelős a víz tényleges mozgásáért
	 */
	public abstract void setInputState();

	public abstract String consolePrint();

	public abstract void damageContainer();

	public abstract boolean isLooseEnd();

	/**
	 * Az evaluation-t (kiértékelést) követően az inputState értékeit megváltoztatjuk
	 */
	public void makeHistory() {

		inputState[0] = inputState[1];
		inputState[1] = false;
	}

	/**
	 * Visszatér a neighbors attribúmmal
	 * @return ArrayList - neighbors attribútum
	 */
	public ArrayList<Container> getNeighbors() {
		return neighbors;
	}

	/**
	 * Beállítja a neigbors attribútumot a paraméterként kapottra
	 * @param neighbors - A beállítani kívánt attribútum
	 */
	public void setNeighbors(ArrayList<Container> neighbors) {
		this.neighbors = neighbors;
	}

	public abstract boolean isDamaged();

	public abstract Container getInput();
	public abstract Container getOutput();
}
