
package player;

import container.*;

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
	 * Kilyukasztja a paraméterül kapott csövet.
	 * @param p - A kilyukasztani kívánt cső
	 */
	public void LeakPipe(Pipe p)
	{
		p.setLeaked(true);
	}
}
