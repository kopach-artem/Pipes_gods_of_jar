package container;

/**
 * Ez a hegyi forrás, ebből származik a víz, amelyért a játékosok versengenek
 */
public class MountainSpring extends Container {

	/**
	 * Tárolja azt a csövet, amely kivezeti a vizet a forrásból.
	 */
	private Pipe output;

	/**
	 * A hegyi forrásvíz kapacitása, azaz mennyi vizet képes tárolni a hegyi forrás
	 */
	private int waterCapac;

	/**
	 * Ennek a metódusnak a segítségével kezd a forrásból kiszivárogni a víz.
	 */
	public void flowStart() {
	}

	
	/**
	 * Mindig false-al tér vissza, ugyanis a MountainSpring-re nem lépheetnek rá a játékosok.
	 * @return boolean
	 */
	public boolean steppable(){
		return false;
	}

	/**
	 * A hegyi forrásban lévő víz mennyiségét csökkenti
	 */
	public void decreaseWaterAm() {
	}
	
	public void eval() {
	}
	
	public void setInputState() {
	}

	
	/**
	 * Visszatér a waterCapac attribűtum értékével
	 * @return int - A waterCapac attribútum értéke
	 */
	public int getWaterCapac() {
		return waterCapac;
	}

	/**
	 * Beállítja a waterCapac attribútum értékét a paraméterként kapottra
	 * @param waterCapac - A beállítani kívánt érték
	 */
	public void setWaterCapac(int waterCapac) {
		this.waterCapac = waterCapac;
	}
}
