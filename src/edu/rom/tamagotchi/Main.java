package edu.rom.tamagotchi;

import edu.rom.tamagotchi.domain.Lifecycle;
import edu.rom.tamagotchi.domain.Pet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Lifecycle lifecycle = new Lifecycle();
        Pet pet = lifecycle.loadPet();

        if (pet == null) {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/starterScreen.fxml"));
            primaryStage.setTitle("Tamagotchi");
            primaryStage.setScene(new Scene(root, 800, 640));
            primaryStage.show();

        } else {

            lifecycle.createNewPet(pet);

            Parent root = FXMLLoader.load(getClass().getResource("fxml/livingScreen.fxml"));
            primaryStage.setTitle("Tamagotchi");
            primaryStage.setScene(new Scene(root, 800, 640));
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}





//    private int index;
//    private Pet petCurrent;
//    Pet[] pets;

//        VBox vBox = new VBox();
//        HBox hBox = new HBox();
//
//        Label label = new Label("Выберите персонажа");
//        Label labels[] = new Label[3];
//
//        pets = new Pet[3];
//        pets[0] = new Homer("Cat1.png");
//        pets[1] = new Lion("Cat1.png");
//        pets[2] = new Duck("Cat1.png");
//        pets[2] = new Terminator("Cat1.png");
//
//        labels[0] = new Label("Homer");
//        labels[1] = new Label("Duck");
//        labels[2] = new Label("Lion");
//        labels[2] = new Label("Terminator");
//
//        labels[0].setMinWidth(200);
//        labels[1].setMinWidth(200);
//        labels[2].setMinWidth(200);
//        labels[2].setMinWidth(200);
//        labels[0].setOnMouseClicked(event -> {
//            setIndex(0);
//            primaryStage.setTitle(petCurrent.getName());
//        });
//        labels[1].setOnMouseClicked(event -> {
//            setIndex(1);
//            primaryStage.setTitle(petCurrent.getName());
//        });
//        labels[2].setOnMouseClicked(event -> {
//            setIndex(2);
//            primaryStage.setTitle(petCurrent.getName());
//        });
//
//
//        labels[0].setStyle("-fx-border: 3px solid black");
//        ImageView[] image = new ImageView[3];
//        image[0].setImage(new Image(pets[0].getUrl()));
//        image[1].setImage(new Image(pets[1].getUrl()));
//        image[2].setImage(new Image(pets[2].getUrl()));

//        Image image1 = new Image(getClass().getResource);
//        ImageView imageView = new ImageView(image1);
//        vBox.setSpacing(50);
//        vBox.setAlignment(Pos.TOP_CENTER);
//        vBox.getChildren().add(label);
//        vBox.getChildren().add(hBox);
//        hBox.getChildren().addAll(labels);
//        hBox.setAlignment(Pos.BASELINE_CENTER);
//        primaryStage.setTitle("Tamagotchi");
//        primaryStage.setScene(new Scene(vBox, 800, 600));
//        primaryStage.show();

//    public void setIndex(int index) {
//        this.index = index;
//        this.petCurrent = this.pets[this.index];
//    }