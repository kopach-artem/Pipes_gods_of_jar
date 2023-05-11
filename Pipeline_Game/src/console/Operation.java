package console;

import container.*;
import exception.MyException;
import map.Map;

import java.util.ArrayList;

public class Operation
{
    public static Map map = new Map();


    public static void printMap(ArrayList<ArrayList<Container>> gameMap) {
        for (int i = 0; i < gameMap.size(); i++) {
            for (int j = 0; j < gameMap.get(i).size(); j++)
            {
                System.out.print('C');
                if(gameMap.get(i).get(j).seeifNeighbors(gameMap.get(i).get(j+1)))
                {
                    System.out.print('-');
                }
                else if(gameMap.get(i).get(j).seeifNeighbors(gameMap.get(i+1).get(j)))
                {
                    System.out.print('|');
                }
                else
                {
                    System.out.print(' ');
                }
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


        //Első sor
        ArrayList<Container> row1 = new ArrayList<Container>();
        row1.add(ms);
        row1.add(pipe1);
        row1.add(pump1);
        row1.add(pipe2);
        row1.add(cs);

        //Második sor
        ArrayList<Container> row2 = new ArrayList<Container>();
        row2.add(pipe3);

        //Harmadik sor
        ArrayList<Container> row3 = new ArrayList<Container>();
        row3.add(pump2);

        map.getGameMap().add(row1);
        map.getGameMap().add(row2);
        map.getGameMap().add(row3);

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
                    // TODO
                }
            }
            else if(container.startsWith("MountainSpring")) //operationCreateContainerMountainSpringAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"MountainSpringAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    // TODO
                }
            }
            else if(container.startsWith("Pipe")) //operationCreateContainerPipeAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PipeAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    // TODO
                }
            }
            else if(container.startsWith("Pump")) //operationCreateContainerPumpAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(container,"PumpAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    // TODO
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
                    // TODO
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
                    // TODO
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
                    // TODO
                }
            }
            else  if(playerpos.startsWith("SaboteurAt")) //operationCreatePlayerSaboteurAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(playerpos,"SaboteurAt"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    // TODO
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
                        //TODO
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
