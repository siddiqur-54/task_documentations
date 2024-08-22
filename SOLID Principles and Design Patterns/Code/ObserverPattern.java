import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}

// ConcreteSubject: YouTubeChannel
class YouTubeChannel implements Channel {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;
    private String latestVideo;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;
        notifySubscribers();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public String getLatestVideo() {
        return latestVideo;
    }

    public String getChannelName() {
        return channelName;
    }
}

// Observer Interface
interface Subscriber {
    void update();
}

// ConcreteObserver: YouTubeSubscriber
class YouTubeSubscriber implements Subscriber {
    private String subscriberName;
    private YouTubeChannel channel;

    public YouTubeSubscriber(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public void subscribeToChannel(YouTubeChannel channel) {
        this.channel = channel;
        channel.subscribe(this);
    }

    @Override
    public void update() {
        System.out.println(subscriberName + ", a new video titled \"" + channel.getLatestVideo() +
                "\" has been uploaded to " + channel.getChannelName() + ".");
    }
}

// Main method: Client Code
public class ObserverPattern {
    public static void main(String[] args) {
        YouTubeChannel techChannel = new YouTubeChannel("Nemo");

        YouTubeSubscriber sub1 = new YouTubeSubscriber("Siddiqur");
        YouTubeSubscriber sub2 = new YouTubeSubscriber("Rahman");

        sub1.subscribeToChannel(techChannel);
        sub2.subscribeToChannel(techChannel);

        techChannel.uploadVideo("Observer Pattern Explained");
        techChannel.uploadVideo("Prototype Pattern Tutorial");
    }
}
