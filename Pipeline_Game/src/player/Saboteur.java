
package player;

import container.*;
import exception.MyException;

/**
 * Ez a szabotőr játékos, a szabotőr lyukaszthatja ki a csöveket
 * illetve a szabotőr a szerelőhöz hasonlóan átállíthatja a pumpákat.
 */
public class Saboteur extends Player {

	/**
	 * SAboteur osztály konstruktora.
	 * @param position - Ebben a pozícióban lesz létrehozva a Saboteur.
	 */
	public Saboteur(Container position) {
		super(position);
	}

	
	/**
	 * Kilyukasztja azon csövet amelyen éppen áll
	 */
	public void LeakPipe() throws MyException {
		this.getPosition().puncturePipe();
	}
}
