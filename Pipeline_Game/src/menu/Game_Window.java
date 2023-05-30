package menu;

import container.Container;
import container.ContainerPos;
import container.Pipe;
import container.Pump;
import controller.Game;
import exception.MyException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.Map;
import player.Direction;
import player.Mechanic;
import player.Player;
import player.Saboteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game_Window {

    private static int gridWidth = 0;
    private static int gridHeight = 0;
    private static Image[][] imageGrid = new Image[gridWidth][gridHeight];

    private static Scene gameScene;

    public static void newGameWindow(int playerNumber, Stage primaryStage){

        if(Game.getInstance() != null){
            Game.destroyInstance();
        }

        Stage gameWindow = new Stage();

        
        gameWindow.setTitle("Game");
        gameWindow.setFullScreen(true);

        //Players
        if(playerNumber == 2){
            Game.getInstance().getPlayers().add(new Mechanic());
            Game.getInstance().getPlayers().add(new Saboteur());
        }
        if(playerNumber == 4){
            for(int i = 0; i < 2; i++){
                Game.getInstance().getPlayers().add(new Mechanic());
                Game.getInstance().getPlayers().add(new Saboteur());
            }
        }
        //Make Map
        Map.makeMap();

        Game.getInstance().setCurrentPlayer(Game.getInstance().getPlayers().get(0));

        Scene gameScene = gameSceneMake(gameWindow, primaryStage);

        gameWindow.setFullScreen(true);
        gameWindow.setScene(gameScene);
        gameWindow.show();
    }

    public static Scene gameSceneMake(Stage gameWindow, Stage primaryStage){


        if(Game.getInstance().isGameOver()){
            //TODO
        }

        //-------Layout Init-------//
        BorderPane root = new BorderPane();
        BorderPane rightWay = new BorderPane();

        //-------Direction Button Init-------//
        Button up = new Button();
        Button left = new Button();
        Button right = new Button();
        Button down = new Button();

        up.setGraphic(new ImageView(new Image("file:resources/up.png")));
        left.setGraphic(new ImageView(new Image("file:resources/left.png")));
        right.setGraphic(new ImageView(new Image("file:resources/right.png")));
        down.setGraphic(new ImageView(new Image("file:resources/down.png")));

        up.setPrefSize(35,35);
        left.setPrefSize(35,35);
        right.setPrefSize(35,35);
        down.setPrefSize(35,35);

        //-------GridPane for Buttons-------//
        GridPane buttonGridPane = new GridPane();

        // Position the buttons in the grid
        buttonGridPane.add(left, 0, 1);
        buttonGridPane.add(up, 1, 0);
        buttonGridPane.add(right, 2, 1);
        buttonGridPane.add(down, 1, 1);

        buttonGridPane.setAlignment(Pos.BOTTOM_CENTER);

        buttonGridPane.setHgap(10);
        buttonGridPane.setVgap(10);

        root.setBottom(buttonGridPane);

        //-------Action Button Init-------//
        Button leakPipe = new Button("Leak Pipe");
        Button adjustPump = new Button("Adjust Pump");
        Button makePipeSticky = new Button("Make Pipe Sticky");
        Button attachPump = new Button("Attach Pump");
        Button detachPipe = new Button("Detach Pipe");
        Button attachPipe = new Button("Attach Pipe");

        //-------ChoiceBox Init-------//
        ChoiceBox<String> attachChoiceBox = new ChoiceBox<>();
        attachChoiceBox.getItems().addAll("Up", "Left", "Right", "Down");

        ChoiceBox<String> detachChoiceBox = new ChoiceBox<>();
        detachChoiceBox.getItems().addAll("Up", "Left", "Right", "Down");

        //-------VBox for Action Buttons and ChoiceBoxes-------//
        VBox buttonLayout = new VBox();
        buttonLayout.setSpacing(10);
        buttonLayout.setAlignment(Pos.BOTTOM_LEFT);

        // Set button sizes
        leakPipe.setPrefSize(150, 50);
        adjustPump.setPrefSize(150, 50);
        makePipeSticky.setPrefSize(150, 50);
        attachPump.setPrefSize(150, 50);
        detachPipe.setPrefSize(125, 50);
        attachPipe.setPrefSize(125, 50);

        // Add buttons and choice boxes to the layout
        buttonLayout.getChildren().addAll(leakPipe, adjustPump,attachPump, makePipeSticky);

        // Create an HBox for Attach Pipe button and ChoiceBox
        HBox attachLayout = new HBox();
        attachLayout.setSpacing(10);
        attachLayout.getChildren().addAll(attachPipe, attachChoiceBox);
        buttonLayout.getChildren().add(attachLayout);

        // Create an HBox for Detach Pipe button and ChoiceBox
        HBox detachLayout = new HBox();
        detachLayout.setSpacing(10);
        detachLayout.getChildren().addAll(detachPipe, detachChoiceBox);
        buttonLayout.getChildren().add(detachLayout);

        rightWay.setBottom(buttonLayout);


        //-------Choice Box-------//
        ChoiceBox choiceBox = Game.getInstance().getCurrentPlayer().getChoiceBox();
        Button choiceBoxButton = new Button("Perform Selected Action");

        //-------HBox ChoiceBox-------//
        HBox hBoxChoice = new HBox();
        hBoxChoice.getChildren().addAll(choiceBox, choiceBoxButton);
        hBoxChoice.setAlignment(Pos.BOTTOM_LEFT);

        root.setLeft(hBoxChoice);


        //-------Text-------//
        Text currentPlayerText = new Text(Game.getInstance().getCurrentPlayer().consolePrint());
        Text mechanicPoints = new Text("Mechanics Accumulated Points : " + Game.getInstance().getMechPoints());
        Text saboteurPoints = new Text("Saboteurs Accumulated Points : " + Game.getInstance().getSabPoints());
        Text mountainSpring = new Text("Water left in Mountain Spring : " + Game.getInstance().getRemainingWater());


        //-------VBox Texts-------//
        VBox vBox = new VBox();
        vBox.getChildren().addAll(currentPlayerText, mechanicPoints, saboteurPoints, mountainSpring);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_RIGHT);

        rightWay.setRight(vBox);
        root.setRight(rightWay);

        //-------Direction Button Event Handler-------//
        up.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().playerMoves(Direction.Up);
            Game.getInstance().turn();
        });

        left.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().playerMoves(Direction.Left);
            Game.getInstance().turn();
        });

        right.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().playerMoves(Direction.Right);
            Game.getInstance().turn();
        });

        down.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().playerMoves(Direction.Down);
            Game.getInstance().turn();
        });

        //-------Action Button Event Handler-------//
        leakPipe.setOnAction(e->{
            Game.getInstance().getCurrentPlayer().leakPipe();
            Game.getInstance().turn();
        });

        adjustPump.setOnAction(e -> {

            //TODO



        });

        attachPump.setOnAction(e->{
            Game.getInstance().getCurrentPlayer().attachPump();
            Game.getInstance().turn();
        });

        makePipeSticky.setOnAction(e->{
            Game.getInstance().getCurrentPlayer().makePipeSticky();
            Game.getInstance().turn();
        });

        detachPipe.setOnAction(e->{

            if(detachChoiceBox.getValue().equals("Up")){
                Game.getInstance().getCurrentPlayer().playerDetachesPipe(Direction.Up);
                Game.getInstance().turn();
            }
            else if(detachChoiceBox.getValue().equals("Left")){
                Game.getInstance().getCurrentPlayer().playerDetachesPipe(Direction.Left);
                Game.getInstance().turn();
            }
            else if(detachChoiceBox.getValue().equals("Right")){
                Game.getInstance().getCurrentPlayer().playerDetachesPipe(Direction.Right);
                Game.getInstance().turn();
            }
            else if(detachChoiceBox.getValue().equals("Down")){
                Game.getInstance().getCurrentPlayer().playerDetachesPipe(Direction.Down);
                Game.getInstance().turn();
            }
            else{
                MyAlert.showInvalidMoveAlert("No suitable option from Choice Box was selected");
            }

        });

        attachPipe.setOnAction(e->{
            if(attachChoiceBox.getValue().equals("Up")){
                Game.getInstance().getCurrentPlayer().playerAttachesPipe(Direction.Up);
                Game.getInstance().turn();
            }
            else if(attachChoiceBox.getValue().equals("Left")){
                Game.getInstance().getCurrentPlayer().playerAttachesPipe(Direction.Left);
                Game.getInstance().turn();
            }
            else if(attachChoiceBox.getValue().equals("Right")){
                Game.getInstance().getCurrentPlayer().playerAttachesPipe(Direction.Right);
                Game.getInstance().turn();
            }
            else if(attachChoiceBox.getValue().equals("Down")){
                Game.getInstance().getCurrentPlayer().playerAttachesPipe(Direction.Down);
                Game.getInstance().turn();
            }
            else{
                MyAlert.showInvalidMoveAlert("No suitable option from Choice Box was selected");
            }
        });

        //-------ChoiceBox Button Event-------//
        choiceBoxButton.setOnAction(e -> handleChoiceBoxButton(choiceBox));

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
            SaveGameBox.display(gameWindow);
        });
        exit.setOnAction(e -> {
            gameWindow.close();
        });
        back.setOnAction(e ->{
            gameWindow.close();
            primaryStage.setScene(Main_Menu.mainScene);
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

        calculateGridSize();

        //Init player location


        for(Player player : Game.getInstance().getPlayers()){
            ArrayList<Container> demoPipes = new ArrayList<>();
            demoPipes.add(new Pipe());
            if(player.getPosition() == null)
                player.setPosition(Map.getContainerAt(0,0).getContainer());
            player.setCarriedPump(new Pump(2));
            player.setCarriedPipes(demoPipes);
        }


        if(Map.getInstance().getGameMap() != null){
            for(ContainerPos containerPos : Map.getInstance().getGameMap()){
                ImageView imageView = new ImageView(new Image(containerPos.getContainer().myIconPath()));
                gridPane.add(imageView, containerPos.getPosX(), containerPos.getPosY());
                imageGrid[containerPos.getPosX()][containerPos.getPosY()] = new Image(containerPos.getContainer().myIconPath());
                if (containerPos.getContainer().equals(Game.getInstance().getCurrentPlayer().getPosition())) {
                    imageView.setStyle("-fx-effect: dropshadow(gaussian, #ff0000, 10, 0, 0, 0);");
                }
            }
        }
        for(int i = 0; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){

                boolean isEmpty = imageGrid[i][j] == null;

                if(isEmpty)
                    gridPane.add(new ImageView(new Image("file:resources/container_components/desert.png")), i, j);
            }
        }

        if (!Game.getInstance().getPlayers().isEmpty()) {
            java.util.Map<ContainerPos, VBox> vBoxMap = new HashMap<>();

            for (Player player : Game.getInstance().getPlayers()) {
                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                    if (containerPos.getContainer().equals(player.getPosition())) {
                        VBox verText = vBoxMap.get(containerPos);
                        if (verText == null) {
                            verText = new VBox();
                            vBoxMap.put(containerPos, verText);
                            gridPane.add(verText, containerPos.getPosX(), containerPos.getPosY());
                        }
                        Text text = new Text(player.consolePrint());
                        verText.getChildren().add(text);
                        if(player.equals(Game.getInstance().getCurrentPlayer())){
                            text.setStyle("-fx-fill: #ff0000;");
                        }
                    }
                }
            }
        }

        root.setCenter(gridPane);

        gameScene = new Scene(root, 1920,1080);
        gameScene.getStylesheets().add("file:resources/customMenu.css");

        return gameScene;
    }

    public static void updateGameScene() {
        BorderPane root = (BorderPane) gameScene.getRoot();
        BorderPane rightWay = (BorderPane) root.getRight();

        // Update the existing elements in the scene

        // Update the text elements
        Text currentPlayerText = new Text(Game.getInstance().getCurrentPlayer().consolePrint());
        Text mechanicPoints = new Text("Mechanics Accumulated Points : " + Game.getInstance().getMechPoints());
        Text saboteurPoints = new Text("Saboteurs Accumulated Points : " + Game.getInstance().getSabPoints());
        Text mountainSpring = new Text("Water left in Mountain Spring : " + Game.getInstance().getRemainingWater());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(currentPlayerText, mechanicPoints, saboteurPoints, mountainSpring);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_RIGHT);

        rightWay.setRight(vBox);

        root.setRight(rightWay);

        //-------Choice Box-------//
        ChoiceBox choiceBox = Game.getInstance().getCurrentPlayer().getChoiceBox();
        Button chButton = (Button) ((HBox)root.getLeft()).getChildren().get(1);

        //-------HBox ChoiceBox-------//
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(choiceBox, ((HBox)root.getLeft()).getChildren().get(1));
        hBox1.setAlignment(Pos.BOTTOM_LEFT);
        chButton.setOnAction(e -> handleChoiceBoxButton(choiceBox));

        root.setLeft(hBox1);


        // Update other elements in the scene

        // Update player positions in the grid map

        calculateGridSize();

        GridPane gridPane = (GridPane) root.getCenter();
        gridPane.getChildren().clear();

        for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
            ImageView imageView = new ImageView(new Image(containerPos.getContainer().myIconPath()));
            gridPane.add(imageView, containerPos.getPosX(), containerPos.getPosY());
            imageGrid[containerPos.getPosX()][containerPos.getPosY()] = new Image(containerPos.getContainer().myIconPath());
            if (containerPos.getContainer().equals(Game.getInstance().getCurrentPlayer().getPosition())) {
                imageView.setStyle("-fx-effect: dropshadow(gaussian, #ff0000, 10, 0, 0, 0);");
            }
        }

        for(int i = 0; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){

                boolean isEmpty = imageGrid[i][j] == null;

                if(isEmpty)
                    gridPane.add(new ImageView(new Image("file:resources/container_components/desert.png")), i, j);
            }
        }

        // Update player information in VBox elements
        if (!Game.getInstance().getPlayers().isEmpty()) {
            java.util.Map<ContainerPos, VBox> vBoxMap = new HashMap<>();

            for (Player player : Game.getInstance().getPlayers()) {
                for (ContainerPos containerPos : Map.getInstance().getGameMap()) {
                    if (containerPos.getContainer().equals(player.getPosition())) {
                        VBox verText = vBoxMap.get(containerPos);
                        if (verText == null) {
                            verText = new VBox();
                            vBoxMap.put(containerPos, verText);
                            gridPane.add(verText, containerPos.getPosX(), containerPos.getPosY());
                        }
                        Text text = new Text(player.consolePrint());
                        verText.getChildren().add(text);
                        if (player.equals(Game.getInstance().getCurrentPlayer())) {
                            text.setStyle("-fx-fill: #ff0000;");
                        }
                    }
                }
            }
        }
    }


    public static void calculateGridSize(){
        int maxX = 0;
        int maxY = 0;
        for(ContainerPos containerPos : Map.getInstance().getGameMap()){
            if(containerPos.getPosX() > maxX){
                maxX = containerPos.getPosX();
            }
            if(containerPos.getPosY() > maxY){
                maxY = containerPos.getPosY();
            }
        }

        gridWidth = maxX + 1;
        gridHeight = maxY + 1;

        imageGrid = new Image[gridWidth][gridHeight];
    }

    public static void handleChoiceBoxButton(ChoiceBox<String> choiceBox){
        if(choiceBox.getValue().equals("Repair Pipe")){
            Game.getInstance().getCurrentPlayer().RepairPipe();
            Game.getInstance().turn();
        }
        else if(choiceBox.getValue().equals("Repair Pump")){
            Game.getInstance().getCurrentPlayer().RepairPump();
            Game.getInstance().turn();
        }
        else if(choiceBox.getValue().equals("Make Pipe Slippery")){
            Game.getInstance().getCurrentPlayer().makePipeSlippery();
            Game.getInstance().turn();
        }
        else{
            System.out.println("uff");
        }
    }
}
