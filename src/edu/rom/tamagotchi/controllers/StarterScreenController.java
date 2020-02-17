package edu.rom.tamagotchi.controllers;

import edu.rom.tamagotchi.Main;
import edu.rom.tamagotchi.domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StarterScreenController {

    private Lifecycle lifecycle = new Lifecycle();
    private ScenesOperator scenesOperator = new ScenesOperator();
    public Pet pet;


    @FXML
    private Button createNewLionButton;

    @FXML
    private Button createNewDuckButton;

    @FXML
    private Button createNewHomerButton;

    @FXML
    private Button createNewTerminatorButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void createNewLion(MouseEvent event) throws IOException {
            pet = new Lion();
            scenesOperator.switchToMentionedScreen(new Stage(),
                    "/edu/rom/tamagotchi/fxml/livingScreen.fxml", createNewLionButton);
    }

    @FXML
    void createNewDuck(MouseEvent event) throws IOException {
            pet = new Duck();
            scenesOperator.switchToMentionedScreen(new Stage(),
                    "/edu/rom/tamagotchi/fxml/livingScreen.fxml", createNewDuckButton);
    }

    @FXML
    void createNewHomer(MouseEvent event) throws IOException {
            pet = new Homer();
            scenesOperator.switchToMentionedScreen(new Stage(),
                    "/edu/rom/tamagotchi/fxml/livingScreen.fxml", createNewHomerButton);
    }

    @FXML
    void createNewTerminator(MouseEvent event) throws IOException {
            pet = new Terminator();
            scenesOperator.switchToMentionedScreen(new Stage(),
                    "/edu/rom/tamagotchi/fxml/livingScreen.fxml", createNewTerminatorButton);
    }

    @FXML
    void exitApp(ActionEvent event) {
        lifecycle.savePet(pet);
        System.exit(0);
    }

    @FXML
    void resetPetProgress(ActionEvent event) {

    }

    @FXML
    void showAbout(ActionEvent event) {

    }

    @FXML
    void initialize() {




    }
}
