# Table of Contents

## 1. SOLID Principles
- 1.1 [S: Single Responsibility Principle (SRP)](#11-s-single-responsibility-principle-srp)
- 1.2 [O: Open/Closed Principle (OCP)](#12-o-openclosed-principle-ocp)
- 1.3 [L: Liskov Substitution Principle (LSP)](#13-l-liskov-substitution-principle-lsp)
- 1.4 [I: Interface Segregation Principle (ISP)](#14-i-interface-segregation-principle-isp)
- 1.5 [D: Dependency Inversion Principle (DIP)](#15-d-dependency-inversion-principle-dip)

## 2. Design Patterns
- 2.1 [Prototype Design Pattern](#21-prototype-design-pattern)
- 2.2 [Observer Design Pattern](#22-observer-design-pattern)
- 2.3 [Facade Design Pattern](#23-facade-design-pattern)


# 1. SOLID Principles
> The SOLID principle was introduced by Robert C. Martin, also known as Uncle Bob and it is a coding standard in programming.

- The SOLID principle helps in reducing tight coupling. Tight coupling means a group of classes are highly dependent on one another which you should avoid in your code.
- Opposite of tight coupling is loose coupling and your code is considered as a good code when it has loosely-coupled classes.
- Loosely coupled classes minimize changes in your code, helps in making code more reusable, maintainable, flexible and stable.
## 1.1 S: Single Responsibility Principle (SRP)

> A class should have a single reason to change, meaning it should be focused on one specific task or responsibility.

By ensuring that a class is dedicated to a single responsibility, it becomes simpler, more focused, and easier to manage. This also makes the class more reusable and maintainable.

### Example of Not Maintaining SRP

Consider a class called `UserManager` that handles user authentication, user profile management, and email notifications.

```java
public class UserManager {

    public void authenticateUser(String username, String password) {
        if ("user123".equals(username) && "password".equals(password)) {
            System.out.println("User authenticated successfully.");
        } else {
            System.out.println("Authentication failed.");
        }
    }

    public void updateUserProfile(String username, String newProfileInfo) {
        System.out.println("Profile of user '" + username + "' updated with new information: " + newProfileInfo);
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        
        userManager.authenticateUser("user123", "password");
        userManager.updateUserProfile("user123", "New Profile Info");
    }
}
```

**Output**
```css
User authenticated successfully.
Profile of user 'user123' updated with new information: New Profile Info
```

This class violates the SRP because it has multiple responsibilities: authentication and profile management.

### Example of Maintaining SRP

To adhere to the SRP, we can split this class into two separate classes, each with a single responsibility:

```java
class AuthenticationManager {
    public void authenticateUser(String username, String password) {
        if ("user123".equals(username) && "password".equals(password)) {
            System.out.println("User authenticated successfully.");
        } else {
            System.out.println("Authentication failed.");
        }
    }
}

class UserProfileManager {
    public void updateUserProfile(String username, String newProfileInfo) {
        System.out.println("Profile of user '" + username + "' updated with new information: " + newProfileInfo);
    }
}

public class UserManager {

    public static void main(String[] args) {
        AuthenticationManager authManager = new AuthenticationManager();
        UserProfileManager profileManager = new UserProfileManager();

        authManager.authenticateUser("user123", "password");
        profileManager.updateUserProfile("user123", "New Profile Info");
    }
}
```

**Output**
```css
User authenticated successfully.
Profile of user 'user123' updated with new information: New Profile Info
```

## 1.2 O: Open/Closed Principle (OCP)

> Software entities such as classes, modules, and functions should be open to extension but closed to modification.

This principle encourages designing software in a way that allows adding new features or behavior without altering the existing code. This approach helps minimize the risk of introducing bugs or errors when enhancing functionality.

### Example of Not Maintaining OCP

Consider a `ShapeCalculator` class that calculates the area of different shapes, such as rectangles and circles.

```java
class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

public class ShapeCalculator {

    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.getWidth() * rectangle.getHeight();
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.getRadius() * circle.getRadius();
        }
        return 0;
    }

    public static void main(String[] args) {
        ShapeCalculator calculator = new ShapeCalculator();

        Rectangle rectangle = new Rectangle(5, 7);
        Circle circle = new Circle(3);

        System.out.println("Rectangle Area: " + calculator.calculateArea(rectangle));
        System.out.println("Circle Area: " + calculator.calculateArea(circle));
    }
}
```

**Output**
```css
Rectangle Area: 35.0
Circle Area: 28.274333882308138
```

In this design, adding support for a new shape, such as a triangle, would require modifying the existing method calculateArea. This violates the Open/Closed Principle because the existing code must be changed to introduce new functionality.

### Example of Maintaining OCP

To comply with the OCP, we can refactor the code by creating an abstract base class or interface for shapes and then implementing specific shape classes. This way, new shapes can be added without changing the existing code.

```java
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class ShapeCalculator {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 7);
        Shape circle = new Circle(3);

        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Circle Area: " + circle.calculateArea());
    }
}
```

**Output**
```css
Rectangle Area: 35.0
Circle Area: 28.274333882308138
```

## 1.3 L: Liskov Substitution Principle (LSP)

> Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

This principle ensures that if you have a base class and one or more derived classes, instances of the derived classes should be able to replace instances of the base class without causing issues or altering the expected behavior of the program.

### Example of Not Maintaining LSP

Consider a `Vehicle` base class and two derived classes: `Car` and `Bicycle`.

**Without adhering to LSP:**
```java
class Vehicle {
    public void startEngine() {
        System.out.println("Engine started.");
    }
}

class Car extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }
}

class Bicycle extends Vehicle {
    @Override
    public void startEngine() {
        throw new UnsupportedOperationException("Bicycles do not have engines.");
    }
}

public class VehicleTest {

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();

        car.startEngine();

        try {
            bicycle.startEngine(); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

**Output**
```css
Car engine started.
Error: Bicycles do not have engines.
```

In this example, the Bicycle class violates the Liskov Substitution Principle because it provides an implementation for the startEngine method that doesn’t apply to bicycles. If you replace a Vehicle instance with a Bicycle instance, it could lead to errors or unexpected behavior since a bicycle doesn’t have an engine.

### Example of Maintaining LSP

To adhere to the LSP, we should modify the design so that all subclasses fulfill the contract established by the base class in a meaningful way.

```java
abstract class Vehicle {
    public abstract void start();
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started.");
    }
}

class Bicycle extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bicycle is ready to ride.");
    }
}

public class VehicleTest {

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();

        car.start();
        bicycle.start();
    }
}
```

**Output**
```css
Car engine started.
Bicycle is ready to ride.
```

## 1.4 I: Interface Segregation Principle (ISP)

> No client should be forced to depend on interfaces they don't use.

The Interface Segregation Principle (ISP) aims to prevent the creation of "fat" or "bloated" interfaces that include methods irrelevant to all implementing classes. By breaking down interfaces into smaller, more specific ones, each client only needs to interact with the methods it actually requires. This approach promotes loose coupling and enhances code organization.

### Example of Not Maintaining ISP

Consider a media player application that handles different types of media files, such as audio files (MP3, WAV) and video files (MP4, AVI).

```java
interface MediaPlayer {
    void playAudio(String file);
    void playVideo(String file);
    void adjustVideoBrightness(int level);
}

class AudioPlayer implements MediaPlayer {

    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }

    @Override
    public void playVideo(String file) {
        throw new UnsupportedOperationException("AudioPlayer cannot play video.");
    }

    @Override
    public void adjustVideoBrightness(int level) {
        throw new UnsupportedOperationException("AudioPlayer cannot adjust video brightness.");
    }
}

public class MediaPlayerTest {

    public static void main(String[] args) {
        MediaPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playAudio("song.mp3");

        try {
            audioPlayer.playVideo("video.mp4");
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

**Output**
```css
Playing audio file: song.mp3
Error: AudioPlayer cannot play video.
```

In this scenario, any class that implements the MediaPlayer interface would be required to implement all methods, even if some are irrelevant to its functionality. For instance, an audio player would have to provide implementations for playVideo and adjustVideoBrightness, which are not applicable to audio playback.

### Example of Maintaining ISP

To adhere to the ISP, we can break the large interface into smaller, more focused interfaces:

```java
interface AudioPlayer {
    void playAudio(String file);
}

interface VideoPlayer {
    void playVideo(String file);
    void adjustVideoBrightness(int level);
}

class MP3Player implements AudioPlayer {
    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }
}

class MP4Player implements VideoPlayer {
    @Override
    public void playVideo(String file) {
        System.out.println("Playing video file: " + file);
    }

    @Override
    public void adjustVideoBrightness(int level) {
        System.out.println("Adjusting video brightness to level: " + level);
    }
}

class MultiMediaPlayer implements AudioPlayer, VideoPlayer {
    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }

    @Override
    public void playVideo(String file) {
        System.out.println("Playing video file: " + file);
    }

    @Override
    public void adjustVideoBrightness(int level) {
        System.out.println("Adjusting video brightness to level: " + level);
    }
}

public class MediaPlayer {
    public static void main(String[] args) {
        AudioPlayer mp3Player = new MP3Player();
        mp3Player.playAudio("song.mp3");

        VideoPlayer mp4Player = new MP4Player();
        mp4Player.playVideo("movie.mp4");
        mp4Player.adjustVideoBrightness(5);

        MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
        multiMediaPlayer.playAudio("podcast.mp3");
        multiMediaPlayer.playVideo("documentary.mp4");
        multiMediaPlayer.adjustVideoBrightness(10);
    }
}
```

**Output**
```css
Playing video file: movie.mp4
Adjusting video brightness to level: 5
Playing audio file: podcast.mp3
Playing video file: documentary.mp4
Adjusting video brightness to level: 10
```

## 1.5 D: Dependency Inversion Principle (DIP)

> High-level modules should not depend on low-level modules; both should depend on abstractions.

The Dependency Inversion Principle (DIP) asserts that high-level components of a system should not be directly dependent on low-level components. Instead, both should rely on abstractions (e.g., interfaces). This approach minimizes the coupling between different parts of the system and enhances code reusability and flexibility.

### Example of Not Maintaining DIP

Consider a scenario where we have an `EmailService` class responsible for sending emails using a specific email provider, such as Gmail.

```java
class GmailClient {
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email to " + recipient + " via Gmail: " + message);
    }
}

class EmailService {
    private GmailClient gmailClient;

    public EmailService() {
        this.gmailClient = new GmailClient();
    }

    public void sendEmail(String recipient, String message) {
        gmailClient.sendEmail(recipient, message);
    }
}

public class EmailServiceTest {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.sendEmail("test@example.com", "Hello!");
    }
}
```

**Output**
```css
Sending email to test@example.com via Gmail: Hello!
```

In this design, the EmailService class directly depends on the GmailClient class, which is a low-level module. This tight coupling makes it difficult to change the email provider or add new ones without modifying the EmailService class.

### Example of Maintaining DIP

To follow the Dependency Inversion Principle, we should introduce an abstraction (interface) for email clients and have the EmailService class depend on this abstraction. Then, specific implementations of email clients can depend on the abstraction.

```java
interface EmailClient {
    void sendEmail(String recipient, String message);
}

class GmailClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email to " + recipient + " via Gmail: " + message);
    }
}

class OutlookClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email to " + recipient + " via Outlook: " + message);
    }
}

class EmailService {
    private EmailClient emailClient;

    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail(String recipient, String message) {
        emailClient.sendEmail(recipient, message);
    }
}

public class EmailServiceTest {
    public static void main(String[] args) {
        EmailClient gmailClient = new GmailClient();
        EmailService emailService = new EmailService(gmailClient);
        emailService.sendEmail("example@gmail.com", "Hello!");

        EmailClient outlookClient = new OutlookClient();
        emailService = new EmailService(outlookClient);
        emailService.sendEmail("example@outlook.com", "Hello!");
    }
}
```

**Output**
```css
Sending email to example@gmail.com via Gmail: Hello!
Sending email to example@outlook.com via Outlook: Hello!
```

# 2. Design Patterns
Design patterns are reusable solutions to common problems in software design. They represent best practices used by experienced object-oriented software developers. Design patterns provide a standard terminology and are specific to particular scenarios.

#### Key Characteristics of Design Patterns
**Reusability:** Patterns can be applied to different projects and problems, saving time and effort in solving similar issues.

**Standardization:** They provide a shared language and understanding among developers, facilitating communication and collaboration.

**Efficiency:** By using established patterns, developers can avoid reinventing the wheel, leading to faster and more reliable development.

**Flexibility:** Patterns are abstract solutions that can be adapted to fit various contexts and requirements.

## 2.1 Prototype Design Pattern
The Prototype design pattern is a creational design pattern used when the cost of creating a new object is high, and you need to create multiple instances of objects that are identical or similar to each other. Instead of creating new objects from scratch, the Prototype pattern allows you to clone existing objects.

### Steps to Implement the Protoype Design Pattern

#### I. Prototype Interface
The `ShapePrototype` interface declares a method for cloning objects. This method is usually named `clone`. The `clone` method is responsible for creating a copy of the object.

```java
public interface ShapePrototype {
    ShapePrototype clone();
}
```
#### II. Concrete Prototype Class
Create a class named `Shape` that implements the `ShapePrototype` interface. Implement the `clone` method to create and return a new instance of `Shape` with the same property values.

**Shape Class**
```java
class Shape implements ShapePrototype {
    private int dimension;
    private String color;

    public Shape(int dimension, String color) {
        this.dimension = dimension;
        this.color = color;
    }

    @Override
    public Shape clone() {
        return new Shape(this.dimension, this.color);
    }

    public int getDimension() {
        return dimension;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Shape - Dimension: " + dimension + ", Color: " + color;
    }
}
```

#### III. PrototypeRegistry Class
The `PrototypeRegistry` class is responsible for storing a collection of prototype objects. This allows the client to retrieve and clone these prototypes as needed.

```java
import java.util.HashMap;
import java.util.Map;

class PrototypeRegistry {
    private Map<String, ShapePrototype> prototypes = new HashMap<>();

    public void registerPrototype(String key, ShapePrototype prototype) {
        prototypes.put(key, prototype);
    }

    public ShapePrototype getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

```

#### IV. The Client Code
Create an instance of `Shape` and register it in the `PrototypeRegistry`. Retrieve and clone the prototype from the registry, modify the cloned instance, and print its details, ensuring that the original instance remains unchanged.

```java
public class PrototypePattern {
    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();

        Shape originalShape = new Shape(5, "Red");
        registry.registerPrototype("RedShape", originalShape);

        System.out.println("Original: " + originalShape);

        Shape clonedShape = (Shape) registry.getPrototype("RedShape");
        System.out.println("Cloned: " + clonedShape);

        clonedShape = new Shape(10, "Blue");
        System.out.println("Modified Cloned: " + clonedShape);

        System.out.println("Unchanged Original: " + originalShape);
    }
}
```

**Output**
```css
Original: Shape - Dimension: 5, Color: Red
Cloned: Shape - Dimension: 5, Color: Red
Modified Cloned: Shape - Dimension: 10, Color: Blue
Unchanged Original: Shape - Dimension: 5, Color: Red
```

## 2.2 Observer Design Pattern

The Observer design pattern is a behavioral design pattern that allows one object (the subject) to notify other objects (the observers) about changes in its state. This pattern is useful for scenarios where changes in one object should trigger updates in other dependent objects.

### Steps to Implement the Observer Design Pattern

#### I. Define the Subject Interface
The `Channel` interface declares methods for attaching, detaching, and notifying observers. This interface is the foundation of the pattern.

```java
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}
```

#### II. Implement the Concrete Subject
we'll create a `YouTubeChannel` class that will notify its subscribers when a new video is uploaded.

```java
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
```

#### III. Define the Observer Interface
The `Subscriber` interface defines the update method that will be called when the subject changes. This interface ensures that all observers implement a common method for receiving updates.

```java
interface Subscriber {
    void update();
}
```

#### IV. Implement the Concrete Observer
In this example, we'll create a `YoutubeSubscriber` class that represents a subscriber to a YouTube channel.

```java
YouTubeSubscriber implements Subscriber {
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

#### V. The Client Code
The client code creates instances of the channel and subscribers, attaches the subscribers to the channel, and updates the changes.

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

**Output**
```css
Siddiqur, a new video titled "Observer Pattern Explained" has been uploaded to Nemo.
Rahman, a new video titled "Observer Pattern Explained" has been uploaded to Nemo.
Siddiqur, a new video titled "Prototype Pattern Tutorial" has been uploaded to Nemo.
Rahman, a new video titled "Prototype Pattern Tutorial" has been uploaded to Nemo.
```

## 2.3 Facade Design Pattern
The Facade Design Pattern provides a simplified interface to a complex system of classes, libraries, or frameworks. This pattern is particularly useful when dealing with complex systems where clients require a simplified interface to interact with the system's core functionality.

### Steps to Implement the Facade Design Pattern
#### I. Define the Subsystem Classes
Each of the subsystem classes (`Memory`, `HardDrive`, `CPU`) handles a specific part of the system's functionality.

**Memory Class**
```java
class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Loading data at position " + position);
    }
}
```

**HardDrive Class**
```java
class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Reading data from sector " + lba + " with size " + size);
        return new byte[size];
    }
}
```

**CPU Class**
```java
class CPU {
    public void freeze() { System.out.println("CPU: Freezing processor."); }
    public void jump(long position) { System.out.println("CPU: Jumping to position " + position); }
    public void execute() { System.out.println("CPU: Executing instructions."); }
}
```

#### II. Create the Facade Class
The `ComputerFacade` class provides a simplified interface to interact with the subsystem classes.
```java
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        System.out.println("ComputerFacade: Starting the computer...");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("ComputerFacade: Computer has started.");
    }
}
```

#### IV. The Client Code
The client interacts with the system through the `ComputerFacade` without needing to understand the underlying subsystem classes.

```java
public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
```

**Output**
```css
ComputerFacade: Starting the computer...
CPU: Freezing processor.
HardDrive: Reading data from sector 0 with size 1024
Memory: Loading data at position 0
CPU: Jumping to position 0
CPU: Executing instructions.
ComputerFacade: Computer has started.
