
package player;

import java.util.ArrayList;

import container.*;
import exception.*;

public class Player {

	private Container position;
	private ArrayList<Pipe> carriedPipes;
	private Pump carriedPump;

	public Player(Container position)
	{
		this.position=position;
		carriedPump=null;
		carriedPipes=null;
	}
	public void adjustPump(Pump pu, Pipe pi, Type t) {
		if (t.Input.equals(t)){
			pu.getNeighbors();
			pu.getInput();
			pi.getWaterFlowing();
			pu.setInput(pi);
		}
		else if (t.Output.equals(t)){
			pu.getNeighbors();
			pu.setOutput(pi);
		}
	}
	public void Move(Container c) throws MyException {
		if (this.position.seeifNeighbors(c)) {
			if (c.steppable()) {
				this.setPosition(c);
			} else {
				throw new MyException("The container is clearly not steppable");
			}
		} else {
			throw new MyException("Not even next to it");
		}

	}
	
	public void attachPipe(Pipe pi)
	{
		Pump pos= (Pump) position;
		if(!pos.isAllConnected())
		{
			pos.addPipe(pi);
			pi.addPump(pos);
			carriedPipes.remove(pi);
		}
	}
	
	public void takePipe(Pipe pi) {
	}
	
	public void attachPump() {

		position.insertPump(this);

	}
	
	public void takePump(Pump pu) {
	}
	
	public void detachPipe(Pipe pi) {
		Pump tmp = (Pump) position;
		if(position.seeifNeighbors(pi)){
			if(pi.isLooseEnd()){
				pi.removePump(tmp);
				tmp.removePipe(pi);
				carriedPipes.add(pi);
			}
		}
	}
	
	public void setPostion(Container c) {
	}

	public Container getPosition() {
		return position;
	}

	public void setPosition(Container position) {
		this.position = position;
	}

	public Pump getCarriedPump() {
		return carriedPump;
	}

	public void setCarriedPump(Pump carriedPump) {
		this.carriedPump = carriedPump;
	}

	public ArrayList<Pipe> getCarriedPipes() {
		return carriedPipes;
	}

	public void setCarriedPipes(ArrayList<Pipe> carriedPipes) {
		this.carriedPipes = carriedPipes;
	}
}
