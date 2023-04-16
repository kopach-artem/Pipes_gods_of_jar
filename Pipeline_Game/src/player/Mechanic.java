package player;

import container.*;

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
	public void RepairPipe(Pipe p)
	{
		if(p.isLeaked())
		{
			p.setLeaked(false);
		}
	}
	
	/**
	 * Megjavítja a paraméterül kapott pumpát.
	 * @param pu - A megjavítani kívánt pumpa
	 */
	public void RepairPump(Pump pu)
	{
		if(pu.getisDamaged())
		{
			pu.setisDamaged(false);
		}
	}
}
