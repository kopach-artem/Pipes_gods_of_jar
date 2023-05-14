package console;

import container.*;
import exception.MyException;
import map.Map;
import player.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Az operation parancsokat megvalósító osztály.
 */
public class Operation
{

    /**
     * Csinál egy kis őályát
     */
    public static void makeSmallMap(){
        MountainSpring ms = new MountainSpring();
        Pipe pipe = new Pipe();
        Pump pump = new Pump(4);

        Map.getInstance().getContainers().add(ms);
        Map.getInstance().getContainers().add(pipe);
        Map.getInstance().getContainers().add(pump);

        Map.getInstance().getGameMap().add(new ContainerPos(ms, 0, 0));
        Map.getInstance().getGameMap().add(new ContainerPos(pipe, 1, 0));
        Map.getInstance().getGameMap().add(new ContainerPos(pump, 2, 0));

        Map.saveToFile("smallMap.txt");
    }


    /**
     * A printMap metódus kirajzolja a jelenleg betöltött pályát a console-ra.
     * @param containerPosList - A pályán található Containerek listája.
     */
    public static void printMap(ArrayList<ContainerPos> containerPosList){

            int maxX = -1;
            int maxY = -1;

            // Find the maximum x and y values
            for (ContainerPos containerPos : containerPosList) {
                if (containerPos.getPosX() > maxX) {
                    maxX = containerPos.getPosX();
                }
                if (containerPos.getPosY() > maxY) {
                    maxY = containerPos.getPosY();
                }
            }

            // Create a 2D grid to store the container symbols
            String[][] grid = new String[maxX + 1][maxY + 1];

            // Fill the grid with ' ' (empty spaces)
            for (int i = 0; i <= maxX; i++) {
                for (int j = 0; j <= maxY; j++) {
                    grid[i][j] = " \t";
                }
            }

            // Place the container symbols in the grid
            for (ContainerPos containerPos : containerPosList) {
                int x = containerPos.getPosX();
                int y = containerPos.getPosY();
                Container c = containerPos.getContainer();
                grid[x][y] = c.consolePrint();
            }

            // Print the grid
            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    System.out.print(grid[x][y]);
                }
                System.out.println();
            }
    }

    /**
     * A parancsok feldolgozása itt történik.
     * @param command - A parancs String formában.
     * @throws MyException
     */
    public static void operation(String command) throws MyException {
        /**
         * Ha a parancs operationCreateTestMap, akkor létrehozunk egy teszt pályát,
         * és kiírjuk a testMap.txt fájlba
         */
        if(command.startsWith("operationCreateTestMap")){

            System.out.println("Map creation started, will be saved as testMap.txt");

            //Szükséges elemek létrehozása, inicializálása

            //Első sor változói
            Pipe pipe1 = new Pipe();
            Pump pump1 = new Pump(3);
            Pipe pipe2 = new Pipe();
            MountainSpring ms = new MountainSpring(pipe1);
            Cistern cs = new Cistern(pipe2);

            //Második sor változói
            Pipe pipe3 = new Pipe();

            //Harmadik sor változói
            Pump pump2 = new Pump(3);

            //Változók hozzáadása Map-hoz
            Map.getInstance().getContainers().add(pipe1);
            Map.getInstance().getContainers().add(pipe2);
            Map.getInstance().getContainers().add(pipe3);
            Map.getInstance().getContainers().add(pump1);
            Map.getInstance().getContainers().add(pump2);
            Map.getInstance().getContainers().add(ms);
            Map.getInstance().getContainers().add(cs);

            //Változók inicializálása
            Map.getInstance().connectPumpToPipe(pump1, pipe1);
            Map.getInstance().connectPumpToPipe(pump1, pipe2);
            Map.getInstance().connectPumpToPipe(pump2, pipe3);

            pump1.setInput(pipe1);
            pump1.setOutput(pipe2);

            Map.getInstance().getGameMap().add(new ContainerPos(ms, 0, 0));
            Map.getInstance().getGameMap().add(new ContainerPos(pipe1, 1, 0));
            Map.getInstance().getGameMap().add(new ContainerPos(pump1, 2, 0));
            Map.getInstance().getGameMap().add(new ContainerPos(pipe2, 3, 0));
            Map.getInstance().getGameMap().add(new ContainerPos(cs, 4, 0));
            Map.getInstance().getGameMap().add(new ContainerPos(pipe3, 2, 1));
            Map.getInstance().getGameMap().add(new ContainerPos(pump2, 2, 2));

            Map.getInstance().getPlayers().add(new Mechanic(cs));
            Map.getInstance().getPlayers().add(new Saboteur(cs));

            Map.saveToFile("testMap.txt");

            System.out.println("Test map has successfully been created as 'testMap.txt', you can load it with command 'operationLoadMaptestMap.txt'");
        }
        /**
         * Ha a parancs operationLodaMap, akkor egy korábban elkészített pályát betöltünk
         * a megadott .txt fájlból.
         */
        else if(command.startsWith("operationLoadMap")) //operationLoadMap <Filename>.txt
        {
            if (!command.endsWith(".txt"))
            {
                System.out.println("Invalid filename. The filename must end with .txt.");
            }
            else
            {
                String filename = command.substring(16); // <Filename>.txt
                String commands[]=Map.readFromFile(filename);
                for(int i=0; i<commands.length; i++)
                {
                    if(commands[i].startsWith("operation"))
                    {
                        try
                        {
                            Operation.operation(commands[i]);
                        }
                        catch (MyException e)
                        {
                            System.out.println(e.getMessage());

                        }
                    }
                    else if(commands[i].startsWith("player"))
                    {
                        try
                        {
                            Playercmd.player(commands[i]);
                        }
                        catch (MyException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(commands[i].startsWith("manual"))
                    {
                        Manual.manual(commands[i]);
                    }
                    else if(commands[i].startsWith("list"))
                    {
                        List.list(commands[i]);
                    }
                    else if(commands[i].startsWith("random"))
                    {
                        Random.random(commands[i]);
                    }
                    else
                    {
                        System.out.println("Unknown command");
                    }
                }
                if(Map.getInstance() != null)
                    System.out.println("Loading map from file: " + filename);
            }
        }
        /**
         * Ha a parancs opeartionSaveMap, akkor a jelenlegi pályaállapotot elmentjük a megadott nevű
         * .txt fájlba.
         */
        else if(command.startsWith("operationSaveMap")) //operationSaveMap <Filename>.txt
        {
            if (!command.endsWith(".txt"))
            {
                System.out.println("Invalid filename. The filename must end with .txt.");
            }
            else
            {
                String filename = command.substring(16); // <Filename>.txt
                if(Map.getInstance() != null) {
                    Map.getInstance().saveToFile(filename);
                    System.out.println("Saving map into file: " + filename);
                }
                else
                    System.out.println("Constructed map is empty");
            }
        }
        /**
         * Ha a parancs operationPrintMap, akkor kirajzoljuk a jelenlegi pályaállapotot a consoleba.
         */
        else if(command.startsWith("operationPrintMap")) {

            if(!Map.getInstance().getGameMap().isEmpty()) {
                Operation.printMap(Map.getInstance().getGameMap());
            }
            else
                System.out.println("Command is not acceptable due to the map being empty");
        }
        /**
         * Ha a parancs operationCreateContainer, akkor léterhozunk egy kívánt típusú Containert
         * a kívánt pozícióba
         */
        else if(command.startsWith("operationCreateContainer")) //operationCreateContainer<ContainerType>At <PosX>_<PosY>
        {
            String container=command.substring(24);
            /**
             * Létrehozunk egy Cistern-t.
             */
            if(container.startsWith("CisternAt")) //operationCreateContainerCisternAt
            {
                int positions[]= StrFunctions.subPosString(container,"CisternAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : Map.getInstance().getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                                break;
                            }
                            else{
                                Cistern cs = new Cistern();
                                Map.getInstance().getContainers().add(cs);
                                ContainerPos containerPos = new ContainerPos(cs, positions[0], positions[1]);
                                Map.getInstance().getGameMap().add(containerPos);
                                Map.addNeighbors(containerPos);

                                break;
                            }
                        }
                    }
                    else {
                        Cistern cs = new Cistern();
                        Map.getInstance().getContainers().add(cs);
                        Map.getInstance().getGameMap().add(new ContainerPos(cs, positions[0], positions[1]));
                    }
                }
            }
            /**
             * Létrehozunk egy MountainSpring-et.
             */
            else if(container.startsWith("MountainSpringAt")) //operationCreateContainerMountainSpringAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"MountainSpringAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : Map.getInstance().getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                                break;
                            }
                            else{
                                MountainSpring ms = new MountainSpring();
                                Map.getInstance().getContainers().add(ms);
                                ContainerPos containerPos = new ContainerPos(ms, positions[0], positions[1]);
                                Map.getInstance().getGameMap().add(containerPos);
                                Map.addNeighbors(containerPos);
                                break;
                            }
                        }
                    }
                    else {
                        MountainSpring ms = new MountainSpring();
                        Map.getInstance().getContainers().add(ms);
                        Map.getInstance().getGameMap().add(new ContainerPos(ms, positions[0], positions[1]));
                    }
                }
            }
            /**
             * Létrehozunk egy Pipe-ot.
             */
            else if(container.startsWith("PipeAt")) //operationCreateContainerPipeAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PipeAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : Map.getInstance().getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                                break;
                            }
                            else{
                                Pipe pipe = new Pipe();
                                Map.getInstance().getContainers().add(pipe);
                                ContainerPos containerPos = new ContainerPos(pipe, positions[0], positions[1]);
                                Map.getInstance().getGameMap().add(containerPos);
                                Map.addNeighbors(containerPos);
                                break;
                            }
                        }
                    }
                    else {
                        Pipe pipe = new Pipe();
                        Map.getInstance().getContainers().add(pipe);
                        Map.getInstance().getGameMap().add(new ContainerPos(pipe, positions[0], positions[1]));
                    }
                }
            }
            /**
             * Léterhozunk egy Pump-ot.
             */
            else if(container.startsWith("PumpAt")) //operationCreateContainerPumpAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PumpAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : Map.getInstance().getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                                break;
                            }
                            else{
                                Pump pump = new Pump(4);
                                Map.getInstance().getContainers().add(pump);
                                ContainerPos containerPos = new ContainerPos(pump, positions[0], positions[1]);
                                Map.getInstance().getGameMap().add(containerPos);
                                Map.addNeighbors(containerPos);
                                break;
                            }
                        }
                    }
                    else {
                        Pump pump = new Pump(4);
                        Map.getInstance().getContainers().add(pump);
                        Map.getInstance().getGameMap().add(new ContainerPos(pump, positions[0], positions[1]));
                    }
                }
            }
            else
            {
                System.out.println("Invalid type of container");
            }
        }
        /**
         * Ha a parancs operationDeleteContainer, akkor a megadott pozícióból kitöröljük
         * az ott lévő Container-t, ha nem üres a pozíció.
         */
        else if(command.startsWith("operationDeleteContainer")) //operationDeleteContainerAt <PosX>_<PosY>
        {
            String container=command.substring(24);
            if(container.startsWith("At")) // operationDeleteContainerAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty())
                        for(ContainerPos cp : Map.getInstance().getGameMap()) {
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                if(Map.getInstance().getContainers().contains(cp.getContainer())){
                                    Map.getInstance().getContainers().remove(cp.getContainer());
                                }
                                Map.getInstance().getGameMap().remove(cp);
                                Map.removeNeighbors(cp);
                                break;
                            }
                        }
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        /**
         * Ha a parancs operationDeletePlayer, akkor kitöröljük a megadott azonosítójú
         * játékost a pájáról, ha érvényes azonosítót adtunk meg.
         */
        else if(command.startsWith("operationDeletePlayer")) //operationDeletePlayer<Playernumber>
        {
            String playernumber=command.substring(21); //<Playernumber>
            if(playernumber.length()==1)
            {
                if(StrFunctions.isDigit(playernumber.charAt(0)))
                {
                    Player p = new Player();
                    for(Player player : Map.getInstance().getPlayers()){
                        if(player.getId() == Integer.parseInt(playernumber)){
                            p = player;
                            break;
                        }
                    }
                    if(!Map.getInstance().getPlayers().isEmpty()){
                        Map.getInstance().getPlayers().remove(p);
                    }
                }
                else
                    System.out.println("Give a valid integer");
            }
            else
            {
                System.out.println("The interval of a player's number is 0-9");
            }
        }
        /**
         * Ha a parancs operationCreatePlayer, akkor létrehozzuk a megadott típuső játékost
         * a megadott pozícióban.
         */
        else if(command.startsWith("operationCreatePlayer")) //operationCreatePlayer <Playertype> At <PosX>_<PosY>

        {
            String playerpos=command.substring(21);
            /**
             * Létrehozunk egy Mechanic-ot
             */
            if(playerpos.startsWith("MechanicAt")) //operationCreatePlayerMechanicAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(playerpos,"MechanicAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty()) {
                        for (ContainerPos cp : Map.getInstance().getGameMap()) {
                            if (cp.getPosX() == positions[0] && cp.getPosY() == positions[1]) {
                                Map.getInstance().getPlayers().add(new Mechanic(cp.getContainer()));
                                break;
                            }
                        }
                    }
                    else
                        System.out.println("There's no Container where Mechanic could be created");
                }
            }
            /**
             * Léterhozunk egy Saboteur-t.
             */
            else  if(playerpos.startsWith("SaboteurAt")) //operationCreatePlayerSaboteurAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(playerpos,"SaboteurAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!Map.getInstance().getGameMap().isEmpty()) {
                        for (ContainerPos cp : Map.getInstance().getGameMap()) {
                            if (cp.getPosX() == positions[0] && cp.getPosY() == positions[1]) {
                                Map.getInstance().getPlayers().add(new Saboteur(cp.getContainer()));
                                break;
                            }
                        }
                    }
                    else
                        System.out.println("There's no Container where Saboteur could be created");
                }
            }
            else
            {
                System.out.println("Invalid type of player");
            }
        }
        /**
         * Ha a parancs operationConnectContainerAt, akkor megadott Container-t a másik megadott
         * Container-hez csatlakoztajuk, ha az lehetséges.
         */
        else if(command.startsWith("operationConnectContainerAt")) //operationConnectContainerAt<Pos1X>_<Pos1Y>ToContainerAt<Pos2X>_<Pos2Y>
        {
            boolean first_=false;
            String str1="";
            String str2="";
            for(int i=0; i<command.length(); i++)
            {
                if(command.charAt(i)=='_')
                {
                    first_=true;
                }
                if(first_)
                {
                    if(StrFunctions.isDigit(command.charAt(i+1)) && StrFunctions.isDigit(command.charAt(i+2)))
                    {
                        str1=command.substring(0,i+3);
                        str2=command.substring(i+3);
                        break;
                    }
                    else if(StrFunctions.isDigit(command.charAt(i+1)))
                    {
                        str1=command.substring(0,i+2);
                        str2=command.substring(i+2);
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid use of command");
                        break;
                    }
                }
            }
            String playerpos1=str1.substring(25);
            String playerpos2=str2.substring(11);
            if(str1.startsWith("operationConnectContainer") && str2.startsWith("ToContainer"))
            {
                if(playerpos1.startsWith("At") && playerpos2.startsWith("At"))
                {
                    int positions1[]= StrFunctions.subPosString(playerpos1,"At"); //<Pos1X>_<Pos1Y>
                    int positions2[]= StrFunctions.subPosString(playerpos2,"At"); //<Pos2X>_<Pos2Y>
                    if(positions1[0]!=-1 && positions1[1]!= -1 && positions2[0]!=-1 && positions2[1]!= -1)
                    {
                        ContainerPos firstCon = new ContainerPos();
                        ContainerPos secondCon = new ContainerPos();
                        if(positions1[0] == positions2[0] && positions1[1] == positions2[1]){
                            System.out.println("The two given positions are one and the same");
                        }
                        else {
                            for (ContainerPos cp : Map.getInstance().getGameMap()) {
                                if (cp.getPosX() == positions1[0] && cp.getPosY() == positions1[1]) {
                                    firstCon = cp;
                                }
                                if (cp.getPosX() == positions2[0] && cp.getPosY() == positions2[1]) {
                                    secondCon = cp;
                                }
                            }
                            if(firstCon.getContainer().equals(null) || secondCon.getContainer().equals(null)){
                                System.out.println("No suitable Container's to connect to each other");
                            }
                            else {
                                firstCon.getContainer().getNeighbors().add(secondCon.getContainer());
                                secondCon.getContainer().getNeighbors().add(firstCon.getContainer());
                            }
                        }
                    }
                    else
                        System.out.println("One of the positions are incorrect");
                }
                else
                {
                    System.out.println("Invalid use of command");
                }
            }
        }
        else
        {
            System.out.println("Unknown command");
        }
    }
}
