package skeleton;

import container.*;
import controller.Controller;
import exception.MyException;

import player.*;
import map.*;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * A DispatcherSkeleton osztály segítségével végezhető a skeleton program tesztelése.
 * Ebben az osztályban található a main metódus, amely a standard inputra vár egész számokat,
 * melyeket beolvasva lefuttatja a kiválasztott szekvenciát
 */
public class DispatcherSkeleton {

    public DispatcherSkeleton() throws MyException {
    }

    public static Map initalizeTable() throws MyException {

        Map map = new Map();
        Pump pos = new Pump(4);

        pos.setDamaged(true);

        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();
        Pipe p3 = new Pipe();
        Pipe detachable = new Pipe();
        Pipe outputChan = new Pipe();

        Cistern cs = new Cistern(detachable);
        detachable.getNeighbors().add(cs);
        cs.getNeighbors().add(detachable);

        Pump pu1 = new Pump(2);
        Pump pu2 = new Pump(3);
        Pump pu3 = new Pump(5);

        map.getContainers().add(pos);
        map.getContainers().add(p1);
        map.getContainers().add(p2);
        map.getContainers().add(p3);
        map.getContainers().add(pu1);
        map.getContainers().add(pu2);
        map.getContainers().add(pu3);
        map.getContainers().add(detachable);
        map.getContainers().add(outputChan);

        map.connectPumpToPipe(pos, p1);
        map.connectPumpToPipe(pu1, p1);
        map.connectPumpToPipe(pu1, p2);
        map.connectPumpToPipe(pu2, p2);
        map.connectPumpToPipe(pu2, p3);
        map.connectPumpToPipe(pu3, p3);
        map.connectPumpToPipe(pu3, detachable);
        map.connectPumpToPipe(pu3, outputChan);

        pu3.setOutput(detachable);
        pu3.setInput(p3);

        return map;
    }


    public void playerMove() throws MyException {


        //map.getPlayers().get(0).Move(map.getContainers().get(0));

    }
    /**
     * Mechanic repairs pump szekvencia
     */
    static void MechanicRepairsPump() throws MyException {

        Map map = initalizeTable();

        Mechanic mecha = new Mechanic(map.getContainers().get(0));
        System.out.println("Mechanic repairs pump has started");
        System.out.print("Pump isDamaged before repair: ");
        System.out.println(((Pump)mecha.getPosition()).getisDamaged());
        System.out.println("RepairPump is called");
        mecha.RepairPump();
        System.out.println("RepairPump has returned");
        if (!((Pump) mecha.getPosition()).getisDamaged()) {
            System.out.println("Pump repair was succesful :)");
        } else {
            System.out.println("Pump repair failed :(");
        }
        System.out.print("Pump isDamaged after repair: ");
        System.out.println(((Pump)mecha.getPosition()).getisDamaged());
        System.out.println("Mechanic repairs pump has finished");
    }

    /**
     * Mechanic repairs pipe szekvencia
     */
    static void MechanicRepairsPipe() throws MyException {

        Map map = initalizeTable();

        Mechanic mecha = new Mechanic(map.getContainers().get(1));

        System.out.println("Mechanic repairs pipe has started");
        ((Pipe)mecha.getPosition()).setLeaked(true);
        System.out.print("Pipe isLeaked before repair: ");
        System.out.println(((Pipe)mecha.getPosition()).isLeaked());
        System.out.println("RepairPipe is called");
        mecha.RepairPipe();
        System.out.println("RepairPipe has returned");
        if (((Pipe)mecha.getPosition()).isLeaked()) {
            System.out.println("Pipe repair was successful :)");
        } else {
            System.out.println("Pipe repair failed :(");
        }
        System.out.print("Pipe isLeaked after repair: ");
        System.out.println(((Pipe)mecha.getPosition()).isLeaked());
        System.out.println("Mechanic repairs pump has finished");

    }

    /**
     * Saboteur leaks pipe szekvencia
     */
    static void SaboteurLeaksPipe() throws MyException {
        Map map = initalizeTable();

        Saboteur saboteur = new Saboteur(map.getContainers().get(1));

        System.out.println("Saboteur leaks pipe has started");
        ((Pipe)saboteur.getPosition()).setLeaked(false);
        System.out.print("Pipe isleaked before sabotage: ");
        System.out.println(((Pipe)saboteur.getPosition()).isLeaked());
        System.out.println("LeakPipe is called");
        saboteur.LeakPipe();
        System.out.println("LeakPipe has returned");
        if (((Pipe) saboteur.getPosition()).isLeaked()) {
            System.out.println("Pipe sabotage was succesful :)");
        } else {
            System.out.println("Pipe sabotage failed :(");
        }
        System.out.print("Pipe isleaked after sabotage: ");
        System.out.println(((Pipe)saboteur.getPosition()).isLeaked());
        System.out.println("Saboteur leaks pipe has finished");

    }

    /**
     * Player attaches pipe szekvencia
     */
    static void PlayerAttachPipe() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        ArrayList<Pipe> carriedPipes = new ArrayList<Pipe>();
        carriedPipes.add(new Pipe());
        player.setCarriedPipes(carriedPipes);

        System.out.println("Player attach pipe successful has started");
        System.out.println("attachPipe is called");
        player.setCarriedPipes(carriedPipes);
        player.attachPipe();
        System.out.println("attachPipe has returned");
        if (player.getCarriedPipes().isEmpty()) {
            System.out.println("Player attach pipe successful was successful :)");
        } else {
            System.out.println("Player attach pipe successful has failed :(");
        }
        System.out.println("Player attach pipe successful has finished");
    }

    /**
     * Player attaches pump szekvencia
     */
    static void PlayerAttachPump() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(2));

        System.out.println("Player attaches pump has started");
        Pump pumpToAttach = new Pump(4);
        player.setCarriedPump(pumpToAttach);
        System.out.println("attachPump is called");
        player.attachPump();
        if (player.getPosition().seeifNeighbors(pumpToAttach)) {
            System.out.println("Player attached pump successfully :)");
        } else {
            System.out.println("Player failed to attach pump :(");
        }
        System.out.println("Player attaches pump has finished");
    }

    /**
     * Player detach pipe szekvencia
     */
    static void PlayerDetachPipe() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        System.out.println("Player detach pipe has started");
        System.out.println("detachPipe is called");
        player.detachPipe((Pipe)map.getContainers().get(7));
        System.out.println("detachPipe has returned");
        if (player.getCarriedPipes().contains((Pipe)map.getContainers().get(7))) {
            System.out.println("Player has detached pipe successfully :)");
        } else {
            System.out.println("Player failed to take pipe :(");
        }
        System.out.println("Player detach pipe has finished");

    }

    /**
     * Player adjust pump Input szekvencia
     */
    static void PlayerAdjustPumpInput() throws MyException {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        System.out.println("Player adjust pump Input has started");
        System.out.println("Original input of pump: " + ((Pump)player.getPosition()).getInput());
        System.out.println("adjustPump is called");
        player.adjustPump((Pipe)map.getContainers().get(8), Type.Input);
        System.out.println("adjustPump has returned");
        if (((Pump)player.getPosition()).getInput().equals(map.getContainers().get(8))) {
            System.out.println("Input adjustment successful :)");
            System.out.println("Input successfully got adjusted to: " + ((Pump)player.getPosition()).getInput());
        } else {
            System.out.println("Input adjustment failed :(");
        }
        System.out.println("Player adjust pump Input has finished");
    }

    /**
     * Player adjust pump Output szekvencia
     */
    static void PlayerAdjustPumpOutput() throws MyException {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        System.out.println("AdjustPump has started");
        System.out.println("Original output of pump: " + ((Pump)player.getPosition()).getOutput());
        System.out.println("adjustPump is called");
        player.adjustPump((Pipe) map.getContainers().get(8), Type.Output);
        System.out.println("adjustPump has returned");
        if (((Pump)player.getPosition()).getOutput().equals(map.getContainers().get(8))) {
            System.out.println("adjustPump Output successful :)");
            System.out.println("Output successfully got adjusted to: " + ((Pump)player.getPosition()).getOutput());
        } else {
            System.out.println("adjustPump Output failed :(");
        }
        System.out.println("AdjustPump has finished");
    }

    /**
     * Player moves to Pipe szekvencia
     */
    static void PlayerMovesToPipe() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(0));

        System.out.println("PlayerMovesOnPipeSuc has started");
        ArrayList<Container> neighbors = player.getPosition().getNeighbors();
        System.out.println("Pipe we want to move onto: " + neighbors.get(0));
        System.out.println("Move is called");
        player.Move(neighbors.get(0));
        System.out.println("Move has returned");
        if (player.getPosition().equals(neighbors.get(0))) {
            System.out.println("Player moves to Pipe successful :)");
            System.out.println("Player moved successfully onto: " + (Pipe)player.getPosition());
        } else {
            System.out.println("Player moves to Pipe failed :(");
        }
        System.out.println("PlayerMovesOnPipeSuc has finished");

    }

    /**
     * Player moves to Pipe Fail szekvencia
     */
    static void PlayerMovesToPipeFail() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(0));

        System.out.println("PlayerMovesOnPipeSuc has started");
        ArrayList<Container> neighbors = player.getPosition().getNeighbors();
        System.out.println("Pipe we want to move onto: " + neighbors.get(0));
        ((Pipe)neighbors.get(0)).setOccupied(true);
        System.out.println("Given pipe is currently occupied so Move to that pipe must fail");
        System.out.println("Move is called");
        player.Move(neighbors.get(0));
        System.out.println("Move has returned");
        if (player.getPosition().equals(neighbors.get(0))) {
            System.out.println("Player moves to Pipe successful :)");
            System.out.println("Player moved successfully onto: " + (Pipe)player.getPosition());
        } else {
            System.out.println("Player moves to Pipe failed :(");
            System.out.println("Player stayed on: " + (Pump)player.getPosition());
        }
        System.out.println("PlayerMovesOnPipeSuc has finished");

    }


    /**
     * Player moves to Pump szekvencia
     */
    static void PlayerMovesToPump() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(1));

        System.out.println("Player moves to pump has started");
        ArrayList<Container> neighbors = player.getPosition().getNeighbors();
        System.out.println("Pump the player wants to move onto: " + neighbors.get(0));
        System.out.println("Move is called");
        player.Move(neighbors.get(0));
        System.out.println("Move has returned");
        if (player.getPosition().equals(neighbors.get(0))) {
            System.out.println("Move to Pump successful :)");
            System.out.println("Player successfully moved onto: " + (Pump)player.getPosition());
        } else {
            System.out.println("Move to Pump failed :(");
        }
        System.out.println("Player moves to pump has finished");

    }



    /**
     * Player moves to Cistern szekvencia
     */
    static void PlayerMovesToCistern() throws MyException
    {

    }


    /**
     * Player moves to MountainSpring szekvencia
     */
    static void PlayerMovesToMountainSpring()
    {

    }

    /**
     * Pump breaks randomly szekvencia
     */
    static void RandomPumpBreaking()
    {

    }

    /**
     * CollectingWater szekvencia
     */
    static void CollectingWater()
    {

    }

    /**
     * @param args
     * @throws MyException
     */
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
        Pump cpump = new Pump(3);
        Pump cpump2 = new Pump(2);
        Pipe pozi = new Pipe();
        Pipe pipeA = new Pipe();
        Pipe pipeB = new Pipe();
        cpump.addPipe(pipeA);
        cpump.addPipe(pipeB);
        cpump.addPipe(pozi);

        cpump.setOutput(pozi);
        cpump.setInput(pipeA);

        pipeA.addPump(cpump);
        pipeB.addPump(cpump);

        cpump2.addPipe(pozi);
        pozi.addPump(cpump2, 0);
        pozi.addPump(cpump2, 1);
        Mechanic emese = new Mechanic(pozi);
        Mechanic m = new Mechanic(cpump);
        Saboteur s = new Saboteur(cpump);
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
                    MechanicRepairsPump();
                    break;

                /**
                 * Mechanic repairs pipe szekvencia
                 */
                case 2:
                    MechanicRepairsPipe();
                    break;

                /**
                 * Saboteur leaks pipe szekvencia
                 */
                case 3:
                    SaboteurLeaksPipe();
                    break;

                /**
                 * Player attach pipe successful szekvencia
                 */
                case 4:
                    PlayerAttachPipe();
                    break;

                /**
                 * Player attach pipe Fail szekvencia
                 */
                case 5:

                    break;

                /**
                 * Player attaches pump szekvencia
                 */
                case 6:
                    PlayerAttachPump();
                    break;

                /**
                 * Player detach pipe szekvencia
                 */
                case 7:
                    PlayerDetachPipe();
                    break;

                /**
                 * Player adjust pump Input szekvencia
                 */
                case 8:
                    PlayerAdjustPumpInput();
                    break;

                /**
                 * Player adjust pump Output szekvencia
                 */
                case 9:
                    PlayerAdjustPumpOutput();
                    break;

                /**
                 * Player moves to Pipe successfully szekvencia
                 */
                case 10:
                    PlayerMovesToPipe();
                    break;

                /**
                 * Player moves to Pipe failed szekvencia
                 */
                case 11:
                    PlayerMovesToPipeFail();
                    break;

                /**
                 * Player moves to Pump szekvencia
                 */
                case 12:
                    PlayerMovesToPump();
                    break;

                /**
                 * Player moves to Cistern szekvencia
                 */
                case 13:
                    System.out.println("Player moves to cistern has started");
                    Player pla1 = new Player(cpump);
                    ArrayList<Container> neighb13 = new ArrayList<Container>();
                    Cistern cis = new Cistern(new Pipe(), new Pump(2), 1000);
                    neighb13.add(cis);
                    cpump.setNeighbors(neighb13);
                    System.out.println("Move is called");
                    pla1.Move(cis);
                    System.out.println("Move has returned");
                    if (pla1.getPosition().equals(cis)) {
                        System.out.println("Move to Cistern successful :)");
                    } else {
                        System.out.println("Move to Cistern failed :(");
                    }
                    System.out.println("Player moves to cistern has finished");
                    break;

                /**
                 * Player moves to MountainSpring szekvencia
                 */
                case 14:
                    System.out.println("Player moves to MountainSpring has started");
                    Player pla2 = new Player(cpump);
                    ArrayList<Container> neighb14 = new ArrayList<Container>();
                    MountainSpring mo = new MountainSpring();
                    neighb14.add(mo);
                    cpump.setNeighbors(neighb14);
                    try {
                        System.out.println("Move is called");
                        pla2.Move(mo);
                        System.out.println("Move has returned");
                    } catch (MyException e) {
                        //throw new RuntimeException(e);
                    }
                    if (pla2.getPosition().equals(mo)) {
                        System.out.println("Move to MountainSpring successful :)");
                    } else {
                        System.out.println("Move to MountainSpring failed :(");
                    }
                    System.out.println("Player moves to MountainSpring has finished");
                    break;

                /**
                 * Evaluation szekvencia
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
