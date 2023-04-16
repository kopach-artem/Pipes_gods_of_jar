package container;

import player.Player;
import exception.*;

/**
 * Ez a cső, ez felelős a víz szállításához, ennek segítségével szállítódhat a víz a ciszternába
 * (amely a szerelők előnyét jelenti) vagy ezeknek kilyukasztásával kerülhetnek előnybe a szabotőrök.
 */
public class Pipe extends Container {

	/**
	 * Ez az attribútum jelzi, hogy az adott cső ki van-e lyukasztva vagy sem.
	 */
	private boolean isLeaked;

	/**
	 * Ez az attribútum jelzi, hogy valaki éppen az adott csövön közlekedik (tehát foglalt)
	 */
	private boolean isOccupied;

	/**
	 * Ez az attribútum jelzi, hogy az adott csövön víz folyik át.
	 */
	private boolean waterFlowing;

	
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
	 * A csőhöz csatlakoztatja hozzá a paraméterként kapott játékos által hordozott pumpát.
	 * @param player - A játékos
	 * @throws MyException
	 */
	public void insertPump(Player player) throws MyException{

		/**
		 * Create new Pipe for the attachement.
		 */
		Pipe split1 = new Pipe();

		/**
		 * Initialize pumps
		 */
		Pump atPu = player.getCarriedPump();
		Container pump2 = this.neighbors.get(1);

		/**
		 * Adding pumps to split Pipe
		 */
		split1.addPump((Pump) pump2, 1);
		split1.addPump(atPu, 0);

		/**
		 * Removing the Pump that now connects to split1 Pipe but was connected to the base Pipe, and Adding the new Pump
		 */
		this.removePump(1);
		this.addPump(atPu, 1);

		/**
		 * Add Pipes to Pumps too
		 */
		atPu.addPipe(split1);
		atPu.addPipe(this);
		((Pump) pump2).addPipe(split1);
		((Pump) pump2).removePipe(this);

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

	public void eval() {
	}

	public void setInputState() {
	}

	/**
	 * Visszatér azzal, hogy a cső egyik vége üresen lóg-e
	 * @return boolean
	 */
	public boolean isLooseEnd() {

		if(getNeighbors().size()==1)
			return true;
		else
			return false;
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

	/**
	 * Visszatér az waterFlowing attribútum értékével
	 * @return boolean
	 */
	public boolean isWaterFlowing() {
		return waterFlowing;
	}

	/**
	 * Beáééítja a waterFlowing attribútum értékét
	 * @param waterFlowing
	 */
	public void setWaterFlowing(boolean waterFlowing) {
		this.waterFlowing = waterFlowing;
	}

	public boolean getWaterFlowing() {
		return false;
	}

	/**
	 * Beállítja az isLeakeda ttribútum értékét
	 * @param b
	 */
	public void setisLeaked(boolean b) {
		isLeaked=b;
	}
}
