package edu.rom.tamagotchi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * для запуска приложения на JDK 11+ через Main в Idea:
     * 1. подключиь SDK JavaFX (/lib) в File - Project Structure... - Libraries
     * 2. установить параметры запуска в Run - Run Configuration - VM options:
     * --module-path {path to javafx}(exmpl:"C:\Java\javafx-sdk-11.0.2\lib") --add-modules javafx.controls,javafx.fxml
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/starterScreen.fxml"));
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}