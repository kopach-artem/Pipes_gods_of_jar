
package player;

import container.*;

public class Saboteur extends Player {

	public Saboteur(Container position) {

		super(position);
	}

	
	/** 
	 * @param p
	 */
	public void LeakPipe(Pipe p)
	{
		p.setisLeaked(true);
	}
}
