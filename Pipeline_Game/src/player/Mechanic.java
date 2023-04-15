package player;

import container.*;

public class Mechanic extends Player {
	
	public void RepairPipe(Pipe p)
	{
		if(p.getisDamaged())
		{
			p.setisDamaged(false);
		}
	}

	void RepairPump(Pump pu)
	{
		if(pu.getisDamaged())
		{
			pu.setisDamaged(false);
		}
	}
}
