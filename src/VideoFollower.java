import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoFollower extends Subscriber {
    public VideoFollower(String name, String topicName) {
        super(name, topicName);

        video = new Button("Esperando publicación...");
        video.setOnAction(e -> {
            if (lastURL != null && !lastURL.isEmpty()) {
                playVideo(lastURL);
            }
        });

        view = new HBox(10);
        view.getChildren().addAll(video);
    }

    @Override
    public void update(String message) {
        lastURL = message;
        video.setText(message); // Actualiza el texto del botón con la nueva URL
    }

    public HBox getView() {
        return view;
    }

    private void playVideo(String url) {
        try {
            Media media = new Media(url);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            VBox root = new VBox(mediaView);
            Scene scene = new Scene(root, 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Reproduciendo video");
            stage.setScene(scene);
            stage.show();

            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al reproducir video: " + e.getMessage());
        }
    }

    private HBox view;
    private Button video;
    private String lastURL;
}
