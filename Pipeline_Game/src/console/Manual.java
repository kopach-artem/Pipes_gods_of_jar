package console;

import container.ContainerPos;
import container.Pipe;
import container.Pump;
import controller.Controller;
import map.Map;
import player.Player;

/**
 * Ez az osztály a pályaelemek manuális
 * létrehozásáért felelős.
 */
public class Manual
{
    
    /**
     * Az osztály egyetlen metódusa, itt történik a manual...
     * parancsok feldolgozása
     * @param command - A parancs String formában
     */
    public static void manual(String command)
    {
        if(command.startsWith("manualCreateContainer")) //manualCreateContainer <Containertype>
        {
            /**
             * Levágjuk a string elejét, hogy csak az innentől szükséges
             * részek maradjanak meg. (manualCreateCOntainer-t levágjuk
             */
            String newcmd=command.substring(21);

            /**
             * Ha PipeAtPlayer a parancs, akkor a kívánt azonosítójú
             * játékosnál létrehozunk egy csövet
             */
            if(newcmd.startsWith("PipeAtPlayer")) //manualCreateContainerPipeAtPlayer<Player>
            {
                String playernumber=newcmd.substring(12); //<Player>
                if(playernumber.length()==1)
                {
                    if(StrFunctions.isDigit(playernumber.charAt(0)))
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == Integer.parseInt(playernumber)){
                                player.getCarriedPipes().add(new Pipe());
                            }
                        }
                    }
                    else
                        System.out.println("Give a valid integer for player's number");
                }
                else
                {
                    System.out.println("The interval of players number is 0-9");
                }
            }

            /**
             * Ha PumpAtPlayer a parancs, akkor létrehozunk egy új pumpát
             * a megadott azonosítójú játékosnál, persze ha érvényes az azonosító (létezik az a játékos).
             */
            else if(newcmd.startsWith("PumpAtPlayer")) //manualCreateContainerPumpAtPlayer<Player>
            {
                String playernumber=newcmd.substring(12); //<Player>
                if(playernumber.length()==1)
                {
                    if(StrFunctions.isDigit(playernumber.charAt(0)))
                    {
                        for(Player player : Map.getInstance().getPlayers()){
                            if(player.getId() == Integer.parseInt(playernumber)){
                                player.setCarriedPump(new Pump(4));
                            }
                        }
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

        /**
         * Ha manualSetTurnCount a parancs, akkor beállítjuk a turnCount-ot a Controller osztályban
         * Ellenőrizni kell, hogy integer-t adott-e meg a felhasználó
         */
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
                Controller.getInstance().setTurnCount(Integer.parseInt(turncount));
            }
        }

        /**
         * Ha a parancs manualDamageContainer, akkor a megadott pozícióban
         * lévő Container-t elrontjuk, persze ha érvényes pozíciót adtunk meg.
         */
        else if(command.startsWith("manualDamageContainer")) //manualDamageContainerAt <PosX> <PosY>
        {
            String newcmd=command.substring(21);
            if(newcmd.startsWith("At")) // manualDamageContainerAt <PosX>_<PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    for(ContainerPos cp : Map.getInstance().getGameMap()){
                        if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                            cp.getContainer().damageContainer();
                        }
                    }
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }

        /**
         * Ha a parancs manualMakePipeSlippery, akkor a megadott pozícióban lévő csövet
         * csúszóssá teszzük, ha érvényes pozíciót adtunk meg.
         */
        else if(command.startsWith("manualMakePipeSlippery")) //manualMakePipeSlipperyAt <PosX> <PosY>
        {
            String newcmd=command.substring(22);
            if(newcmd.startsWith("At")) // manualMakePipeSlipperyAt <PosX> <PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    for (ContainerPos cp : Map.getInstance().getGameMap()){
                        if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                            cp.getContainer().pipeGetsSlippery();
                        }
                    }
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }

        /**
         * Ha a parancs manualMakePipeSticky, akkor a megadott pozícióban lévő csövet
         * ragadóssá teszzük, ha érvényes pozíciót adtunk meg.
         */
        else if(command.startsWith("manualMakePipeSticky")) //manualMakePipeStickyAt <PosX> <PosY>
        {
            String newcmd=command.substring(20);
            if(newcmd.startsWith("At")) // manualMakePipeStickyAt <PosX> <PosY>
            {
                int positions[]= StrFunctions.subPosString(newcmd,"At"); //<PosX>_<PosY>
                if(positions[0]!=-1 && positions[1]!= -1)
                {
                    for (ContainerPos cp : Map.getInstance().getGameMap()){
                        if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                            cp.getContainer().pipeGetsSticky();
                        }
                    }
                }
            }
            else
            {
                System.out.println("Invalid use of command");
            }
        }

        /**
         * Ha a parancs manualTeleportPlayer, akkor a kívánt játékost
         * áthelyezzük a megadott pozícióba, ha létezik a játékos, érvényes pozíciót adtunk meg,
         * és lehet épp az adott pozícióba lépni.
         */
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
                            Player p = new Player();
                            ContainerPos containerPos = new ContainerPos();
                            for(Player player : Map.getInstance().getPlayers()){
                                if(player.getId() == playernumber){
                                    p = player;
                                }
                            }
                            for(ContainerPos cp : Map.getInstance().getGameMap()){
                                if(cp.getPosX() == positions[0] && cp.getPosY() == positions[1]){
                                    containerPos = cp;
                                }
                            }
                            if(p.getPosition() == null || containerPos.getContainer() == null){
                                System.out.println("There's no valid player with given id, or given X and Y coordinates don't specifiy any Container");
                            }
                            else{
                                p.setPosition(containerPos.getContainer());
                            }
                        }
                    }
                    /**
                     * Ha rosszul adtuk meg a parancsa paramétereit.
                     */
                    else
                    {
                        System.out.println("Invalid use of command");
                    }
                }
                /**
                 * Ha rossz játékos id-t adtunk meg.
                 */
                else
                    System.out.println("Give a valid integer for player's number");
            }
            /**
             * Ha rosszul adtuk meg a parancsa paramétereit.
             */
            else
            {
                System.out.println("Invalid use of command");
            }
        }
        /**
         * Víz folyatása
         */
        else if(command.startsWith("manualFlowWaterTillTurn"))
        {
            boolean allDigit=true;
            String newcmd=command.substring(23);
            if(newcmd.length()<3)
            {
                for(int i=0; i<newcmd.length(); i++)
                {
                    if(!StrFunctions.isDigit(newcmd.charAt(i)))
                    {
                        allDigit=false;
                    }
                }
            }

            if(allDigit  && newcmd.length()>0 && newcmd.length()<3)
            {
                //TODO
                System.out.println(newcmd);
            }
            else
                System.out.println("Give a valid turn. The interval is 0..50");
        }
        /**
         * Ha érvénytelen parancsot adtunk meg.
         */
        else
        {
            System.out.println("Unknown command");
        }
    }
}
