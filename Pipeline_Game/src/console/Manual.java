package console;

public class Manual
{
    public static void manual(String command)
    {
        if(command.startsWith("manualCreateContainer")) //manualCreateContainer <Containertype>
        {
            String newcmd=command.substring(21);
            if(newcmd.startsWith("PipeAtCistern")) //manualCreateContainerPipeAtCistern
            {
                //TODO
            }
            else if(newcmd.equals("PumpAtCistern")) //manualCreateContainerPumpAtCistern
            {
                //TODO
            }
            else if(newcmd.equals("PipeAtPlayer")) //manualCreateContainerPipeAtPlayer<Player>
            {
                String playernumber=newcmd.substring(12); //<Player>
                if(playernumber.length()==1)
                {
                    if(StrFunctions.isDigit(playernumber.charAt(0)))
                    {
                        //TODO
                    }
                    else
                        System.out.println("Give a valid integer for player's number");
                }
                else
                {
                    System.out.println("The interval of players number is 0-9");
                }
            }
            else if(newcmd.startsWith("PumpAtPlayer")) //manualCreateContainerPumpAtPlayer<Player>
            {
                String playernumber=newcmd.substring(12); //<Player>
                if(playernumber.length()==1)
                {
                    if(StrFunctions.isDigit(playernumber.charAt(0)))
                    {
                        //TODO
                    }
                    else
                        System.out.println("Give a valid integer for player's number");
                }
                else
                {
                    System.out.println("The interval of a player's number is 0-9");
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        else if(command.equals("manualFlowWater")) //manualFlowWater
        {
            //TODO
        }
        else if(command.startsWith("manualSetTurnCount")) //manualSetTurnCount <Turns>
        {
            String turncount=command.substring(18); //<Turns>
            boolean gotchar=false;
            for(int i=0; i<turncount.length(); i++)
            {
                if(!StrFunctions.isDigit(turncount.charAt(i)))
                {
                    gotchar=true;
                    System.out.println("The turn count must be an integer");
                    break;
                }
            }
            if(!gotchar && turncount!="")
            {
                //TODO
            }
        }
        else if(command.startsWith("manualDamageContainer")) //manualDamageContainerAt <PosX> <PosY>
        {
            String newcmd=command.substring(21);
            if(newcmd.startsWith("At")) // manualDamageContainerAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    //TODO
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        else if(command.startsWith("manualMakePipeSlippery")) //manualMakePipeSlipperyAt <PosX> <PosY>
        {
            String newcmd=command.substring(22);
            if(newcmd.startsWith("At")) // manualMakePipeSlipperyAt <PosX> <PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    //TODO
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        else if(command.startsWith("manualMakePipeSticky")) //manualMakePipeStickyAt <PosX> <PosY>
        {
            String newcmd=command.substring(20);
            if(newcmd.startsWith("At")) // manualMakePipeStickyAt <PosX> <PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    //TODO
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        else if(command.startsWith("manualTeleportPlayer")) //manualTeleportPlayer<Player>To <PosX> <PosY>
        {
            if(command.length()>20)
            {
                if(StrFunctions.isDigit(command.charAt(20)))
                {
                    int playernumber=Character.getNumericValue(command.charAt(20)); //<Player>
                    String newcmd=command.substring(21);
                    if(newcmd.startsWith("To")) //manualTeleportPlayer<Player>To <PosX> <PosY>
                    {
                        int positions[]= StrFunctions.subPosString(newcmd,"To"); //<PosX>_<PosY>
                        if(positions[0]!=-1 && positions[1]!= -1)
                        {
                            //TODO
                        }
                    }
                    else
                    {
                        System.out.println("Invalid use of command");
                    }
                }
                else
                    System.out.println("Give a valid integer for player's number");
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        else
        {
            System.out.println("Unknown command");
        }
    }
}
