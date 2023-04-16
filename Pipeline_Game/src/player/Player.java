
package player;

import java.util.ArrayList;

import container.*;
import exception.*;

/**
 * Egy általános játékost megvalósító osztály (Mechanic, Saboteur)
 */
public class Player {

	/**
	 * Ezzel tároljuk, hogy az adott játékos hol helyezkedik el.
	 */
	protected Container position;

	/**
	 * A játékos által hordozott csövek listája.
	 */
	protected ArrayList<Pipe> carriedPipes = new ArrayList<Pipe>();

	/**
	 * Ez tartalmazza a játékos által hordozott pumpát.
	 */
	protected Pump carriedPump = new Pump(3);

	/**
	 * Player osztály konstruktora.
	 * @param position - Ebben a pozícióban lesz létrehozva a Player.
	 */
	public Player(Container position)
	{
		this.position=position;
	}
	
	/**
	 * A játékos átállítja a paraméterül kapott Pump be- vagy kimeneti csövét.
	 * @param pu - Az átállítani kívánt pumpa
	 * @param pi - Ezt a csövet szeretnénk beállítani
	 * @param d - Ez alapján dönti el a metódus, hogy a be- vagy kimenetet szeretnénk átállítani
	 */
	public void adjustPump(Pump pu, Pipe pi, Direction d) {
		if (d == Direction.Input){
			pu.getNeighbors();
			pu.getInput();
			pi.getWaterFlowing();
			pu.setInput(pi);
		}
		else{
			pu.getNeighbors();
			pu.setOutput(pi);
		}
	}

	
	/**
	 * A játékos mozgatását megvalósító metódus. A paraméterül kapott Containerre csak akkor léphet a játékos,
	 * ha az a jelenlegi pozíciójának szomszédja.
	 * @param c - Erre a Containerre szeretnénk lépni
	 * @throws MyException
	 */
	public void Move(Container c) throws MyException {
		if (this.position.seeifNeighbors(c)) {
			if (c.steppable()) {
				((Pipe) this.position).setOccupied(false);
				this.setPosition(c);
				((Pipe) this.position).setOccupied(true);
			} else {
				throw new MyException("The container is clearly not steppable");
			}
		} else {
			throw new MyException("Not even next to it");
		}
	}

	/**
	 * Hozzáadja a saját maga által cipelt csövet a jelenlegi pozíciójához.
	 * @throws MyException
	 */
	public void attachPipe() throws MyException
	{
		if (!getCarriedPipes().isEmpty())
		this.position.insertPipe(this);
	}

	/**
	 * Elvesz egy szabad csövet a Cistern-től.
	 * @param c - A Cistern
	 */
	public void takePipe(Cistern c) {
			if(position==c)
			{
				if(!c.getMadePipes().isEmpty())
				{
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

		position.insertPump(this);

	}

	/**
	 * Elveszi a Cisterntől a szabad Pumpot
	 * @param c - A Cistern.
	 */
	public void takePump(Cistern c) {
		if(position==c)
		{
			if(c.getFreePump()!=null)
			{
				carriedPump=c.getFreePump();
				c.setFreePump(null);
			}
		}
	}

	/**
	 * Felveszi a paraméterül kapott csövet a jelenlegi pozíciójából.
	 * @param pi - A felvenni kívánt cső
	 * @throws MyException
	 */
	public void detachPipe(Pipe pi) throws MyException {

		position.extractPipe(this, pi);

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
	public ArrayList<Pipe> getCarriedPipes() {
		return carriedPipes;
	}

	/**
	 * Beállítja a carriedPipes attribútum értékét a paraméterül kapottra.
	 * @param carriedPipes - A beállítani kívánt ArrayList
	 */
	public void setCarriedPipes(ArrayList<Pipe> carriedPipes) {
		this.carriedPipes = carriedPipes;
	}
}
