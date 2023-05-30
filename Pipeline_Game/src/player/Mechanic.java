package player;

import container.*;
import exception.MyException;
import javafx.scene.control.ChoiceBox;

import java.io.Serializable;

/**
 * Ez a szerelő játékos, a szerelő javíthatja meg a csöveket, pumpákat és állíthatja is azokat.
 */
public class Mechanic extends Player implements Serializable {

	/**
	 * Mechanic konmstruktora
	 * @param position - Ebben a pozícióban lesz létrehozva a Mechanic
	 */
	public Mechanic(Container position) {
		super(position);
	}
	public Mechanic() {
	}

	
	/**
	 * Megjavítja a pozíciónál lévő csövet
	 */
	public void RepairPipe(){
		this.getPosition().mendPipe();
	}
	
	/**
	 * Megjavítja a pozíciójánál lévő pumpát
	 */
	public void RepairPump(){
		this.getPosition().mendPump();
	}

	public String consolePrint(){ return "ME" + id;}

	public ChoiceBox<String> getChoiceBox(){
		ChoiceBox<String> choiceBox = new ChoiceBox<>();

		choiceBox.getItems().addAll("Repair Pipe", "Repair Pump");

		return choiceBox;
	}
}
