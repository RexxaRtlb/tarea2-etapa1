public abstract class Subscriber extends Component {
    protected Subscriber() {}

    public Subscriber(String name, String topicName) {
        super(name, topicName);
    }

    public abstract void update(String message);
}