package menu;

import javafx.scene.control.Alert;

public class MyAlert {

    private static boolean turnWasWasted;
    public static void showInvalidMoveAlert(String s) {
        turnWasWasted = true;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Move");
        alert.setHeaderText("Cannot make the move");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static void showStickyMoveAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Move");
        alert.setHeaderText("Cannot make the move");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static boolean getTurnWasWasted(){
        return turnWasWasted;
    }

    public static void setTurnWasWasted(boolean tW){
        turnWasWasted = tW;
    }
}
