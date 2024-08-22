# Design Patterns

## 1. Prototype Design Pattern
The Prototype design pattern is a creational design pattern used when the cost of creating a new object is high, and you need to create multiple instances of objects that are identical or similar to each other. Instead of creating new objects from scratch, the Prototype pattern allows you to clone existing objects.

### Steps to Implement the Protoype Pattern

#### I. Prototype Interface
The Prototype interface declares a method for cloning objects. This method is usually named clone. The clone method is responsible for creating a copy of the object.

```java
public interface Prototype {
    Prototype clone();
}
```
#### II. Concrete Prototype Classes
Concrete prototype classes implement the Prototype interface and override the clone method to return a copy of the object.

**Circle Class**
```java
public class Circle implements Prototype {
    private int radius;
    private String color;

    public Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Circle clone() {
        return new Circle(this.radius, this.color);
    }

    @Override
    public String toString() {
        return "Circle{radius=" + radius + ", color='" + color + "'}";
    }
}
```

**Rectangle Class**
```java
public class Rectangle implements Prototype {
    private int width;
    private int height;
    private String color;

    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.width, this.height, this.color);
    }

    @Override
    public String toString() {
        return "Rectangle{width=" + width + ", height=" + height + ", color='" + color + "'}";
    }
}
```

#### III. Prototype Registry
A prototype registry maintains a collection of prototype objects, allowing clients to clone objects without needing to know their exact types. The registry stores prototypes in a HashMap and retrieves them by their identifiers.

```java
import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    public void addPrototype(String ind, Prototype prototype) {
        prototypes.put(ind, prototype);
    }

    public Prototype getPrototype(String ind) {
        return prototypes.get(ind).clone();
    }
}
```

#### IV. The Dirver Code
The driver code creates prototypes, adds them to the registry, clones them, and modifies the cloned objects.

```java
public class PrototypePattern {
    public static void main(String[] args) {
        Circle circlePrototype = new Circle(5, "Red");
        Rectangle rectanglePrototype = new Rectangle(10, 20, "Blue");
        
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.addPrototype("Large Red Circle", circlePrototype);
        registry.addPrototype("Large Blue Rectangle", rectanglePrototype);

        Circle clonedCircle = (Circle) registry.getPrototype("Large Red Circle");
        Rectangle clonedRectangle = (Rectangle) registry.getPrototype("Large Blue Rectangle");

        System.out.println(clonedCircle);
        System.out.println(clonedRectangle);

        clonedCircle = new Circle(7, "Green");
        System.out.println(clonedCircle);

        System.out.println(registry.getPrototype("Large Red Circle"));
    }
}
```

#### VI. Output
```css
Circle{radius=5, color='Red'}
Rectangle{width=10, height=20, color='Blue'}
Circle{radius=7, color='Green'}
Circle{radius=5, color='Red'}
```

## 2. Observer Design Pattern

The Observer design pattern is a behavioral design pattern that allows one object (the subject) to notify other objects (the observers) about changes in its state. This pattern is useful for scenarios where changes in one object should trigger updates in other dependent objects.

### Steps to Implement the Observer Pattern

#### I. Define the `Channel` Interface
The `Channel` interface declares methods for attaching, detaching, and notifying observers. This interface is the foundation of the pattern.

```java
public interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}
```

#### II. Implement the `YouTubeChannel` Class
we'll create a `YouTubeChannel` class that will notify its subscribers when a new video is uploaded.

```java
public class YouTubeChannel implements Channel {
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
```

#### III. Define the `Subscriber` Interface
The `Subscriber` interface defines the update method that will be called when the subject changes. This interface ensures that all observers implement a common method for receiving updates.

```java
public interface Subscriber {
    void update();
}
```

#### IV. Implement the `YoutubeSubscriber` Class
In this example, we'll create a `YoutubeSubscriber` class that represents a subscriber to a YouTube channel.

```java
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
```

#### V. The Driver Code
The driver code creates instances of the channel and subscribers, attaches the subscribers to the channel, and updates the changes.

```java
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
```

#### VI. Output
```css
Siddiqur, a new video titled "Observer Pattern Explained" has been uploaded to Nemo.
Rahman, a new video titled "Observer Pattern Explained" has been uploaded to Nemo.
Siddiqur, a new video titled "Prototype Pattern Tutorial" has been uploaded to Nemo.
Rahman, a new video titled "Prototype Pattern Tutorial" has been uploaded to Nemo.
```
