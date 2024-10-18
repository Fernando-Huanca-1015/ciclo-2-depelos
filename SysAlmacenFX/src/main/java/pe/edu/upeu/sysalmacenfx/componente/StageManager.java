package pe.edu.upeu.sysalmacenfx.componente;

import javafx.stage.Stage;
import lombok.*;


public class StageManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}

