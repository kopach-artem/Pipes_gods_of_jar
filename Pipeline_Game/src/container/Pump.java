package container;
import exception.MyException;
import map.Map;
import player.Player;

import java.util.Random;

public class Pump extends Container {

	private Pipe input;
	private Pipe output;
	private boolean isDamaged;
	private int randomDamageValue;
	private int maxPipeAmount;

	public Pump(int maxPipeAmount) {
		this.maxPipeAmount=maxPipeAmount;
		Random rand=new Random();
		randomDamageValue=rand.nextInt(11) + 10;
		isDamaged=false;
	}


	/**
	 * @param player
	 * @throws MyException
	 */
	public void insertPipe(Player player) throws MyException{

		Pipe atPipe = player.getCarriedPipes().get(0);

		if(!isAllConnected()){
			this.addPipe(atPipe);
			atPipe.addPump(this, 0);
			player.getCarriedPipes().remove(atPipe);
		}

	}

	
	/** 
	 * @param player
	 * @param pi
	 * @throws MyException
	 */
	public void extractPipe(Player player, Pipe pi) throws MyException {

		if(this.seeifNeighbors(pi)){
			if(pi.isLooseEnd()){
				pi.removePump(this);
				this.removePipe(pi);
				player.getCarriedPipes().add(pi);
			}
		}
	}

	
	/** 
	 * @return boolean
	 */
	public boolean steppable() {
		return true;
	}

	public void lifeCycle(int turnCount){
		if(turnCount == this.randomDamageValue && !this.isDamaged){
			this.isDamaged = true;
		}
	}

	
	/** 
	 * @param pi
	 */
	public void addPipe(Pipe pi) {

		if(!this.isAllConnected())
			this.neighbors.add(pi);

	}

	
	/** 
	 * @param pi
	 */
	public void removePipe(Pipe pi) {

		if(!this.neighbors.isEmpty())
			this.neighbors.remove(pi);

	}

	public void eval() {

	}

	public void setInputState() {

	}

	
	/** 
	 * @return boolean
	 */
	public boolean isAllConnected() {
		if(getMaxPipeAmount()==neighbors.size())
			return true;
		else
			return false;
	}

	
	/** 
	 * @return int
	 */
	public int getRandomDamageValue() {
		return randomDamageValue;
	}

	public int getMaxPipeAmount() {
		return maxPipeAmount;
	}

	public void setMaxPipeAmount(int maxPipeAmount) {
		this.maxPipeAmount = maxPipeAmount;
	}

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	public Pipe getOutput() {
		return output;
	}

	public void setOutput(Pipe output) {
		this.output = output;
	}

	public Pipe getInput() {
		return input;
	}

	public void setInput(Pipe input) {
		this.input = input;
	}

    public void setisDamaged(boolean b) {
		isDamaged=b;
    }

	public boolean getisDamaged() {
		return isDamaged;
	}
}
