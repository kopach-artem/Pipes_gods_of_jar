package container;

import java.util.ArrayList;
import java.util.Random;
import map.Map;

public class Cistern extends Container {
	
	private Pipe input;
	private int collectedWater;
	private Pump freePump;
	private int randomPipeCreationTime;
	private ArrayList<Pipe> madePipes;

	public Cistern(Pipe input, Pump freePump, int randomPipeCreationTime) {
        this.input = input;
        this.collectedWater = 0;
        this.freePump = freePump;
        this.madePipes = new ArrayList<Pipe>();
    }
	
	public void increaseCollectedWater()
	{
		collectedWater++;
	}
	
	public void createPipe()
	{
		if(Map.getTurnCount()%3==0)
			madePipes.add(new Pipe());
	}

	
	/** 
	 * @return boolean
	 */
	public boolean steppable(){
		return true;
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}

	public Pipe getInput() {
		return input;
	}

	public void setInput(Pipe input) {
		this.input = input;
	}

	public int getCollectedWater() {
		return collectedWater;
	}

	public void setCollectedWater(int collectedWater) {
		this.collectedWater = collectedWater;
	}

	public Pump getFreePump() {
		return freePump;
	}

	public void setFreePump(Pump freePump) {
		this.freePump = freePump;
	}

	public int getRandomPipeCreationTime() {
		return randomPipeCreationTime;
	}

	public void setRandomPipeCreationTime(int randomPipeCreationTime) {
		this.randomPipeCreationTime = randomPipeCreationTime;
	}

	public ArrayList<Pipe> getMadePipes() {
		return madePipes;
	}

	public void setMadePipes(ArrayList<Pipe> madePipes) {
		this.madePipes = madePipes;
	}
}
