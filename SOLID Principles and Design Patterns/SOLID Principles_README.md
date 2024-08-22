# SOLID Principles

## 1. S: Single Responsibility Principle (SRP)

A class should have a single reason to change, meaning it should be focused on one specific task or responsibility.

By ensuring that a class is dedicated to a single responsibility, it becomes simpler, more focused, and easier to manage. This also makes the class more reusable and maintainable.

**Example of Not Maintaining SRP**

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

**Example of Maintaining SRP**

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

## 2. O: Open/Closed Principle (OCP)

Software entities such as classes, modules, and functions should be open to extension but closed to modification.

This principle encourages designing software in a way that allows adding new features or behavior without altering the existing code. This approach helps minimize the risk of introducing bugs or errors when enhancing functionality.

**Example of Not Maintaining OCP**

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

**Example of Maintaining OCP**

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

## 3. L: Liskov Substitution Principle (LSP)

Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

This principle ensures that if you have a base class and one or more derived classes, instances of the derived classes should be able to replace instances of the base class without causing issues or altering the expected behavior of the program.

**Example of Not Maintaining LSP**

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

**Example of Maintaining LSP**

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

## 4. I: Interface Segregation Principle (ISP)

No client should be forced to depend on interfaces they don't use.

The Interface Segregation Principle (ISP) aims to prevent the creation of "fat" or "bloated" interfaces that include methods irrelevant to all implementing classes. By breaking down interfaces into smaller, more specific ones, each client only needs to interact with the methods it actually requires. This approach promotes loose coupling and enhances code organization.

**Example of Not Maintaining ISP**

Consider a media player application that handles different types of media files, such as audio files (MP3, WAV) and video files (MP4, AVI).

**Without applying ISP:**

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

**Example of Maintaining ISP**

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

## 5. D: Dependency Inversion Principle (DIP)

High-level modules should not depend on low-level modules; both should depend on abstractions.

The Dependency Inversion Principle (DIP) asserts that high-level components of a system should not be directly dependent on low-level components. Instead, both should rely on abstractions (e.g., interfaces). This approach minimizes the coupling between different parts of the system and enhances code reusability and flexibility.

**Example of Not Maintaining DIP**

Consider a scenario where we have an `EmailService` class responsible for sending emails using a specific email provider, such as Gmail.

**Without adhering to DIP:**

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

**Example of Maintaining DIP**

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
