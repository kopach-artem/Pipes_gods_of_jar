package player;

import container.*;

public class Mechanic extends Player {

	public Mechanic(Container position) {
		super(position);
	}

	
	/** 
	 * @param p
	 */
	public void RepairPipe(Pipe p)
	{
		if(p.isLeaked())
		{
			p.setisLeaked(false);
		}
	}
	
	/** 
	 * @param pu
	 */
	public void RepairPump(Pump pu)
	{
		if(pu.getisDamaged())
		{
			pu.setisDamaged(false);
		}
	}
}
