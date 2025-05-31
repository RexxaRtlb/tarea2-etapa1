import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GPSFollower extends Subscriber {

    private Label label;
    private Stage stage;

    public GPSFollower(String name, String topicName) {
        super(name, topicName);

        label = new Label("Esperando posición...");

        VBox root = new VBox(label);
        Scene scene = new Scene(root, 300, 100);
        stage = new Stage();
        stage.setTitle("GPS Follower - " + name);
        stage.setScene(scene);
    }

    @Override
    public void update(String message) {
        label.setText(message);
        show();
    }

    public void show() {
        stage.show();
    }
}