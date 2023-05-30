package container;

import controller.Game;
import map.Map;
import menu.MyAlert;
import player.*;

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

	final int STICKY_TIMER = 3;
	final int SLIPPERY_TIMER = 3;


	/**
	 * Megadja, hogy az adott csőre léphet-e játékos
	 * @return boolean - Léphet-e ide játékos vagy sem.
	 */
	public boolean steppable() {

		if(!isOccupied){
			return true;
		} else
			return false;
	}


	/**
	 * Ez a függvény valósítja azt meg, hogy a cső amelyről a játékos ellépet átállítsa azt, hogy a cső már nem foglalt
	 * @return
	 */
	public void movedFrom() {
		setOccupied(false);
	}


	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void alterPump(int x, int y, Type t) {

	}


	/**
	 * Ez a függvény a cső megjavításáért felelős függvény
	 * Ha a cső ki van lyukasztva akkor javítjuk más esetben kivételt dobunk
	 */
	public void mendPipe() {
		if(this.isLeaked){
			this.setLeaked(false);
		} else
			MyAlert.showInvalidMoveAlert("It wasn't damaged to begin with");
	}


	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void mendPump() {

	}


	/**
	 * Ez a függvény valósítja meg a cső kilyukasztását
	 * Megnézzük, hogy a cső lyukas-e ha nem kilyukasztjuk (beállítjuk az isLeaked attribútumát true-ra)
	 * Ha pedig ki volt már lyukasztva kivételt dobunk
	 */
	public void puncturePipe() {
		if(!this.isLeaked){
			if(this.canBeLeaked) {
				this.setLeaked(true);
				this.randomInterval = (int)(Math.random() * 10);
				this.leakedTimer = Game.getInstance().getTurnCount();
				this.canBeLeaked = false;
			} else{
				MyAlert.showInvalidMoveAlert("Pipe cannot be leaked due to: Pipe leaking is on cooldown");
			}
		} else
			MyAlert.showInvalidMoveAlert("It was already damaged");
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
	 */
	public void insertPump(Player player) {

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
				if(!isLooseEnd()) {
					for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
						if ((containerPos.getPosX() == cp.getPosX() + 1) && (containerPos.getPosY() == cp.getPosY())) {
							nextContainer = containerPos;
						}
					}

					//If pipe was input
					if (nextContainer.getContainer().amInput(this)) {
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

					Map.addAllNeighbors();
				} else {
					if (cp.getPosX() - 2 >= 0) {
						for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
							if (containerPos.getContainer().seeifNeighbors(cp.getContainer())) {
								nextContainer = containerPos;
							}
						}

						boolean left = false;
						boolean right = false;

						if(nextContainer.getPosX() == cp.getPosX() - 1){
							left = true;
						} else{
							right = true;
						}

						//If pipe was input
						if (nextContainer.getContainer().amInput(this)) {
							nextContainer.getContainer().setInput(newPipe);
							newPump.setInput(this);
							newPump.setOutput(newPipe);
						}

						//Adding everything to map
						Map.getInstance().getContainers().add(newPump);
						Map.getInstance().getContainers().add(newPipe);

						if(right){
							Map.getInstance().getGameMap().add(new ContainerPos(newPump, cp.getPosX() - 1, cp.getPosY()));
							Map.getInstance().getGameMap().add(new ContainerPos(newPipe, cp.getPosX() - 2, cp.getPosY()));
						}
						if(left){
							for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
								if (containerPos.getPosX() > cp.getPosX()) {
									int x = containerPos.getPosX() + 2;
									containerPos.setPosX(x);
								}
							}

							Map.getInstance().getGameMap().add(new ContainerPos(newPump, cp.getPosX() + 1, cp.getPosY()));
							Map.getInstance().getGameMap().add(new ContainerPos(newPipe, cp.getPosX() + 2, cp.getPosY()));
						}


						//Remove the next Container from Pipe's neigbors
						this.getNeighbors().remove(nextContainer.getContainer());
						nextContainer.getContainer().getNeighbors().remove(this);

						Map.addAllNeighbors();
					} else {
						MyAlert.showInvalidMoveAlert("Can't expand more towards that direction");
					}
				}
			} else{
				for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
					if ((containerPos.getPosX() == cp.getPosX()) && (containerPos.getPosY() - 1 == cp.getPosY())) {
						nextContainer = containerPos;
					}
				}

				//If pipe was input
				if (nextContainer.getContainer().amInput(this)) {
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

				newPipe.setStateofInput(this.inputState[0], this.inputState[1]);

				//Adding everything to map
				Map.getInstance().getContainers().add(newPump);
				Map.getInstance().getContainers().add(newPipe);
				Map.getInstance().getGameMap().add(new ContainerPos(newPump, cp.getPosX(), cp.getPosY() + 1));
				Map.getInstance().getGameMap().add(new ContainerPos(newPipe, cp.getPosX(), cp.getPosY() + 2));

				//Remove the next Container from Pipe's neigbors
				this.getNeighbors().remove(nextContainer.getContainer());
				nextContainer.getContainer().getNeighbors().remove(this);

				Map.addAllNeighbors();
			}
		} else
			MyAlert.showInvalidMoveAlert("Player has no pumps at his disposal");
	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void extractPipe(Player player, int xCord, int yCord) {

	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void insertPipe(Player player, int xCord, int yCord) {

		MyAlert.showInvalidMoveAlert("Bruh, it's not possible");

	}

	/**
	 * Az adott cső csúszóssá válik
	 */
	@Override
	public void pipeGetsSlippery() {
		this.slipperyTimer = Game.getInstance().getTurnCount() + SLIPPERY_TIMER;
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
		this.stickyTimer = Game.getInstance().getTurnCount() + STICKY_TIMER;
		if(this.isSlippery){
			this.isSlippery = false;
		}
		this.isSticky = true;
	}

	@Override
	public void takePipeFromCs(Player player) {

	}

	@Override
	public void takePumpFromCs(Player player) {

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

	@Override
	public void getsOccupied() {
		isOccupied = true;
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

	@Override
	public int hasPipes() {
		return -1;
	}

	@Override
	public boolean hasPump() {
		return false;
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

	public void setStateofInput(boolean first, boolean second){
		inputState[0] = first;
		inputState[1] = second;
	}

	@Override
	public String myIconPath() {
		String basePath = "file:resources/container_components/";
		String orientation = getOrientation();
		String leakStatus = isLeaked ? "_Damaged" : "";
		String waterStatus = inputState[0] || inputState[1] ? "_Water" : "";
		String stickyStatus = isSticky ? "_Sticky" : "";
		String slipperyStatus = isSlippery ? "_Slippery" : "";

		if(leakStatus.equals("") && waterStatus.equals("") && stickyStatus.equals("") && slipperyStatus.equals("")){
			return basePath + orientation + ".png";
		} else
			return basePath + orientation + leakStatus + waterStatus + stickyStatus + slipperyStatus + ".png";

	}
	public String getOrientation()
	{
		//Get the current position of the element
		ContainerPos cp = new ContainerPos();
		for(ContainerPos containerPos : Map.getInstance().getGameMap()){
			if(containerPos.getContainer().equals(this)){
				cp = containerPos;
			}
		}

		int maxX = -1;
		int maxY = -1;

		// Find the maximum x and y values
		for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
			if (containerPos.getPosX() > maxX) {
				maxX = containerPos.getPosX();
			}
			if (containerPos.getPosY() > maxY) {
				maxY = containerPos.getPosY();
			}
		}

		Container[][] grid = new Container[maxX+1][maxY+1];

		// Place the containers in the grid
		for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
			int x = containerPos.getPosX();
			int y = containerPos.getPosY();
			grid[x][y] = containerPos.getContainer();
		}

		for(ContainerPos containerPos : Map.getInstance().getGameMap())
		{
			int x = cp.getPosX();
			int y = cp.getPosY();
			if(grid[x][y]==containerPos.getContainer())
			{
				if (x > 0 && containerPos.getContainer().seeifNeighbors(grid[x - 1][y]))
				{
					// DownToLeft
					if (y < grid[x].length - 1 && containerPos.getContainer().seeifNeighbors(grid[x][y + 1]))
						return "PipeDownToLeft";

					// UpToLeft
					if (y > 0 && containerPos.getContainer().seeifNeighbors(grid[x][y - 1]))
						return "PipeUpToLeft";

					// Vertical
					if (x < grid.length - 1 && containerPos.getContainer().seeifNeighbors(grid[x + 1][y]))
						return "PipeLeftRight_UpSide";

					return "PipeLeftRight_UpSide";
				}

				if (x + 1 < grid.length && containerPos.getContainer().seeifNeighbors(grid[x + 1][y]))
				{

					// DownToRight
					if (y + 1 < grid[x].length && containerPos.getContainer().seeifNeighbors(grid[x][y + 1]))
						return "PipeDownToRight";

					// UpToRight
					if (y - 1 >= 0 && containerPos.getContainer().seeifNeighbors(grid[x][y - 1]))
						return "PipeUpToRight";

					// Vertical
					if (x - 1 >= 0 && containerPos.getContainer().seeifNeighbors(grid[x - 1][y]))
						return "PipeLeftRight_UpSide";

					return "PipeLeftRight_UpSide";
				}

				if (y + 1 < grid[x].length && y - 1 >= 0 && containerPos.getContainer().seeifNeighbors(grid[x][y + 1]) && containerPos.getContainer().seeifNeighbors(grid[x][y - 1])) {
					return "PipeUpDown_RightSide";
				}
				return "";
			}
		}
		return "";
	}

}
