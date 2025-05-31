import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoFollower extends Subscriber {

    private VBox view;
    private ComboBox<String> comboBox;
    private Label label;
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    public VideoFollower(String name, String topicName) {
        super(name, topicName);

        view = new VBox(10);
        label = new Label(name + " -> " + topicName + ":");
        comboBox = new ComboBox<>();

        // Agrega URLs oficiales
        comboBox.getItems().addAll(
            "http://profesores.elo.utfsm.cl/~agv/elo329/1s22/Assignments/20220430_100849.mp4",
            "http://profesores.elo.utfsm.cl/~agv/elo329/1s22/Assignments/20220430_101027.mp4"
        );
        comboBox.setPromptText("Selecciona un video...");
        comboBox.setEditable(false);

        comboBox.setOnAction(e -> playSelectedVideo());

        mediaView = new MediaView();

        view.getChildren().addAll(label, comboBox, mediaView);
    }

    @Override
    public void update(String message) {
        if (!comboBox.getItems().contains(message)) {
            comboBox.getItems().add(message);
        }
        comboBox.setValue(message);
        playVideo(message);
    }

    private void playSelectedVideo() {
        String url = comboBox.getValue();
        if (url != null) {
            playVideo(url);
        }
    }

    private void playVideo(String url) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            Media media = new Media(url);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            System.out.println("No se pudo reproducir el video: " + url);
        }
    }

    public VBox getView() {
        return view;
    }
}