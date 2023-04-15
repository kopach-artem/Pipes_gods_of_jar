
package player;

import container.*;

public class Saboteur extends Player {

	public Saboteur(Container position) {
		super(position);
	}

	public void LeakPipe(Pipe p)
	{
		p.setisLeaked(true);
	}
}
