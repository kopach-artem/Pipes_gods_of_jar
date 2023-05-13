package console;

import container.Container;
import container.ContainerPos;
import container.Pipe;
import container.Pump;
import controller.Controller;
import map.Map;
import player.Player;

import java.util.ArrayList;

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
            int sum=0;
            int sum2=0;
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                sum++;
                Container con=c.getContainer();
                if(con.isDamaged())
                {
                    sum2++;
                    System.out.println("There is a damaged "+con.consolePrint() +"at X: "+c.getPosX()+ " Y: "+c.getPosY());
                }
            }
            if(sum==0)
                System.out.println("The map is empty");
            else if(sum2==0)
                System.out.println("There are no damaged containers on the map");
        }
        else if(command.startsWith("listConnectedContainers")) //listConnectedContainers
        {
            if(command.length()==23) //listConnectedContainers
            {
                int sum=0;
                int sum2=0;
                for(ContainerPos c:Map.getInstance().getGameMap())
                {
                    sum++;
                    Container con=c.getContainer();
                    for(ContainerPos c2:Map.getInstance().getGameMap())
                    {
                        Container con2=c2.getContainer();
                        if(con.seeifNeighbors(con2) && con!=con2)
                        {
                            sum2++;
                            System.out.println("The "+ con.consolePrint() +"at X: "+c.getPosX()+ " Y: "+c.getPosY()+" is connected to a "+con2.consolePrint()+"at X:"+ c2.getPosX()+" Y:"+c2.getPosY());
                        }
                    }
                }
                if(sum==0)
                    System.out.println("The map is empty");
                else if(sum2==2)
                    System.out.println("Nothing is connected on the map");
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

                    }
                    if(sum==0)
                        System.out.println("The map is empty");
                    else if(sum2==0)
                        System.out.println("There is no container at the given position");
                    else if(sum3==0)
                        System.out.println("There is nothing connected to this container");
                }
                else
                {
                    System.out.println("Invalid use of command");
                }
            }
        }
        else if(command.equals("listSlipperyPipes")) //listSlipperyPipes
        {
            int sum=0;
            int sum2=0;
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                sum++;
                Container con=c.getContainer();
                if(con.getIsSlippery())
                {
                    sum2++;
                    System.out.println("There is a slippery pipe at X: "+c.getPosX()+ " Y: "+c.getPosY());
                }
            }
            if(sum==0)
                System.out.println("The map is empty");
            else if(sum2==0)
                System.out.println("There are no slippery pipes on the map");
        }
        else if(command.equals("listStickyPipes")) //listStickyPipes
        {
            int sum=0;
            int sum2=0;
            for(ContainerPos c:Map.getInstance().getGameMap())
            {
                sum++;
                Container con=c.getContainer();
                if(con.getIsSticky())
                {
                    sum2++;
                    System.out.println("There is a sticky pipe at X: "+c.getPosX()+ " Y: "+c.getPosY());
                }
            }
            if(sum==0)
                System.out.println("The map is empty");
            else if(sum2==0)
                System.out.println("There are no sticky pipes on the map");
        }
        else if(command.startsWith("listPlayer"))
        {

            if(command.length()>10)
            {
                String newcmd=command.substring(10);
                if(newcmd.startsWith("sPos")) //listPlayersPos
                {
                    int sum=0;
                    ArrayList<Player> players=Map.getInstance().getPlayers();
                    for(Player p:players)
                    {
                        sum++;
                        Container con=p.getPosition();
                        for(ContainerPos c:Map.getInstance().getGameMap())
                        {
                            if(c.getContainer()==con)
                            System.out.println("Player"+p.getId()+" is at X: "+c.getPosX()+" Y:"+c.getPosY());
                        }
                    }
                    if(sum==0)
                        System.out.println("There are no players on the map");
                }
                else if(StrFunctions.isDigit(newcmd.charAt(0)))
                {
                    int playernumber=Character.getNumericValue(newcmd.charAt(0)); //<Player>
                    newcmd=newcmd.substring(1);
                    if(newcmd.equals("Pos")) //listPlayer<Player>Pos
                    {
                        int sum=0;
                        ArrayList<Player> players=Map.getInstance().getPlayers();
                        for(Player p:players)
                        {
                            if(p.getId()==playernumber)
                            {
                                sum++;
                                Container con=p.getPosition();
                                for(ContainerPos c:Map.getInstance().getGameMap())
                                {
                                    if(c.getContainer()==con)
                                        System.out.println("Player"+p.getId()+" is at X: "+c.getPosX()+" Y:"+c.getPosY());
                                }
                            }
                        }
                        if(sum==0)
                            System.out.println("There is no player with the given playernumber");
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
            System.out.println(Controller.getTurnCount());
        }
        else if(command.startsWith("listPump"))
        {
            String newcmd=command.substring(8);
            if(newcmd.equals("sDamageTurn")) //listPumpsDamageTurn
            {
                int sum=0;
                int sum2=0;
                for(ContainerPos c:Map.getInstance().getGameMap())
                {
                    sum++;
                    Container con=c.getContainer();
                    String str=con.consolePrint();
                    if(str.equals("PU\t"))
                    {
                        sum2++;
                        if(((Pump)c.getContainer()).getRandomDamageValue()!=-1)
                            System.out.println("The pump at X:"+c.getPosX()+" Y: "+c.getPosY()+" will be damaged in turn " +((Pump)c.getContainer()).getRandomDamageValue());
                        else
                            System.out.println("The pump at X:"+c.getPosX()+" Y: "+c.getPosY()+" won't be damaged in a random turn, because the random pump breakdown is off");
                    }

                }
                if(sum==0)
                    System.out.println("The map is empty");
                else if(sum2==0)
                    System.out.println("There are no pumps on the map");
            }
            else if(newcmd.equals("sDirection")) //listPumpsDirection
            {
                int sum=0;
                int sum2=0;

                int maxX = -1;
                int maxY = -1;

                // Find the maximum x and y values
                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                    if (containerPos.getPosX() > maxX) {
                        maxX = containerPos.getPosX();
                    }
                    if (containerPos.getPosY() > maxY) {
                        maxY = containerPos.getPosY();
                    }
                }

                // Create a 2D grid to store the containers
                Container[][] grid = new Container[maxX + 1][maxY + 1];

                // Fill the grid with null
                for (int i = 0; i <= maxX; i++) {
                    for (int j = 0; j <= maxY; j++) {
                        grid[i][j] =null;
                    }
                }

                // Place the containers in the grid
                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                    int x = containerPos.getPosX();
                    int y = containerPos.getPosY();
                    grid[x][y] = containerPos.getContainer();
                }

                for(ContainerPos c:Map.getInstance().getGameMap())
                {
                    sum++;
                    if(c.getContainer().consolePrint().equals("PU\t"))
                    {
                        Container in=c.getContainer().getInput();
                        Container out=c.getContainer().getOutput();
                        String dirin="";
                        String dirout="";
                        sum2++;
                        for (int y = 0; y <= maxY; y++)
                        {
                            for (int x = 0; x <= maxX; x++)
                            {
                                if(c.getPosX()==x && c.getPosY()==y)
                                {
                                    if(grid[x-1][y]!=null)
                                    {
                                        if(in==grid[x-1][y])
                                            dirin="Left";
                                    }
                                    else if(grid[x+1][y]!=null)
                                    {
                                        if(in==grid[x+1][y])
                                            dirin="Right";
                                    }
                                    else if(grid[x][y-1]!=null)
                                    {
                                        if(in==grid[x][y-1])
                                            dirin="Up";
                                    }
                                    else if(grid[x][y+1]!=null)
                                    {
                                        if(in==grid[x][y+1])
                                            dirin="Down";
                                    }

                                    if(grid[x-1][y]!=null)
                                    {
                                        if(out==grid[x-1][y])
                                            dirout="Left";
                                    }
                                    else if(grid[x+1][y]!=null)
                                    {
                                        if(out==grid[x+1][y])
                                            dirout="Right";
                                    }
                                    else if(grid[x][y-1]!=null)
                                    {
                                        if(out==grid[x][y-1])
                                            dirout="Up";
                                    }
                                    else if(grid[x][y+1]!=null)
                                    {
                                        if(out==grid[x][y+1])
                                            dirout="Down";
                                    }
                                }
                            }
                        }
                        System.out.println("The pump at X:"+c.getPosX()+" Y: "+c.getPosY()+" is pumping from "+dirin+" to"+dirout);
                    }
                }
                if(sum==0)
                    System.out.println("The map is empty");
                else if(sum2==0)
                    System.out.println("There are no pumps on the map");
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
                        int sum=0;
                        for(ContainerPos c:Map.getInstance().getGameMap())
                        {

                            Container con=c.getContainer();
                            String str=con.consolePrint();
                            if(str.equals("PU\t") && c.getPosX()==positions[0] && c.getPosY()==positions[1])
                            {
                                sum++;
                                System.out.println("The pump at X:"+c.getPosX()+" Y: "+c.getPosY()+" will be damaged in turn " +((Pump)c.getContainer()).getRandomDamageValue());
                            }

                        }
                        if(sum==0)
                            System.out.println("There is no pump on the map, with the given position");
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
