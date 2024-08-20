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
    public void authenticateUser(String username, String password) {
        // Authentication logic
    }

    public void updateUserProfile(String username, String newProfileInfo) {
        // Profile management logic
    }

    public void sendEmailNotification(String email, String message) {
        // Email notification logic
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
        // Authentication logic
    }
}

// Class responsible for user profile management
public class UserProfileManager {
    public void updateUserProfile(String username, String newProfileInfo) {
        // Profile management logic
    }
}

// Class responsible for email notifications
public class EmailNotificationManager {
    public void sendEmailNotification(String email, String message) {
        // Email notification logic
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
// This class violates the OCP
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
```

Now, the ShapeCalculator class can be designed to work with any object that extends the Shape class:

```java
// ShapeCalculator class adhering to OCP
public class ShapeCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }

    public double calculatePerimeter(Shape shape) {
        return shape.calculatePerimeter();
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
    }
}

// Derived class Car
public class Car extends Vehicle {
    @Override
    public void startEngine() {
        // Car-specific engine start logic
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
```

**Implementations:**

Now, separate classes can implement the interfaces they require:

```java
// Audio player implementation
public class MP3Player implements AudioPlayer {
    @Override
    public void playAudio(String file) {
        // Audio playback logic
    }

    @Override
    public void stopAudio() {
        // Stop audio logic
    }
}

// Video player implementation
public class MP4Player implements VideoPlayer {
    @Override
    public void playVideo(String file) {
        // Video playback logic
    }

    @Override
    public void stopVideo() {
        // Stop video logic
    }

    @Override
    public void adjustVideoBrightness(int level) {
        // Adjust video brightness logic
    }
}
```
If a class needs to support both audio and video playback, it can implement both interfaces:

```java
// Class supporting both audio and video playback
public class MultiMediaPlayer implements AudioPlayer, VideoPlayer {
    @Override
    public void playAudio(String file) {
        // Audio playback logic
    }

    @Override
    public void stopAudio() {
        // Stop audio logic
    }

    @Override
    public void playVideo(String file) {
        // Video playback logic
    }

    @Override
    public void stopVideo() {
        // Stop video logic
    }

    @Override
    public void adjustVideoBrightness(int level) {
        // Adjust video brightness logic
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
    }
}

// High-level class directly depending on GmailClient
public class EmailService {
    private GmailClient gmailClient;

    public EmailService() {
        this.gmailClient = new GmailClient();
    }

    public void sendEmail(String recipient, String message) {
        gmailClient.sendEmail(recipient, message);
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
    }
}

// Low-level Outlook client implementation
public class OutlookClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        // Logic to send email via Outlook
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
```

**Usage Example:**

To use the EmailService with different email clients, simply provide the desired implementation of EmailClient:

```java
public class Main {
    public static void main(String[] args) {
        EmailClient gmailClient = new GmailClient();
        EmailService emailService = new EmailService(gmailClient);
        emailService.sendEmail("example@example.com", "Hello, world!");

        EmailClient outlookClient = new OutlookClient();
        emailService = new EmailService(outlookClient);
        emailService.sendEmail("example@example.com", "Hello, world!");
    }
}
```
