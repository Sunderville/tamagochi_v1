package edu.rom.tamagotchi.controllers;

import edu.rom.tamagotchi.domain.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StarterScreenController {

    private ScenesOperator scenesOperator;
    private LifeCycle lifecycle;
    private Stage stageF;
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
    private AnchorPane anchorPane;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    void createNewLion(MouseEvent event) {
            pet = new Lion(System.currentTimeMillis(), System.currentTimeMillis(), 1, 4, 8, false);
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/livingScreen.fxml", stageF, createNewLionButton, new LivingScreenController(pet, stageF));
            lifecycle.savePet(pet);
    }

    @FXML
    void createNewDuck(MouseEvent event) {
            pet = new Duck(System.currentTimeMillis(), System.currentTimeMillis(), 1, 4, 8, false);
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/livingScreen.fxml", stageF, createNewDuckButton, new LivingScreenController(pet, stageF));
            lifecycle.savePet(pet);
    }

    @FXML
    void createNewHomer(MouseEvent event) {
            pet = new Homer(System.currentTimeMillis(), System.currentTimeMillis(), 1, 4, 8, false);
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/livingScreen.fxml", stageF, createNewHomerButton, new LivingScreenController(pet, stageF));
            lifecycle.savePet(pet);
    }

    @FXML
    void createNewTerminator(MouseEvent event) {
            pet = new Terminator(System.currentTimeMillis(), System.currentTimeMillis(), 1, 4, 8, false);
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/livingScreen.fxml", stageF, createNewTerminatorButton, new LivingScreenController(pet, stageF));
            lifecycle.savePet(pet);
    }

    @FXML
    void exitApp(ActionEvent event) {
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
        scenesOperator = new ScenesOperator();
        scenesOperator.showAboutWindow();
    }

    @FXML
    void initialize() {
        scenesOperator = new ScenesOperator();
        lifecycle = new LifeCycle();
        pet = lifecycle.loadPet();
        stageF = new Stage();

        long theoreticalDeathTime = lifecycle.calculateTheoreticalDeathTime(pet);
        if (System.currentTimeMillis() < theoreticalDeathTime) {
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/livingScreen.fxml",
                    stageF, anchorPane, new LivingScreenController(pet, stageF));
        } else if (System.currentTimeMillis() < (theoreticalDeathTime + pet.getFREEZE_AFTER_DEATH() * 1000)) {
            pet.setDeathMoment(theoreticalDeathTime);
            pet.setIsDead(true);
            lifecycle.savePet(pet);
            scenesOperator.switchToMentionedScreen("/edu/rom/tamagotchi/fxml/gameOverScreen.fxml",
                    stageF, anchorPane, new GameOverScreenController(pet, stageF));
        }
    }
}
