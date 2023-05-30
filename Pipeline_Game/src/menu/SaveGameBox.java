package menu;

import controller.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class SaveGameBox {

    public static void display(Stage primaryStage){
        Stage window = new Stage();

        BorderPane root = new BorderPane();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save Game");

        //-------Text-------//
        Text saveText = new Text("O traveler of the digital realms, heed my words in the desert winds." +
                "\tSave thy game, lest thy progress be lost in the sands of time. Preserve thy conquests, for valor should not vanish in vain." +
                "\tEmbrace the sanctuary of the save game menu and secure thy destiny within its timeless vault." +
                "\tLet not the whispers of misfortune unravel thy journey." +
                "\tThe sands may shift, but your triumphs shall endure. Save, and let your story transcend the ages.");

        Text sText = new Text("O revered traveler do not forget, the preserved essences of bygone ages all lay before you." +
                "\tIf thou tries to overwrite these, no one could tell what may befall thee...");

        //-------ListView-------//

        ListView<String> lsView = new ListView<String>();
        File path = new File("resources/saves");

        File [] files = path.listFiles();
        if(files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) { //this line weeds out other directories/folders
                    lsView.getItems().add(files[i].getName());
                }
            }
        }

        lsView.getStylesheets().add("file:resources/listViewCustom.css");

        //-------Buttons-------//
        Button back = new Button("Back");
        Button save = new Button("Save");

        //-------HBox-------//
        HBox hBox = new HBox();
        hBox.getChildren().addAll(back, save);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        hBox.setSpacing(400);
        hBox.setPadding(new Insets(0,0,25,25));

        //-------Handle Button Events-------//
        back.setOnAction(e -> {
            window.close();
            primaryStage.show();
        });
        save.setOnAction(e -> {
            displaySave();
        });

        //-------BorderPane Alignments-------//
        root.setBottom(hBox);
        root.setCenter(lsView);

        Scene scene = new Scene(root, 500, 300);

        window.setScene(scene);
        window.showAndWait();
    }

    public static void displaySave(){

        Stage window = new Stage();

        window.setTitle("Modal Window");

        // Create the text input field
        TextField inputField = new TextField();
        Text fdUp = new Text("In about 5 seconds you will be dropped out of the program!");
        fdUp.setVisible(false);
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Close the window when Enter is pressed
                if(inputField.getText() != null && !invalidSaveName(inputField.getText())){
                    Game.getInstance().saveGame(inputField.getText());
                    window.close();
                }
                else{
                    inputField.setText("O traveler of Codehaven Sands you made a foolish mistake by trying to fool me." +
                            "\tFor your transgressions I shall send thee to the depths of these Sands!");
                    fdUp.setVisible(true);
                }
            }
        });

        // Create the main layout
        VBox root = new VBox();
        root.getChildren().addAll(fdUp, inputField);

        // Create the scene and set it as the primary stage's scene
        Scene scene = new Scene(root, 300, 200);
        window.setScene(scene);

        // Set the window modality to APPLICATION_MODAL
        window.initModality(Modality.APPLICATION_MODAL);

        // Show the window
        window.show();

    }

    public static boolean invalidSaveName(String filename){

        File path = new File("resources/saves");

        File [] files = path.listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){ //this line weeds out other directories/folders
                if(filename.equals(files[i].getName())){
                    return true;
                }
            }
        }
        return false;
    }
}
