# SOLID Principles in Java

## 1. S: Single Responsibility Principle (SRP)

**Definition:**

A class should have a single reason to change, meaning it should be focused on one specific task or responsibility.

By ensuring that a class is dedicated to a single responsibility, it becomes simpler, more focused, and easier to manage. This also makes the class more reusable and maintainable.

When a class is burdened with multiple responsibilities, it can become complex and difficult to work with. This increases the risk of errors, as changes made to address one responsibility might unintentionally impact others.

**Example:**

Consider a class called `UserManager` that handles user authentication, user profile management, and email notifications.

```java
// This class violates the SRP
public class UserManager {

    // Method to authenticate the user
    public void authenticateUser(String username, String password) {
        // Simplified authentication logic
        if ("user123".equals(username) && "password".equals(password)) {
            System.out.println("User authenticated successfully.");
        } else {
            System.out.println("Authentication failed.");
        }
    }

    // Method to update the user profile
    public void updateUserProfile(String username, String newProfileInfo) {
        // Simplified profile update logic
        System.out.println("Profile of user '" + username + "' updated with new information: " + newProfileInfo);
    }

    // Method to send an email notification
    public void sendEmailNotification(String email, String message) {
        // Simplified email notification logic
        System.out.println("Email sent to " + email + " with message: " + message);
    }

    // Main method to run the program
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        
        // Authenticating user
        userManager.authenticateUser("user123", "password");
        
        // Updating user profile
        userManager.updateUserProfile("user123", "New Profile Info");
        
        // Sending email notification
        userManager.sendEmailNotification("user@example.com", "Welcome to our service!");
    }
}
```

This class violates the SRP because it has multiple responsibilities: authentication, profile management, and email notifications.

If you need to change the way user authentication is handled, you might inadvertently affect the email notification logic, or vice versa.

**Solution:**

To adhere to the SRP, we can split this class into three separate classes, each with a single responsibility:

```java
// Class responsible for user authentication
public class AuthenticationManager {
    public void authenticateUser(String username, String password) {
        // Simplified authentication logic
        if ("user123".equals(username) && "password".equals(password)) {
            System.out.println("User authenticated successfully.");
        } else {
            System.out.println("Authentication failed.");
        }
    }
}

// Class responsible for user profile management
public class UserProfileManager {
    public void updateUserProfile(String username, String newProfileInfo) {
        // Simplified profile update logic
        System.out.println("Profile of user '" + username + "' updated with new information: " + newProfileInfo);
    }
}

// Class responsible for email notifications
public class EmailNotificationManager {
    public void sendEmailNotification(String email, String message) {
        // Simplified email notification logic
        System.out.println("Email sent to " + email + " with message: " + message);
    }
}

// Main class to demonstrate the use of the classes adhering to SRP
public class UserManagerSRP {

    public static void main(String[] args) {
        // Creating instances of the responsible classes
        AuthenticationManager authManager = new AuthenticationManager();
        UserProfileManager profileManager = new UserProfileManager();
        EmailNotificationManager emailManager = new EmailNotificationManager();

        // Authenticating user
        authManager.authenticateUser("user123", "password");
        
        // Updating user profile
        profileManager.updateUserProfile("user123", "New Profile Info");
        
        // Sending email notification
        emailManager.sendEmailNotification("user@example.com", "Welcome to our service!");
    }
}
```

## 2. O: Open/Closed Principle (OCP)

**Definition:**

Software entities such as classes, modules, and functions should be open to extension but closed to modification.

This principle encourages designing software in a way that allows adding new features or behavior without altering the existing code. This approach helps minimize the risk of introducing bugs or errors when enhancing functionality.

**Example:**

Consider a `ShapeCalculator` class that calculates the area and perimeter of different shapes, such as rectangles and circles.

```java
// Class representing a Rectangle
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

// Class representing a Circle
class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

// Class that violates the Open/Closed Principle
public class ShapeCalculator {

    // Method to calculate area of a shape
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

    // Method to calculate perimeter of a shape
    public double calculatePerimeter(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return 2 * (rectangle.getWidth() + rectangle.getHeight());
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return 2 * Math.PI * circle.getRadius();
        }
        return 0;
    }

    // Main method to run the program
    public static void main(String[] args) {
        ShapeCalculator calculator = new ShapeCalculator();

        Rectangle rectangle = new Rectangle(5, 7);
        Circle circle = new Circle(3);

        System.out.println("Rectangle Area: " + calculator.calculateArea(rectangle));
        System.out.println("Rectangle Perimeter: " + calculator.calculatePerimeter(rectangle));

        System.out.println("Circle Area: " + calculator.calculateArea(circle));
        System.out.println("Circle Perimeter: " + calculator.calculatePerimeter(circle));
    }
}
```

In this design, adding support for a new shape, such as a triangle, would require modifying the existing methods calculateArea and calculatePerimeter. This violates the Open/Closed Principle because the existing code must be changed to introduce new functionality.

**Solution:**

To comply with the OCP, we can refactor the code by creating an abstract base class or interface for shapes and then implementing specific shape classes. This way, new shapes can be added without changing the existing code.

```java
// Abstract Shape class
public abstract class Shape {
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
}

// Rectangle class
public class Rectangle extends Shape {
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

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

// Circle class
public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Main class to demonstrate OCP adherence
public class ShapeCalculator {

    public static void main(String[] args) {
        // Creating instances of different shapes
        Shape rectangle = new Rectangle(5, 7);
        Shape circle = new Circle(3);

        // Calculating area and perimeter using polymorphism
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());
    }
}
```

## 3. L: Liskov Substitution Principle (LSP)

**Definition:**

Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

This principle ensures that if you have a base class and one or more derived classes, instances of the derived classes should be able to replace instances of the base class without causing issues or altering the expected behavior of the program.

**Example:**

Consider a `Vehicle` base class and two derived classes: `Car` and `Bicycle`.

**Without adhering to LSP:**
```java
// Base class
public class Vehicle {
    public void startEngine() {
        // Logic to start engine
        System.out.println("Engine started.");
    }
}

// Derived class Car
public class Car extends Vehicle {
    @Override
    public void startEngine() {
        // Car-specific engine start logic
        System.out.println("Car engine started.");
    }
}

// Derived class Bicycle
public class Bicycle extends Vehicle {
    @Override
    public void startEngine() {
        // This doesn't make sense for a bicycle
        throw new UnsupportedOperationException("Bicycles do not have engines.");
    }
}

// Main class to demonstrate Liskov violation
public class VehicleTest {

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();

        // Starting engine for a car (this works as expected)
        car.startEngine();

        // Attempting to start engine for a bicycle (this violates LSP)
        try {
            bicycle.startEngine(); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

In this example, the Bicycle class violates the Liskov Substitution Principle because it provides an implementation for the startEngine method that doesn’t apply to bicycles. If you replace a Vehicle instance with a Bicycle instance, it could lead to errors or unexpected behavior since a bicycle doesn’t have an engine.

**Solution:**

To adhere to the LSP, we should modify the design so that all subclasses fulfill the contract established by the base class in a meaningful way.

```java
// Base class with a more general method
public abstract class Vehicle {
    public abstract void start();
}

// Derived class Car
public class Car extends Vehicle {
    @Override
    public void start() {
        // Car-specific start logic
        System.out.println("Car engine started.");
    }
}

// Derived class Bicycle
public class Bicycle extends Vehicle {
    @Override
    public void start() {
        // Bicycle-specific start logic
        System.out.println("Bicycle is ready to ride.");
    }
}

// Main class to demonstrate LSP adherence
public class VehicleTest {

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();

        // Starting the car (this works as expected)
        car.start();

        // Starting the bicycle (this also works as expected, maintaining LSP)
        bicycle.start();
    }
}
```

## 4. I: Interface Segregation Principle (ISP)

**Definition:**

No client should be forced to depend on interfaces they don't use.

The Interface Segregation Principle (ISP) aims to prevent the creation of "fat" or "bloated" interfaces that include methods irrelevant to all implementing classes. By breaking down interfaces into smaller, more specific ones, each client only needs to interact with the methods it actually requires. This approach promotes loose coupling and enhances code organization.

**Example:**

Consider a media player application that handles different types of media files, such as audio files (MP3, WAV) and video files (MP4, AVI).

**Without applying ISP:**

```java
// A single, large interface
public interface MediaPlayer {
    void playAudio(String file);
    void stopAudio();
    void playVideo(String file);
    void stopVideo();
    void adjustVideoBrightness(int level);
}

// A class that only plays audio, but is forced to implement all methods
public class AudioPlayer implements MediaPlayer {

    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }

    @Override
    public void stopAudio() {
        System.out.println("Stopping audio.");
    }

    @Override
    public void playVideo(String file) {
        // AudioPlayer doesn't need this method, but must implement it
        throw new UnsupportedOperationException("AudioPlayer cannot play video.");
    }

    @Override
    public void stopVideo() {
        // AudioPlayer doesn't need this method, but must implement it
        throw new UnsupportedOperationException("AudioPlayer cannot stop video.");
    }

    @Override
    public void adjustVideoBrightness(int level) {
        // AudioPlayer doesn't need this method, but must implement it
        throw new UnsupportedOperationException("AudioPlayer cannot adjust video brightness.");
    }
}

// Main class to demonstrate ISP violation
public class MediaPlayerTest {

    public static void main(String[] args) {
        MediaPlayer audioPlayer = new AudioPlayer();

        // Playing audio (this works as expected)
        audioPlayer.playAudio("song.mp3");
        audioPlayer.stopAudio();

        // Attempting to play video (this violates ISP)
        try {
            audioPlayer.playVideo("video.mp4");
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```
In this scenario, any class that implements the MediaPlayer interface would be required to implement all methods, even if some are irrelevant to its functionality. For instance, an audio player would have to provide implementations for playVideo, stopVideo, and adjustVideoBrightness, which are not applicable to audio playback.

**Solution:**

To adhere to the ISP, we can break the large interface into smaller, more focused interfaces:

```java
// Interface for audio operations
public interface AudioPlayer {
    void playAudio(String file);
    void stopAudio();
}

// Interface for video operations
public interface VideoPlayer {
    void playVideo(String file);
    void stopVideo();
    void adjustVideoBrightness(int level);
}

// Audio player implementation
public class MP3Player implements AudioPlayer {
    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }

    @Override
    public void stopAudio() {
        System.out.println("Stopping audio.");
    }
}

// Video player implementation
public class MP4Player implements VideoPlayer {
    @Override
    public void playVideo(String file) {
        System.out.println("Playing video file: " + file);
    }

    @Override
    public void stopVideo() {
        System.out.println("Stopping video.");
    }

    @Override
    public void adjustVideoBrightness(int level) {
        System.out.println("Adjusting video brightness to level: " + level);
    }
}

// Class supporting both audio and video playback
public class MultiMediaPlayer implements AudioPlayer, VideoPlayer {
    @Override
    public void playAudio(String file) {
        System.out.println("Playing audio file: " + file);
    }

    @Override
    public void stopAudio() {
        System.out.println("Stopping audio.");
    }

    @Override
    public void playVideo(String file) {
        System.out.println("Playing video file: " + file);
    }

    @Override
    public void stopVideo() {
        System.out.println("Stopping video.");
    }

    @Override
    public void adjustVideoBrightness(int level) {
        System.out.println("Adjusting video brightness to level: " + level);
    }
}

// Main class to test the code
public class MediaPlayer {
    public static void main(String[] args) {
        // Test MP3Player
        AudioPlayer mp3Player = new MP3Player();
        mp3Player.playAudio("song.mp3");
        mp3Player.stopAudio();

        // Test MP4Player
        VideoPlayer mp4Player = new MP4Player();
        mp4Player.playVideo("movie.mp4");
        mp4Player.stopVideo();
        mp4Player.adjustVideoBrightness(5);

        // Test MultiMediaPlayer
        MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
        multiMediaPlayer.playAudio("podcast.mp3");
        multiMediaPlayer.playVideo("documentary.mp4");
        multiMediaPlayer.adjustVideoBrightness(10);
        multiMediaPlayer.stopAudio();
        multiMediaPlayer.stopVideo();
    }
}
```

## 5. D: Dependency Inversion Principle (DIP)

**Definition:**

High-level modules should not depend on low-level modules; both should depend on abstractions.

The Dependency Inversion Principle (DIP) asserts that high-level components of a system should not be directly dependent on low-level components. Instead, both should rely on abstractions (e.g., interfaces). This approach minimizes the coupling between different parts of the system and enhances code reusability and flexibility.

**Example:**

Consider a scenario where we have an `EmailService` class responsible for sending emails using a specific email provider, such as Gmail.

**Without adhering to DIP:**

```java
// Low-level class for Gmail client
public class GmailClient {
    public void sendEmail(String recipient, String message) {
        // Logic to send email via Gmail
        System.out.println("Sending email to " + recipient + " via Gmail: " + message);
    }
}

// High-level class directly depending on GmailClient
public class EmailService {
    private GmailClient gmailClient;

    public EmailService() {
        this.gmailClient = new GmailClient(); // Direct dependency on a concrete class
    }

    public void sendEmail(String recipient, String message) {
        gmailClient.sendEmail(recipient, message);
    }
}

// Main class to test the code
public class EmailService {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.sendEmail("test@example.com", "Hello, this is a test email.");
    }
}
```
In this design, the EmailService class directly depends on the GmailClient class, which is a low-level module. This tight coupling makes it difficult to change the email provider or add new ones without modifying the EmailService class.

**Solution:**

To follow the Dependency Inversion Principle, we should introduce an abstraction (interface) for email clients and have the EmailService class depend on this abstraction. Then, specific implementations of email clients can depend on the abstraction.

```java
// Abstraction for email clients
public interface EmailClient {
    void sendEmail(String recipient, String message);
}

// Low-level Gmail client implementation
public class GmailClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        // Logic to send email via Gmail
        System.out.println("Sending email to " + recipient + " via Gmail: " + message);
    }
}

// Low-level Outlook client implementation
public class OutlookClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        // Logic to send email via Outlook
        System.out.println("Sending email to " + recipient + " via Outlook: " + message);
    }
}

// High-level class depending on abstraction
public class EmailService {
    private EmailClient emailClient;

    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail(String recipient, String message) {
        emailClient.sendEmail(recipient, message);
    }
}

// Main class to test the code
public class EmailService {
    public static void main(String[] args) {
        // Using Gmail client
        EmailClient gmailClient = new GmailClient();
        EmailService emailService = new EmailService(gmailClient);
        emailService.sendEmail("example@example.com", "Hello from Gmail!");

        // Using Outlook client
        EmailClient outlookClient = new OutlookClient();
        emailService = new EmailService(outlookClient);
        emailService.sendEmail("example@example.com", "Hello from Outlook!");
    }
}
```
