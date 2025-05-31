import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class PubSubsPatternSimulator extends Application {
    private VBox vBoxLeft, vBoxRight;
    private Broker broker;
    private Stage primaryStage;
    private FileChooser fileChooser;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        broker = new Broker();
        fileChooser = new FileChooser();
        MenuBar menuBar = new MenuBar();
        Menu menuPublisher = new Menu("Publisher");
        Menu menuSubscriber = new Menu("Subscriber");
        menuBar.getMenus().addAll(menuPublisher, menuSubscriber);
        MenuItem menuItemVideoPub = new MenuItem("Video");
        MenuItem menuItemGPSPub = new MenuItem("Car's GPS");
        menuPublisher.getItems().addAll(menuItemVideoPub, menuItemGPSPub);
        MenuItem menuItemVideoSubs = new MenuItem("Video");
        MenuItem menuItemGPSSubs = new MenuItem("Car's GPS");
        menuSubscriber.getItems().addAll(menuItemVideoSubs, menuItemGPSSubs);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        vBoxLeft = new VBox(5);
        vBoxLeft.setAlignment(Pos.CENTER);
        borderPane.setLeft(vBoxLeft);
        vBoxRight = new VBox(10);
        vBoxRight.setAlignment(Pos.CENTER);
        borderPane.setRight(vBoxRight);
        ScrollPane scrollPane = new ScrollPane(borderPane);
        Scene scene = new Scene(scrollPane, 800, 400);
        primaryStage.setTitle("Publisher-Subscriber Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);

        menuItemVideoPub.setOnAction(e -> addVideoPub());
        menuItemGPSPub.setOnAction(e -> addGPSCarPub(primaryStage));
        menuItemVideoSubs.setOnAction(e -> addVideoSubs());
        menuItemGPSSubs.setOnAction(e -> addCarSubs());
    }

    private String getInputSting(String prompt) {
        TextInputDialog dialog = new TextInputDialog("default");
        dialog.setTitle(prompt);
        dialog.setHeaderText("Please enter your " + prompt);
        dialog.setContentText(prompt + ":");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("default");
    }

    private void addVideoPub() {
        String name = getInputSting("Video Publisher Name");
        String topic = getInputSting("Video Publisher Topic");
        vBoxLeft.getChildren().add(new VideoPublisher(name, broker, topic).getView());
    }

    private void addGPSCarPub(Stage stage) {
        String name = getInputSting("Car GPS Publisher Name");
        String topic = getInputSting("Car GPS Topic");

        File file = fileChooser.showOpenDialog(stage);
        if (file == null) return;

        try {
            Scanner scanner = new Scanner(file);
            GPSCarPublisher gpsPublisher = new GPSCarPublisher(name, broker, topic, scanner);
            vBoxLeft.getChildren().add(gpsPublisher.getView());
        } catch (Exception ex) {
            System.out.println("Error al abrir archivo: " + ex.getMessage());
        }
    }

    private void addVideoSubs() {
        String name = getInputSting("Video Subscriber Name");
        String topic = getInputSting("Video Subscriber Topic");
        VideoFollower videoFollower = new VideoFollower(name, topic);
        if (broker.subscribe(videoFollower))
            vBoxRight.getChildren().add(videoFollower.getView());
    }

    private void addCarSubs() {
        String name = getInputSting("GPS Subscriber Name");
        String topic = getInputSting("GPS Topic");
        GPSFollower follower = new GPSFollower(name, topic);
        if (broker.subscribe(follower)) {
            follower.show();
        } else {
            System.out.println("Tópico no encontrado.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}