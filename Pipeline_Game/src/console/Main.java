package console;

import exception.MyException;

import java.util.Scanner;
import container.*;
import map.Map;

public class Main
{
    public static void main(String[] args) throws MyException
    {
        Scanner scanner = new Scanner(System.in);
        String command="";

        Operation.testMap();

        Map m = Map.loadFromFile("testMap.txt");

        Operation.printMap(m.getGameMap());

        /*
        while (true)
        {
            System.out.print("> ");
            command = scanner.nextLine();
            if (command.isEmpty()) {
                continue;
            }

            if(command.startsWith("operation"))
            {
                Operation.operation(command);
            }
            else if(command.startsWith("player"))
            {
                Playercmd.player(command);
            }
            else if(command.startsWith("manual"))
            {
                Manual.manual(command);
            }
            else if(command.startsWith("list"))
            {
                List.list(command);
            }
            else if(command.startsWith("random"))
            {
                Random.random(command);
            }
            else
            {
                System.out.println("Unknown command");
            }
        }

         */
    }
}