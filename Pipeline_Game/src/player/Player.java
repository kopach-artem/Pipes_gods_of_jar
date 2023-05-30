
package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import container.*;
import controller.Game;
import exception.*;
import javafx.scene.control.ChoiceBox;
import map.Map;
import menu.MyAlert;


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

	private final int STICKY_TIMER = 4;
	private int timeLeft = 0;

	private boolean getSticky = false;

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


	/**
	 * @return int
	 */
	public int getId(){
		return id;
	}

	/**
	 * A játékos átállítja a paraméterül kapott Pump be- vagy kimeneti csövét.
	 * @param pi - Ezt a csövet szeretnénk beállítani
	 * @param t - Ez alapján dönti el a metódus, hogy a be- vagy kimenetet szeretnénk átállítani
	 */
	public void adjustPump(Pipe pi, Type t){
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
	public void leakPipe(){
		this.getPosition().puncturePipe();
	}

	public void getReallySticky(){
		getSticky = true;
		timeLeft = Game.getInstance().getTurnCount() + STICKY_TIMER;
	}

	public void turnAsSticky(){
		if(getSticky)
			if(timeLeft == Game.getInstance().getTurnCount()){
				getSticky = false;
				timeLeft = 0;
			}
	}


	/**
	 * A játékos mozgatását megvalósító metódus. A paraméterül kapott Containerre csak akkor léphet a játékos,
	 * ha az a jelenlegi pozíciójának szomszédja.
	 * @param c - Erre a Containerre szeretnénk lépni
	 * @throws MyException
	 */
	public void Move(Container c){

		ArrayList<Container> neighbors = new ArrayList<>();

		for(Container container : c.getNeighbors()){
			neighbors.add(container);
		}

		System.out.println(neighbors);

		if(getSticky){
			MyAlert.showStickyMoveAlert("Sticky");
			return;
		}

		if (this.position.seeifNeighbors(c)) {
			if(c.getIsSlippery() && c.steppable()){
				Random random = new Random();
				int index = random.nextInt(neighbors.size());
				if(neighbors.get(index).steppable()) {
					this.setPosition(neighbors.get(index));
					return;
				} else {
					neighbors.remove(index);
					if(!neighbors.isEmpty()) {
						this.setPosition(neighbors.get(0));
						return;
					} else
						MyAlert.showInvalidMoveAlert("There is no neighboring steppable container");
				}
			}
			else if (c.steppable()) {
				this.position.movedFrom();
				this.setPosition(c);
				if(position.getIsSticky()){
					getReallySticky();
				}
			} else {
				MyAlert.showInvalidMoveAlert("The given Container is not steppable");
			}
		} else {
			MyAlert.showInvalidMoveAlert("Not even next to it");
		}
	}


	/**
	 * @throws MyException
	 */
	public void RepairPipe(){

	}


	/**
	 * @throws MyException
	 */
	public void RepairPump(){

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
	public void attachPipe(int xCord, int yCord){
		if (!getCarriedPipes().isEmpty())
			this.position.insertPipe(this, xCord, yCord);
	}

	public void playerAttachesPipe(Direction direction){

		ContainerPos cp = new ContainerPos();

		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getContainer().equals(this.position)){
				cp = containerPos;
			}
		}
		switch (direction){
			case Up : {
				this.attachPipe(cp.getPosX(), cp.getPosY() - 1);
				break;
			}
			case Down : {
				this.attachPipe(cp.getPosX(), cp.getPosY() + 1);
				break;
			}
			case Left : {
				this.attachPipe(cp.getPosX() - 1, cp.getPosY());
				break;
			}
			case Right : {
				this.attachPipe(cp.getPosX() + 1, cp.getPosY());
				break;
			}
		}
	}

	public void playerDetachesPipe(Direction direction){
		ContainerPos cp = new ContainerPos();

		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getContainer().equals(this.position)){
				cp = containerPos;
			}
		}
		switch (direction){
			case Up : {
				this.detachPipe(cp.getPosX(), cp.getPosY() - 1);
				break;
			}
			case Down : {
				this.detachPipe(cp.getPosX(), cp.getPosY() + 1);
				break;
			}
			case Left : {
				this.detachPipe(cp.getPosX() - 1, cp.getPosY());
				break;
			}
			case Right : {
				this.detachPipe(cp.getPosX() + 1, cp.getPosY());
				break;
			}
		}
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
	public void attachPump(){

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
	public void detachPipe(int x, int y) {

		position.extractPipe(this, x, y);

	}

	public void playerMoves(Direction direction){

		ContainerPos pos = new ContainerPos();

		if(Map.getInstance().getGameMap() != null){
			for(ContainerPos containerPos : Map.getInstance().getGameMap()){
				if(containerPos.getContainer().equals(this.position)){
					pos = containerPos;
				}
			}
			switch(direction){
				case Up : {
					if(Map.getContainerAt(pos.getPosX(), pos.getPosY() - 1) != null){
						this.Move(Map.getContainerAt(pos.getPosX(), pos.getPosY() - 1).getContainer());
					}
					break;
				}
				case Down : {
					if(Map.getContainerAt(pos.getPosX(), pos.getPosY() + 1) != null){
						this.Move(Map.getContainerAt(pos.getPosX(), pos.getPosY() + 1).getContainer());
					}
					break;
				}
				case Left : {
					if(Map.getContainerAt(pos.getPosX() - 1, pos.getPosY()) != null){
						this.Move(Map.getContainerAt(pos.getPosX() - 1, pos.getPosY()).getContainer());
					}
					break;
				}
				case Right : {
					if(Map.getContainerAt(pos.getPosX() + 1, pos.getPosY()) != null){
						this.Move(Map.getContainerAt(pos.getPosX() + 1, pos.getPosY()).getContainer());
					}
					break;
				}
			}
		}
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

	public ChoiceBox<String> getChoiceBox(){
		return null;
	}
}

