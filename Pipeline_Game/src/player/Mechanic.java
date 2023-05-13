package player;

import container.*;
import exception.MyException;

import java.io.Serializable;

/**
 * Ez a szerelő játékos, a szerelő javíthatja meg a csöveket, pumpákat és állíthatja is azokat.
 */
public class Mechanic extends Player implements Serializable {

	/**
	 * Mechanic konmstruktora
	 * @param position - Ebben a pozícióban lesz létrehozva a Mechanic
	 */
	public Mechanic(Container position) {
		super(position);
	}

	
	/**
	 * Megjavítja a pozíciónál lévő csövet
	 */
	public void RepairPipe() throws MyException {
		this.getPosition().mendPipe();
	}
	
	/**
	 * Megjavítja a pozíciójánál lévő pumpát
	 */
	public void RepairPump() throws MyException {
		this.getPosition().mendPump();
	}
}
