package map;

import java.util.ArrayList;
import exception.MyException;
import player.*;
import container.*;

/**
 * A Map osztály felelőssége a pályán elhelyezett pumpák és csövek tárolása.
 */
public class Map{

	/**
	 * Az eddig elfolyt víz értéke.
	 */
	private static int leakedWater;

	/**
	 * A pályán lévő játékosok ArrayList-je.
	 */

	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * A pályán lévő Containerek ArrayList-je.
	 */
	private static ArrayList<Container> containers = new ArrayList<Container>();

	/**
	 * Map osztály konstruktora.
	 */
	public Map(){
		leakedWater = 0;
	}

	/**
	 * A paraméterként kapott pumpát és csövet egymáshoz csatlakoztatja.
	 * @param pu - A pumpa
	 * @param pi - A cső
	 * @throws MyException
	 */
	public void connectPumpToPipe(Pump pu, Pipe pi) throws MyException {

		pu.addPipe(pi);
		pi.addPump(pu);

	}

	/**
	 * Növeli a leakedWater attribútum értékét.
	 */
	public static void increaseLeakedWater() {

		leakedWater++;

	}
	
	
	/**
	 * Eltávolít egy Container a játéktérről
	 * @param c - Az eltávolítano kívánt Container
	 */
	public void removeElement(Container c) {
		
	}

	/**
	 * Hozzáad egy Containert a játéktérhez.
	 * @param c - A hozzáadni kívánt Container
	 */
	public static void addElement(Container c) {
		containers.add(c);
	}

	/**
	 * Visszatér a containers attribútum értékével.
	 * @return Arraylist - A containerek
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * Visszatér a player attribútum értékével.
	 * @return ArrayList - A playerek
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Beállítja a player attribútumot a paraméterként kapottra
	 * @param players - A beállítani kívánt ArrayList
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
