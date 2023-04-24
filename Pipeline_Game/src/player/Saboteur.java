
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
	 * A szabotőrhöz tartozó metódus amely segítségével egy csövet (amelyen éppen áll) csúszóssá tud tenni
	 */
	public void makePipeSlippery(){
		this.position.pipeGetsSlippery();
	}
}
