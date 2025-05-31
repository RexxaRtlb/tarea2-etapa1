import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CarTracker extends Subscriber {

    private final Stage stage;
    private final Circle car;
    private final Label telemetry;
    private final Pane map;

    public CarTracker(String name, String topicName) {
        super(name, topicName);

        car = new Circle(10, Color.RED);
        telemetry = new Label("Esperando posición...");
        map = new Pane(car);
        map.setPrefSize(400, 400);

        BorderPane layout = new BorderPane();
        layout.setCenter(map);
        layout.setBottom(telemetry);

        Scene scene = new Scene(layout, 400, 450);
        stage = new Stage();
        stage.setTitle("Car Tracker - " + name);
        stage.setScene(scene);
    }

    @Override
    public void update(String message) {
        try {
            String[] parts = message.trim().split("\\s+");
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);

            telemetry.setText("Posición recibida: (" + x + ", " + y + ")");

            // Escalamos a 400x400
            double scaledX = x * (map.getPrefWidth() / 100.0);
            double scaledY = y * (map.getPrefHeight() / 100.0);

            car.setCenterX(scaledX);
            car.setCenterY(scaledY);

            show();
        } catch (Exception e) {
            telemetry.setText("Error al interpretar mensaje: " + message);
        }
    }


    public void show() {
        stage.show();
    }
}
