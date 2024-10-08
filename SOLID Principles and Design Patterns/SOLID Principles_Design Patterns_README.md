# Table of Contents

## 1. SOLID Principles
- 1.1 [S: Single Responsibility Principle (SRP)](#11-s-single-responsibility-principle-srp)
- 1.2 [O: Open/Closed Principle (OCP)](#12-o-openclosed-principle-ocp)
- 1.3 [L: Liskov Substitution Principle (LSP)](#13-l-liskov-substitution-principle-lsp)
- 1.4 [I: Interface Segregation Principle (ISP)](#14-i-interface-segregation-principle-isp)
- 1.5 [D: Dependency Inversion Principle (DIP)](#15-d-dependency-inversion-principle-dip)

## 2. Design Patterns
- 2.1 [Observer Design Pattern](#21-observer-design-pattern)
- 2.2 [Facade Design Pattern](#22-facade-design-pattern)
- 2.3 [Prototype Design Pattern](#23-prototype-design-pattern)


# 1. SOLID Principles
> The SOLID principle was introduced by Robert C. Martin, also known as Uncle Bob and it is a coding standard in programming.

- The SOLID principle helps in reducing tight coupling. Tight coupling means a group of classes are highly dependent on one another which you should avoid in your code.
- Opposite of tight coupling is loose coupling and your code is considered as a good code when it has loosely-coupled classes.
- Loosely coupled classes minimize changes in your code, helps in making code more reusable, maintainable, flexible and stable.
## 1.1 S: Single Responsibility Principle (SRP)
🔼 [Back to Top](#table-of-contents)

> A class should have a single reason to change, meaning it should be focused on one specific task or responsibility.

- Ensuring that a class is dedicated to a single responsibility makes it simpler and more focused.
- A class with a single responsibility is easier to manage, understand, and modify.
- Classes become more reusable and maintainable as they are not burdened with multiple unrelated tasks.

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

**Issues:**

- **Multiple Responsibilities:** The `UserManager` class violates the SRP because it handles more than one responsibility: authentication and profile management.

- **Difficult Maintenance:** Modifying or extending any of the responsibilities (authentication or profile management) requires changes to the UserManager class, making it harder to maintain.

- **Low Reusability:** Since the `UserManager` class handles multiple tasks, it is less reusable in other parts of the system where only one of the tasks is needed.

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

**Issues Solved:**

- **Single Responsibility:** Each class now has a single responsibility: `AuthenticationManager` handles user authentication, and `UserProfileManager` handles user profile management.

- **Improved Maintainability:** Modifying or extending the authentication process or profile management can be done independently, without affecting other parts of the code.

- **Higher Reusability:** The `AuthenticationManager` and `UserProfileManager` classes can be reused independently in different parts of the system where only one of the functionalities is needed.

## 1.2 O: Open/Closed Principle (OCP)
🔼 [Back to Top](#table-of-contents)

> Software entities such as classes, modules, and functions should be open to extension but closed to modification.

- The principle encourages designing software so that new features or behaviors can be added without altering existing code.
- By avoiding changes to existing code, the risk of introducing bugs or errors when enhancing functionality is reduced.

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
**Issues**

- **Fragile Code:** Modifying the `calculateArea` method to add new shapes increases the risk of introducing errors or breaking existing functionality.

- **Poor Extensibility:** The design does not support easy extension. Every time a new shape is introduced, the `ShapeCalculator` class must be modified, making it difficult to maintain and extend.

### Example of Maintaining OCP

To comply with the OCP, we can refactor the code by creating an abstract base class or interface for shapes and then implementing specific shape classes.

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

**Issues Solved:**

- **Robustness:** The design is more robust, as it minimizes the risk of breaking existing functionality when adding new features.

- **Improved Extensibility:** The code is easily extendable. Adding new shapes is straightforward and does not require changes to existing classes, making the system more maintainable.

## 1.3 L: Liskov Substitution Principle (LSP)
🔼 [Back to Top](#table-of-contents)

> Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

- This principle ensures that if you have a base class and one or more derived classes, instances of the derived classes should be able to replace instances of the base class without causing issues or altering the expected behavior of the program.

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

**Issues**

- **Unexpected Behavior:** Replacing a `Vehicle` instance with a `Bicycle` instance can lead to errors or unexpected behavior, as bicycles do not have engines.

- **Fragile Code:** The design is fragile and prone to errors, as the base class (`Vehicle`) assumes that all vehicles have an engine, which is not true for bicycles.

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

**Issues Solved:**

- **Behavioral Consistency:** Replacing a `Vehicle` instance with any of its subclasses (`Car` or `Bicycle`) will not cause errors or unexpected behavior.

- **Robust and Flexible Design:** The code is more robust and flexible, as it allows for the correct behavior of each vehicle type while maintaining consistency in how the start method is used.

## 1.4 I: Interface Segregation Principle (ISP)
🔼 [Back to Top](#table-of-contents)

> No client should be forced to depend on interfaces they don't use.

- The ISP aims to prevent the creation of `fat` or `bloated` interfaces that include methods irrelevant to all implementing classes.
- By breaking down interfaces into smaller, more specific ones, each client only needs to interact with the methods it actually requires. This approach promotes loose coupling and improves code organization.

### Example of Not Maintaining ISP

Consider the following design where a `MediaPlayer` interface handles different types of media files, such as audio and video files.

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

**Issues**

- **Unnecessary Methods:** The `MediaPlayer` interface includes methods that not all implementing classes need, leading to `fat` interfaces.

- **Potential Errors:** Implementing irrelevant methods can lead to errors or unhandled exceptions, as seen in the example where the `AudioPlayer` cannot play videos.

### Example of Maintaining ISP

To adhere to the ISP, we can break the large interface into smaller, more focused interfaces.

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

**Issues Solved:**

- **No Unnecessary Methods:** Classes only implement the methods they actually use, avoiding the need to handle irrelevant methods.

- **Flexible and Maintainable Design:** The code is more flexible and maintainable, allowing different types of media players to implement only the relevant interfaces without being burdened by unnecessary methods.

## 1.5 D: Dependency Inversion Principle (DIP)
🔼 [Back to Top](#table-of-contents)

> High-level modules should not depend on low-level modules; both should depend on abstractions.

- The Dependency Inversion Principle (DIP) asserts that high-level components of a system should rely on abstractions (e.g., interfaces) rather than directly on low-level components.
- This approach minimizes coupling between different parts of the system.
- It enhances code reusability and flexibility by allowing easy replacement of low-level components without affecting high-level components.

### Example of Not Maintaining DIP

Consider a scenario where we have an `EmailService` class responsible for sending emails by directly depends on a `GmailClient` class.
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

**Issues**

- **Tight Coupling:** The `EmailService` class is tightly coupled with the `GmailClient` class. If you want to switch to a different email provider, such as Outlook, you need to modify the `EmailService` class.

- **Limited Flexibility:** The current design restricts the ability to add or switch email providers without altering the `EmailService` class.

- **Difficulty in Testing:** Testing the `EmailService` class in isolation becomes harder since it directly depends on the concrete `GmailClient` class.

### Example of Maintaining DIP

we can introduce an abstraction for email clients (`EmailClient`)  and have the `EmailService` class depend on this abstraction.

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
        emailService.sendEmail("test1@example.com", "Hello!");

        EmailClient outlookClient = new OutlookClient();
        emailService = new EmailService(outlookClient);
        emailService.sendEmail("test2@example.com", "Hello!");
    }
}
```

**Output**
```css
Sending email to test1@example.com via Gmail: Hello!
Sending email to test2@example.com via Outlook: Hello!
```

**Issues Solved:**

- **Reduced Coupling:** The `EmailService` class now depends on the `EmailClient` interface rather than on a specific implementation.

- **Increased Flexibility:** You can easily switch to different email providers by creating new classes that implement the `EmailClient` interface without changing the `EmailService` class.

- **Easier Testing:** The `EmailService` class can now be easily tested by passing in a mock implementation of the `EmailClient` interface, making unit tests more straightforward.


# 2. Design Patterns
Design patterns are reusable solutions to common problems in software design. They represent best practices used by experienced object-oriented software developers. Design patterns provide a standard terminology and are specific to particular scenarios.

#### Key Characteristics of Design Patterns
**Reusability:** Patterns can be applied to different projects and problems, saving time and effort in solving similar issues.

**Standardization:** They provide a shared language and understanding among developers, facilitating communication and collaboration.

**Efficiency:** By using established patterns, developers can avoid reinventing the wheel, leading to faster and more reliable development.

**Flexibility:** Patterns are abstract solutions that can be adapted to fit various contexts and requirements.

## 2.1 Observer Design Pattern
🔼 [Back to Top](#table-of-contents)

The Observer design pattern is a behavioral design pattern that allows one object (the `subject`) to notify other objects (the `observers`) about changes in its state. This pattern is useful for scenarios where changes in one object should trigger updates in other dependent objects.

### Components of Observer Design Pattern
i. **Subject:** The `subject` maintains a list of `observers` (subscribers or listeners). It Provides methods to register and unregister observers dynamically and defines a method to notify observers of changes in its state.

ii. **Observer:** `Observer` defines an interface with an update method that concrete observers must implement and ensures a common or consistent way for concrete observers to receive updates from the subject.

iii. **ConcreteSubject:** `ConcreteSubjects` are specific implementations of the `Subject`. They hold the actual state or data that observers want to track. When this state changes, concrete subjects notify their observers.

iv. **ConcreteObserver:** `ConcreteObserver` implements the `observer` interface. They register with a concrete subject and react when notified of a state change.

![Observer Pattern Diagram](https://github.com/siddiqur-54/task_documentations/blob/main/images/Solid%20Principles%20and%20Design%20Patterns/observer_diagram.png)

### Implementation of the Observer Design Pattern

#### i. Subject
- The `Subject` interface outlines the operations a subject (`WeatherStation`) should support.
- `addObserver` and `removeObserver` are for managing the list of observers.
- `notifyObservers` is for informing observers about changes.

```java
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
```

#### ii. Observer
- The `Observer` interface defines a contract for objects that want to be notified about changes in the subject (`WeatherStation`).
- It includes a method `update` that concrete observers must implement to receive and handle updates.

```java
interface Observer {
    void update(String weather);
}
```

#### iii. ConcreteSubject(WeatherStation)
- `WeatherStation` is the concrete subject implementing the `Subject` interface.
- `notifyObservers` iterates through the observers and calls their `update` method, passing the current weather.
- `setWeather` method updates the weather and notifies observers of the change.

```java
import java.util.ArrayList;
import java.util.List;

class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String weather;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(weather);
        }
    }

    public void setWeather(String newWeather) {
        this.weather = newWeather;
        notifyObservers();
    }
}
```

#### iv. ConcreteObserver (PhoneDisplay & TVDisplay)
**PhoneDisplay**
- `PhoneDisplay` is a concrete observer implementing the `Observer` interface.
- The `update` method sets the new weather and calls the `display` method.

```java
class PhoneDisplay implements Observer {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Weather updated - " + weather);
    }
}
```

**TVDisplay**
- `TVDisplay` is another concrete observer similar to `PhoneDisplay`.

```java
class TVDisplay implements Observer {
    private String weather;
 
    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }
 
    private void display() {
        System.out.println("TV Display: Weather updated - " + weather);
    }
}
```

#### v. The Client Code
- In `WeatherApp`, a `WeatherStation` is created.
- Two observers (`PhoneDisplay` and `TVDisplay`) are registered with the weather station using `addObserver`.
- The `setWeather` method simulates a weather change to `Sunny`, triggering the `update` method in both observers.

```java
public class WeatherApp {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay = new PhoneDisplay();
        Observer tvDisplay = new TVDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);

        weatherStation.setWeather("Sunny");
    }
}
```

**Output**
```css
Phone Display: Weather updated - Sunny
TV Display: Weather updated - Sunny
```

### When to use the Observer Design Pattern?

i. **One-to-Many Dependence:** Use the Observer pattern when there is a one-to-many relationship between objects, and changes in one object should notify multiple dependent objects.

ii. **Decoupling:** Use the Observer pattern to achieve loose coupling between objects. This allows the subject (publisher) and observers (subscribers) to interact without being aware of each other’s specific details.

iii. **Dynamic Composition:** If you need to support dynamic composition of objects with runtime registration and deregistration of observers, the Observer pattern is suitable. New observers can be added or existing ones removed without modifying the subject.

iv. **Real-World Project Implementations:**
- Real-Time Notifications System
- Stock Market Application
- Weather Monitoring System

### Pros and Cons of the Observer Design Pattern

### Pros

i. **Loose Coupling:** The subject and observers are loosely coupled. The subject only knows that observers implement a specific interface, allowing for flexibility and reducing dependencies between them.
   
ii. **Dynamic Relationships:** Observers can be added or removed at runtime. This allows for dynamic changes in the behavior of the system as different observers can register or deregister to the subject as needed.
   
iii. **Broadcast Communication:** The pattern enables the subject to broadcast updates to multiple observers simultaneously, ensuring that all interested parties are notified of changes.
   
iv. **Improved Scalability:** The pattern supports the addition of new observers with minimal changes to the existing code. This aligns with the Open/Closed Principle, as new behavior can be added without modifying the subject.

### Cons

i. **Potential Performance Overhead:** If there are many observers, notifying all of them can lead to performance issues, especially if the update operation is costly. This can become problematic in systems with a large number of observers.
   
ii. **Unexpected Updates:** Observers may receive updates unexpectedly, which could lead to inconsistent states if they are not designed to handle such updates appropriately.
   
iii. **Complex Debugging:** Because observers are updated automatically, it can be difficult to trace the flow of updates and understand how changes propagate through the system, leading to potential difficulties in debugging.
   
iv. **Memory Leaks:** If observers are not properly removed from the subject when they are no longer needed, it can lead to memory leaks, as the subject may keep references to unused observers.


## 2.2 Facade Design Pattern
🔼 [Back to Top](#table-of-contents)

The Facade Design Pattern provides a simplified interface to a complex system of classes, libraries, or frameworks. This pattern is particularly useful when dealing with complex systems where clients require a simplified interface to interact with the system's core functionality.

### Components of Facade Method Design Pattern

i. **Subsystem classes**
Subsystem Classes are responsible for implementing the core functionality of the subsystem. They handle the specific tasks assigned to them by the Facade object. These classes operate independently of the Facade; they do not maintain any references to it. This separation ensures that subsystem classes remain unaware of the Facade and its higher-level operations, promoting loose coupling and modularity.

ii. **Facade**
Facade serves as an intermediary between the client and the subsystem classes. It is aware of the various subsystem classes and understands which ones are needed to fulfill a request. The Facade delegates client requests to the appropriate subsystem objects, thereby simplifying the client's interaction with the system. This central point of access streamlines client operations and abstracts the complexities of the underlying subsystem.

iii. **Client**
Client is the entity that interacts with the Facade to request operations. By communicating solely with the Facade, the client avoids dealing directly with the complex subsystem classes, which enhances ease of use and reduces potential errors. The client benefits from a simplified interface while the Facade manages the complexity of coordinating subsystem interactions.

![Facade Pattern Diagram](https://github.com/siddiqur-54/task_documentations/blob/main/images/Solid%20Principles%20and%20Design%20Patterns/facade_pattern.jpg)

### Implementation of the Facade Design Pattern

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
```

### When to use Facade Design Pattern

i. **Simplifying Complex External Systems:** A facade encapsulates database connection, query execution, and result processing, offering a clean interface to the application.
A facade simplifies the usage of external APIs by hiding the complexities of authentication, request formatting, and response parsing.

ii. **Decoupling subsystems:** Facades define clear boundaries between subsystems, reducing dependencies and promoting modularity.

iii. **Providing high-level views:** Facades offer simplified interfaces to lower-level subsystems, making them easier to understand and use.

iv. **Real-World Project Implementations:**
- Banking System
- Automation System
- E-commerce Platform

### Pros and Cons of the Facade Design Pattern
### Pros
i. **Simplified Interface:** Provides a clear and concise interface to a complex system, making it easier to understand and use. Hides the internal details and intricacies of the system, reducing cognitive load for clients.

ii. **Reduced Coupling:** Decouples clients from the underlying system, making them less dependent on its internal structure. Promotes modularity and reusability of code components. Facilitates independent development and testing of different parts of the system.

iii. **Improved Maintainability:** Easier to change or extend the underlying system without affecting clients, as long as the facade interface remains consistent. Allows for refactoring and optimization of the subsystem without impacting client code.

### Cons
i. **Increased Complexity:** Introducing a facade layer adds an extra abstraction level, potentially increasing the overall complexity of the system. This can make the code harder to understand and debug, especially for developers unfamiliar with the pattern.

ii. **Reduced Flexibility:** The facade acts as a single point of access to the underlying system. This can limit the flexibility for clients who need to bypass the facade or access specific functionalities hidden within the subsystem.

iii. **Potential Performance Overhead:** Adding an extra layer of indirection through the facade can introduce a slight performance overhead, especially for frequently used operations. This may not be significant for most applications, but it’s worth considering in performance-critical scenarios.



## 2.3 Prototype Design Pattern
🔼 [Back to Top](#table-of-contents)

The Prototype design pattern is a creational design pattern used when the cost of creating a new object is high, and you need to create multiple instances of objects that are identical or similar to each other. Instead of creating new objects from scratch, the Prototype pattern allows you to clone existing objects.

### Components of Prototype Design Pattern

- i. **Prototype Interface:** The `Prototype` interface declares the method(s) for cloning an object. It defines the common interface that concrete prototypes must implement.

- ii. **Concrete Prototype:** The `Concrete Prototype` is a class that implements the prototype interface or extends the abstract class. It’s the class representing a specific type of object that you want to clone.

- iii. **Clone Method:** The `clone` method specifies how an object should be copied or cloned. It Describes how the object’s internal state should be duplicated to create a new, independent instance.

- iv.  **Client:** The `Client` is the code or module that requests the creation of new objects by interacting with the prototype. It initiates the cloning process without being aware of the concrete classes involved.

![Prototype Pattern Diagram](https://github.com/siddiqur-54/task_documentations/blob/main/images/Solid%20Principles%20and%20Design%20Patterns/prototype_diagram.png)

### Implementation of the Protoype Design Pattern

#### i. Prototype Interface (Shape)
We define an interface called `Shape` that acts as the prototype. It declares two methods: `clone()` for making a copy of itself and `draw()` for drawing the shape.

```java
interface Shape {
    Shape clone();
    void draw();
}
```

#### ii. Concrete Prototype (Circle)
We implement the `Shape` interface with a concrete class `Circle`. The Circle class has a private field color and a constructor to set the `color` when creating a circle. It implements the `clone()` method to create a copy of itself (a new Circle with the same color).

```java
class Circle implements Shape {
    private String color;
 
    public Circle(String color) {
        this.color = color;
    }
 
    @Override
    public Shape clone() {
        return new Circle(this.color);
    }
 
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle.");
    }
}
```

#### iii. The Client (ShapeClient)
We create a client class, `ShapeClient`, which will use the prototype to create new shapes. The client has a field `shapePrototype` representing the prototype it will use. The constructor takes a Shape prototype, and there’s a method `createShape()` that creates a new shape using the prototype’s `clone()` method.

```java
class ShapeClient {
    private Shape shapePrototype;

    public ShapeClient(Shape shapePrototype) {
        this.shapePrototype = shapePrototype;
    }

    public Shape createShape() {
        return shapePrototype.clone();
    }
}
```

#### iv. The Driver Code
We create a `Circle` object as a prototype with the color `red`. A `ShapeClient` is then set up with this prototype to clone and create a new Circle object. Finally, the newly created circle is drawn, demonstrating the efficient replication of objects based on a prototype.

```java
public class PrototypePattern {
    public static void main(String[] args) {
        Shape circlePrototype = new Circle("red");
        ShapeClient client = new ShapeClient(circlePrototype);
        Shape redCircle = client.createShape();
        redCircle.draw();
    }
}
```

**Output**
```css
Drawing a red circle.
```

#### When to use the Prototype Design Pattern

i. **Creating Objects is Costly:** Use the Prototype pattern when creating objects is more expensive or complex than copying existing ones. If object creation involves significant resources, such as database or network calls.

ii. **Variations of Objects:** Use the Prototype pattern when your system needs to support a variety of objects with slight variations. Instead of creating multiple classes for each variation, you can create prototypes and clone them with modifications.

iii. **Reducing Initialization Overhead:** Use the Prototype pattern when you want to reduce the overhead of initializing an object. Creating a clone can be faster than creating an object from scratch, especially when the initialization process is resource-intensive.

iv. **Real-World Project Implementations:**
- Document Management Systems
- Game Development
- Data Caching Systems

### Pros and Cons of the Prototype Design Pattern

### Pros
i. **Easy Object Cloning:** The pattern enables you to create new objects by copying existing ones, which promotes code reuse. This is especially beneficial when objects have complex or resource-intensive initialization processes.

ii. **Reduced Initialization Overhead:** Since objects are cloned instead of being created from scratch, it can significantly reduce the overhead associated with expensive object initialization.

iii. **Individual Customization:** Cloned objects can be easily customized to suit specific requirements while retaining the common characteristics of the prototype. This allows for flexibility in object creation.

### Cons
i. **Shallow vs Deep Copy:** In scenarios where objects contain references to other objects (e.g., nested objects), cloning might result in shallow copies by default. This means that changes to the nested objects in a cloned object can affect the original object and vice versa. Deep copying may be required, which can be complex to implement.

<details>
<summary><b>Example of Shallow Copy</b></summary>

```java
interface Shape {
    Shape clone();
    void draw();
}

class Color {
    private String name;
    
    public Color(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}

class Circle implements Shape {
    private Color color;
 
    public Circle(Color color) {
        this.color = color;
    }
 
    @Override
    public Shape clone() {
        return new Circle(this.color);
    }
 
    @Override
    public void draw() {
        System.out.println("Drawing a " + color.getName() + " circle.");
    }

    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
}

public class PrototypeShallow {
    public static void main(String[] args) {
        Color red = new Color("red");
        Shape circlePrototype = new Circle(red);
        Shape clonedCircle = circlePrototype.clone();
        
        System.out.println("Original prototype:");
        circlePrototype.draw();
        
        System.out.println("Cloned circle:");
        clonedCircle.draw();
        
        ((Circle)clonedCircle).getColor().setName("blue");
        
        System.out.println("Modified cloned circle:");
        clonedCircle.draw();
        
        System.out.println("Original prototype after modifying the cloned object:");
        circlePrototype.draw();
    }
}
```

**Output**
```css
Original prototype:
Drawing a red circle.
Cloned circle:
Drawing a red circle.
Modified cloned circle:
Drawing a blue circle.
Original prototype after modifying the cloned object:
Drawing a blue circle.
```
</details>

<details>
<summary><b>Example of Deep Copy</b></summary>

```java
interface Shape {
    Shape clone();
    void draw();
}

class Color {
    private String name;
    
    public Color(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Color(Color other) {
        this.name = other.name;
    }
}

class Circle implements Shape {
    private Color color;
 
    public Circle(Color color) {
        this.color = color;
    }
 
    public Circle(Circle other) {
        this.color = new Color(other.color);
    }
 
    @Override
    public Shape clone() {
        return new Circle(this);
    }
 
    @Override
    public void draw() {
        System.out.println("Drawing a " + color.getName() + " circle.");
    }

    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
}

public class PrototypeDeep {
    public static void main(String[] args) {
        Color red = new Color("red");
        Shape circlePrototype = new Circle(red);
        Shape clonedCircle = circlePrototype.clone();
        
        System.out.println("Original prototype:");
        circlePrototype.draw();
        
        System.out.println("Cloned circle:");
        clonedCircle.draw();
        
        ((Circle)clonedCircle).getColor().setName("blue");
        
        System.out.println("Modified cloned circle:");
        clonedCircle.draw();
        
        System.out.println("Original prototype after modifying the cloned object:");
        circlePrototype.draw();
    }
}
```

**Output**
```css
Original prototype:
Drawing a red circle.
Cloned circle:
Drawing a red circle.
Modified cloned circle:
Drawing a blue circle.
Original prototype after modifying the cloned object:
Drawing a red circle.
```
</details>
</br>

ii. **Managing Object State:** If an object has an internal state that should not be shared across clones, careful management of the object state is necessary to ensure that each clone maintains its integrity.

iii. **Compatibility with Serialization:** If you need to clone objects that are serializable, you might encounter challenges related to object serialization and deserialization.
