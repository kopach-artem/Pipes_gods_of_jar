package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
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

	private static Map map;

	private static final long serialVersionUID = 1L;

	/**
	 * A pályán lévő játékosok ArrayList-je.
	 */

	private static ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * A pályán lévő Containerek ArrayList-je.
	 */
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private static ArrayList<ContainerPos> gameMap = new ArrayList<ContainerPos>();

	/**
	 * Map osztály konstruktora.
	 */
	private Map(){

	}

	
	/** 
	 * @return Map
	 */
	public static Map getInstance() {
		if (map == null) {
			map = new Map();
		}
		return map;
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

	public static void readFromFile(String filePath) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("maps/" + filePath))) {
			map = (Map) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void saveToFile(String filePath) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("maps/" + filePath))) {
			oos.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ContainerPos> getGameMap(){
		return gameMap;
	}
}
