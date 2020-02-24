package edu.rom.tamagotchi.controllers;

import edu.rom.tamagotchi.domain.Pet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

class GameOverScreenController {

    private LifeCycle lifecycle;
    private Pet pet;
    private Stage stageF;

    GameOverScreenController() {
    }

    GameOverScreenController(Pet pet, Stage stageF) {
        this.pet = pet;
        this.stageF = stageF;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Rectangle skyBox;

    @FXML
    private Circle sun;

    @FXML
    private ImageView background;

    @FXML
    private Button RIP;

    @FXML
    private ImageView characterPic;

    @FXML
    void exitApp(ActionEvent event) {
        pet.setShuttingDownMoment(System.currentTimeMillis());
        lifecycle.savePet(pet);
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void resetPetProgress(ActionEvent event) {
        lifecycle.deletingSaveFile();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void showAbout(ActionEvent event) {
        ScenesOperator scenesOperator = new ScenesOperator();
        scenesOperator.showAboutWindow();
    }

    @FXML
    void initialize() {
        if (stageF != null) {
            stageF.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
        }
        lifecycle = new LifeCycle();
        LivingScreenController livingScreenController = new LivingScreenController();
        livingScreenController.changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/5_" + pet.getClass().getSimpleName() + "_dead.png", characterPic);
    }
}
