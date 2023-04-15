package player;

import container.*;

public class Mechanic extends Player {

	public Mechanic(Container position) {
		super(position);
	}

	public void RepairPipe(Pipe p)
	{
		if(p.isLeaked())
		{
			p.setisLeaked(false);
		}
	}
	public void RepairPump(Pump pu)
	{
		if(pu.getisDamaged())
		{
			pu.setisDamaged(false);
		}
	}
}
