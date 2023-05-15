package console;

import container.ContainerPos;
import container.Pipe;
import exception.MyException;
import map.Map;
import player.Player;
import player.Type;

/**
 * Enumeráció, a játékban lehetséges irányokat adja meg.
 * Mivel 2D-ben dolgozunk, ezért 4 irány lesz (+invalid)
 */
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
     * A player irányításáért felelős függvény amely a bemeneti commandokat olvasva
     * dönti el, hogy a játékos merre mozogjon, illetve milyen tevékenységet vigyen végbe(DetachPipe, AdjustPump, stb...)
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
                    /**
                     * Ha a parancs moveTo, akkor valamerre mozgatni akarjuk a játékost.
                     */
                    if(newcmd.startsWith("moveTo")) //player <Player> moveTo <Direction>
                    {
                        String direction=newcmd.substring(6); // <Direction>
                        Directions dir = Directions.Invalid;
                        /**
                         * Balra, jobbra, fel vagy le.
                         */
                        if(direction.equals("Left"))
                            dir=Directions.Left;
                        else if(direction.equals("Right"))
                            dir = Directions.Right;
                        else if(direction.equals("Up"))
                            dir = Directions.Up;
                        else if(direction.equals("Down"))
                            dir = Directions.Down;
                        /**
                         * Ha rossz irányt adtunk meg.
                         */
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

                        /**
                         * Megadott iránytól függően megtörténik a mozgatás.
                         */
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
                    /**
                     * Ha a parancs AdjustPumpOutputTo, akkor átállítjuk a Pump output-ját.
                     */
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
                        /**
                         * Invalid irány :(
                         */
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

                        /**
                         * Na itt történik a mozgatás igazából.
                         */
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
                    /**
                     * Itt ugyanaz, mint az előbb, csak itt a Pump Input-ját állítjuk át.
                     */
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

                        /**
                         * Az átállítás itt történik.
                         */
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

                    /**
                     * Itt szépen kilyukasztjuk a csövet.
                     */
                    //Cső kilyukasztása
                    else if(newcmd.equals("LeakPipe")) //player <Player> LeakPipe
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.LeakPipe();
                            }
                        }
                    }

                    /**
                     * Itt a csövet csatlakoztatjuk egy Pump-hoz.
                     */
                    //Cső csatlakoztatása pumpához
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
                    /**
                     * Leszedjük a csövet a pumpáról.
                     */
                    else if(newcmd.startsWith("DetachPipeAt")) //player <Player> DetachPipe <PosX>_<PosY>
                    {
                        String new2cmd=newcmd.substring(10); //<PosX>_<PosY>
                        if(new2cmd.startsWith("At"))
                        {
                            int positions[]= StrFunctions.subPosString(new2cmd,"At"); //<PosX>_<PosY>
                            if(positions[0]!=-1 && positions[1]!= -1)
                            {
                                ContainerPos cp = new ContainerPos();
                                Player p = new Player();

                                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                                    if(containerPos.getPosX() == positions[0] && containerPos.getPosY() == positions[1]){
                                        cp = containerPos;
                                        break;
                                    }
                                }
                                for(Player player : Map.getInstance().getPlayers()){
                                    if(player.getId() == playernumber){
                                        p = player;
                                        break;
                                    }
                                }
                                p.detachPipe(cp);
                            }
                        }
                    }
                    //Cső javítása
                    else if(newcmd.equals("RepairPipe")) //player <Player> RepairPipe
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.RepairPipe();
                            }
                        }
                    }
                    //Pumpa javításq
                    else if(newcmd.equals("RepairPump")) //player <Player> RepairPump
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.RepairPump();
                            }
                        }
                    }
                    //Cső csúszóssá tétele
                    else if(newcmd.equals("MakePipeSlippery")) //player <Player> MakePipeSlippery
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.makePipeSlippery();
                            }
                        }
                    }
                    //Cső ragadóssá tétele
                    else if(newcmd.equals("MakePipeSticky")) //player <Player> MakePipeSticky
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == playernumber){
                                player.makePipeSticky();
                            }
                        }
                    }
                    /**
                     * Ha rossz parancsot adtunk meg.
                     */
                    else
                    {
                        System.out.println("Unknown command");
                    }
                }
                /**
                 * Ha nem adtunk meg számot a player id-jának.
                 */
                else
                {
                    System.out.println("You must give a player's number");
                }
            }
            /**
             * Ha rosszul paramétereztük a parancsot
             */
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        /**
         * Ha rossz parancsot adtunk meg.
         */
        else
        {
            System.out.println("Unknown command");
        }
    }
}
