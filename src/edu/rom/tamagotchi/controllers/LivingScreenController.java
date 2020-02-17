package edu.rom.tamagotchi.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.rom.tamagotchi.domain.Lifecycle;
import edu.rom.tamagotchi.domain.Pet;
import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class LivingScreenController {


    private Lifecycle lifecycle = new Lifecycle();
    private ScenesOperator scenesOperator = new ScenesOperator();
    public Pet pet;

    public LivingScreenController() {
    }

    public LivingScreenController(Pet pet) {
        this.pet = pet;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Circle sun;
    @FXML
    private Rectangle skyBox;

    @FXML
    private ImageView characterPic;

    @FXML
    private Button feed;

    @FXML
    void feed(MouseEvent event) {
//        changeImage("edu/rom/tamagotchi/images/1_" + characterPic.getClass().getName() + "/3_" + characterPic.getClass().getName() + "_content.png", characterPic);

        changeImage("edu/rom/tamagotchi/images/1_Lion/3_Lion_content.png", characterPic);

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> changeImage("edu/rom/tamagotchi/images/1_Lion/2_Lion_walks.png", characterPic), 2, TimeUnit.SECONDS);
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
    void initialize() throws NoSuchFieldException {
//        characterPic.setImage();

        sunrise();
        sunDimming();
        skyDimming();
        characterMoving();
        characterGrowing();
        System.out.println(pet);
//        System.out.println(StarterScreenController.class.getField("pet"));



    }

    public void environment() {

    }

    public void sunrise() {
        TranslateTransition sunrise = new TranslateTransition(Duration.millis(10000), sun);
        sunrise.setByY(-400);
        sunrise.setCycleCount(300);
        sunrise.setAutoReverse(true);
        sunrise.play();
    }

    public void sunDimming() {
        FadeTransition dimming = new FadeTransition(Duration.millis(10000), skyBox);
        dimming.setFromValue(4.0);
        dimming.setToValue(1.0);
        dimming.setAutoReverse(true);
        dimming.setCycleCount(300);
        dimming.play();
    }

    public void skyDimming() {
        FadeTransition dimming = new FadeTransition(Duration.millis(10000), skyBox);
        dimming.setFromValue(4.0);
        dimming.setToValue(0.1);
        dimming.setAutoReverse(true);
        dimming.setCycleCount(300);
        dimming.play();
    }

    public void characterMoving() {
        TranslateTransition move = new TranslateTransition(Duration.millis(5000), characterPic);
        move.setByX(-400);
        move.setCycleCount(300);
        move.setAutoReverse(true);
        move.play();
    }

    public void characterGrowing() {
        ScaleTransition petGrowing = new ScaleTransition(Duration.millis(20000), characterPic);
        petGrowing.setByY(1.25);
        petGrowing.setByX(1.25);
        petGrowing.play();
    }

    public void changeImage(String newUrl, ImageView characterPic) {
        characterPic.setImage(new Image(newUrl));
    }



}
