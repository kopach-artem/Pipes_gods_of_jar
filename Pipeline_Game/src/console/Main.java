package console;

import exception.MyException;

import java.util.Scanner;
import container.*;
import map.Map;

public class Main
{
    
    /**
     * A prototípus main osztálya. Itt történik a bemenet beolvsasáa,
     * illetve a metódushívások is itt történnek.
     * @param args - String[]
     * @throws MyException
     */
    public static void main(String[] args) throws MyException
    {
        Scanner scanner = new Scanner(System.in);
        String command="";

        /**
         * Végtelen ciklus, itt várjuk a parancsokat. exit paranccsal tudunk kilépni a programból.
         */
        while (true)
        {
            System.out.print("> ");
            command = scanner.nextLine();
            if (command.isEmpty()) {
                continue;
            }

            /**
             * Az operation műveletek itt kezdődnek.
             */
            if(command.startsWith("operation"))
            {
                try
                {
                    Operation.operation(command);
                }
                catch (MyException e)
                {
                    System.out.println(e.getMessage());

                }

            }

            /**
             * A player parancsokhoz tartozó metódushívás itt történik.
             */
            else if(command.startsWith("player"))
            {
                try
                {
                    Playercmd.player(command);
                }
                catch (MyException e)
                {
                    System.out.println(e.getMessage());
                }

            }

            /**
             * A manual parancsokhoz tartozó metódushívás itt található.
             */
            else if(command.startsWith("manual"))
            {

                    Manual.manual(command);
            }

            /**
             * A list parancsokhoz tartozó metódushívás itt található.
             */
            else if(command.startsWith("list"))
            {
                    List.list(command);
            }

            /**
             * A random pumpaelromlás itt kapcsolható ki és be.
             */
            else if(command.startsWith("random"))
            {
                    Random.random(command);
            }

            /**
             * Kiléphetünk a programból.
             */
            else if(command.startsWith("exit")){
                System.exit(0);
            }

            /**
             * Érvénytelen parancs.
             */
            else
            {
                System.out.println("Unknown command");
            }
        }
    }
}