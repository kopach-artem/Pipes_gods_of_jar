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
            
        }
        else if(command.equals("randomPumpBreakdownTurnOff"))
        {
            ArrayList<Container> container = Map.getInstance().getContainers();
            for(Container c : container){
                if(c.getClass() == Pump.class)
                {
                    c.setBreakOff();
                }
                else{
                    continue;
                }
            }
        }
        else
        {
            System.out.println("Unknown command");
        }
    }
}
