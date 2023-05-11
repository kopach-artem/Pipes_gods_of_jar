package console;

public class Playercmd
{
    public static void player(String command)
    {
        if(command.startsWith("player"))
        {
            if(command.length()>6)
            {
                if(StrFunctions.isDigit(command.charAt(6)))
                {
                    int playernumber=Character.getNumericValue(command.charAt(6)); //<Player>
                    String newcmd=command.substring(7);
                    if(newcmd.startsWith("moveTo")) //player <Player> moveTo <Direction>
                    {
                        String direction=newcmd.substring(6); // <Direction>
                        if(direction.equals("Left"))
                            direction="Left";
                        else if(direction.equals("Right"))
                            direction="Right";
                        else if(direction.equals("Up"))
                            direction="Up";
                        else if(direction.equals("Down"))
                            direction="Down";
                        else
                        {
                            System.out.println("Invalid direction");
                            direction="";
                        }

                        // TODO
                    }
                    else if(newcmd.startsWith("AdjustPumpTo")) //player <Player> AdjustPumpTo <Direction>
                    {
                        String direction=newcmd.substring(6); // <Direction>
                        if(direction.equals("Left"))
                            direction="Left";
                        else if(direction.equals("Right"))
                            direction="Right";
                        else if(direction.equals("Up"))
                            direction="Up";
                        else if(direction.equals("Down"))
                            direction="Down";
                        else
                        {
                            System.out.println("Invalid direction");
                            direction="";
                        }

                        // TODO
                    }
                    else if(newcmd.equals("LeakPipe")) //player <Player> LeakPipe
                    {
                        // TODO
                    }
                    else if(newcmd.equals("AttachPipe")) //player <Player> AttachPipe
                    {
                        // TODO
                    }
                    else if(newcmd.equals("AttachPump")) //player <Player> AttachPump
                    {
                        // TODO
                    }
                    else if(newcmd.equals("DetachPipe")) //player <Player> DetachPipe
                    {
                        // TODO
                    }
                    else if(newcmd.equals("RepairPipe")) //player <Player> RepairPipe
                    {
                        // TODO
                    }
                    else if(newcmd.equals("RepairPump")) //player <Player> RepairPump
                    {
                        // TODO
                    }
                    else if(newcmd.equals("MakePipeSlippery")) //player <Player> MakePipeSlippery
                    {
                        // TODO
                    }
                    else if(newcmd.equals("MakePipeSticky")) //player <Player> MakePipeSticky
                    {
                        // TODO
                    }
                    else
                    {
                        System.out.println("Unknown command");
                    }
                }
                else
                {
                    System.out.println("You must give a player's number");
                }
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
