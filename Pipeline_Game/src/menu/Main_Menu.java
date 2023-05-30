package menu;

import container.ContainerPos;
import controller.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.Map;
import player.Mechanic;
import player.Player;
import player.Saboteur;

import java.nio.file.Paths;

import static menu.Game_Window.newGameWindow;


public class Main_Menu extends Application {

    Button exit, new_game, load_game, music;
    Slider volumeSlider = new Slider(0, 1, 1);
    Duration savedTime;
    boolean musicPlaying = true;
    Stage window;
    public static Scene mainScene, newGameScene;
    public static MediaPlayer mediaPlayer;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){

        window = primaryStage;
        window.setTitle("Pipeline Game");

        initMainScene();
        initNewGameScene();

        window.setScene(mainScene);
        window.setFullScreen(true);
        window.show();
    }

    public void initMusicButton(){

        //MusicOff im
        music = new Button();
        Image audioOff = new Image("file:resources/mute-off-sound-off-icon-1.png"); // Replace "path/to/your/image.png" with the actual path to your image file
        ImageView audioOffView = new ImageView(audioOff);
        audioOffView.setFitWidth(32); // Adjust the width of the image to your desired size
        audioOffView.setFitHeight(32); // Adjust the height of the image to your desired size

        //MusicOn im
        Image audioOn = new Image("file:resources/audio_on.png"); // Replace "path/to/your/image.png" with the actual path to your image file
        ImageView audioOnView = new ImageView(audioOn);
        audioOnView.setFitWidth(32); // Adjust the width of the image to your desired size
        audioOnView.setFitHeight(32); // Adjust the height of the image to your desired size

        music.setGraphic(audioOffView);

        //Music
        String audioPath = "resources/YT2mp3.info_-_Middle_Eastern_Music_Sands_of_Arabia_320kbps.mp3";
        Media media = new Media(Paths.get(audioPath).toUri().toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO)); // Reset the media to the beginning when it ends
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Set the cycle count to indefinitely repeat
        mediaPlayer.play();

        // Create a slider with values ranging from 0 to 1, and set the initial value to 1 (maximum volume)
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setMajorTickUnit(0.25);
        volumeSlider.setBlockIncrement(0.1);
        volumeSlider.getStylesheets().add("file:resources/customSlider.css");
        volumeSlider.setValue(0.5);
        mediaPlayer.setVolume(0.5);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue()); // Set the volume of the media player based on the slider value
            if(mediaPlayer.getVolume() == 0){
                music.setGraphic(audioOnView);
            }
            else{
                music.setGraphic(audioOffView);
            }
        });

        music.setOnAction(e->{
            if(musicPlaying){
                savedTime = mediaPlayer.getCurrentTime();
                mediaPlayer.pause();
                musicPlaying = false;
                music.setGraphic(audioOnView);
            }
            else if(!musicPlaying){
                mediaPlayer.seek(savedTime);
                mediaPlayer.play();
                musicPlaying = true;
                music.setGraphic(audioOffView);
            }
        });
    }

    public void initExitButton(){
        exit = new Button();
        Image image = new Image("file:resources/open_the_noor.png"); // Replace "path/to/your/image.png" with the actual path to your image file
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32); // Adjust the width of the image to your desired size
        imageView.setFitHeight(32); // Adjust the height of the image to your desired size
        exit.setGraphic(imageView);

        exit.setOnAction(e -> window.close());

    }

    public void initNewGameScene(){

        BorderPane root = new BorderPane();

        Image backgroundImage = new Image("file:resources/phlusocosta-deserto.gif");

        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        root.setBackground(new Background(background));

        Text newGameText = new Text("New Game");
        Font pixelFont = Font.loadFont("file:resources/Arabic Ramadhan.ttf", 70);
        newGameText.setFont(pixelFont);
        newGameText.setStyle("-fx-fill: #000000;"); // Set the text color

        Text descriptionText = new Text("Welcome revered guests of the Codehaven Sands, where secrets unfold like whispered tales on a desert breeze." +
                "\nLike stars adorned upon the night's velvet veil, choose, O esteemed ones, the number of players that shall embark upon this mystic odyssey, for in unity or solitude lies the genesis of our enigmatic journey.");
        descriptionText.setStyle("-fx-fill: #000000;");
        descriptionText.setFont(Font.loadFont("file:resources/JMH Rastan Fine Black.ttf", 15));
        descriptionText.setWrappingWidth(980);
        descriptionText.setTextAlignment(TextAlignment.CENTER);

        HBox playerLayout = new HBox();
        playerLayout.setSpacing(30);
        Text player = new Text("Player");
        player.setStyle("-fx-fill: #000000;");
        player.setFont(Font.loadFont("file:resources/JMH Rastan Fine Black.ttf", 12));
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("One Pair of Players", "Two Pair of Players");
        choiceBox.getStylesheets().add("file:resources/choiceBox.css");
        choiceBox.setValue("One Pair of Players");

        playerLayout.getChildren().addAll(player, choiceBox);


        Button closeButton = new Button("Close");
        Button startButton = new Button("Start Game");

        closeButton.setPrefSize(100, 30);
        startButton.setPrefSize(120, 30);

        closeButton.setOnAction(e ->{
            window.setScene(mainScene);
            window.setResizable(true);
            window.setFullScreen(true);
        });
        startButton.setOnAction(e ->{
            int playerNumber = 0;
            if(choiceBox.getValue().equals("One Pair of Players")){
                playerNumber = 2;
            }
            if(choiceBox.getValue().equals("Two Pair of Players")){
                playerNumber = 4;
            }
            if(playerNumber != 0){
                newGameWindow(playerNumber, window);
                mediaPlayer.stop();
                window.close();
            }
        });

        HBox horLayout = new HBox();
        horLayout.getChildren().addAll(closeButton, startButton);
        horLayout.setAlignment(Pos.BOTTOM_LEFT);
        horLayout.setSpacing(830);
        horLayout.setPadding(new Insets(0,0,25,25));

        HBox textLayout = new HBox();
        textLayout.getChildren().addAll(newGameText);
        textLayout.setAlignment(Pos.TOP_CENTER);
        textLayout.setPadding(new Insets(50,0,0,0));


        VBox verLayout = new VBox();
        verLayout.getChildren().addAll(descriptionText,playerLayout);
        verLayout.setAlignment(Pos.TOP_LEFT);
        verLayout.setSpacing(25);
        verLayout.setPadding(new Insets(50,0,0,70));

        root.setLeft(verLayout);
        root.setBottom(horLayout);
        root.setTop(textLayout);

        newGameScene = new Scene(root, 1100,600);
        newGameScene.getStylesheets().add("file:resources/button_style_load.css");

        window.setResizable(false);
    }

    public void initMainScene(){
        BorderPane root = new BorderPane();

        Image backgroundImage = new Image("file:resources/v2.gif");

        // Create an ImageView to display the image
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        root.setBackground(new Background(background));

        new_game = new Button("New Game");
        load_game = new Button("Load Game");

        initExitButton();
        initMusicButton();

        //Button sizes
        exit.setPrefSize(50, 50);
        music.setPrefSize(50, 50);
        new_game.setPrefSize(500, 50);
        load_game.setPrefSize(500, 50);

        //Handle Buttons for mainScene
        new_game.setOnAction(e -> {
            window.setScene(newGameScene);
            window.centerOnScreen();
        });
        load_game.setOnAction(e ->{
            window.setFullScreen(false);
            window.centerOnScreen();
            LoadGameBox.display(window);
        });

        //Main Text for mainScene
        Text text = new Text("Pipeline Game");
        Font pixelFont = Font.loadFont("file:resources/Blox2.ttf", 100);
        text.setFont(pixelFont);
        text.setStyle("-fx-fill: #0C2340;"); // Set the text color

        //VBox for mainScene
        VBox verLayout = new VBox();
        verLayout.getChildren().addAll(text, new_game, load_game);
        verLayout.setAlignment(Pos.TOP_LEFT);
        verLayout.setSpacing(20);
        verLayout.setPadding(new Insets(200,0,0,50));

        //HBox for mainScene
        HBox horLayout = new HBox();
        horLayout.getChildren().addAll(exit, music, volumeSlider);
        horLayout.setAlignment(Pos.BOTTOM_LEFT);
        horLayout.setSpacing(20);
        horLayout.setPadding(new Insets(0,0,30,50));

        root.setLeft(verLayout);
        root.setBottom(horLayout);

        mainScene = new Scene(root, 1600, 1200);
        mainScene.getStylesheets().add("file:resources/button_style_main.css");
    }
}
