package edu.rom.tamagotchi.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.rom.tamagotchi.domain.Numbers;
import edu.rom.tamagotchi.domain.Pet;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

class LivingScreenController {

    private Numbers numbers;
    private LifeCycle lifecycle;
    private Stage stageF;
    private Pet pet;
    private final double PICTURE_GROWING_RATE = 0.01;
    private final int NUMBER_OF_CYCLES = 2;          //
    private final int WALKING_SPEED = 10;             // in seconds;  more is slower
    private final int CYCLE_LENGTH = 20;              // in seconds

    LivingScreenController() {
    }

    LivingScreenController(Pet pet, Stage stageF) {
        this.pet = pet;
        this.stageF = stageF;
    }

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private Text text_fullness;
    @FXML
    private Text text_happiness;
    @FXML
    private Text text_age1;
    @FXML
    private Text text_age2;
    @FXML
    private Rectangle just_rectangle;
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
    private ImageView happinessRate;
    @FXML
    private ImageView fullnessRate;
    @FXML
    private TextField currentFullnessIndicator;
    @FXML
    private TextField currentHappinessIndicator;
    @FXML
    private TextField currentAgeIndicator;
    @FXML
    private TextField maxAgeIndicator;
    @FXML
    private Button feed;
    @FXML
    private Button play;

    @FXML
    void play(MouseEvent event) {
        if (pet.getHappiness() > 0) {
            if (pet.getHappiness() < pet.getMAX_HAPPINESS()) {
                pet.setHappiness(pet.getHappiness() + 1);
            }
            System.out.println("increased happiness: " + pet.getHappiness()); //for checkout
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

            ImageView game = new ImageView(new Image("/edu/rom/tamagotchi/images/Games/" + pet.getClass().getSimpleName() + "_game.png"));
            anchorPane.getChildren().add(game);
            game.setX(550);
            game.setY(100);
            executorService.schedule(() -> game.setVisible(false), 2, TimeUnit.SECONDS);

            changeImage("/edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/3_" + pet.getClass().getSimpleName() + "_content.png", characterPic);
            setScaleFittedForAge(PICTURE_GROWING_RATE);
            if (pet.getHappiness() > 3){
                executorService.schedule(() -> changeImage("/edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic), 2, TimeUnit.SECONDS);
            } else {
                executorService.schedule(() -> changeImage("/edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/4_" + pet.getClass().getSimpleName() + "_sick.png", characterPic), 2, TimeUnit.SECONDS);
                setScaleFittedForAge(PICTURE_GROWING_RATE);
            }
        }
    }

    @FXML
    void feed(MouseEvent event) {
        if (pet.getFullness() > 0) {
            if (pet.getFullness() < pet.getMAX_FULLNESS()) {
                pet.setFullness(pet.getFullness() + 1);
            }
            System.out.println("increased fullness: " + pet.getFullness()); //for checkout
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

            ImageView meal = new ImageView(new Image("edu/rom/tamagotchi/images/Food/" + pet.getClass().getSimpleName() + "_food.png"));
            anchorPane.getChildren().add(meal);
            meal.setX(470);
            meal.setY(120);
            executorService.schedule(() -> meal.setVisible(false), 2, TimeUnit.SECONDS);

            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/3_" + pet.getClass().getSimpleName() + "_content.png", characterPic);
            setScaleFittedForAge(PICTURE_GROWING_RATE);
            if (pet.getFullness() > 3){
                executorService.schedule(() -> changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic), 2, TimeUnit.SECONDS);
            } else {
                executorService.schedule(() -> changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/4_" + pet.getClass().getSimpleName() + "_sick.png", characterPic), 2, TimeUnit.SECONDS);
                setScaleFittedForAge(PICTURE_GROWING_RATE);
            }
        }
    }

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
                pet.setShuttingDownMoment(System.currentTimeMillis());
                lifecycle.savePet(pet);
                Platform.exit();
                System.exit(0);
            });
        }

        GettingHungryAndBoringToDeath gettingHungryAndBoringToDeath = new GettingHungryAndBoringToDeath();
        EnvironmentInAction environmentInAction = new EnvironmentInAction();
        CharacterInAction characterInAction = new CharacterInAction();
        IndicatorsControl indicatorsControl = new IndicatorsControl();
        GettingOlder gettingOlder = new GettingOlder();
        BecomingDead becomingDead = new BecomingDead();
        lifecycle = new LifeCycle();
        numbers = new Numbers();

        changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic);
        setScaleFittedForAge(PICTURE_GROWING_RATE);
        currentFullnessIndicator.setText(String.valueOf(pet.getFullness()));
        currentHappinessIndicator.setText(String.valueOf(pet.getHappiness()));
        maxAgeIndicator.setText(String.valueOf(pet.getMAX_AGE()));
        currentAgeIndicator.setText(String.valueOf(pet.getAge()));
        fullnessRate.setVisible(true);
        happinessRate.setVisible(true);

        gettingHungryAndBoringToDeath.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                characterInAction.cancel();
                indicatorsControl.cancel();
                gettingOlder.cancel();
                becomingDead.dieNow();
            }
        });
        gettingOlder.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                gettingHungryAndBoringToDeath.cancel();
                characterInAction.cancel();
                indicatorsControl.cancel();
                becomingDead.dieNow();
            }
        });

        gettingHungryAndBoringToDeath.start();
        environmentInAction.start();
        indicatorsControl.start();
        characterInAction.start();
        gettingOlder.start();

        //TODO:
        // - solve "stage.showAndWait();" exception flickering issue (ScenesOperator: 28)
        // - button "walking/stop"
        // - more synchronized flip
        // - find a way to use .properties file

    }

    void changeImage(String newUrl, ImageView characterPic) {
        characterPic.setImage(new Image(newUrl));
    }

    private void setScaleFittedForAge(double v) {
        characterPic.setScaleX(1.0 + ((double) pet.getAge() * v));
        characterPic.setScaleY(1.0 + ((double) pet.getAge() * v));
    }


    private class EnvironmentAnimation {

        private void wakeUpNature() {
            sunrise();
            sunDimming();
            skyDimming();
        }

        private void sunrise() {
            TranslateTransition sunrise = new TranslateTransition(Duration.millis(CYCLE_LENGTH * 1000), sun);
            sunrise.setByY(-400);
            sunrise.setCycleCount(NUMBER_OF_CYCLES);
            sunrise.setAutoReverse(true);
            sunrise.play();
        }

        private void sunDimming() {
            FillTransition sunDimming = new FillTransition(Duration.millis(CYCLE_LENGTH * 1000), sun);
            sunDimming.setFromValue(Color.valueOf("#ef7f13"));
            sunDimming.setToValue(Color.valueOf("#f2e611"));
            sunDimming.setAutoReverse(true);
            sunDimming.setCycleCount(NUMBER_OF_CYCLES);
            sunDimming.play();
        }

        private void skyDimming() {
            FadeTransition skyDimming = new FadeTransition(Duration.millis(CYCLE_LENGTH * 1000), skyBox);
            skyDimming.setFromValue(6.0);
            skyDimming.setToValue(0.1);
            skyDimming.setAutoReverse(true);
            skyDimming.setCycleCount(NUMBER_OF_CYCLES);
            skyDimming.play();
        }
    }

    class EnvironmentInAction extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    EnvironmentAnimation environmentAnimation = new EnvironmentAnimation();
                    while (true) {
                        if (!pet.getIsDead()) {
                            environmentAnimation.wakeUpNature();
                            Thread.sleep(NUMBER_OF_CYCLES * CYCLE_LENGTH * 1000);
                        } else {
                            break;
                        }
                    }
                    return null;
                }
            };
        }
    }


    private class CharacterAnimation {

        private void moveForward() {
            TranslateTransition moveF = new TranslateTransition(Duration.millis(WALKING_SPEED * 1000), characterPic);
            moveF.setByX(-400);
            moveF.setCycleCount(1);
            moveF.setAutoReverse(false);
            moveF.play();
        }

        private void moveBackward() {
            TranslateTransition moveB = new TranslateTransition(Duration.millis(WALKING_SPEED * 1000), characterPic);
            moveB.setByX(400);
            moveB.setCycleCount(1);
            moveB.setAutoReverse(false);
            moveB.play();
        }

    }

    class CharacterInAction extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    CharacterAnimation characterAnimation = new CharacterAnimation();
                    while (true) {
                        if (!pet.getIsDead()) {
                            characterAnimation.moveForward();
                            Thread.sleep(WALKING_SPEED * 1000);
                            characterPic.setScaleX(-1);
                            Thread.yield();
                            characterAnimation.moveBackward();
                            Thread.sleep(WALKING_SPEED * 1000);
                            characterPic.setScaleX(1);
                            Thread.yield();
                        } else {
                            break;
                        }
                    }
                    return null;
                }
            };
        }
    }


    class GettingOlder extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                        while (pet.getAge() < pet.getMAX_AGE()) {
                            Thread.sleep(pet.getAGING_STEP() * 1000);
                            pet.setAge(pet.getAge() + 1);
                            currentAgeIndicator.setText(String.valueOf(pet.getAge()));
                            setScaleFittedForAge(PICTURE_GROWING_RATE);
                        }
                    return null;
                }
            };
        }
    }

    class IndicatorsControl extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    do {
                        Thread.sleep(200);
                        currentFullnessIndicator.setText(String.valueOf(pet.getFullness()));
                        currentHappinessIndicator.setText(String.valueOf(pet.getHappiness()));
                        if (pet.getFullness() < 10) {
                            changeImage(numbers.numbers.get(pet.getFullness()), fullnessRate);
                            fullnessRate.setVisible(true);
                        } else {
                            fullnessRate.setVisible(false);
                        }
                        if (pet.getHappiness() < 10) {
                            changeImage(numbers.numbers.get(pet.getHappiness()), happinessRate);
                            happinessRate.setVisible(true);
                        } else {
                            happinessRate.setVisible(false);
                        }
                    } while (!pet.getIsDead());
                    return null;
                }
            };
        }
    }

    class GettingHungryAndBoringToDeath extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    while (pet.getFullness() > 0 && pet.getHappiness() > 0) {
                        if (pet.getFullness() > 3 && pet.getHappiness() > 3) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*1000);
                            pet.setFullness(pet.getFullness() - 1);
                            pet.setHappiness(pet.getHappiness() - 1);
                            System.out.println("current fullness and happiness are  " + pet.getFullness() + " " + pet.getHappiness());
                        } else                        if ((pet.getFullness() <= 3 && pet.getFullness() > 0) || (pet.getHappiness() <= 3 && pet.getHappiness() > 0)) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/4_" + pet.getClass().getSimpleName() + "_sick.png", characterPic);
                            setScaleFittedForAge(PICTURE_GROWING_RATE);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*1000);
                            pet.setFullness(pet.getFullness() - 1);
                            pet.setHappiness(pet.getHappiness() - 1);
                            System.out.println("current fullness and happiness are  " + pet.getFullness() + " " + pet.getHappiness());
                        }
                    }
                    return null;
                }
            };
        }
    }

    private class BecomingDead {

        private void dieNow() {
            dieLogically();
            dieGraphically();
        }

        private void dieLogically() {
            pet.setIsDead(true);
            pet.setDeathMoment(System.currentTimeMillis());
            lifecycle.savePet(pet);
            System.out.println("Dead");
        }

        private void dieGraphically() {
            Shadow shadow = new Shadow();
            fullnessRate.setVisible(false);
            happinessRate.setVisible(false);
            text_age1.setVisible(false);
            text_age2.setVisible(false);
            text_fullness.setVisible(false);
            text_happiness.setVisible(false);
            currentAgeIndicator.setVisible(false);
            maxAgeIndicator.setVisible(false);
            currentFullnessIndicator.setVisible(false);
            currentHappinessIndicator.setVisible(false);
            just_rectangle.setVisible(false);
            background.setEffect(shadow);
            skyBox.setStroke(Color.BLACK);
            skyBox.setStrokeType(StrokeType.INSIDE);
            feed.setVisible(false);
            play.setVisible(false);
            Button ripButton = new Button("R I P");
            ripButton.setStyle("-fx-font: 22 system;");
            ripButton.setLayoutX(360.0);
            ripButton.setLayoutY(573.0);
            ripButton.prefHeight(53.0);
            ripButton.prefHeight(156.0);
            ripButton.setVisible(true);
            anchorPane.getChildren().add(ripButton);

            ImageView gameOver = new ImageView(new Image("edu/rom/tamagotchi/images/GameOver.png"));
            gameOver.setLayoutX(223.0);
            gameOver.setLayoutY(153.0);
            gameOver.setFitWidth(354.0);
            gameOver.setFitHeight(234.0);
            gameOver.setPreserveRatio(true);
            gameOver.setPickOnBounds(true);
            gameOver.setVisible(true);
            anchorPane.getChildren().add(gameOver);

            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/5_" + pet.getClass().getSimpleName() + "_dead.png", characterPic);
            characterPic.setLayoutX(300.0);
            characterPic.setLayoutY(400.0);
            characterPic.setFitWidth(200.0);
            characterPic.setFitHeight(150.0);
            characterPic.setScaleX(1.0);
            characterPic.setScaleY(1.0);
            characterPic.setPreserveRatio(true);
            characterPic.setPickOnBounds(true);
            characterPic.setVisible(true);

            DropShadow shadowedRipButton = new DropShadow();

            // Adding the shadow when the mouse cursor is on
            ripButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    ripButton.setEffect(shadowedRipButton);
                }
            });
            // Removing the shadow when the mouse cursor is off
            ripButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    ripButton.setEffect(null);
                }
            });

            ripButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pet.setShuttingDownMoment(System.currentTimeMillis());
                    lifecycle.savePet(pet);
                    Platform.exit();
                    System.exit(0);
                }
            });
        }
    }


    // just in case
    class GettingHungryToDeath extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    Thread.sleep(pet.getHUNGRINESS_STEP()*1000 - 1000);
                    while (pet.getFullness() != 0) {
                        if (pet.getFullness() > 3) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*1000);
                            pet.setFullness(pet.getFullness() - 1);
                            System.out.println("current fullness is " + pet.getFullness());
                        }
                        if (pet.getFullness() <= 3 && pet.getFullness() > 0) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/4_" + pet.getClass().getSimpleName() + "_sick.png", characterPic);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*1000);
                            pet.setFullness(pet.getFullness() - 1);
                            System.out.println("current fullness is " + pet.getFullness());
                        }
                    }
                    return null;
                }
            };
        }
    }
    class GettingBoringToDeath extends Service {
        @Override
        protected Task createTask() {
            return new Task(){
                @Override
                protected Object call() throws Exception {
                    while (pet.getHappiness() != 0) {
                        if (pet.getHappiness() > 3) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/2_" + pet.getClass().getSimpleName() + "_walks.png", characterPic);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*2000);
                            pet.setHappiness(pet.getHappiness() - 1);
                            System.out.println("current happiness is " + pet.getHappiness());
                        }
                        if (pet.getHappiness() <= 3 && pet.getHappiness() > 0) {
                            changeImage("edu/rom/tamagotchi/images/" + pet.getClass().getSimpleName() + "/4_" + pet.getClass().getSimpleName() + "_sick.png", characterPic);
                            Thread.sleep(pet.getHUNGRINESS_STEP()*2000);
                            pet.setHappiness(pet.getHappiness() - 1);
                            System.out.println("current happiness is " + pet.getHappiness());
                        }
                    }
                    return null;
                }
            };
        }
    }
}
