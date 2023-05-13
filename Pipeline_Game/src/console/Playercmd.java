package console;

import container.ContainerPos;
import container.Pipe;
import exception.MyException;
import map.Map;
import player.Player;
import player.Type;

enum Directions{
    Left,
    Right,
    Up,
    Down,
    Invalid;
}

public class Playercmd
{
    
    /** 
     * @param command
     * @throws MyException
     */
    public static void player(String command) throws MyException {
        if(command.startsWith("player"))
        {
            if(command.length()>6)
            {
                if(StrFunctions.isDigit(command.charAt(6)))
                {
                    int playernumber=Character.getNumericValue(command.charAt(6)); //<Player>
                    String newcmd=command.substring(7);
                    if(newcmd.startsWith("moveTo")) //player <Player> moveTo <Direction> //TODO The given Container is not steppable exception
                    {
                        String direction=newcmd.substring(6); // <Direction>
                        Directions dir = Directions.Invalid;

                        if(direction.equals("Left"))
                            dir=Directions.Left;
                        else if(direction.equals("Right"))
                            dir = Directions.Right;
                        else if(direction.equals("Up"))
                            dir = Directions.Up;
                        else if(direction.equals("Down"))
                            dir = Directions.Down;
                        else
                        {
                            System.out.println("Invalid direction");
                        }
                        Player p = new Player();
                        ContainerPos cp = new ContainerPos();

                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                p = player;
                            }
                        }

                        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                            if(containerPos.getContainer().equals(p.getPosition())){
                                cp = containerPos;
                            }
                        }

                        switch(dir){
                            case Left :{
                                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                                    if((containerPos.getPosX() == cp.getPosX() - 1) && containerPos.getPosY() == cp.getPosY()){
                                        p.Move(containerPos.getContainer());
                                    }
                                }
                                break;
                            }
                            case Right:{
                                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                                    if((containerPos.getPosX() == cp.getPosX() + 1) && containerPos.getPosY() == cp.getPosY()){
                                        p.Move(containerPos.getContainer());
                                    }
                                }
                                break;
                            }
                            case Up:{
                                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                                    if((containerPos.getPosY() == cp.getPosY() -1) && containerPos.getPosX() == cp.getPosX()){
                                        p.Move(containerPos.getContainer());
                                    }
                                }
                                break;
                            }
                            case Down:{
                                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                                    if((containerPos.getPosY() == cp.getPosY() + 1) && containerPos.getPosX() == cp.getPosX()){
                                        p.Move(containerPos.getContainer());
                                    }
                                }
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                    }
                    else if(newcmd.startsWith("AdjustPumpOutputTo")) //player <Player> AdjustPumpTo <Direction>
                    {
                        String direction=newcmd.substring(18); // <Direction>
                        Directions dir = Directions.Invalid;
                        if(direction.equals("Left"))
                            dir = Directions.Left;
                        else if(direction.equals("Right"))
                            dir = Directions.Right;
                        else if(direction.equals("Up"))
                            dir = Directions.Up;
                        else if(direction.equals("Down"))
                            dir = Directions.Down;
                        else
                        {
                            System.out.println("Invalid direction");
                        }

                        Player p = new Player();
                        ContainerPos cp = new ContainerPos();

                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                p = player;
                            }
                        }
                        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                            if(cp.getContainer() == p.getPosition()){
                                cp = containerPos;
                            }
                        }

                        switch(dir) {
                            case Left: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosX() == cp.getPosX() - 1) && containerPos.getPosY() == cp.getPosY()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Output);
                                    }
                                }
                                break;
                            }
                            case Right: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosX() == cp.getPosX() + 1) && containerPos.getPosY() == cp.getPosY()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Output);
                                    }
                                }
                                break;
                            }
                            case Up: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosY() == cp.getPosY() - 1) && containerPos.getPosX() == cp.getPosX()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Output);
                                    }
                                }
                                break;
                            }
                            case Down: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosY() == cp.getPosY() + 1) && containerPos.getPosX() == cp.getPosX()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Output);
                                    }
                                }
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                    else if(newcmd.startsWith("AdjustPumpInputTo")) //player <Player> AdjustPumpTo <Direction>
                    {
                        String direction = newcmd.substring(17); // <Direction>
                        Directions dir = Directions.Invalid;
                        if (direction.equals("Left"))
                            dir = Directions.Left;
                        else if (direction.equals("Right"))
                            dir = Directions.Right;
                        else if (direction.equals("Up"))
                            dir = Directions.Up;
                        else if (direction.equals("Down"))
                            dir = Directions.Down;
                        else {
                            System.out.println("Invalid direction");
                        }

                        Player p = new Player();
                        ContainerPos cp = new ContainerPos();

                        for (Player player : Map.getInstance().getPlayers()) {
                            if (player.getId() == playernumber) {
                                p = player;
                            }
                        }
                        for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                            if (cp.getContainer() == p.getPosition()) {
                                cp = containerPos;
                            }
                        }

                        switch (dir) {
                            case Left: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosX() == cp.getPosX() - 1) && containerPos.getPosY() == cp.getPosY()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Input);
                                    }
                                }
                                break;
                            }
                            case Right: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosX() == cp.getPosX() + 1) && containerPos.getPosY() == cp.getPosY()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Input);
                                    }
                                }
                                break;
                            }
                            case Up: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosY() == cp.getPosY() - 1) && containerPos.getPosX() == cp.getPosX()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Input);
                                    }
                                }
                                break;
                            }
                            case Down: {
                                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if ((containerPos.getPosY() == cp.getPosY() + 1) && containerPos.getPosX() == cp.getPosX()) {
                                        p.adjustPump((Pipe) containerPos.getContainer(), Type.Input);
                                    }
                                }
                                break;
                            }
                            default: {
                                break;
                            }

                        }
                    }
                    else if(newcmd.equals("LeakPipe")) //player <Player> LeakPipe
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.LeakPipe();
                            }
                        }
                    }
                    else if(newcmd.startsWith("AttachPipeTo")) //player <Player> AttachPipeTo<PosX>_<PosY> //TODO vmiért nemlehet minden irányba illeszteni lásd lejjebb
                    {
                        int positions[]= StrFunctions.subPosString(newcmd,"AttachPipeTo"); //<PosX>_<PosY>
                        if(positions[0]!=-1 && positions[1]!= -1)
                        {
                            for(Player player : Map.getInstance().getPlayers()){
                                if(player.getId() == playernumber){
                                    if(!player.getCarriedPipes().isEmpty())
                                        player.attachPipe(positions[0], positions[1]);
                                    else
                                        System.out.println("No suitable pipe to place (aka player carries no pipe)");
                                }
                            }
                        }
                    }
                    else if(newcmd.equals("AttachPump")) //player <Player> AttachPump //TODO ez még hiányzik
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                if(player.getCarriedPump() != null){
                                    player.attachPump();
                                }
                                else
                                    System.out.println("No suitable pump to place (aka player carries no pump)");
                            }
                        }
                    }
                    else if(newcmd.startsWith("DetachPipeAt")) //player <Player> DetachPipe <PosX>_<PosY>
                        /* TODO ez nemteljesen mükődik jól, meg instance of helyett kéne vmi más,
                            fura mert ha player 2_2-ben egy pipe-t elhelyez 1_2-re, majd azt elveszi
                            akkor  "No suitable pump to place (aka player carries no pump)" iródik ki, meg ha
                            utána kirajzoljuk a map-et akkor ottmarad az elhelyezett pipe, de ha megpróbálunk rálépni
                            exceptiont kapunk:
                            operationCreateTestMap
                            manualTeleportPlayer1To2_2
                            manualCreateContainerPipeAtPlayer1
                            player1AttachPipeTo1_2 (amugy pl itt player1AttachPipeTo3_2 v player1AttachPipeTo2_3 nemmüködik)
                            player1DetachPipeAt1_2 -> "No suitable pump to place (aka player carries no pump)"
                            operationPrintMap -> ottvan a detachelt pipe
                            player1moveToLeft ->Exception: Not even next to it

                         */
                    {
                        String new2cmd=newcmd.substring(10); //<PosX>_<PosY>
                        if(new2cmd.startsWith("At"))
                        {
                            int positions[]= StrFunctions.subPosString(new2cmd,"At"); //<PosX>_<PosY>
                            if(positions[0]!=-1 && positions[1]!= -1)
                            {
                                ContainerPos cp = new ContainerPos();
                                for(ContainerPos containerPos : Map.getInstance().getGameMap()) {
                                    if(containerPos.getPosX() == positions[0] && containerPos.getPosY() == positions[1]) {
                                        cp = containerPos;
                                    }
                                }
                                for(Player player : Map.getInstance().getPlayers()){
                                    if(player.getId() == playernumber) {
                                        if(cp.getContainer() instanceof Pipe) //TODO használd a tricket amit én (consoleprint ==PI\t)
                                            player.detachPipe(cp);
                                        else
                                            System.out.println("Found position is not valid");
                                    }else
                                        System.out.println("No suitable pump to place (aka player carries no pump)");
                                }
                            }
                        }
                    }
                    else if(newcmd.equals("RepairPipe")) //player <Player> RepairPipe //TODO az ilyeneknél kéne kezelni exceptiont, vagy jó az ha lefagy a program? pl ha olyat javit meg ami nem rossz
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.RepairPipe();
                            }
                        }
                    }
                    else if(newcmd.equals("RepairPump")) //player <Player> RepairPump
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.RepairPump();
                            }
                        }
                    }
                    else if(newcmd.equals("MakePipeSlippery")) //player <Player> MakePipeSlippery
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.makePipeSlippery();
                            }
                        }
                    }
                    else if(newcmd.equals("MakePipeSticky")) //player <Player> MakePipeSticky
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.makePipeSticky();
                            }
                        }
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
