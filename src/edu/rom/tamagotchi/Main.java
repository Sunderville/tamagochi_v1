package edu.rom.tamagotchi;

import edu.rom.tamagotchi.domain.Lifecycle;
import edu.rom.tamagotchi.domain.Pet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//TODO фиксация и проверка времени при запуске программы - не должен ли персонаж быть уже мёртв и на сколько изменятся его показатели
//        File saveFile = new File("save.txt");
//        if (saveFile.exists()){
//            LocalDateTime.now();
//
//        }

        Lifecycle lifecycle = new Lifecycle();
        Pet pet = lifecycle.loadPet();

        if (pet == null) {
            switchToMentionedScreen(primaryStage, "fxml/starterScreen.fxml");
        } else {
            lifecycle.createNewPet(pet);
            switchToMentionedScreen(primaryStage, "fxml/livingScreen.fxml");
        }
    }

    public void switchToMentionedScreen(Stage primaryStage, String fxmlPath) throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}