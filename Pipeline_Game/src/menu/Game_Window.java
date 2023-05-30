package menu;

import container.*;
import controller.Game;
import exception.MyException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.Map;
import player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Game_Window {

    private static int gridWidth = 0;
    private static int gridHeight = 0;
    private static Image[][] imageGrid;

    private static Scene gameScene;

    private static ArrayList<ChoiceBox<String>> choiceBoxes = new ArrayList<ChoiceBox<String>>();

    public static void newGameWindow(int playerNumber, Stage primaryStage){

        if(Game.getInstance() != null){
            Game.destroyInstance();
        }

        Stage gameWindow = new Stage();

        imageGrid = new Image[gridWidth][gridHeight];

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

        up.setPrefSize(35, 35);
        left.setPrefSize(35, 35);
        right.setPrefSize(35, 35);
        down.setPrefSize(35, 35);

//-------GridPane for Buttons-------//
        GridPane buttonGridPane = new GridPane();

        ImageView borderImg1 = new ImageView(new Image("file:resources/border.png"));
        ImageView borderImg2 = new ImageView(new Image("file:resources/border.png"));
        ImageView borderImg3 = new ImageView(new Image("file:resources/border.png"));
        ImageView borderImg4 = new ImageView(new Image("file:resources/border.png"));

// Position the buttons in the grid
        buttonGridPane.add(left, 0, 1);
        buttonGridPane.add(borderImg1, 0, 1);

        buttonGridPane.add(up, 1, 0);
        buttonGridPane.add(borderImg2, 1, 0);

        buttonGridPane.add(right, 2, 1);
        buttonGridPane.add(borderImg3, 2, 1);

        buttonGridPane.add(down, 1, 1);
        buttonGridPane.add(borderImg4, 1, 1);

        buttonGridPane.setAlignment(Pos.BOTTOM_CENTER);

        buttonGridPane.setHgap(10);
        buttonGridPane.setVgap(10);

        root.setBottom(buttonGridPane);

        //-------Action Button Init-------//
        Button leakPipe = new Button("Leak Pipe");

        Button makePipeSticky = new Button("Make Pipe Sticky");
        Button adjustPump = new Button("Adjust Pump");
        Button attachPump = new Button("Attach Pump");
        Button detachPipe = new Button("Detach Pipe");
        Button attachPipe = new Button("Attach Pipe");
        Button takePipe = new Button("Take Pipe From Cistern");
        Button takePump = new Button("Take Pump From Cistern");

        //-------ChoiceBox Init-------//
        ChoiceBox<String> attachChoiceBox = new ChoiceBox<>();
        attachChoiceBox.getItems().addAll("Choice Can Be Selected", "Up", "Left", "Right", "Down");

        ChoiceBox<String> detachChoiceBox = new ChoiceBox<>();
        detachChoiceBox.getItems().addAll("Choice Can Be Selected","Up", "Left", "Right", "Down");

        ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.getItems().addAll("Choice Can Be Selected","Input", "Output");

        ChoiceBox<String> adjustChoiceBox = new ChoiceBox<>();
        adjustChoiceBox.getItems().addAll("Choice Can Be Selected","Up", "Left", "Right", "Down");

        choiceBoxes.addAll(Arrays.asList(attachChoiceBox, detachChoiceBox, typeChoiceBox, adjustChoiceBox));

        refreshChoiceBoxes();

        //-------VBox for Action Buttons and ChoiceBoxes-------//
        VBox buttonLayout = new VBox();
        buttonLayout.setSpacing(10);
        buttonLayout.setAlignment(Pos.BOTTOM_LEFT);

        // Set button sizes
        leakPipe.setPrefSize(150, 50);
        makePipeSticky.setPrefSize(150, 50);
        attachPump.setPrefSize(150, 50);
        detachPipe.setPrefSize(125, 50);
        attachPipe.setPrefSize(125, 50);
        adjustPump.setPrefSize(125, 50);
        takePipe.setPrefSize(200, 50);
        takePump.setPrefSize(200, 50);

        // Add buttons and choice boxes to the layout
        buttonLayout.getChildren().addAll(leakPipe,attachPump, makePipeSticky);


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

        // Create an HBox for Adjust Pump button and ChoiceBox
        HBox adjustLayout = new HBox();
        adjustLayout.setSpacing(10);
        adjustLayout.getChildren().addAll(adjustPump, typeChoiceBox, adjustChoiceBox);

        buttonLayout.getChildren().add(adjustLayout);


        // Create an HBox for taking stuff from Cistern
        HBox cisternLayout = new HBox();
        cisternLayout.setSpacing(10);
        cisternLayout.getChildren().addAll(takePipe, takePump);

        buttonLayout.getChildren().add(cisternLayout);

        root.setLeft(buttonLayout);

        //-------Choice Box-------//
        ChoiceBox choiceBox = Game.getInstance().getCurrentPlayer().getChoiceBox();
        Button choiceBoxButton = new Button("Perform Selected Action");

        //-------HBox ChoiceBox-------//
        HBox hBoxChoice = new HBox();
        hBoxChoice.getChildren().addAll(choiceBoxButton, choiceBox);
        hBoxChoice.setSpacing(10);
        hBoxChoice.setAlignment(Pos.BOTTOM_LEFT);

        rightWay.setBottom(hBoxChoice);


        //-------Text-------//
        Text currentPlayerText = new Text(Game.getInstance().getCurrentPlayer().consolePrint());
        currentPlayerText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        currentPlayerText.setFill(Color.TAN);

        Text mechanicPoints = new Text("Mechanics Accumulated Points : " + Game.getInstance().getMechPoints());
        mechanicPoints.setFont(Font.font("Arial", 16));
        mechanicPoints.setFill(Color.SANDYBROWN);

        Text saboteurPoints = new Text("Saboteurs Accumulated Points : " + Game.getInstance().getSabPoints());
        saboteurPoints.setFont(Font.font("Arial", 16));
        saboteurPoints.setFill(Color.SANDYBROWN);

        Text mountainSpring = new Text("Water left in Mountain Spring : " + Game.getInstance().getRemainingWater());
        mountainSpring.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        mountainSpring.setFill(Color.SIENNA);


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

            if(adjustChoiceBox.getValue().equals("Up")){
                if(typeChoiceBox.getValue().equals("Input")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Up, Type.Input);
                    Game.getInstance().turn();
                }
                else if(typeChoiceBox.getValue().equals("Output")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Up, Type.Output);
                    Game.getInstance().turn();
                }
            }
            else if(adjustChoiceBox.getValue().equals("Left")){
                if(typeChoiceBox.getValue().equals("Input")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Left, Type.Input);
                    Game.getInstance().turn();
                }
                else if(typeChoiceBox.getValue().equals("Output")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Left, Type.Output);
                    Game.getInstance().turn();
                }
            }
            else if(adjustChoiceBox.getValue().equals("Right")){
                if(typeChoiceBox.getValue().equals("Input")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Right, Type.Input);
                    Game.getInstance().turn();
                }
                else if(typeChoiceBox.getValue().equals("Output")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Right, Type.Output);
                    Game.getInstance().turn();
                }
            }
            else if(adjustChoiceBox.getValue().equals("Down")){
                if(typeChoiceBox.getValue().equals("Input")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Down, Type.Input);
                    Game.getInstance().turn();
                }
                else if(typeChoiceBox.getValue().equals("Output")){
                    Game.getInstance().getCurrentPlayer().playerAdjustsPump(Direction.Down, Type.Output);
                    Game.getInstance().turn();
                }
            }
            else{
                MyAlert.showInvalidMoveAlert("No suitable option from Choice Box was selected");
            }

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

        takePipe.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().takePipe();
            Game.getInstance().turn();
        });

        takePump.setOnAction(e ->{
            Game.getInstance().getCurrentPlayer().takePump();
            Game.getInstance().turn();
        });

        //-------ChoiceBox Button Event-------//
        choiceBoxButton.setOnAction(e -> handleChoiceBoxButton(choiceBox));

        //-------GameMenu Init-------//
        MenuItem save = new MenuItem("Save Current Progress");
        MenuItem exit = new MenuItem("Exit the Program");

        Menu gameMenu = new Menu("Game Menu");
        gameMenu.getItems().addAll(save, exit);

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

        //-------Text-------//
        Text currentPlayerText = new Text(Game.getInstance().getCurrentPlayer().consolePrint());
        currentPlayerText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        currentPlayerText.setFill(Color.TAN);

        Text mechanicPoints = new Text("Mechanics Accumulated Points : " + Game.getInstance().getMechPoints());
        mechanicPoints.setFont(Font.font("Arial", 16));
        mechanicPoints.setFill(Color.SANDYBROWN);

        Text saboteurPoints = new Text("Saboteurs Accumulated Points : " + Game.getInstance().getSabPoints());
        saboteurPoints.setFont(Font.font("Arial", 16));
        saboteurPoints.setFill(Color.SANDYBROWN);

        Text mountainSpring = new Text("Water left in Mountain Spring : " + Game.getInstance().getRemainingWater());
        mountainSpring.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        mountainSpring.setFill(Color.SIENNA);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(currentPlayerText, mechanicPoints, saboteurPoints, mountainSpring);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_RIGHT);

        rightWay.setRight(vBox);


        //-------Choice Box-------//
        ChoiceBox choiceBox = Game.getInstance().getCurrentPlayer().getChoiceBox();
        Button chButton = (Button)(((HBox)((BorderPane)(root.getRight())).getBottom()).getChildren().get(0));

        //-------HBox ChoiceBox-------//
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(chButton, choiceBox);
        hBox1.setAlignment(Pos.BOTTOM_LEFT);
        chButton.setOnAction(e -> handleChoiceBoxButton(choiceBox));

        rightWay.setBottom(hBox1);

        root.setRight(rightWay);


        // Update other elements in the scene

        refreshChoiceBoxes();

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
            if(containerPos.getContainer().hasPipes() >= 0){
                gridPane.add(new Text("" + containerPos.getContainer().hasPipes()), containerPos.getPosX(), containerPos.getPosY());
                if(containerPos.getContainer().hasPump()){
                    imageView.setStyle("-fx-effect: dropshadow(gaussian, #0000ff, 10, 0, 0, 0);");
                }
                else{
                    imageView.setStyle(null);
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
            MyAlert.showInvalidMoveAlert("No suitable choice was selected");
        }
    }

    public static void refreshChoiceBoxes(){
        for(int i = 0; i < choiceBoxes.size(); i++){
            choiceBoxes.get(i).setValue(choiceBoxes.get(i).getItems().get(0));
        }
    }

}
