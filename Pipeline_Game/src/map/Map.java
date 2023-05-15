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

	
	/** 
	 * @param filePath
	 * @return String[]
	 */
	public static String[] readFromFile(String filePath) {
		File file = new File("maps/" + filePath);
		if (!file.exists()) {
			System.err.println("File not found: " + filePath);
			return null;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			return lines.toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/** 
	 * @param filePath
	 */
	public static void saveToFile(String filePath) {
		try (BufferedWriter bw = new BufferedWriter (new FileWriter("maps/" + filePath)))
		{
			int maxX = -1;
			int maxY = -1;

			// Find the maximum x and y values
			for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
				if (containerPos.getPosX() > maxX) {
					maxX = containerPos.getPosX();
				}
				if (containerPos.getPosY() > maxY) {
					maxY = containerPos.getPosY();
				}
			}

			// Create a 2D grid to store the containers
			Container[][] grid = new Container[maxX + 1][maxY + 1];

			// Fill the grid with null
			for (int i = 0; i <= maxX; i++) {
				for (int j = 0; j <= maxY; j++) {
					grid[i][j] =null;
				}
			}

			// Place the containers in the grid
			for (ContainerPos containerPos : getInstance().getGameMap()) {
				int x = containerPos.getPosX();
				int y = containerPos.getPosY();
				grid[x][y] = containerPos.getContainer();
			}

			//Make commands to create containers
			for (int y = 0; y <= maxY; y++)
			{
				for (int x = 0; x <= maxX; x++)
				{
					if(grid[x][y]!=null)
					{
						if(grid[x][y].consolePrint().equals("CS\t"))
						{
							bw.write("operationCreateContainerCisternAt"+x+'_'+y);
							bw.newLine();
						}
						else if(grid[x][y].consolePrint().equals("MS\t"))
						{
							bw.write("operationCreateContainerMountainSpringAt"+x+'_'+y);
							bw.newLine();
						}
						else if(grid[x][y].consolePrint().equals("PI\t"))
						{
							bw.write("operationCreateContainerPipeAt"+x+'_'+y);
							bw.newLine();
						}
						else if(grid[x][y].consolePrint().equals("PU\t"))
						{
							bw.write("operationCreateContainerPumpAt"+x+'_'+y);
							bw.newLine();
						}
					}
				}
			}

			//Make commands to create connections between containers
			for (int y = 0; y <= maxY; y++)
			{
				for (int x = 0; x <= maxX; x++)
				{
					if(grid[x][y]!=null)
					{
						if(grid[x][y].consolePrint().equals("CS\t"))
						{
							for(ContainerPos con: getInstance().getGameMap())
							{
								if(grid[x][y].seeifNeighbors(con.getContainer()) && grid[x][y]!=con.getContainer())
								{
									bw.write("operationConnectContainerAt"+x+'_'+y+"ToContainerAt"+con.getPosX()+'_'+con.getPosY());
									bw.newLine();
								}
							}
						}
						else if(grid[x][y].consolePrint().equals("MS\t"))
						{
							for(ContainerPos con: getInstance().getGameMap())
							{
								if(grid[x][y].seeifNeighbors(con.getContainer()) && grid[x][y]!=con.getContainer())
								{
									bw.write("operationConnectContainerAt"+x+'_'+y+"ToContainerAt"+con.getPosX()+'_'+con.getPosY());
									bw.newLine();
								}
							}
						}
						else if(grid[x][y].consolePrint().equals("PI\t"))
						{
							for(ContainerPos con: getInstance().getGameMap())
							{
								if(grid[x][y].seeifNeighbors(con.getContainer()) && grid[x][y]!=con.getContainer())
								{
									bw.write("operationConnectContainerAt"+x+'_'+y+"ToContainerAt"+con.getPosX()+'_'+con.getPosY());
									bw.newLine();
								}
							}
						}
						else if(grid[x][y].consolePrint().equals("PU\t"))
						{
							for(ContainerPos con: getInstance().getGameMap())
							{
								if(grid[x][y].seeifNeighbors(con.getContainer()) && grid[x][y]!=con.getContainer())
								{
									bw.write("operationConnectContainerAt"+x+'_'+y+"ToContainerAt"+con.getPosX()+'_'+con.getPosY());
									bw.newLine();
								}
							}
						}
					}
				}
			}

			//Make commands to create players
			for (int y = 0; y <= maxY; y++)
			{
				for (int x = 0; x <= maxX; x++)
				{
					if(grid[x][y]!=null)
					{

						for(int i=0; i<players.size(); i++)
						{
							if(players.get(i).consolePrint().equals("ME\t"))
							{
								if(grid[x][y]==players.get(i).getPosition())
								{
									bw.write("operationCreatePlayerMechanicAt"+x+'_'+y);
									bw.newLine();
								}
							}
							else if(players.get(i).consolePrint().equals("SA\t"))
							{
								if(grid[x][y]==players.get(i).getPosition())
								{
									bw.write("operationCreatePlayerSaboteurAt"+x+'_'+y);
									bw.newLine();
								}
							}
						}
					}
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
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

	public static int getLeakedWater() {
		return leakedWater;
	}

	public ArrayList<ContainerPos> getGameMap(){
		return gameMap;
	}
}
