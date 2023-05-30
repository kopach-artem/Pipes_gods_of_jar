package controller;

import container.Container;
import container.ContainerPos;
import map.Map;
import menu.MyAlert;
import player.Player;

import java.io.*;
import java.util.ArrayList;

import static menu.Game_Window.updateGameScene;

public class Game implements Serializable{

    private static Game instance;

    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList<>();
    private int turnCount = 0;
    private int currentPlayerIdx = 0;


    private Game(){

    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }

    public void switchToNextPlayer(){
        currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
        currentPlayer = players.get(currentPlayerIdx);
    }

    public boolean isGameOver(){
        for(Container container : Map.getInstance().getContainers()){
            if(container.mountainSpringQuery() == 0 && allIsOut()){
                return true;
            }
        }
        return false;
    }

    public boolean allIsOut(){
        int count = 0;
        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
            if(containerPos.getContainer().isEmpty()){
                count++;
            }
        }
        if(count == Map.getInstance().getContainers().size()){
            return true;
        }
        return false;
    }

    public void checkForSticky(){
        for(Player player : players){
            player.turnAsSticky();
        }
    }

    public void turn(){
        if(!isGameOver()){
            if(!MyAlert.getTurnWasWasted()){
                switchToNextPlayer();
                Controller.getInstance().waterFlow();
                Controller.getInstance().evaluateCycles();
                checkForSticky();
                updateGameScene();
                turnCount++;
            }
            MyAlert.setTurnWasWasted(false);
        }
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public int getMechPoints() {

        ContainerPos cs = new ContainerPos();

        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
            if(containerPos.getContainer().queryCistern() >= 0){
                cs = containerPos;
            }
        }

        return cs.getContainer().queryCistern();
    }

    public int getRemainingWater(){
        ContainerPos ms = new ContainerPos();

        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
            if(containerPos.getContainer().mountainSpringQuery() >= 0){
                ms = containerPos;
            }
        }

        return ms.getContainer().mountainSpringQuery();
    }


    public int getSabPoints() {
        return Map.getLeakedWater();
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public void saveGame(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/saves/" + filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
            System.out.println("Game saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    // Method to load the game state from a file
    public static Game loadGame(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            instance = (Game) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Game loaded successfully from " + filename);
            return instance;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
