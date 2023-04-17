package container;

import map.Map;
import player.*;
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
			this.setLeaked(true);
		} else
			throw new MyException("It was already damaged");
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
		player.setCarriedPump(null);
		Pump pump2 = (Pump) this.neighbors.get(0);

		/**
		 * Adding pumps to split Pipe
		 */
		split1.addPump(pump2);
		split1.addPump(atPu);

		/**
		 * Removing the Pump that now connects to split1 Pipe but was connected to the base Pipe, and Adding the new Pump
		 */
		this.removePump(pump2);
		this.addPump(atPu);

		/**
		 * Add Pipes to Pumps too
		 */
		atPu.addPipe(split1);
		atPu.addPipe(this);

		pump2.addPipe(split1);
		pump2.removePipe(this);


	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void extractPipe(Player player, Pipe pi) throws MyException {

	}

	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void insertPipe(Player player) throws MyException {

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
	 * Az inputState-hez tartozó kiírást valósítja meg, ez különösebben csak a víz mozgásának "grafikus" szemléltetésére kell
	 * @return
	 */
	public String writeInputState(){

		return "Pipe inputStatjének első illetve második eleme: " + this + ": "+ inputState[0] + ',' + inputState[1];
	}


	/**
	 * A Pipe nem valósítja meg ezt a függvényt, ezért erről nem is beszélek többet
	 */
	@Override
	public void lifeCycle(int turnCount) {

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
				System.out.println("Before: "+ this.writeInputState());
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
		System.out.println("After: "+ this.writeInputState());

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
}
