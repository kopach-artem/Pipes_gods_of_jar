package console;

import java.util.ArrayList;

import container.Container;
import container.ContainerPos;
import container.Pump;
import map.Map;

public class Random
{
    
    /** 
     * @param command
     */
    public static void random(String command)
    {
        if(command.equals("randomPumpBreakdownTurnOn"))
        {
            java.util.Random rand=new java.util.Random();
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                c.getContainer().setBreakOff(rand.nextInt(11) + 10);
            }
        }
        else if(command.equals("randomPumpBreakdownTurnOff"))
        {
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                c.getContainer().setBreakOff(-1);
            }
        }
        else
        {
            System.out.println("Unknown command");
        }
    }
}
