import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VideoFollower extends Subscriber {
    public VideoFollower(String name, String topicName) {
        super(name, topicName);

        video = new Button("Esperando publicación...");
        view = new HBox(10);
        view.getChildren().addAll(video);
    }

    @Override
    public void update(String message) {
        video.setText(message);
    }

    public HBox getView() {
        return view;
    }

    private HBox view;
    private Button video;
}