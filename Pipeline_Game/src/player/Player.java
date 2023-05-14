
package player;

import java.io.Serializable;
import java.util.ArrayList;

import container.*;
import exception.*;


/**
 * Egy általános játékost megvalósító osztály (Mechanic, Saboteur)
 */
public class Player implements Serializable {

	/**
	 * Ezzel tároljuk, hogy az adott játékos hol helyezkedik el.
	 */
	protected Container position;

	protected static int latestId = 0;

	protected int id;

	/**
	 * A játékos által hordozott csövek listája.
	 */
	protected ArrayList<Container> carriedPipes = new ArrayList<Container>();

	/**
	 * Ez tartalmazza a játékos által hordozott pumpát.
	 */
	protected Pump carriedPump;

	/**
	 * Player osztály konstruktora.
	 * @param position - Ebben a pozícióban lesz létrehozva a Player.
	 */
	public Player(Container position) {
		this.position=position;
		carriedPump = null;
		id = generateId();
	}

	public Player() {
		this.position=null;
		carriedPump = null;
		id = generateId();
	}

	
	/** 
	 * @return int
	 */
	public static int generateId(){
		return ++latestId;
	}

	public int getId(){
		return id;
	}

	/**
	 * A játékos átállítja a paraméterül kapott Pump be- vagy kimeneti csövét.
	 * @param pi - Ezt a csövet szeretnénk beállítani
	 * @param t - Ez alapján dönti el a metódus, hogy a be- vagy kimenetet szeretnénk átállítani
	 */
	public void adjustPump(Pipe pi, Type t) throws MyException {

		this.position.alterPump(this, pi, t);

	}

	/*
	 * Új függvény. Ez a függvény csinálni csö, amin Player áll Sticky.
	 * 
	 */

	public void makePipeSticky(){
		this.position.pipeGetsSticky();
	}

	/**
	 * Ez a függvény csinálni csö, amin Player áll lyukasztani, mert “Szerelő is tud lyukasztani.”
	 * @throws MyException
	 */
	public void LeakPipe() throws MyException {
		this.getPosition().puncturePipe();
	}


	/**
	 * A játékos mozgatását megvalósító metódus. A paraméterül kapott Containerre csak akkor léphet a játékos,
	 * ha az a jelenlegi pozíciójának szomszédja.
	 * @param c - Erre a Containerre szeretnénk lépni
	 * @throws MyException
	 */
	public void Move(Container c) throws MyException {

		ArrayList<Container> neighbors = c.getNeighbors();

		if(this.position.getIsSticky()){
			throw new MyException("Player cannot move due to: Pipe is sticky");
		}
		if (this.position.seeifNeighbors(c)) {
			if(c.getIsSlippery() && c.steppable()){
				int index = (int)(Math.random() * neighbors.size());
				if(neighbors.get(index).steppable()) {
					this.setPosition(neighbors.get(index));
					return;
				}
				else {
					neighbors.remove(index);
					if(!neighbors.isEmpty()) {
						this.setPosition(neighbors.get(0));
						return;
					}
					else
						throw new MyException("There is no neighboring steppable container");
				}
			}
			if (c.steppable()) {
				this.position.movedFrom();
				this.setPosition(c);
			} else {
				throw new MyException("The given Container is not steppable");
			}
		} else {
			throw new MyException("Not even next to it");
		}
	}

	public void RepairPipe() throws MyException {

	}

	public void RepairPump() throws MyException {

	}
	public void makePipeSlippery(){

	}

	/**
	 * Változni logiká, mert most attachPipe csak a fűggvény, 
	 * amelyik csak kezdi eset és néz, 
	 * hogy Playernek van carriedPipes (!getCarriedPipes.isEmpty()). 
	 * Meghívni insertPipe(p: Player) : void függvényt.  
	 * @throws MyException
	 */
	public void attachPipe(int xCord, int yCord) throws MyException {
		if (!getCarriedPipes().isEmpty())
			this.position.insertPipe(this, xCord, yCord);
	}

	/**
	 * Ha van cső Cisternában, amelyben Player áll, 
	 * akkor ez függvény tud adni új csöt carriedPipes List-be.
	 * @param c - A Cistern
	 */
	public void takePipe(Cistern c) {
		if(position==c) {
			if(!c.getMadePipes().isEmpty()) {
				carriedPipes.add(c.getMadePipes().get(0));
				c.getMadePipes().remove(0);
			}
		}
	}

	/**
	 * A jelenlegi pozíciójához adja hozzá a Pumpot.
	 * @throws MyException
	 */
	public void attachPump() throws MyException {

		if(!(getCarriedPump() == null))
			position.insertPump(this);

	}

	/**
	 * Elveszi a Cisterntől a szabad Pumpot
	 * @param c - A Cistern.
	 */
	public void takePump(Cistern c) {
		if(position==c) {
			if(c.getFreePump()!=null) {
				carriedPump=c.getFreePump();
				c.setFreePump(null);
			}
		}
	}

	/**
	 * Felveszi a paraméterül kapott csövet a jelenlegi pozíciójából.
	 * @param container - A felvenni kívánt cső
	 * @throws MyException
	 */
	public void detachPipe(ContainerPos container) throws MyException {

		position.extractPipe(this, container.getPosX(), container.getPosY());

	}

	/**
	 * Visszatér a position attribútum értékével.
	 * @return Container
	 */
	public Container getPosition() {
		return position;
	}

	/**
	 * Beállítja a position attribútum értékét a paraméterül kapottra.
	 * @param position - A beállítani kívánt Container
	 */
	public void setPosition(Container position) {
		this.position = position;
	}

	/**
	 * Visszatér a carriedPump attribútum értékével.
	 * @return Pump
	 */
	public Pump getCarriedPump() {
		return carriedPump;
	}

	/**
	 * Beállítja a carriedPump attribútum értékét a paraméterül kapottra.
	 * @param carriedPump - A beállítani kívánt Pump
	 */
	public void setCarriedPump(Pump carriedPump) {
		this.carriedPump = carriedPump;
	}

	/**
	 * Visszatér a carriedPipes attribútum értékével.
	 * @return ArrayList
	 */
	public ArrayList<Container> getCarriedPipes() {
		return carriedPipes;
	}

	/**
	 * Beállítja a carriedPipes attribútum értékét a paraméterül kapottra.
	 * @param carriedPipes - A beállítani kívánt ArrayList
	 */
	public void setCarriedPipes(ArrayList<Container> carriedPipes) {
		this.carriedPipes = carriedPipes;
	}

	public String consolePrint(){ return "";}
}

