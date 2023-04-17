package skeleton;

import container.*;
import controller.Controller;
import exception.MyException;

import player.*;
import map.*;


import java.util.ArrayList;
import static java.lang.Thread.sleep;

/**
 * A DispatcherSkeleton osztály segítségével végezhető a skeleton program tesztelése.
 * Ebben az osztályban található a main metódus, amely a standard inputra vár egész számokat,
 * melyeket beolvasva lefuttatja a kiválasztott szekvenciát
 */
public class DispatcherSkeleton {

    public DispatcherSkeleton() throws MyException {
    }

    /**
     * Inicializál egy pályát a víz mozgatásának bemutatásához
     * @return
     */
    public static Map waterFlowingMap() throws MyException {
        Map map = new Map();

        Pipe msOutput = new Pipe();
        Pipe looseEnd = new Pipe();
        Pipe pu1Output = new Pipe();
        Pipe pu2Output = new Pipe();

        Pump pu1 = new Pump(4);
        Pump pu2 = new Pump(4);

        System.out.println("msOutput Pipe: " + msOutput);
        System.out.println("looseEnd Pipe: " + looseEnd);
        System.out.println("pu1Output" + pu1Output);
        System.out.println("pu2Output" + pu2Output);

        MountainSpring ms = new MountainSpring();
        Cistern cs = new Cistern(pu2Output);

        ms.getNeighbors().add(msOutput);
        msOutput.getNeighbors().add(ms);
        cs.getNeighbors().add(pu2Output);
        pu2Output.getNeighbors().add(cs);


        //I-O
        ms.setOutput(msOutput);
        pu1.setInput(msOutput);
        pu1.setOutput(pu1Output);
        pu2.setInput(pu1Output);
        pu2.setOutput(pu2Output);

        //Adding everyone to the family - Pipes, Pumps, Cistern, MountainSpring
        map.getContainers().add(ms);
        map.getContainers().add(msOutput);
        map.getContainers().add(pu1);
        map.getContainers().add(pu1Output);
        map.getContainers().add(looseEnd);
        map.getContainers().add(pu2);
        map.getContainers().add(pu2Output);
        map.getContainers().add(cs);


        //Connecting the dots - Pipes and Pumps
        map.connectPumpToPipe(pu1, msOutput);
        map.connectPumpToPipe(pu1, pu1Output);
        map.connectPumpToPipe(pu2, pu1Output);
        map.connectPumpToPipe(pu2, pu2Output);

        return map;
    }

    /**
     * Inicializál egy pályát a programhoz tartozó alkatrészek bemutatásához
     * @return
     */
    public static Map initalizeTable() throws MyException {

        Map map = new Map();
        Pump pos = new Pump(4);

        pos.setDamaged(true);

        Pipe msOutput = new Pipe();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();
        Pipe p3 = new Pipe();
        Pipe detachable = new Pipe();
        Pipe outputChan = new Pipe();

        MountainSpring ms = new MountainSpring();
        ms.setOutput(msOutput);
        ms.getNeighbors().add(msOutput);
        ms.getNeighbors().add(ms);

        Cistern cs = new Cistern(outputChan);
        outputChan.getNeighbors().add(cs);
        cs.getNeighbors().add(outputChan);

        Pump pu1 = new Pump(2);
        Pump pu2 = new Pump(3);
        Pump pu3 = new Pump(4);

        //containers -> 0.idx
        map.getContainers().add(pos);
        //containers -> 1.idx
        map.getContainers().add(p1);
        //containers -> 2.idx
        map.getContainers().add(p2);
        //containers -> 3.idx
        map.getContainers().add(p3);
        //containers -> 4.idx
        map.getContainers().add(pu1);
        //containers -> 5.idx
        map.getContainers().add(pu2);
        //containers -> 6.idx
        map.getContainers().add(pu3);
        //containers -> 7.idx
        map.getContainers().add(detachable);
        //containers -> 8.idx
        map.getContainers().add(outputChan);
        //containers -> 9.idx
        map.getContainers().add(cs);
        //containers -> 10.idx
        map.getContainers().add(ms);
        //containers -> 11.idx
        map.getContainers().add(msOutput);

        map.connectPumpToPipe(pos, msOutput);
        //Set input for pos Pump
        pos.setInput(msOutput);
        map.connectPumpToPipe(pos, p1);
        //Set output for pos Pump
        pos.setOutput(p1);
        map.connectPumpToPipe(pu1, p1);
        //Set input for pu1 Pump
        pu1.setInput(p1);
        map.connectPumpToPipe(pu1, p2);
        //Set input for pu1 Pump
        pu1.setOutput(p2);
        map.connectPumpToPipe(pu2, p2);
        //Set input for pu2 Pump
        pu2.setInput(p2);
        map.connectPumpToPipe(pu2, p3);
        //Set input for pu2 Pump
        pu2.setOutput(p3);
        map.connectPumpToPipe(pu3, p3);
        //Set input for pu3 Pump
        pu3.setInput(p3);
        map.connectPumpToPipe(pu3, detachable);
        //Set output for pu3 Pump
        pu3.setOutput(detachable);
        map.connectPumpToPipe(pu3, outputChan);


        return map;
    }

    /**
     * Mechanic repairs pump szekvencia
     * Ebben azt teszteljük le a szerelő pumpa javító akcióját
     * Inicializálunk egy pályát és egy szerelő játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások után) meghívjuk a játékosra a RepairPump() függvényt
     * A RepairPump() függvény meghívása után egy if ágban megvizsgáljuk, hogy a szerelő pozíciójánál lévő pumpa megjavult-e
     * Ha megjavult szuper minden rendben, ha nem azt meg nem kell részletezni
     */
    public void MechanicRepairsPump() throws MyException {

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
     * Ebben azt teszteljük le a szerelő cső javító akcióját
     * Inicializálunk egy pályát és egy szerelő játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások után) meghívjuk a játékosra a RepairPipe() függvényt
     * A RepairPipe() függvény meghívása után egy if ágban megvizsgáljuk, hogy a szerelő pozíciójánál lévő pipe megjavult-e, azaz nincsen meglyukasztva már
     * Ha megjavult szuper minden rendben, ha nem azt meg nem kell részletezni
     */
    public void MechanicRepairsPipe() throws MyException {

        Map map = initalizeTable();

        Mechanic mecha = new Mechanic(map.getContainers().get(1));

        System.out.println("Mechanic repairs pipe has started");
        ((Pipe)mecha.getPosition()).setLeaked(true);
        System.out.print("Pipe isLeaked before repair: ");
        System.out.println(((Pipe)mecha.getPosition()).isLeaked());
        System.out.println("RepairPipe is called");
        mecha.RepairPipe();
        System.out.println("RepairPipe has returned");
        if (!((Pipe)mecha.getPosition()).isLeaked()) {
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
     * Ebben azt teszteljük le a szabotőr cső lyukasztó akcióját
     * Inicializálunk egy pályát és egy szabotőr játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások után) meghívjuk a játékosra a LeakPipe() függvényt
     * A LeakPipe() függvény meghívása után egy if ágban megvizsgáljuk, hogy a szabotőr pozíciójánál lévő pipe kilyukadt-e
     * Ha igen szuper minden rendben, ha nem azt meg nem kell részletezni
     */
    public void SaboteurLeaksPipe() throws MyException {
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
     * Ebben azt teszteljük le hogyan tesz egy játékos egy csövet egy pumpához
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások és egy cső lista létrehozása és játékosnak adása után) meghívjuk a játékosra az attachPipe() függvényt
     * Az attachPipe() függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciójánál lévő pumpához hozzáadódott-e cső illetve, hogy maradt-e nálunk.
     * Ha nem maradt nálunk és hozzácsatlakozott mindena legnagyobb rendben van
     */
    public void PlayerAttachPipe() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        ArrayList<Pipe> carriedPipes = new ArrayList<Pipe>();
        Pipe pipe = new Pipe();
        carriedPipes.add(pipe);
        player.setCarriedPipes(carriedPipes);

        System.out.println("Player attach pipe successful has started");
        System.out.println("attachPipe is called");
        player.setCarriedPipes(carriedPipes);
        player.attachPipe();
        System.out.println("attachPipe has returned");
        if (player.getCarriedPipes().isEmpty() && player.getPosition().seeifNeighbors(pipe)) {
            System.out.println("Player attach pipe successful was successful :)");
        } else {
            System.out.println("Player attach pipe successful has failed :(");
        }
        System.out.println("Player attach pipe successful has finished");
    }

    /**
     * Player attaches pump szekvencia
     * Ebben azt teszteljük le hogyan tesz egy játékos egy pumpát egy csőhöz
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások és egy pumpa létrehozása és játékosnak adása után) meghívjuk a játékosra az attachPump() függvényt
     * Az attachPump() függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciójánál lévő csőhöz hozzáadódott-e pumpa illetve, hogy maradt-e nálunk.
     * Ha nem maradt nálunk és hozzácsatlakozott minden a legnagyobb rendben van
     */
    public void PlayerAttachPump() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(2));

        System.out.println("Player attaches pump has started");
        Pump pumpToAttach = new Pump(4);
        player.setCarriedPump(pumpToAttach);
        System.out.println("attachPump is called");
        player.attachPump();
        if (player.getPosition().seeifNeighbors(pumpToAttach) && player.getCarriedPump() == null) {
            System.out.println("Player attached pump successfully :)");
        } else {
            System.out.println("Player failed to attach pump :(");
        }
        System.out.println("Player attaches pump has finished");
    }

    /**
     * Player detaches pipe szekvencia
     * Ebben azt teszteljük le hogyan vesz egy játékos egy csövet egy pumpától le
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra a detachPipe() függvényt
     * z detachPipe() függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciójánál lévő pumpától lekerült-e a cső vagy sem, illetve, hogy nálunk van-e a lecsatlakoztatott cső
     * Ha nálunk van és nem szomszédos a pozícónknál lévő pumpával akkor minden rendben
     */
    public void PlayerDetachPipe() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(6));

        System.out.println("Player detach pipe has started");
        System.out.println("detachPipe is called");
        player.detachPipe((Pipe)map.getContainers().get(7));
        System.out.println("detachPipe has returned");
        if (player.getCarriedPipes().contains((Pipe)map.getContainers().get(7)) && !(player.getPosition().seeifNeighbors(map.getContainers().get(7)))) {
            System.out.println("Player has detached pipe successfully :)");
        } else {
            System.out.println("Player failed to take pipe :(");
        }
        System.out.println("Player detach pipe has finished");

    }

    /**
     * Player adjusts pump input szekvencia
     * Ebben azt teszteljük le hogyan állít a játékos át egy pumpát
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az adjustPump() függvényt
     * Az adjustPump() függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciójánál lévő pumpának az általunk választott új Pipe lett-e az inputja
     * Ha igen akkor minden a legnagyobb rendben
     */
    public void PlayerAdjustPumpInput() throws MyException {
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
     * Player adjusts pump output szekvencia
     * Ebben azt teszteljük le hogyan állít a játékos át egy pumpát
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az adjustPump() függvényt
     * Az adjustPump() függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciójánál lévő pumpának az általunk választott új Pipe lett-e az outputja
     * Ha igen akkor minden a legnagyobb rendben
     */
    public void PlayerAdjustPumpOutput() throws MyException {
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
     * Player moves to pipe szekvencia
     * Ebben azt teszteljük le hogyan lép a játékos egy csőre
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az Move(Container c) függvényt
     * A Move(Container c) függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciója a kívánt konténerre változott-e
     * Ha igen akkor minden a legnagyobb rendben
     */
    public void PlayerMovesToPipe() throws MyException
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
     * Player moves to pipe fail szekvencia
     * Ebben azt teszteljük le hogyan próbál lépni a játékos egy csőre ha a cső foglalt
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy pumpán hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az Move(Container c) függvényt
     * A Move(Container c) függvény meghívása után, elsősorban kell kapnun egy kivételt, amelyet ki is iratun illetve egy if ágban megvizsgáljuk, hogy a játékos pozíciója megváltozott-e
     * Ha nem akkor minden a legnagyobb rendben
     */
    public void PlayerMovesToPipeFail() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(0));

        System.out.println("PlayerMovesOnPipeFail has started");
        ArrayList<Container> neighbors = player.getPosition().getNeighbors();
        System.out.println("Pipe we want to move onto: " + neighbors.get(0));
        ((Pipe)neighbors.get(0)).setOccupied(true);
        System.out.println("Given pipe is currently occupied so Move to that pipe must fail");
        System.out.println("Move is called");
        try {
            player.Move(neighbors.get(0));
        }catch (MyException e){
            System.out.println(e);
        }
        System.out.println("Move has returned");
        if (player.getPosition().equals(neighbors.get(0))) {
            System.out.println("Player moves to Pipe successful :(");
            System.out.println("Player moved successfully onto: " + (Pipe)player.getPosition());
        } else {
            System.out.println("Player moves to Pipe failed :)");
            System.out.println("Player stayed on: " + (Pump)player.getPosition());
        }
        System.out.println("PlayerMovesOnPipeSuc has finished");

    }

    /**
     * Player moves to pump szekvencia
     * Ebben azt teszteljük le hogyan lép a játékos egy pumpára
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az Move(Container c) függvényt
     * A Move(Container c) függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciója a kívánt konténerre változott-e
     * Ha igen akkor minden a legnagyobb rendben
     */
    public void PlayerMovesToPump() throws MyException
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
     * Player moves to cistern szekvencia
     * Ebben azt teszteljük le hogyan lép a játékos a ciszternára
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre, majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az Move(Container c) függvényt
     * A Move(Container c) függvény meghívása után egy if ágban megvizsgáljuk, hogy a játékos pozíciója a kívánt konténerre (ciszternára) változott-e
     * Ha igen akkor minden a legnagyobb rendben
     */
    public void PlayerMovesToCistern() throws MyException
    {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(8));

        System.out.println("Player moves to cistern has started");
        System.out.println("The Cistern that the player wants to move onto: " + map.getContainers().get(9));
        System.out.println("Move is called");
        player.Move(map.getContainers().get(9));
        System.out.println("Move has returned");
        if (player.getPosition().equals(map.getContainers().get(9))) {
            System.out.println("Move to Cistern successful :)");
            System.out.println("Player sucessfully moved onto: " + (Cistern) player.getPosition());
        } else {
            System.out.println("Move to Cistern failed :(");
        }
        System.out.println("Player moves to cistern has finished");
    }


    /**
     * Player moves to mountain spring szekvencia
     * Ebben azt teszteljük le hogyan nem tud lépni a játékos a hegyi forrásra
     * Inicializálunk egy pályát és egy játékost és velük dolgozva végezzük el az akciót
     * A játékost először is egy csövön hozzuk létre (hegyi forráshoz tartozó csövön), majd (a konzolban érthetőséget segítő kiiratások) meghívjuk a játékosra az Move(Container c) függvényt
     * A Move(Container c) függvény meghívása után egy kivételt (MyException) kell, hogy kapjunk majd egy if ágban megvizsgáljuk, hogy a játékos pozíciója a hegyi forrásra változott-e
     * Ha nem akkor minden a legnagyobb rendben
     */
    public void PlayerMovesToMountainSpring() throws MyException {
        Map map = initalizeTable();

        Player player = new Player(map.getContainers().get(11));

        System.out.println("Player moves to MountainSpring has started");
        System.out.println("MountainSpring the player wants to move onto: " + map.getContainers().get(10));
        System.out.println("Move is called");
        try {
            player.Move(map.getContainers().get(10));
        }catch (MyException e){
            System.out.println(e);
        }
        if (player.getPosition().equals(map.getContainers().get(10))) {
            System.out.println("Move to MountainSpring successful :(");
        } else {
            System.out.println("Move to MountainSpring failed :)");
            System.out.println("Mission failed successfully, position is: " + (Pipe)player.getPosition());
        }
        System.out.println("Player moves to MountainSpring has finished");

    }

    /**
     * Pump breaks randomly szekvencia
     * Ebben a függvényben azt teszteljük le, hogy az idő múlásával hogyan mennek tönkre a pumpák
     * Inicializálunk egy Map-ot (map) és egy Controller-t (konTroll), majd a map-ot átadjuk a konTroll-nak
     * Egy for cilusban 20 eltelt kört szimulálva megnézzük mi történik a pumpákkal a konTroll-on való damagePump meghívása után, illetve növeljük a kör számlálót
     * Mivel a pályánkon összesen 3 pumpa van ezért három olyan üzenetet kell kapnunk ahol tönkrementek
     */
    public void RandomPumpBreaking() throws MyException, InterruptedException {
        Map map = initalizeTable();

        Controller konTroll = new Controller();
        konTroll.setMap(map);

        System.out.println("Pump breaking sequence initiated!");

        for(int i=1; i <= 20; i++){
            System.out.println("Current turnCount:" + i);
            konTroll.increaseTurnCount();
            konTroll.damagePump();
            sleep(1000);
        }
        System.out.println("Pumps all up and gone!");
    }

    /**
     * CollectingWater szekvencia
     * Ebben a függvényben azt teszteljük le, hogy az idő múlásával, hogyan megy a víz
     * Inicializálunk egy Map-ot (map) és egy Controller-t (konTroll), majd a map-ot átadjuk a konTroll-nak
     * Egy for ciklusban 7 eltelt kört szimulálva megnézzük, hogy hogyan mozgott a víz.
     * A víz mozgását a waterFlow() függvény valósítja meg részletes leírás a Controller osztályon belül található róla
     */
    public void CollectingWater() throws MyException {
        Map map = waterFlowingMap();

        Controller konTroll = new Controller();
        konTroll.setMap(map);

        System.out.println("Water is about to flow!");
        for(int i=0; i < 7; i++) {
            konTroll.waterFlow();
            System.out.println();
            System.out.println();
        }
        System.out.println("Phew...");
    }
}
