package container;
import exception.MyException;
import map.Map;
import player.Player;
import player.Type;

import java.io.Serializable;
import java.util.Random;

/**
 * Ez a Pump osztály, ez teszi lehetővé a csövek közötti összeköttetést
 */
public class Pump extends Container implements Serializable {

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
		if(maxPipeAmount > 4){
			System.out.println("Pump can only be connected to 4 or less pipes");
			this.maxPipeAmount = 4;
		} else
			this.maxPipeAmount=maxPipeAmount;
		Random rand=new Random();
		randomDamageValue=rand.nextInt(11) + 10;
		isDamaged=false;
	}


	/**
	 * Ez a függvény valósítja meg a pumpa megjavítását
	 * Megnézzük, hogy a pumpa el van-e romolva amennyiben igen elvégezzük a javítást más esetben kivételt dobunk
	 * @throws MyException
	 */
	public void mendPump() throws MyException {
		if(this.isDamaged){
			this.setDamaged(false);
		} else
			throw new MyException("Wasn't even a scratch on it");
	}

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	@Override
	public void puncturePipe() throws MyException {

	}

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	@Override
	public void insertPump(Player player) throws MyException {

	}

	/**
	 * Hozzácsatlakoztatja a paraméterük kapott Player áttal hordozott csövet a pumpához.
	 * @param player - A játékos
	 * @throws MyException
	 */
	public void insertPipe(Player player, int xCord, int yCord) throws MyException{

		if(!Map.getInstance().getGameMap().isEmpty()){

			ContainerPos cp = new ContainerPos();

			for(ContainerPos containerPos : Map.getInstance().getGameMap()){

				if(containerPos.getContainer().equals(player.getPosition())){
					cp = containerPos;
				}
			}
			if(!isAllConnected() && cp.isOnNeighboringTile(xCord, yCord)){
				this.getNeighbors().add(player.getCarriedPipes().get(0));
				player.getCarriedPipes().get(0).getNeighbors().add(0, this);

				Map.getInstance().getContainers().add(player.getCarriedPipes().get(0));
				Map.getInstance().getGameMap().add(new ContainerPos(player.getCarriedPipes().get(0), xCord, yCord));

				player.getCarriedPipes().remove(player.getCarriedPipes().get(0));
			}
		}
	}

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	@Override
	public void pipeGetsSlippery() {

	}


	/**
	 * @return boolean
	 */
	@Override
	public boolean getIsSlippery() {
		return false;
	}

	@Override
	public void pipeGetsSticky() {
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean getIsSticky() {
		return false;
	}

	/**
	 * Ez a függvény felelős a pumpa outputjának illetve inputjának átváltásáért
	 * A paraméterül kapott csőről megállapítjuk, hogy szomszédos-e ezzel a pumpával amennyiben igen mélyebbre megyünk
	 * Megnézzük, hogy melyiket akarjuk változtatni: a pumpa kimenetét (output) vagy bemenetét (input), ezt a t paraméterrel adjuk meg
	 * Mind a két esetben megnézzük, hogy az egyik már nem másikhoz tartozik azaz, ha inputot akarunk változtatni nem az output-e amire változtatni akrjuk és fordítva
	 * @param player
	 * @param pi
	 * @param t
	 */
	public void alterPump(Player player, Pipe pi, Type t) throws MyException {

		if(this.seeifNeighbors(pi)){
			if(t == Type.Input){
				if(this.getOutput() != pi){
					this.setInput(pi);
				} else
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

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	@Override
	public void mendPipe() throws MyException {

	}


	/**
	 * Elveszi a kívánt csövet a pumpától és a player-hez adja hozzá.
	 * @param player - A játékos
	 * @param pi - Az elvételre kijelölt cső.
	 * @throws MyException
	 */
	public void extractPipe(Player player, int xCord, int yCord) throws MyException {
		ContainerPos cp = new ContainerPos();
		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getPosX() == xCord && containerPos.getPosY() == yCord){
				cp = containerPos;
			}
		}
		if(cp.getContainer().amIGettingDeatched()) {
			if (this.seeifNeighbors(cp.getContainer())) {
				if (cp.getContainer().isLooseEnd()) {
					if(this.getInput() != null){
						if (this.getInput().equals(cp.getContainer())) {
							this.setInput(null);
						}
					}
					if(this.getOutput() != null){
						if(this.getOutput().equals((cp.getContainer()))){
							this.setOutput(null);
						}
					}
					cp.getContainer().getNeighbors().remove(this);
					this.getNeighbors().remove(cp.getContainer());
					player.getCarriedPipes().add(cp.getContainer());
					Map.getInstance().getGameMap().remove(cp);
					Map.getInstance().getContainers().remove(cp.getContainer());
				}
			}
		}
		else
			System.out.println("Got'cha little man! You thought you could detach something other than a Pipe?");
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

	/**
	 * Azt határozza meg a paraméterben kapott Containerről, hogy megegyezik-e az input-jával
	 * @param c
	 * @return
	 */
	public boolean amInput(Container c){

		return this.input.equals(c);
	}

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	@Override
	public void movedFrom() {

	}

	/**
	 * A Pump nem valósítja meg ezt a függvényt, ezért többet nem is írok róla
	 */
	public void eval() {

	}

	/**
	 * A Pump osztályhoz tartozó setInputState
	 * Ez a függvény egyszerűen csak annyit csinál, hogy az output Pipe-jára "továbbítja" a setInputState() függvény hívást
	 * Mivel a Pump nem változtatja az inputStatjét hanem csak símán meghívja kimenetére az ő setInputState-jét ezért a pumpán keresztül egyből végigmegy a víz
	 */
	@Override
	public void setInputState() {
		if(!this.isDamaged)
			output.setInputState();
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String consolePrint() {
		return "PU\t";
	}

	@Override
	public void damageContainer() {
		this.isDamaged = true;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean isLooseEnd() {
		return false;
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
	 * @return boolean
	 */
	@Override
	public boolean amIGettingDeatched() {
		return false;
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

	


	@Override
	public void setBreakOff(int rng) {
		randomDamageValue = rng;
	}

	@Override
	public int queryCistern() {
		return -1;
	}

	@Override
	public int mountainSpringQuery() {
		return -1;
	}
}
