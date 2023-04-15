
package player;

import container.*;

public class Saboteur extends Player {
	
	public void LeakPipe(Pipe p)
	{
		if(p.isLeaked()==false)
		{
			p.setisLeaked(true);
		}
	}
}
