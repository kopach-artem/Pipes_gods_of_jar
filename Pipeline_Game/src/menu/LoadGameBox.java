package menu;

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

        Button close;

        close = new Button("Close");

        close.setOnAction(e -> {
            window.close();
            primaryStage.setFullScreen(true);
        });

        HBox horLayout = new HBox();
        horLayout.getChildren().add(close);
        horLayout.setSpacing(190);
        horLayout.setAlignment(Pos.BOTTOM_LEFT);
        horLayout.setPadding(new Insets(0, 0,20,20));

        root.getChildren().add(horLayout);

        Scene scene = new Scene(root, 200, 200);

        window.setScene(scene);
        window.showAndWait();
    }
}
