package container;
import exception.MyException;
import player.Player;
import player.Type;

import java.util.Random;

/**
 * Ez a Pump osztály, ez teszi lehetővé a csövek közötti összeköttetést
 */
public class Pump extends Container {

	/**
	 * Ebben tároljuk, hogy a pumpában melyik cső a bemeneti cső
	 * (azaz melyikből akarjuk átpumpálni a vizet).
	 */
	private Pipe input;

	/**
	 * Ebben tároljuk a kimeneti csövet
	 * (azaz, hogy melyik csőbe akarjuk pumpálni a vizet).
	 */
	private Pipe output;

	/**
	 * Ez az attribútum tárolja, hogy az adott pumpa éppen sérült-e vagy sem.
	 */
	private boolean isDamaged;

	/**
	 * Azt az értéket tartalmazza amely meghatározza, hogy az adott pumpa mely körben fog megsérülni.
	 */
	private int randomDamageValue;

	/**
	 * Megadja, hogy a pumpához hány cső csatlakozhat maximum.
	 */
	private int maxPipeAmount;

	/**
	 * Pump konstruktora
	 * @param maxPipeAmount - Ennyi cső csatlakozthat majd legfeljebb a Pumphoz.
	 */
	public Pump(int maxPipeAmount) {
		this.maxPipeAmount=maxPipeAmount;
		Random rand=new Random();
		randomDamageValue=rand.nextInt(11) + 10;
		isDamaged=false;
	}

	
	/** 
	 * @throws MyException
	 */
	public void mendPump() throws MyException {
		if(this.isDamaged){
			this.setDamaged(false);
		}
		else
			throw new MyException("Wasn't even a scratch on it");
	}

	@Override
	public void puncturePipe() throws MyException {

	}

	@Override
	public void insertPump(Player player) throws MyException {

	}

	/**
	 * Hozzácsatlakoztatja a paraméterük kapott Player áttal hordozott csövet a pumpához.
	 * @param player - A játékos
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

	public void alterPump(Player player, Pipe pi, Type t) throws MyException {

		if(this.seeifNeighbors(pi)){
			if(t == Type.Input){
				if(this.getOutput() != pi){
					this.setInput(pi);
				}
				else
					throw new MyException("Input pipe cannot be changed once water is flowing through it");
			}
			if(t == Type.Output){
				if(this.getInput() != pi)
					this.setOutput(pi);
				else
					throw new MyException("Input pipe cannot be changed to be the output pipe of the pump");
			}
		}
	}

	@Override
	public void mendPipe() throws MyException {

	}


	/**
	 * Elveszi a kívánt csövet a pumpától és a player-hez adja hozzá.
	 * @param player - A játékos
	 * @param pi - Az elvételre kijelölt cső.
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
	 * Mindig true-val tér vissza, ugyanis a Pump-ra akárhány játékos léphet.
	 * @return boolean
	 */
	public boolean steppable() {
		return true;
	}

	
	/**
	 * Elrontja a pumpát a randomDamageValue és a paraméterül kapott érték alapján
	 * @param turnCount - Ha egyenéő a randomDamageValue-val és működik a pumpa, akkor elrontja.
	 */
	public void lifeCycle(int turnCount){
		if(turnCount == this.randomDamageValue && !this.isDamaged){
			System.out.println("Naww this pump got damaged: " + this);
			this.isDamaged = true;
		}
	}

	
	/**
	 * Csatlakoztatja a paraméterül kapott csövet a pumpához.
	 * @param pi
	 */
	public void addPipe(Pipe pi) {

		if(!this.isAllConnected())
			this.neighbors.add(pi);

	}

	
	/**
	 * Leszedi a paraméterül kapott csövet a pumpáról.
	 * @param pi
	 */
	public void removePipe(Pipe pi) {

		if(!this.neighbors.isEmpty())
			this.neighbors.remove(pi);

	}

	public boolean amInput(Container c){

		return this.input.equals(c);
	}

	@Override
	public void movedFrom() {

	}

	public void eval() {

	}

	public void setInputState() {
		if(!this.isDamaged)
			output.setInputState();
	}

	
	/**
	 * Ha teli van a pumpa, vagyis már nem lehet több csövet hozzácstolni, akkor true-val tér vissza.
	 * @return boolean
	 */
	public boolean isAllConnected() {
		if(getMaxPipeAmount()==neighbors.size())
			return true;
		else
			return false;
	}

	
	/**
	 * Vissaztér a randomDamageValue attribútum értékével.
	 * @return int
	 */
	public int getRandomDamageValue() {
		return randomDamageValue;
	}

	/**
	 * Vissaztér a maxPipeAmount attribútum értékével.
	 * @return boolean
	 */
	public int getMaxPipeAmount() {
		return maxPipeAmount;
	}

	/**
	 * Beállítja a maxPipeAmount attribútum értékét.
	 * @param maxPipeAmount
	 */
	public void setMaxPipeAmount(int maxPipeAmount) {
		this.maxPipeAmount = maxPipeAmount;
	}

	/**
	 * Visszatér az isDamaged attribútum értékével.
	 * @return boolean
	 */
	public boolean isDamaged() {
		return isDamaged;
	}

	/**
	 * Beállítja az isDamaged attribútum értékét.
	 * @param isDamaged
	 */
	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	/**
	 * Visszatér az output csővel.
	 * @return Pipe
	 */
	public Pipe getOutput() {
		return output;
	}

	/**
	 * Beállítja az output csövet a paraméterként akpottra
	 * @param output - A beállítani kívánt cső.
	 */
	public void setOutput(Pipe output) {
		this.output = output;
	}

	/**
	 * Visszatér az input csővel.
	 * @return Pipe
	 */
	public Pipe getInput() {
		return input;
	}

	/**
	 * Beállítja az input csövet a paraméterként kapottra.
	 * @param input
	 */
	public void setInput(Pipe input) {
		this.input = input;
	}

	/**
	 * Beállítja az isDamaged attribútum értékét a parméterként kapottra.
	 * @param b
	 */
    public void setisDamaged(boolean b) {
		isDamaged=b;
    }

	/**
	 * Visszatér az isDamaged attribútum értékével.
	 * @return
	 */
	public boolean getisDamaged() {
		return isDamaged;
	}
}
