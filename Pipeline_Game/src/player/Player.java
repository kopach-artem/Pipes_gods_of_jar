
package player;

import java.util.ArrayList;

import container.*;

public class Player {
	private Container position;
	private ArrayList<Pipe> carriedPipes;
	private Pump carriedPump;
	
	public void AdjustPump(Pump pu, Pipe pi, Type t) {
	}
	
	public void Move(Container c) {
	}
	
	public void attachPipe(Pipe pi) {
	}
	
	public void takePipe(Pipe pi) {
	}
	
	public void attachPump(Pump pu) {
	}
	
	public void takePump(Pump pu) {
	}
	
	public void detachPipe(Pipe pi) {
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
