package player;

import container.*;
import exception.MyException;

/**
 * Ez a szerelő játékos, a szerelő javíthatja meg a csöveket, pumpákat és állíthatja is azokat.
 */
public class Mechanic extends Player {

	/**
	 * Mechanic konmstruktora
	 * @param position - Ebben a pozícióban lesz létrehozva a Mechanic
	 */
	public Mechanic(Container position) {
		super(position);
	}

	
	/**
	 * Megjavítja a praméterül kapott csövet.
	 * @param p - A javítani kívánt cső
	 */
	public void RepairPipe(Pipe p) throws MyException {
		this.getPosition().mendPipe();
	}
	
	/**
	 * Megjavítja a paraméterül kapott pumpát.
	 * @param pu - A megjavítani kívánt pumpa
	 */
	public void RepairPump(Pump pu) throws MyException {
		this.getPosition().mendPump();
	}
}
