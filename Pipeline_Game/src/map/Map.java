package map;

import java.io.*;
import java.util.ArrayList;
import exception.MyException;
import player.*;
import container.*;

/**
 * A Map osztály felelőssége a pályán elhelyezett pumpák és csövek tárolása.
 */
public class Map implements Serializable{

	/**
	 * Az eddig elfolyt víz értéke.
	 */
	private static int leakedWater;

	private static final long serialVersionUID = 1L;

	/**
	 * A pályán lévő játékosok ArrayList-je.
	 */

	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * A pályán lévő Containerek ArrayList-je.
	 */
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private static ArrayList<ArrayList<Container>> gameMap = new ArrayList<>();

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

	public void saveToFile(String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream("maps/" + filename);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this);
			objectOut.close();
			fileOut.close();
			System.out.println("Map object saved to " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a Map object from a text file.
	 * @param filename The name of the file to load the object from.
	 * @return The loaded Map object.
	 */
	public static Map loadFromFile(String filename) {
		try {
			FileInputStream fileIn = new FileInputStream("maps/" + filename);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Map map = (Map) objectIn.readObject();
			objectIn.close();
			fileIn.close();
			System.out.println("Map object loaded from " + filename);
			return map;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ArrayList<Container>> getGameMap(){
		return gameMap;
	}
}
