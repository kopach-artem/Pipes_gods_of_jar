package skeleton;

import container.*;
import controller.Controller;
import exception.MyException;
import player.Mechanic;
import player.Saboteur;
import player.Type;

import container.Pipe;
import container.Pump;
import player.Player;
import player.Type;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A DispatcherSkeleton osztály segítségével végezhető a skeleton program tesztelése.
 * Ebben az osztályban található a main metódus, amely a standard inputra vár egész számokat,
 * melyeket beolvasva lefuttatja a kiválasztott szekvenciát
 */
public class DispatcherSkeleton {
    public static void main(String[] args) throws MyException {
        System.out.println("Üdvözli önt a Gods of jar csapat skeleton programja!");
        System.out.println("Válassza ki a kívánt szekvenciát!");
        System.out.println("1. Mehcanic repairs pump");
        System.out.println("2. Mechanic repairs pipe");
        System.out.println("3. Saboteur leaks pipe");
        System.out.println("4. Player attach pipe (Successful)");
        System.out.println("5. Player attach pipe (Fail)");
        System.out.println("6. Player attaches pump");
        System.out.println("7. Player detach pipe");
        System.out.println("8. Player adjust pump Input");
        System.out.println("9. Player adjust pump Output");
        System.out.println("10. Player moves to pipe successfully");
        System.out.println("11. Player moves to pipe fail");
        System.out.println("12. Player moves to pump");
        System.out.println("13. Player moves to cistern");
        System.out.println("14. Player moves to mountain spring");
        System.out.println("15. Random pump breaking");
        System.out.println("16. Collecting water in cistern");
        System.out.println("0. Kilépés");

        Scanner scanner = new Scanner(System.in);
        Pump c = new Pump(2);
        Mechanic m = new Mechanic(c);
        Saboteur s = new Saboteur(c);
        Pump pu = new Pump(2);
        Pipe pi = new Pipe();
        ArrayList<Pipe> carriedPipe=new ArrayList<>();
        carriedPipe.add(new Pipe());
        int n;
        /**
         * A főciklus, itt várjuk az egész számokat.
         * Ha 0-t kap a bementre a program, akkor kilép.
         */
        while((n = scanner.nextInt()) != 0) {
            switch(n){
                /**
                 * Mechanic repairs pump szekvencia
                 */
                case 1:
                    System.out.println("Mechanic repairs pump has started");
                    pu.setisDamaged(true);
                    System.out.print("Pump isDamaged before repair: ");
                    System.out.println(pu.getisDamaged());
                    System.out.println("RepairPump is called");
                    m.RepairPump(pu);
                    System.out.println("RepairPump has returned");
                    if (pu.getisDamaged() == false) {
                        System.out.println("Pump repair was succesful :)");
                    } else {
                        System.out.println("Pump repair failed :(");
                    }
                    System.out.print("Pump isDamaged after repair: ");
                    System.out.println(pu.getisDamaged());
                    System.out.println("Mechanic repairs pump has finished");
                    break;

                /**
                 * Mechanic repairs pipe szekvencia
                 */
                case 2:
                    System.out.println("Mechanic repairs pipe has started");
                    pi.setisLeaked(true);
                    System.out.print("Pipe isLeaked before repair: ");
                    System.out.println(pi.isLeaked());
                    System.out.println("RepairPipe is called");
                    m.RepairPipe(pi);
                    System.out.println("RepairPipe has returned");
                    if (pi.isLeaked() == false) {
                        System.out.println("Pipe repair was succesful :)");
                    } else {
                        System.out.println("Pipe repair failed :(");
                    }
                    System.out.print("Pipe isLeaked after repair: ");
                    System.out.println(pi.isLeaked());
                    System.out.println("Mechanic repairs pump has finished");
                    break;

                /**
                 * Saboteur leaks pipe szekvencia
                 */
                case 3:
                    System.out.println("Saboteur leaks pipe has started");
                    pi.setisLeaked(false);
                    System.out.print("Pipe isleaked before sabotage: ");
                    System.out.println(pi.isLeaked());
                    System.out.println("LeakPipe is called");
                    s.LeakPipe(pi);
                    System.out.println("LeakPipe has returned");
                    if (pi.isLeaked() == true) {
                        System.out.println("Pipe sabotage was succesful :)");
                    } else {
                        System.out.println("Pipe sabotage failed :(");
                    }
                    System.out.print("Pipe isleaked after sabotage: ");
                    System.out.println(pi.isLeaked());
                    System.out.println("Saboteur leaks pipe has finished");
                    break;

                /**
                 * Player attach pipe successful szekvencia
                 */
                case 4:
                    System.out.println("Player attach pipe successful has started");
                    System.out.println("attachPipe is called");
                    m.setCarriedPipes(carriedPipe);
                    m.attachPipe();
                    System.out.println("attachPipe has returned");
                    if (m.getCarriedPipes().isEmpty()) {
                        System.out.println("Player attach pipe successful was successful :)");
                    } else {
                        System.out.println("Player attach pipe successful has failed :(");
                    }
                    System.out.println("Player attach pipe successful has finished");
                    break;

                /**
                 * Player attach pipe Fail szekvencia
                 */
                case 5:
                    System.out.println("Player attach pipe fail has started");
                    System.out.println("attachPipe is called");
                    m.attachPipe();
                    System.out.println("attachPipe has returned");
                    if (!m.getCarriedPipes().isEmpty()) {
                        System.out.println("Player attach pipe fail was successful :)");
                    } else {
                        System.out.println("Player attach pipe fail has failed :(");
                    }
                    System.out.println("Player attach pipe fail has finished");
                    break;

                /**
                 * Player attaches pump szekvencia
                 */
                case 6:
                    System.out.println("Player attaches pump has started");
                    Pump pumpToAttach = new Pump(1);
                    m.setCarriedPump(pumpToAttach);
                    System.out.println("attachPump is called");
                    m.attachPump();
                    System.out.println("attachPump has returned");
                    if (m.getCarriedPump() == null) {
                        System.out.println("Player attached pump successfully :)");
                    } else {
                        System.out.println("Player failed to attach pump :(");
                    }
                    System.out.println("Player attaches pump has finished");
                    break;

                /**
                 * Player detach pipe szekvencia
                 */
                case 7:
                    System.out.println("Player detach pipe has started");
                    System.out.println("detachPipe is called");
                    m.detachPipe(pi);
                    System.out.println("detachPipe has returned");
                    if (m.getCarriedPipes().contains(pi)) {
                        System.out.println("Player has detached pipe successfully :)");
                    } else {
                        System.out.println("Player failed to take pipe :(");
                    }
                    System.out.println("Player detach pipe has finished");
                    break;

                /**
                 * Player adjust pump Input szekvencia
                 */
                case 8:
                    System.out.println("Player adjust pump Input has started");
                    System.out.println("adjustPump is called");
                    m.adjustPump(pu, pi, new Type());
                    System.out.println("adjustPump has returned");
                    if (pu.getInput().equals(pi)) {
                        System.out.println("Input adjustment successful :)");
                    } else {
                        System.out.println("Input adjustment failed :(");
                    }
                    System.out.println("Player adjust pump Input has finished");
                    break;

                /**
                 * Player adjust pump Output szekvencia
                 */
                case 9:
                    System.out.println("AdjustPump has started");
                    Player p = new Player(c);
                    Pipe pipe = new Pipe();
                    Type t = new Type();
                    Pump pum = new Pump(2);
                    p.adjustPump(pu, pipe, t);
                    System.out.println("AdjustPump has finished");
                    break;

                /**
                 * Player moves to Pipe successfully szekvencia
                 */
                case 10:
                    System.out.println("PlayerMovesOnPipeSuc has started");
                    Player pl = new Player(c);
                    Pipe pipe1 = new Pipe();
                    Pipe pipe2 = new Pipe();
                    pipe1.seeifNeighbors(pipe2); //true
                    pl.setPosition(pipe1);
                    System.out.println("PlayerMovesOnPipeSuc has finished");
                    break;

                /**
                 * Player moves to Pipe failed szekvencia
                 */
                case 11:
                    System.out.println("PlayerMovesOnPipeFail has started");
                    Pipe pip1 = new Pipe();
                    Pipe pip2 = new Pipe();
                    Player p1 = new Player(pip1);
                    ArrayList<Container> neighb = new ArrayList<Container>();
                    neighb.add(pip2);
                    pip1.setNeighbors(neighb);
                    pip2.setOccupied(true);
                    System.out.println("Move is called");
                    try {
                        p1.Move(pip2);
                    } catch (MyException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Move has returned");
                    if (!p1.getPosition().equals(pip2)) {
                        System.out.println("Test was successful, move failed :)");
                    } else {
                        System.out.println("Test failed, move was successful :(");
                    }
                    System.out.println("PlayerMovesOnPipeFail has finished");
                    break;

                /**
                 * Player moves to Pump szekvencia
                 */
                case 12:
                    System.out.println("Player moves to pump has started");
                    Player pla = new Player(c);
                    Pump pu1 = new Pump(2);
                    pu1.seeifNeighbors(c); //true
                    pla.setPosition(pu1);
                    System.out.println("Player moves to pump has finished");
                    break;

                /**
                 * Player moves to Cistern szekvencia
                 */
                case 13:
                    System.out.println("Player moves to cistern has started");
                    Player pla1 = new Player(c);
                    Pipe co2 = new Pipe();
                    Cistern cis = new Cistern(new Pipe(), new Pump(2), 1000);
                    cis.seeifNeighbors(co2); //true
                    pla1.setPosition(cis);
                    System.out.println("Player moves to cistern has finished");
                    break;

                /**
                 * Player moves to MountainSpring szekvencia
                 */
                case 14:
                    System.out.println("Player moves to MountainSpring has started");
                    Player pla2 = new Player(c);
                    MountainSpring mo = new MountainSpring();
                    Pump pu3 = new Pump(2);
                    try {
                        pla2.Move(mo);
                    } catch (MyException e) {
                        throw new RuntimeException(e);
                    }
                    mo.steppable(); //false
                    System.out.println("Player moves to MountainSpring has finished");
                    break;

                /**
                 * Evalutaion szekvencia
                 */
                case 15:
                    System.out.println("Evaluation has started");
                    Pump pump1 = new Pump(2);
                    pump1.setisDamaged(true);
                    System.out.println("Evaluation has started");
                    break;

                /**
                 * CollectingWater szekvencia
                 */
                case 16:
                    System.out.println("CollectingWater has started");
                    Controller cont = new Controller();
                    Pipe input = new Pipe();
                    Pipe output = new Pipe();
                    Pump pu4 = new Pump(2);
                    Cistern ci = new Cistern(input, pu4, 1000);
                    cont.waterFlow(input, pu4, output);
                    input.eval();
                    ci.setInputState();
                    ci.eval();
                    input.makeHistory();
                    ci.makeHistory();
                    System.out.println("CollectingWater has finished");
                    break;

                default:
                    break;
            }

        }
    }
}
