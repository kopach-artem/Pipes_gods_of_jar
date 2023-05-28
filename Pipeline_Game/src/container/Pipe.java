package container;

import controller.Controller;
import controller.Game;
import map.Map;
import player.*;
import exception.*;

import java.io.Serializable;

/**
 * Ez a cső, ez felelős a víz szállításához, ennek segítségével szállítódhat a víz a ciszternába
 * (amely a szerelők előnyét jelenti) vagy ezeknek kilyukasztásával kerülhetnek előnybe a szabotőrök.
 */
public class Pipe extends Container implements Serializable {

	/**
	 * Ez az attribútum jelzi, hogy az adott cső ki van-e lyukasztva vagy sem.
	 */
	private boolean isLeaked;
	private boolean canBeLeaked = true;
	private int leakedTimer;
	private int randomInterval = 0;

	/**
	 * Ez az attribútum jelzi, hogy valaki éppen az adott csövön közlekedik (tehát foglalt)
	 */
	private boolean isOccupied;

	/**
	 * Ez az attribútum jelzi, hogy a cső az csúszós-e vagy sem
	 */
	private boolean isSlippery;

	private int slipperyTimer;
	private boolean isSticky;
	private int stickyTimer;

	final int STICKY_TIMER = 2;
	final int SLIPPERY_TIMER = 2;


	/**
	 * Megadja, hogy az adott csőre léphet-e játékos
	 * @return boolean - Léphet-e ide játékos vagy sem.
	 */
	public boolean steppable() {

		if(!isOccupied){
			this.setOccupied(true);
			return true;
		} else
			return false;
	}


	/**
	 * Ez a függvény valósítja azt meg, hogy a cső amelyről a játékos ellépet átállítsa azt, hogy a cső már nem foglalt
	 * @return
	 */
	public void movedFrom(){
		setOccupied(false);
	}


	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void alterPump(Player player, Pipe pi, Type t) throws MyException {

	}


	/**
	 * Ez a függvény a cső megjavításáért felelős függvény
	 * Ha a cső ki van lyukasztva akkor javítjuk más esetben kivételt dobunk
	 * @throws MyException
	 */
	public void mendPipe() throws MyException {
		if(this.isLeaked){
			this.setLeaked(false);
		} else
			throw new MyException("It wasn't damaged to begin with");
	}


	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void mendPump() throws MyException {

	}


	/**
	 * Ez a függvény valósítja meg a cső kilyukasztását
	 * Megnézzük, hogy a cső lyukas-e ha nem kilyukasztjuk (beállítjuk az isLeaked attribútumát true-ra)
	 * Ha pedig ki volt már lyukasztva kivételt dobunk
	 */
	public void puncturePipe() throws MyException {
		if(!this.isLeaked){
			if(this.canBeLeaked) {
				this.setLeaked(true);
				this.randomInterval = (int)(Math.random() * 10);
				this.leakedTimer = Game.getInstance().getTurnCount();
				this.canBeLeaked = false;
			} else{
				throw new MyException("Pipe cannot be leaked due to: Pipe leaking is on cooldown");
			}
		} else
			throw new MyException("It was already damaged");
	}


	/**
	 * @return boolean
	 */
	public boolean isVertical(){
		ContainerPos cp = new ContainerPos();
		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getContainer().equals(this)){
				cp = containerPos;
			}
		}

		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getPosY() - 1 >= 0)
				if((containerPos.getPosX() == cp.getPosX()) && (containerPos.getPosY() - 1 == cp.getPosY()) && containerPos.getContainer().seeifNeighbors(cp.getContainer())){
					return true;
				}
		}
		return false;
	}

	/**
	 * A csőhöz csatlakoztatja hozzá a paraméterként kapott játékos által hordozott pumpát.
	 * @param player - A játékos
	 * @throws MyException
	 */
	public void insertPump(Player player) throws MyException{

		if(player.getCarriedPump() != null) {

			ContainerPos cp = new ContainerPos();
			ContainerPos nextContainer = new ContainerPos();

			Pipe newPipe = new Pipe();
			Pump newPump = player.getCarriedPump();

			player.setCarriedPump(null);

			//Iterate through gameMap to find Containers
			for(ContainerPos containerPos : Map.getInstance().getGameMap()) {
				if (containerPos.getContainer().equals(this)) {
					cp = containerPos;
				}
			}
			if(!isVertical()) {
				for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
					if ((containerPos.getPosX() - 1 == cp.getPosX()) && (containerPos.getPosY() == cp.getPosY())) {
						nextContainer = containerPos;
					}
				}

				//If pipe was input
				if (amInput(nextContainer.getContainer())) {
					nextContainer.getContainer().setInput(newPipe);
					newPump.setInput(this);
					newPump.setOutput(newPipe);
				}

				//Shift everything by X = 2
				for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
					if (containerPos.getPosX() > cp.getPosX()) {
						int x = containerPos.getPosX() + 2;
						containerPos.setPosX(x);
					}
				}

				//Adding everything to map
				Map.getInstance().getContainers().add(newPump);
				Map.getInstance().getContainers().add(newPipe);
				Map.getInstance().getGameMap().add(new ContainerPos(newPump, cp.getPosX() + 1, cp.getPosY()));
				Map.getInstance().getGameMap().add(new ContainerPos(newPipe, cp.getPosX() + 2, cp.getPosY()));

				//Remove the next Container from Pipe's neigbors
				this.getNeighbors().remove(nextContainer.getContainer());
				nextContainer.getContainer().getNeighbors().remove(this);

				this.getNeighbors().add(newPump);
				newPump.getNeighbors().add(this);
				newPump.getNeighbors().add(newPipe);
				newPipe.getNeighbors().add(newPump);
				newPipe.getNeighbors().add(nextContainer.getContainer());
			} else{
				for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
					if ((containerPos.getPosX() == cp.getPosX()) && (containerPos.getPosY() - 1 == cp.getPosY())) {
						nextContainer = containerPos;
					}
				}

				//If pipe was input
				if (amInput(nextContainer.getContainer())) {
					nextContainer.getContainer().setInput(newPipe);
					newPump.setInput(this);
					newPump.setOutput(newPipe);
				}

				//Shift everything by X = 2
				for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
					if (containerPos.getPosY() > cp.getPosY()) {
						int y = containerPos.getPosY() + 2;
						containerPos.setPosY(y);
					}
				}

				//Adding everything to map
				Map.getInstance().getContainers().add(newPump);
				Map.getInstance().getContainers().add(newPipe);
				Map.getInstance().getGameMap().add(new ContainerPos(newPump, cp.getPosX(), cp.getPosY() + 1));
				Map.getInstance().getGameMap().add(new ContainerPos(newPipe, cp.getPosX(), cp.getPosY() + 2));

				//Remove the next Container from Pipe's neigbors
				this.getNeighbors().remove(nextContainer.getContainer());
				nextContainer.getContainer().getNeighbors().remove(this);

				this.getNeighbors().add(newPump);
				newPump.getNeighbors().add(this);
				newPump.getNeighbors().add(newPipe);
				newPipe.getNeighbors().add(newPump);
				newPipe.getNeighbors().add(nextContainer.getContainer());
			}
		} else
			System.out.println("Player has no pumps at his disposal");
	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void extractPipe(Player player, int xCord, int yCord) throws MyException {

	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void insertPipe(Player player, int xCord, int yCord) throws MyException {

	}

	/**
	 * Az adott cső csúszóssá válik
	 */
	@Override
	public void pipeGetsSlippery() {
		this.slipperyTimer = Game.getInstance().getTurnCount();
		if(this.isSticky){
			this.isSticky = false;
		}
		this.isSlippery = true;
	}


	/**
	 * @return boolean
	 */
	@Override
	public boolean getIsSlippery() {
		return this.isSlippery;
	}

	@Override
	public void pipeGetsSticky() {
		this.stickyTimer = Game.getInstance().getTurnCount();
		if(this.isSlippery){
			this.isSlippery = false;
		}
		this.isSticky = true;
	}


	/**
	 * @return boolean
	 */
	@Override
	public boolean getIsSticky() {
		return isSticky;
	}


	/**
	 * @param b
	 */
	public void setSticky(boolean b){
		this.isSticky = b;
	}

	/**
	 * A csőhöz csatlakoztatott pumpák listájához újabb pumpa hozzáadását megvalósító függvény
	 * @param pu - Ezt a pumpát akarjuk hozzáadni
	 * @param index - Erre a helyre akarjuk berakni a pumpát
	 * @throws MyException
	 */
	public void addPump(Pump pu, int index) throws MyException {

		if(index > 1 || index < 0){
			throw new MyException("Invalid index");
		}

		if(!(this.neighbors.size() == 2))
			this.neighbors.add(index, pu);
		else
			throw new MyException("Add pump failed");
	}

	/**
	 * A csőhöz csatlakoztatott pumpák listájához újabb pumpa hozzáadását megvalósító függvény
	 * @param pu - Ezt a pumpát akarjuk hozzáadni
	 * @throws MyException
	 */
	public void addPump(Pump pu) throws MyException {

		if(!(this.neighbors.size() == 2))
			this.neighbors.add(pu);
		else
			throw new MyException("Add pump failed");
	}

	/**
	 * A csőhöz csatlakoztatott pumpák listájából kitöröl egy megadott pumpát
	 * @param index - A törölni kívánt pumpa indexe
	 * @throws MyException
	 */
	public void removePump(int index) throws MyException {

		if(index > 1 || index < 0){
			throw new MyException("Invalid index");
		}

		if(!(this.neighbors.isEmpty()))
			this.neighbors.remove(index);
		else
			throw new MyException("Remove Pump failed");
	}

	/**
	 * A csőhöz csatlakoztatott pumpák listájából kitöröl egy megadott pumpát
	 * @param pu - A törlésre szánt pumpa
	 * @throws MyException
	 */
	public void removePump(Pump pu) throws MyException {

		if(!(this.neighbors.isEmpty()))
			this.neighbors.remove(pu);
		else
			throw new MyException("Remove Pump failed");
	}


	/**
	 * @param pipe
	 */
	@Override
	public void setInput(Pipe pipe) {

	}

	@Override
	public boolean amIGettingDeatched() {
		return true;
	}

	/**
	 * Az inputState-hez tartozó kiírást valósítja meg, ez különösebben csak a víz mozgásának "grafikus" szemléltetésére kell
	 * @return
	 */
	public String writeInputState(){

		return "Pipe inputStatjének első illetve második eleme: " + this + ": "+ inputState[0] + ',' + inputState[1];
	}


	/**
	 * A Pipe csúszós (isSlippery) állapotát változtatja meg egy adott idő után
	 * @param turnCount
	 */
	@Override
	public void lifeCycle(int turnCount) {

		if(!this.canBeLeaked){
			if((this.leakedTimer + this.randomInterval) == turnCount){
				this.canBeLeaked = true;
				this.leakedTimer = 0;
			}
		}
		if(this.isSticky){
			if(this.stickyTimer + STICKY_TIMER == turnCount){
				this.isSticky = false;
				this.stickyTimer = 0;
			}
		}
		if(this.isSlippery) {
			if (this.slipperyTimer + SLIPPERY_TIMER == turnCount) {
				this.isSlippery = false;
				this.slipperyTimer = 0;
			}
		}

	}


	/**
	 * Ez a függvény a gerince a víz mozgatásának
	 * Ez a függvény valósítja meg a víz mozgásának csőnél való kiértékelését
	 * Nézzük végig:
	 * 		Az első amit megnézünk, hogy az inputState[0] értéke igaz-e, azaz, hogy az előző körben volt-e benne víz, ha nem akkor nem is foglalkozunk ezzel tovább
	 * 		Ha volt akkor vizsgáljuk meg a többi esetet, mint például szabadvégű-e a cső (isLooseEnd()), ha igen akkor növeljük a kifolyt víz mennyiségét
	 * 		Ezután megkeressük a cső szomszédosságában azt a Container-t akinek-ő az inputja -> a két elágazás közötti rész
	 * 		Ezt követően egy újabb elágazáshoz érkezünk, ahol is azt nézzük meg, hogy:
	 *
	 * 										1. Ki van-e lyukasztva? - ha igen akkor növeljük a kifolyt víz mennyiségét, máskülönben megyünk mélyebbre(ha a másik feltétel is teljesül)
	 * 										2. Van-e outputja? - azaz, hogy létezik-e olyan Container akinek ő lenne az inputja
	 * 															->ha van ilyen akkor erre meghívjuk a setInputState-et
	 * 															->ha nincs ilyen akkor nem csinálunk semmit és visszatérünk
	 *
	 *
	 */
	public void eval() {

		if(inputState[0]) {

			if(this.isLooseEnd()){
				Map.increaseLeakedWater();
				return;
			}

			Container output = null;
			for(Container c : this.neighbors){
				if(c.amInput(this)){
					output = c;
					break;
				}
			}

			if (!isLeaked && (output != null)) {
				output.setInputState();
			} else if(isLeaked)
				Map.increaseLeakedWater();

		}
	}


	/**
	 * Előzőleg hazudtam igazából ez a gerince az egésznek mivel is itt történik meg ténylegesen az inputState módosítása
	 * Amennyiben ez a függvény meghívódik az inputState[1] átállítjuk true (igaz) értékre (azaz, víz folyt/folyik bele/benne)
	 */
	public void setInputState(){

		inputState[1] = true;

	}

	@Override
	public String consolePrint() {
		return "PI\t";
	}

	@Override
	public void damageContainer() {
		this.isLeaked = true;
	}

	/**
	 * Visszatér azzal, hogy a cső egyik vége üresen lóg-e
	 *
	 * @return boolean
	 */
	public boolean isLooseEnd() {

		if(getNeighbors().size()==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isDamaged() {
		return isLeaked;
	}

	@Override
	public Container getInput() {
		return null;
	}

	@Override
	public Container getOutput() {
		return null;
	}

	/**
	 * Vissaztér az isLeaked attribútum értékével
	 * @return boolean
	 */
	public boolean isLeaked() {
		return isLeaked;
	}

	/**
	 * Beállítja az isLeaked attribútum értékét
	 * @param isLeaked - A beállítsára szánt érték
	 */
	public void setLeaked(boolean isLeaked) {
		this.isLeaked = isLeaked;
	}

	/**
	 * Visszatér az isOccupied attribútum értékével
	 * @return boolean
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Beállítja az isOccupied attribútum értékét
	 * @param isOccupied
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getRandomInterval(){
		return randomInterval;
	}


	@Override
	public void setBreakOff(int rng) {

	}

	@Override
	public int queryCistern() {
		return -1;
	}

	@Override
	public int mountainSpringQuery() {
		return -1;
	}

	@Override
	public String myIconPath() {
		if(isSlippery){
			return "file:resources/container_components/slipperypipe.png";
		} else if(isLeaked){
			return "file:resources/container_components/pipeturn2dmg.png";
		} else if(isSticky){
			return "file:resources/container_components/stickypipe.png";
		} else{
			if(isVertical()){
				return "file:resources/container_components/pipeud.png";
			} else {
				return "file:resources/container_components/pipelr.png";
			}
		}
	}
}
