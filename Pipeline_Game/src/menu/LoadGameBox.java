package menu;

import controller.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadGameBox {

    public static void display(Stage primaryStage){

        Stage window = new Stage();

        StackPane root = new StackPane();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load Game");
        window.setMinWidth(200);



        Button close, load;

        close = new Button("Close");
        load = new Button("Load");

        close.setOnAction(e -> {
            window.close();
            primaryStage.setFullScreen(true);
        });

        load.setOnAction(e->{
            Stage gameWindow = new Stage();
            if(Game.getInstance() != null){
                Game.destroyInstance();
            }
            Game.loadGame("resources/saves/baromfi");
            Scene gameScene = Game_Window.gameSceneMake(gameWindow, primaryStage);
            gameWindow.setFullScreen(true);
            gameWindow.setScene(gameScene);
            gameWindow.show();
        });

        HBox horLayout = new HBox();
        horLayout.getChildren().addAll(close, load);
        horLayout.setSpacing(190);
        horLayout.setAlignment(Pos.BOTTOM_LEFT);
        horLayout.setPadding(new Insets(0, 0,20,20));

        root.getChildren().add(horLayout);

        Scene scene = new Scene(root, 200, 200);

        window.setScene(scene);
        window.showAndWait();
    }
}
