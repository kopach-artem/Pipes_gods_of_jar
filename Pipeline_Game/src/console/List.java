package console;

import container.Container;
import container.ContainerPos;
import map.Map;

public class List
{
    public static void list(String command)
    {
        if(command.equals("listContainers")) //listContainer
        {
            int sum=0;
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                sum++;
                Container con=c.getContainer();
                System.out.println("There is a "+con.consolePrint() +"at X: "+c.getPosX()+ " Y: "+c.getPosY());
            }
            if(sum==0)
                System.out.println("The map is empty");
        }
        else if(command.equals("listDamagedContainers")) //listDamagedContainers
        {
            //TODO
        }
        else if(command.startsWith("listConnectedContainers")) //listConnectedContainers
        {
            if(command.length()==23) //listConnectedContainers
            {
                int sum=0;
                for(ContainerPos c:Map.getInstance().getGameMap())
                {
                    sum++;
                    Container con=c.getContainer();
                    for(ContainerPos c2:Map.getInstance().getGameMap())
                    {
                        Container con2=c2.getContainer();
                        if(con.seeifNeighbors(con2) && con!=con2)
                        {
                            System.out.println("The "+ con.consolePrint() +"at X: "+c.getPosX()+ " Y: "+c.getPosY()+" is connected to a "+con2.consolePrint()+"at X:"+ c2.getPosX()+" Y:"+c2.getPosY());
                        }
                    }
                }
                if(sum==0)
                    System.out.println("The map is empty");
            }
            else //listConnectedContainersAt <PosX>_<PosY>
            {
                int sum=0;
                int sum2=0;
                int sum3=0;
                String newcmd=command.substring(23);
                if(newcmd.startsWith("At"))
                {
                    int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                    if(positions[0]!=-1 && positions[1]!= -1)
                    {
                        for(ContainerPos c:Map.getInstance().getGameMap())
                        {
                            sum++;
                            if(c.getPosX()==positions[0] && c.getPosY()==positions[1])
                            {
                                sum2++;
                                Container con=c.getContainer();
                                for(ContainerPos c2:Map.getInstance().getGameMap())
                                {
                                    Container con2=c2.getContainer();
                                    if(con.seeifNeighbors(con2) && con!=con2)
                                    {
                                        sum3++;
                                        System.out.println("The "+ con.consolePrint() +"at X: "+c.getPosX()+ " Y: "+c.getPosY()+" is connected to a "+con2.consolePrint()+"at X:"+ c2.getPosX()+" Y:"+c2.getPosY());
                                    }
                                }
                            }

                        }
                        if(sum2==0)
                            System.out.println("There is no container at the given position");
                        else if(sum3==0)
                            System.out.println("There is nothing connected to this container");
                    }
                    if(sum==0)
                        System.out.println("The map is empty");
                }
                else
                {
                    System.out.println("Invalid use of command");
                }
            }
        }
        else if(command.equals("listSlipperyPipes")) //listSlipperyPipes
        {
            // TODO
        }
        else if(command.equals("listStickyPipes")) //listStickyPipes
        {
            // TODO
        }
        else if(command.startsWith("listPlayer"))
        {

            if(command.length()>10)
            {
                String newcmd=command.substring(10);
                if(newcmd.startsWith("sPos")) //listPlayersPos
                {
                    // TODO
                }
                else if(StrFunctions.isDigit(newcmd.charAt(0)))
                {
                    int playernumber=Character.getNumericValue(newcmd.charAt(0)); //<Player>
                    newcmd=newcmd.substring(1);
                    if(newcmd.equals("Pos")) //listPlayer<Player>Pos
                    {
                        // TODO
                    }
                    else
                        System.out.println("The interval of a player's number is 0-9 or you're missing the end of command 'Pos'");
                }
                else
                    System.out.println("Invalid use of command");
            }
            else
                System.out.println("Invalid use of command");

        }
        else if(command.equals("listCurrentTurn")) //listCurrentTurn
        {
            // TODO
        }
        else if(command.startsWith("listPump"))
        {
            String newcmd=command.substring(8);
            if(newcmd.equals("sDamageTurn")) //listPumpsDamageTurn
            {
                // TODO
                System.out.println("Test");
            }
            else if(newcmd.equals("sDirection")) //listPumpsDirection
            {
                // TODO
                System.out.println("Test");
            }
            else if(newcmd.startsWith("At"))
            {
                boolean first_=false;
                String str1="";
                String str2="";
                for(int i=0; i<newcmd.length(); i++)
                {
                    if(newcmd.charAt(i)=='_')
                    {
                        first_=true;
                    }
                    if(first_)
                    {
                        if(StrFunctions.isDigit(newcmd.charAt(i+1)) && StrFunctions.isDigit(newcmd.charAt(i+2)))
                        {
                            str1=newcmd.substring(0,i+3);
                            str2=newcmd.substring(i+3);
                            break;
                        }
                        else if(StrFunctions.isDigit(newcmd.charAt(i+1)))
                        {
                            str1=newcmd.substring(0,i+2);
                            str2=newcmd.substring(i+2);
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid use of command");
                            break;
                        }
                    }
                }

                if(str2.equals("DamageTurn") && str1.startsWith("At")) //listPumpAt <PosX>_<PosY> DamageTurn
                {
                    int positions[]= StrFunctions.subPosString(str1,"At"); //<PosX>_<PosY>
                    if(positions[0]!=-1 && positions[1]!= -1)
                    {
                        // TODO
                    }
                }
                else if(str2.equals("Direction") && str1.startsWith("At")) //listPumpAt <PosX>_<PosY> Direction
                {
                    int positions[]= StrFunctions.subPosString(str1,"At"); //<PosX>_<PosY>
                    if(positions[0]!=-1 && positions[1]!= -1)
                    {
                        // TODO
                    }
                }
                else
                    System.out.println("Invalid use of command");
            }
            else
                System.out.println("Unknown command");
        }
    }
}
