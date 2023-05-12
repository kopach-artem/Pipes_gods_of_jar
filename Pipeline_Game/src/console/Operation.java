package console;

import container.*;
import exception.MyException;
import map.Map;
import player.*;

import java.util.ArrayList;

public class Operation
{
    public static Map map = new Map();
    public static Map constructedMap = new Map();


    public static void printMap(ArrayList<ContainerPos> containerPosList) {
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
     * Ez a metódus hoz létre nekünk egy teszt Map-ot amelyet elment a testMap.txt néven
     * A következő páylát hozza létre
     * MS - PU -  C
     * |
     * PU
     */
    public static void testMap() throws MyException {

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
        map.getContainers().add(pipe1);
        map.getContainers().add(pipe2);
        map.getContainers().add(pipe3);
        map.getContainers().add(pump1);
        map.getContainers().add(pump2);
        map.getContainers().add(ms);
        map.getContainers().add(cs);

        //Változók inicializálása
        map.connectPumpToPipe(pump1, pipe1);
        map.connectPumpToPipe(pump1, pipe2);
        map.connectPumpToPipe(pump2, pipe3);

        map.getGameMap().add(new ContainerPos(ms, 0, 0));
        map.getGameMap().add(new ContainerPos(pipe1, 1, 0));
        map.getGameMap().add(new ContainerPos(pump1, 2, 0));
        map.getGameMap().add(new ContainerPos(pipe2, 3, 0));
        map.getGameMap().add(new ContainerPos(cs, 4, 0));
        map.getGameMap().add(new ContainerPos(pipe3, 2, 1));
        map.getGameMap().add(new ContainerPos(pump2, 2, 2));

        map.getPlayers().add(new Mechanic(cs));
        map.getPlayers().add(new Saboteur(cs));

        map.saveToFile("testMap.txt");
    }
    public static void operation(String command) throws MyException {
        if(command.startsWith("operationLoadMap")) //operationLoadMap <Filename>.txt
        {
            if (!command.endsWith(".txt"))
            {
                System.out.println("Invalid filename. The filename must end with .txt.");
            }
            else
            {
                String filename = command.substring(16); // <Filename>.txt
                map = Map.loadFromFile(filename);
                System.out.println("Loading map from file: " + filename);
            }
        }
        else if(command.startsWith("operationSaveMap")) //operationSaveMap <Filename>.txt
        {
            if (!command.endsWith(".txt"))
            {
                System.out.println("Invalid filename. The filename must end with .txt.");
            }
            else
            {
                String filename = command.substring(16); // <Filename>.txt
                map.saveToFile(filename);
                System.out.println("Saving map into file: " + filename);
            }
        }
        else if(command.equals("operationCreateMap")) //operationCreateMap
        {
            //Előre megadott pálya elkészítése
            Operation.testMap();
        }
        else if(command.startsWith("operationCreateContainer")) //operationCreateContainer<ContainerType>At <PosX>_<PosY>
        {
            String container=command.substring(24);
            if(container.startsWith("CisternAt")) //operationCreateContainerCisternAt
            {
                int positions[]= StrFunctions.subPosString(container,"CisternAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : constructedMap.getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                            }
                            else{
                                Cistern cs = new Cistern();
                                constructedMap.getContainers().add(cs);
                                constructedMap.getGameMap().add(new ContainerPos(cs, positions[0], positions[1]));
                            }
                        }
                    }
                    else {
                        Cistern cs = new Cistern();
                        constructedMap.getContainers().add(cs);
                        constructedMap.getGameMap().add(new ContainerPos(cs, positions[0], positions[1]));
                    }
                }
            }
            else if(container.startsWith("MountainSpringAt")) //operationCreateContainerMountainSpringAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"MountainSpringAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : constructedMap.getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                            }
                            else{
                                MountainSpring ms = new MountainSpring();
                                constructedMap.getContainers().add(ms);
                                constructedMap.getGameMap().add(new ContainerPos(ms, positions[0], positions[1]));
                            }
                        }
                    }
                    else {
                        MountainSpring ms = new MountainSpring();
                        constructedMap.getContainers().add(ms);
                        constructedMap.getGameMap().add(new ContainerPos(ms, positions[0], positions[1]));
                    }
                }
            }
            else if(container.startsWith("PipeAt")) //operationCreateContainerPipeAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PipeAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : constructedMap.getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                            }
                            else{
                                Pipe pipe = new Pipe();
                                constructedMap.getContainers().add(pipe);
                                constructedMap.getGameMap().add(new ContainerPos(pipe, positions[0], positions[1]));
                            }
                        }
                    }
                    else {
                        Pipe pipe = new Pipe();
                        constructedMap.getContainers().add(pipe);
                        constructedMap.getGameMap().add(new ContainerPos(pipe, positions[0], positions[1]));
                    }
                }
            }
            else if(container.startsWith("PumpAt")) //operationCreateContainerPumpAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PumpAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty())
                    {
                        for(ContainerPos cp : constructedMap.getGameMap()){
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                System.out.println("There's already a container stationed at:" + positions[0] + ',' + positions[1] + " position");
                            }
                            else{
                                Pump pump = new Pump(4);
                                constructedMap.getContainers().add(pump);
                                constructedMap.getGameMap().add(new ContainerPos(pump, positions[0], positions[1]));
                            }
                        }
                    }
                    else {
                        Pump pump = new Pump(4);
                        constructedMap.getContainers().add(pump);
                        constructedMap.getGameMap().add(new ContainerPos(pump, positions[0], positions[1]));
                    }
                }
            }
            else
            {
                System.out.println("Invalid type of container");
            }
        }
        else if(command.startsWith("operationDeleteContainer")) //operationDeleteContainerAt <PosX>_<PosY>
        {
            String container=command.substring(24);
            if(container.startsWith("At")) // operationDeleteContainerAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty())
                        for(ContainerPos cp : constructedMap.getGameMap()) {
                            if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                if(constructedMap.getContainers().contains(cp.getContainer())){
                                    constructedMap.getContainers().remove(cp.getContainer());
                                }
                                constructedMap.getGameMap().remove(cp);
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
        else if(command.startsWith("operationDeletePlayer")) //operationDeletePlayer<Playernumber>
        {
            String playernumber=command.substring(21); //<Playernumber>
            if(playernumber.length()==1)
            {
                if(StrFunctions.isDigit(playernumber.charAt(0)))
                {
                    if(!map.getPlayers().isEmpty()){
                        map.getPlayers().remove(playernumber);
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
        else if(command.startsWith("operationCreatePlayer")) //operationCreatePlayer <Playertype> At <PosX>_<PosY>

        {
            String playerpos=command.substring(21);
            if(playerpos.startsWith("MechanicAt")) //operationCreatePlayerMechanicAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(playerpos,"MechanicAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty()) {
                        for (ContainerPos cp : constructedMap.getGameMap()) {
                            if (cp.getPosX() == positions[0] && cp.getPosY() == positions[1]) {
                                constructedMap.getPlayers().add(new Mechanic(cp.getContainer()));
                                break;
                            }
                        }
                    }
                    else
                        System.out.println("There's no Container where Mechanic could be created");
                }
            }
            else  if(playerpos.startsWith("SaboteurAt")) //operationCreatePlayerSaboteurAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(playerpos,"SaboteurAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    if(!constructedMap.getGameMap().isEmpty()) {
                        for (ContainerPos cp : constructedMap.getGameMap()) {
                            if (cp.getPosX() == positions[0] && cp.getPosY() == positions[1]) {
                                constructedMap.getPlayers().add(new Saboteur(cp.getContainer()));
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
                            for (ContainerPos cp : constructedMap.getGameMap()) {
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
