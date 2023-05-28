package menu;

import container.ContainerPos;
import controller.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.Map;
import player.Mechanic;
import player.Player;
import player.Saboteur;

public class Game_Window {

    private static int GRID_SIZE = 5;
    private static Image[][] imageGrid = new Image[GRID_SIZE][GRID_SIZE];

    public static void newGameWindow(int playerNumber, Stage primaryStage, Scene lastScene){

        Stage gameWindow = new Stage();

        Game game = Game.getInstance();

        gameWindow.setTitle("Game");
        gameWindow.setFullScreen(true);

        //Players
        if(playerNumber == 2){
            game.getPlayers().add(new Mechanic());
            game.getPlayers().add(new Saboteur());
        }
        if(playerNumber == 4){
            for(int i = 0; i < 2; i++){
                game.getPlayers().add(new Mechanic());
                game.getPlayers().add(new Saboteur());
            }
        }
        //Make Map
        Map.makeMap();

        //Placing all Players on Tile (0,0)
        ContainerPos starterPos = new ContainerPos();
        if(Map.getInstance().getGameMap() != null){
            for(ContainerPos cp: Map.getInstance().getGameMap()){
                if(cp.getPosX() == 0 && cp.getPosY() == 0){
                    starterPos = cp;
                }
            }
        }
        for(Player p : game.getPlayers()){
            p.setPosition(starterPos.getContainer());
        }

        Scene gameScene = gameSceneInit(gameWindow, primaryStage, lastScene);

        gameWindow.setFullScreen(true);
        gameWindow.setScene(gameScene);
        gameWindow.show();

    }

    public static Scene gameSceneInit(Stage gameWindow, Stage primaryStage, Scene lastScene){

        //-------Layout Init-------//

        BorderPane root = new BorderPane();
        Scene gameScene;
        Game game = Game.getInstance();

        //-------Button Init-------//




        //-------GameMenu Init-------//
        MenuItem save = new MenuItem("Save Current Progress");
        MenuItem exit = new MenuItem("Exit the Program");
        MenuItem back = new MenuItem("Back to Main Menu");

        Menu gameMenu = new Menu("Game Menu");
        gameMenu.getItems().addAll(save, back, exit);

        MenuBar gameMenuBar = new MenuBar();
        gameMenuBar.getMenus().add(gameMenu);

        root.setTop(gameMenuBar);

        //-------Handle Menu Events-------//
        save.setOnAction(e -> {
            //TODO
        });
        exit.setOnAction(e -> {
            gameWindow.close();
        });
        back.setOnAction(e ->{
            gameWindow.close();
            primaryStage.setScene(lastScene);
            primaryStage.setFullScreen(true);
            Main_Menu.mediaPlayer.play();
            primaryStage.show();
        });

        //-------GridMap Init-------//
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStylesheets().add("file:resources/gridPane.css");


        //-------GridMap Fill-------//
        if(Map.getInstance().getGameMap() != null){
            for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                gridPane.add(new ImageView(new Image(containerPos.getContainer().myIconPath())), containerPos.getPosX(), containerPos.getPosY());
                imageGrid[containerPos.getPosX()][containerPos.getPosY()] = new Image(containerPos.getContainer().myIconPath());
            }
        }
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){

                boolean isEmpty = imageGrid[i][j] == null;

                if(isEmpty)
                    gridPane.add(new ImageView(new Image("file:resources/container_components/desert1.png")), i, j);
            }
        }
        if(!game.getPlayers().isEmpty()){
            for(Player player : game.getPlayers()){
                for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                    if(containerPos.getContainer().equals(player.getPosition())){
                        VBox verText = new VBox();
                        verText.setSpacing(5);
                        verText.getChildren().add(new Text(player.consolePrint()));
                        gridPane.add(verText, containerPos.getPosX(), containerPos.getPosY());
                    }
                }
            }
        }

        root.setCenter(gridPane);

        gameScene = new Scene(root, 800,800);
        gameScene.getStylesheets().add("file:resources/customMenu.css");

        return gameScene;
    }
}
