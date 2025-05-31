import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class VideoPublisher extends Publisher {
    public VideoPublisher(String name, Broker broker, String topicName) {
        super(name, broker, topicName);

        message = new TextField();
        message.setPromptText("Ingresa URL del video");

        message.setOnAction(e -> {
            String url = message.getText();
            if (!url.isEmpty()) {
                publishNewEvent(url);
                message.clear();
            }
        });

        view = new HBox(10);
        view.getChildren().addAll(message);
    }

    public HBox getView() {
        return view;
    }

    private HBox view;
    private TextField message;
}