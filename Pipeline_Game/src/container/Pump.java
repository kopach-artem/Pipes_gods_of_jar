package container;

public class Pump extends Container {
	
	private Pipe input;
	private Pipe output;
	private boolean isDamaged;
	private int randomDamageValue;
	private int maxPipeAmount;
	
	
	public boolean steppable() {
		
		return true;
		
	}
	
	public void addPipe(Pipe pi) {
	}
	
	public void removePipe(Pipe pi) {
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}
	
	public boolean isAllConnected() {
		return false;
	}

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
}
