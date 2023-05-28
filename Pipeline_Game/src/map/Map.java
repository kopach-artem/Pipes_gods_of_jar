package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

		pu.getNeighbors().add(pi);
		pi.getNeighbors().add(pu);

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

	/**
	 * Beállítja a player attribútumot a paraméterként kapottra
	 * @param players - A beállítani kívánt ArrayList
	 */
	
	/** 
	 * @param cp
	 */
	public static void addNeighbors(ContainerPos cp){
		if(!Map.getInstance().getGameMap().isEmpty()) {
			for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
				if (containerPos.isOnNeighboringTile(cp.getPosX(), cp.getPosY())) {
					if (!(containerPos.getContainer().getNeighbors().contains(cp.getContainer()))) {
						cp.getContainer().getNeighbors().add(containerPos.getContainer());
						containerPos.getContainer().getNeighbors().add(cp.getContainer());
					}
				}
			}
		}
	}

	public static void removeNeighbors(ContainerPos cp){
		if(!Map.getInstance().getGameMap().isEmpty()) {
			for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
				if (containerPos.isOnNeighboringTile(cp.getPosX(), cp.getPosY())) {
					if (!(containerPos.getContainer().getNeighbors().contains(cp.getContainer()))) {
						cp.getContainer().getNeighbors().remove(containerPos.getContainer());
						containerPos.getContainer().getNeighbors().remove(cp.getContainer());
					}
				}
			}
		}
	}

	public static void addAllNeighbors(){
		for(ContainerPos cp : gameMap){
			addNeighbors(cp);
		}
	}

	public static int getLeakedWater() {
		return leakedWater;
	}

	public ArrayList<ContainerPos> getGameMap(){
		return gameMap;
	}

	public static void makeMap(){

		Pipe p1 = new Pipe();
		Pump pu1 = new Pump(2);
		Pipe p2 = new Pipe();
		Pump pu2 = new Pump(2);
		Pipe p3 = new Pipe();
		Cistern cs = new Cistern(p3);
		MountainSpring ms = new MountainSpring(p1);

		containers.add(ms);
		containers.add(p1);
		containers.add(pu1);
		containers.add(p2);
		containers.add(pu2);
		containers.add(p3);
		containers.add(cs);

		gameMap.add(new ContainerPos(ms, 0,0));
		gameMap.add(new ContainerPos(p1, 1,0));
		gameMap.add(new ContainerPos(pu1, 2,0));
		gameMap.add(new ContainerPos(p2, 3,0));
		gameMap.add(new ContainerPos(pu2, 4,0));
		gameMap.add(new ContainerPos(p3, 4,1));
		gameMap.add(new ContainerPos(cs, 4,2));

		addAllNeighbors();
	}

	public static ContainerPos getContainerAt(int x, int y){
		for(ContainerPos containerPos : gameMap){
			if(containerPos.getPosX() == x && containerPos.getPosY() == y){
				return containerPos;
			}
		}
		return null;
	}

}
