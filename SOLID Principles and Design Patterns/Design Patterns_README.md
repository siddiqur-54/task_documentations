# Design Patterns

## Table of Contents
[1. Prototype Design Pattern](#1-prototype-design-pattern)
[2. Observer Design Pattern](#2-observer-design-pattern)
[3. Facade Design Pattern](#3-facade-design-pattern)
[4. Singleton Design Pattern](#4-singleton-design-pattern)
[5. Factory Design Pattern](#5-factory-design-pattern)
[6. Builder Design Pattern](#6-builder-design-pattern)
[7. Strategy Design Pattern](#7-strategy-design-pattern)
[8. Command Design Pattern](#8-command-design-pattern)
[9. Chain of Responsibility Design Pattern](#9-chain-of-responsibility-design-pattern)


## 1. Prototype Design Pattern
The Prototype design pattern is a creational design pattern used when the cost of creating a new object is high, and you need to create multiple instances of objects that are identical or similar to each other. Instead of creating new objects from scratch, the Prototype pattern allows you to clone existing objects.

### Steps to Implement the Protoype Pattern

#### I. Prototype Interface
The `Prototype` interface declares a method for cloning objects. This method is usually named `clone`. The clone method is responsible for creating a copy of the object.

```java
public interface Prototype {
    Prototype clone();
}
```
#### II. Concrete Prototype Classes
Create a class named `Shape` that implements the `Prototype `interface. Implement the clone method to create and return a new instance of `Shape` with the same property values.

**Shape Class**
```java
class Shape implements Prototype {
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
}
```

#### III. The Client Code
Create an instance of Shape and use it as the original prototype. Call the clone method on the original prototype to create a cloned instance. Modify the cloned instance and print its details, ensuring that the original instance remains unchanged.

```java
public class PrototypePattern {
    public static void main(String[] args) {
        Shape originalShape = new Shape(5, "Red");
        System.out.println("Original Shape - Dimension: " + originalShape.getDimension() + ", Color: " + originalShape.getColor());

        Shape clonedShape = originalShape.clone();
        System.out.println("Cloned Shape - Dimension: " + clonedShape.getDimension() + ", Color: " + clonedShape.getColor());

        clonedShape = new Shape(10, "Blue");
        System.out.println("Modified Cloned Shape - Dimension: " + clonedShape.getDimension() + ", Color: " + clonedShape.getColor());
        
        System.out.println("Unchanged Original Shape - Dimension: " + originalShape.getDimension() + ", Color: " + originalShape.getColor());
    }
}
```

**Output**
```css
Original Shape - Dimension: 5, Color: Red
Cloned Shape - Dimension: 5, Color: Red
Modified Cloned Shape - Dimension: 10, Color: Blue
Unchanged Original Shape - Dimension: 5, Color: Red
```

## 2. Observer Design Pattern

The Observer design pattern is a behavioral design pattern that allows one object (the subject) to notify other objects (the observers) about changes in its state. This pattern is useful for scenarios where changes in one object should trigger updates in other dependent objects.

### Steps to Implement the Observer Pattern

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

## 3. Facade Design Pattern
The Facade Design Pattern provides a simplified interface to a complex system of classes, libraries, or frameworks. This pattern is particularly useful when dealing with complex systems where clients require a simplified interface to interact with the system's core functionality.

### Steps to Implement the Bill Pugh Singleton Design
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

## 4. Singleton Design Pattern
We often require classes that can only have one object. For example: thread pools, caches, loggers etc. Creating more than one objects of these could lead to issues such as incorrect program behavior, overuse of resources, or inconsistent results. This is where Singleton Design Pattern comes into play.

### Steps to Implement the Bill Pugh Singleton Design

#### I: Define the Singleton Class
- Create a class, e.g., `DatabaseConnection`.
- Make the constructor private to prevent instantiation from outside the class.

#### II: Create a Static Inner Class
- Inside the `DatabaseConnection` class, create a private static inner class, e.g., `SingletonHelper`.
- Inside this inner class, define a static final instance of the outer class (`DatabaseConnection`).

#### III: Provide a Public Method to Access the Singleton Instance
- In the outer class, provide a public static method, e.g., `getInstance()`, that returns the instance from the static inner class.

#### IV: Use the Singleton Instance
- In your client code, call the `getInstance()` method to get the single instance of the class and use it.

```java
class DatabaseConnection {
    private DatabaseConnection() {
        System.out.println("Establishing connection to the database...");
    }

    private static class SingletonHelper {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    public static DatabaseConnection getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void query(String sql) {
        System.out.println("Executing query: " + sql);
    }
}

public class Singleton {
    public static void main(String[] args) {
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        connection1.query("SELECT * FROM users");

        DatabaseConnection connection2 = DatabaseConnection.getInstance();
        connection2.query("SELECT * FROM products");

        System.out.println("Are both connections the same instance? " + (connection1 == connection2));
    }
}
```

**Output**
```css
Establishing connection to the database...
Executing query: SELECT * FROM users
Executing query: SELECT * FROM products
Are both connections the same instance? true
```

#### Why the Bill Pugh Singleton Design is Efficient
**I. Lazy Initialization**
The Singleton instance is created only when the getInstance() method is called for the first time. This ensures that resources are not consumed until the instance is actually needed.
**II. Thread Safety**
The Java class loading mechanism ensures that the SingletonHelper inner class is loaded and initialized only when it is referenced, making the creation of the Singleton instance inherently thread-safe without requiring synchronization.
**III. No Synchronization Overhead**
Unlike other methods that require synchronized blocks or methods, the Bill Pugh design avoids any performance overhead associated with synchronization, leading to faster execution.
**IV. Effective Resource Management**
By delaying the creation of the instance until it's needed and ensuring that only one instance is created, this method effectively manages resources, making it suitable for scenarios where the Singleton instance is resource-intensive to create.


## 5. Factory Design Pattern
The Factory Design Pattern is a creational pattern used to create objects without specifying the exact class of the object that will be created. It provides a way to delegate the instantiation logic to subclasses or a factory class. This pattern is particularly useful when you need to manage or manipulate a collection of related objects that share a common interface.

### Steps to Implement the Factory Design Pattern
#### I. Define the Product Interface
Create the `OperatingSystem` interface with a boot method.

```java
interface OperatingSystem {
    void boot();
}
```

#### II. Create Concrete Products
Implement the `OperatingSystem` interface with `Windows` and `Linux` classes.

```java
class Windows implements OperatingSystem {
    @Override
    public void boot() {
        System.out.println("Booting Windows OS...");
    }
}

class Linux implements OperatingSystem {
    @Override
    public void boot() {
        System.out.println("Booting Linux OS...");
    }
}
```

#### III. Define the Factory Interface
Create the `OperatingSystemFactory` interface with a createOperatingSystem method.

```java
interface OperatingSystemFactory {
    OperatingSystem createOperatingSystem();
}
```

#### IV. Implement Concrete Factories
Implement the factory interface for each OS type with `WindowsFactory` and `LinuxFactory`.

```java
class WindowsFactory implements OperatingSystemFactory {
    @Override
    public OperatingSystem createOperatingSystem() {
        return new Windows();
    }
}

class LinuxFactory implements OperatingSystemFactory {
    @Override
    public OperatingSystem createOperatingSystem() {
        return new Linux();
    }
}
```

#### V. The Client Code
Instantiate the appropriate factory and use it to create and boot different operating system instances.

```java
public class FactoryPattern {
    public static void main(String[] args) {
        OperatingSystemFactory windowsFactory = new WindowsFactory();
        OperatingSystem windows = windowsFactory.createOperatingSystem();
        windows.boot();

        OperatingSystemFactory linuxFactory = new LinuxFactory();
        OperatingSystem linux = linuxFactory.createOperatingSystem();
        linux.boot();
    }
}
```

**Output**
```css
Booting Windows OS...
Booting Linux OS...
```

## 6. Builder Design Pattern
The Builder design pattern is a creational design pattern that provides a flexible solution for constructing complex objects. The pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

### Steps to Implement the Factory Design Pattern

#### I. Define the Product
Define the class `Computer` that represents the complex object. This class contains attributes that need to be initialized in a controlled manner.

```java
class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;

    public Computer(String CPU, String RAM, String storage, String GPU) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.storage = storage;
        this.GPU = GPU;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage + ", GPU=" + GPU + "]";
    }
}
```


#### II. Create the Builder Class
Create the `ComputerBuilder` class, which will build the `Computer` object step by step.

```java
class ComputerBuilder {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;

    public ComputerBuilder setCPU(String CPU) {
        this.CPU = CPU;
        return this;
    }

    public ComputerBuilder setRAM(String RAM) {
        this.RAM = RAM;
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        this.storage = storage;
        return this;
    }

    public ComputerBuilder setGPU(String GPU) {
        this.GPU = GPU;
        return this;
    }

    public Computer build() {
        return new Computer(CPU, RAM, storage, GPU);
    }
}
```


#### III. The Client Code
Use the `ComputerBuilder` class to construct a `Computer` object in a flexible and controlled manner.

```java
public class BuilderPattern {
    public static void main(String[] args) {
        Computer myComputer = new ComputerBuilder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .build();

        System.out.println(myComputer);
    }
}
```

**Output:**
```css
Computer [CPU=Intel i9, RAM=32GB, Storage=1TB SSD, GPU=NVIDIA RTX 3080]
```

## 7. Strategy Design Pattern
The Strategy Pattern is a behavioral design pattern that enables selecting an algorithm from a family of algorithms at runtime. It allows you to define a strategy interface, implement various strategy classes, and use them interchangeably in the client code.

### Steps to Implement the Strategy Design Pattern

#### I. Define the Strategy Interface
The `PaymentStrategy` interface declares the `pay(int amount)` method. This method must be implemented by all concrete strategy classes to perform payment operations.

```java
interface PaymentStrategy {
    void pay(int amount);
}
```

#### II. Create Concrete Strategy Classes
Concrete strategy classes like `CreditCardPayment`, `PayPalPayment`, and `BitcoinPayment` implement the `PaymentStrategy` interface. Each class provides a specific implementation of the `pay` method.

**BitcoinPayment Class**
```java
public class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;

    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Bitcoin from wallet: " + walletAddress);
    }
}
```

**CreditCardPayment Class**
```java
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card. Card Holder: " + cardHolderName);
    }
}
```
**PayPalPayment Class**
```java
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal from account: " + email);
    }
}
```

#### III. Implement the Context Class
The `ShoppingCart` class serves as the context in which the selected `PaymentStrategy` is used. The `setPaymentStrategy(PaymentStrategy paymentStrategy)` method allows you to set the desired payment strategy, and the `checkout()` method processes the payment using the chosen strategy.

**ShoppingCart Class**

```java
class ShoppingCart {
    private List<Integer> items = new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    public void addItem(int price) {
        items.add(price);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout() {
        int totalAmount = items.stream().mapToInt(Integer::intValue).sum();
        paymentStrategy.pay(totalAmount);
    }
}
```

#### IV. The Client Code
The `StrategyPattern` class demonstrates the flexibility of the Strategy Pattern. By setting different strategies (`BitcoinPayment`, `CreditCardPayment`, `PayPalPayment`) using the `setPaymentStrategy` method, the payment process can be altered dynamically.

```java
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(100);
        cart.addItem(200);

        cart.setPaymentStrategy(new CreditCardPayment("123456789", "John Doe"));
        cart.checkout();

        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout();

        cart.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout();
    }
}
```

**Output**
```css
Paid 300 using Credit Card. Card Holder: John Doe
Paid 300 using PayPal from account: john.doe@example.com
Paid 300 using Bitcoin from wallet: 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa
```

## 8. Command Design Pattern
The Command Pattern is a behavioral design pattern that turns a request into a stand-alone object containing all the information about the request. This transformation allows the parameterization of clients with queues, requests, and operations, and supports undoable operations.

### Steps to Implement the Command Design Pattern

#### I. Define the Command Interface
The `Command` interface declares the `execute()` method, which all concrete command classes must implement. This method encapsulates the request as an object.

```java
interface Command {
    void execute();
    void undo();
}
```

#### II. Create the Receiver Class
The `Light` class acts as the receiver, containing the actual business logic that needs to be performed, such as turning the light on or off.

```java
class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}
```
#### III. Implement Concrete Command Classes
Concrete command classes, such as `LightOnCommand` and `LightOffCommand`, implement the `Command` interface and define the binding between an action and the receiver.

**LightOnCommand Class**

```java
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
```

**LightOffCommand Class**

```java
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
```

#### IV. Implement the Invoker Class
The `RemoteControl` class acts as the invoker, storing the command objects and invoking the `execute()` method when a specific action is requested.

```java
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

#### V. Extend the Invoker to Support Undo
The `RemoteControlWithUndo` class adds functionality to undo the last executed command by storing a reference to the last command executed.

```java
class RemoteControlWithUndo {
    private Command command;
    private Command lastCommand;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
        lastCommand = command;
    }

    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }
}
```

#### VI. The Client Code
The `CommandPattern` class demonstrates how to use the `RemoteControl` and `RemoteControlWithUndo` to turn the light on and off, and undo the operation.

```java
public class CommandPattern {
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println("----- With Undo -----");

        RemoteControlWithUndo remoteWithUndo = new RemoteControlWithUndo();
        remoteWithUndo.setCommand(lightOn);
        remoteWithUndo.pressButton();
        remoteWithUndo.pressUndo();

        remoteWithUndo.setCommand(lightOff);
        remoteWithUndo.pressButton();
        remoteWithUndo.pressUndo();
    }
}
```

**Output**
```css
Light is ON
Light is OFF
----- With Undo -----
Light is ON
Light is OFF
Light is OFF
Light is ON
```

## 9. Chain of Responsibility Design Pattern
The Chain of Responsibility Pattern is a behavioral design pattern that allows an object to pass a request along a chain of potential handlers until the request is handled. This pattern decouples the sender of a request from its receiver by giving more than one object the opportunity to handle the request.

### Steps to Implement the Chain of Responsibility Design Pattern

#### I. Define the Handler Interface
The `SupportHandler` interface declares two methods: `setNextHandler(SupportHandler nextHandler)` to link the handlers and `handleRequest(String issue)` to process or pass on the request.

```java
interface SupportHandler {
    void setNextHandler(SupportHandler nextHandler);
    void handleRequest(String issue);
}
```

#### II. Implement Concrete Handlers
Each concrete handler (`LevelOneSupport`, `LevelTwoSupport`, `LevelThreeSupport`) handles a specific issue type. If it can't handle the request, it passes it to the next handler.

**LevelOneSupport Class**
```java
class LevelOneSupport implements SupportHandler {
    private SupportHandler nextHandler;
    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("basic")) {
            System.out.println("Level 1 support handling the issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}
```

**LevelTwoSupport Class**
```java
class LevelTwoSupport implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String issue) {
        if (issue.equals("intermediate")) {
            System.out.println("Level 2 support handling the issue.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(issue);
            }
        }
    }
}
```

**LevelThreeSupport Class**
```java
class LevelThreeSupport implements SupportHandler {
    @Override
    public void setNextHandler(SupportHandler nextHandler) {
    }

    @Override
    public void handleRequest(String issue) {
        if (issue.equals("complex")) {
            System.out.println("Level 3 support handling the issue.");
        } else {
            System.out.println("Issue could not be handled.");
        }
    }
}
```

#### III. The Client Code
The client sends requests to `LevelOneSupport`. Link the handlers (LevelOneSupport -> LevelTwoSupport -> LevelThreeSupport). The chain passes requests until they are handled or reach the end. If it can’t handle the request, it passes it down the chain.


```java
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportHandler levelOneSupport = new LevelOneSupport();
        SupportHandler levelTwoSupport = new LevelTwoSupport();
        SupportHandler levelThreeSupport = new LevelThreeSupport();
        
        levelOneSupport.setNextHandler(levelTwoSupport);
        levelTwoSupport.setNextHandler(levelThreeSupport);
        
        levelOneSupport.handleRequest("basic");
        levelOneSupport.handleRequest("intermediate");
        levelOneSupport.handleRequest("complex");
        levelOneSupport.handleRequest("unknown");
    }
}
```

**Output**
```css
Level 1 support handling the issue.
Level 2 support handling the issue.
Level 3 support handling the issue.
Issue could not be handled.
```
