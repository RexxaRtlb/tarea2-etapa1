import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Stage1 extends Application {

    private Broker broker = new Broker();
    private VBox publishersView = new VBox(10);
    private VBox subscribersView = new VBox(10);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu publisherMenu = new Menu("Publisher");
        MenuItem videoPublisherItem = new MenuItem("Video");
        videoPublisherItem.setOnAction(e -> createVideoPublisher());
        publisherMenu.getItems().add(videoPublisherItem);

        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem videoFollowerItem = new MenuItem("Video");
        videoFollowerItem.setOnAction(e -> createVideoFollower());
        subscriberMenu.getItems().add(videoFollowerItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        root.setTop(menuBar);
        root.setLeft(publishersView);
        root.setRight(subscribersView);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Simulador Publicador/Suscriptor - Etapa 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createVideoPublisher() {
        String name = prompt("Nombre del publicador:");
        if (name == null) return;

        String topic = prompt("Tópico del publicador:");
        if (topic == null) return;

        VideoPublisher vp = new VideoPublisher(name, broker, topic);
        publishersView.getChildren().add(vp.getView());
    }

    private void createVideoFollower() {
        String name = prompt("Nombre del suscriptor:");
        if (name == null) return;

        String topic = prompt("Tópico del suscriptor:");
        if (topic == null) return;

        VideoFollower vf = new VideoFollower(name, topic);
        if (broker.subscribe(vf)) {
            subscribersView.getChildren().add(vf.getView());
        } else {
            alert("Error", "El tópico no existe. Primero debes crear un publicador.");
        }
    }

    private String prompt(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingreso de datos");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        return dialog.showAndWait().orElse(null);
    }

    private void alert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}