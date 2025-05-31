import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.Scanner;

public class GPSCarPublisher extends Publisher {

    public GPSCarPublisher(String name, Broker broker, String topicName, Scanner scanner) {
        super(name, broker, topicName);
        view = new HBox();
        GPS = new Label();
        view.getChildren().addAll(new Label(name + "->" + topicName + ":"), GPS);
        GPSfile = scanner;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> reportPosition()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void reportPosition() {
        if (GPSfile.hasNextLine()) {
            String line = GPSfile.nextLine();
            GPS.setText(line);
            publishNewEvent(line);
        }
    }

    public HBox getView() {
        return view;
    }

    private HBox view;
    private Label GPS;
    private Scanner GPSfile;
    private Timeline timeline;
}