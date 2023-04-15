
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
		Pump pos = new Pump(position);
		if(!pos.isAllConnected())
		{
			pos.addPipe(pi);
			pi.addPump(pos);
			try
			{
				carriedPipes.remove(pi);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void takePipe(Cistern c)
	{
		if(position==c)
		{
			if(!c.getMadePipes().isEmpty())
			{
				carriedPipes.add(c.getMadePipes().get(0));
				c.getMadePipes().remove(0);
			}
		}
	}
	
	public void attachPump(Pump pu) {
	}
	
	public void takePump(Cistern c)
	{
		if(position==c)
		{
			if(c.getFreePump()!=null)
			{
				carriedPump=c.getFreePump();
				c.setFreePump(null);
			}
		}
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
