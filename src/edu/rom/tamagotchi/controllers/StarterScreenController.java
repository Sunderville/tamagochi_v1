package edu.rom.tamagotchi.controllers;

import edu.rom.tamagotchi.domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class StarterScreenController {

    private Lifecycle lifecycle = new Lifecycle();
    private Pet pet;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void createNewLion(MouseEvent event) {
        pet = new Lion();
    }

    @FXML
    void createNewDuck(MouseEvent event) {
        pet = new Duck();
    }

    @FXML
    void createNewHomer(MouseEvent event) {
        pet = new Homer();
    }

    @FXML
    void createNewTerminator(MouseEvent event) {
        pet = new Terminator();
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
