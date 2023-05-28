package controller;

import container.Container;
import map.Map;
import player.Player;

import java.util.ArrayList;

public class Game {

    private static Game instance;
    private int mechPoints;
    private int sabPoints;
    private Map gameMap = Map.getInstance();
    private ArrayList<Player> players = new ArrayList<>();
    private int turnCount = 0;
    private int currentPlayerIdx = 0;


    private Game(){

        mechPoints = 0;
        sabPoints = 0;

    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void start(){
        while (!isGameOver()){
            Player currentPlayer = players.get(currentPlayerIdx);
            playerAction(currentPlayer);
            switchToNextPlayer();


        }
    }

    public void playerAction(Player player){

        //TODO

    }

    public void switchToNextPlayer(){
        currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
    }

    public boolean isGameOver(){
        for(Container container : Map.getInstance().getContainers()){
            if(container.mountainSpringQuery() == 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public int getMechPoints() {
        return mechPoints;
    }

    public void setMechPoints(int mechPoints) {
        this.mechPoints = mechPoints;
    }

    public int getSabPoints() {
        return sabPoints;
    }

    public void setSabPoints(int sabPoints) {
        this.sabPoints = sabPoints;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
}
