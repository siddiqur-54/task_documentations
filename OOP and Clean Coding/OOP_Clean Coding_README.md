# Object-Oriented Programming (OOP)

## Introduction
Object-Oriented Programming (OOP) is a programming paradigm centered around "objects," which encapsulate data as attributes and behavior as methods. OOP promotes a modular structure through key principles like encapsulation, abstraction, inheritance, and polymorphism. These principles enhance code readability, maintainability, and reusability.

![OOP Concepts](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/Object-Oriented-Programming-Concepts.jpg)

## 1. Class and Object

### I. Class
- A class is a blueprint or prototype from which objects are created.
- It defines the structure and behavior that the objects will have.
- **Components**:
  - **Attributes**: Define the properties or state of the class (e.g., `brand`, `model`, `year`).
  - **Methods**: Define the behaviors or actions that objects of the class can perform (e.g., `startEngine()`).

### II. Object

- An object is an instance of a class.
- It represents a concrete entity with state and behavior.
- Memory is allocated for an object when it is instantiated from a class.
- **Attributes and Methods**:
  - **Attributes**: Store the current state or values of the object (e.g., `myCar.brand`, `myCar.model`).
  - **Methods**: Perform actions on the object's data or define the object’s behavior (e.g., `myCar.startEngine()`).

- **Example**:
    ```java
    // Define the Vehicle class
    class Vehicle {
        // Attributes of Vehicle
        String brand;
        int speed;

        // Method to display vehicle information
        void displayInfo() {
            System.out.println("Brand: " + brand);
            System.out.println("Speed: " + speed + " km/h");
        }

        // Method to accelerate the vehicle
        void accelerate(int increment) {
            speed += increment;
            System.out.println("Vehicle accelerated. New speed: " + speed + " km/h");
        }
    }

    // Main class to run the program
    public class Main {
        public static void main(String[] args) {
            // Create an object of the Vehicle class
            Vehicle myVehicle = new Vehicle();

            // Set attributes for the object
            myVehicle.brand = "Toyota";
            myVehicle.speed = 100;

            // Call methods on the object
            myVehicle.displayInfo();
            myVehicle.accelerate(20);
        }
    }
    ```

## 2. Abstraction
Abstraction is a fundamental concept in Object-Oriented Programming (OOP) that focuses on hiding the implementation details of an object and exposing only the essential functionality to the user. It allows users to interact with objects at a high level without needing to understand the internal workings.

**Key Points:**
- **Focus**: Abstraction emphasizes "what" an object does rather than "how" it does it.
- **Implementation**: There are two primary ways to achieve abstraction in Java:
  - **Abstract Classes**: Classes that cannot be instantiated on their own and may contain abstract methods (methods without a body).
  - **Interfaces**: Fully abstract types that can only contain abstract methods and final, static constants.

**Real-Life Example:**
- A remote control for home appliances, such as a television or air conditioner, is a perfect example of abstraction. When using a remote, you interact with a simple interface of buttons for power, volume, and channel control, without needing to understand the complex internal workings of the appliance.

### I. Abstract Class (0 to 100% Abstraction)
An abstract class is a class that cannot be instantiated directly. Abstract classes provide partial abstraction by allowing some methods to be implemented while others are left to be defined by subclasses.

**Key Points:**
- An abstract class cannot be instantiated directly and can have both abstract methods (without implementation) and concrete methods (with implementation).
- It can include constructors, static methods, and final methods that cannot be overridden by subclasses.

### II. Interface (100% Abstraction)
An interface is a reference type that defines a contract for what methods a class should implement. Interfaces achieve 100% abstraction. This allows you to define what functionality a class should offer while hiding the implementation details.

**Key Points:**
- **Method Declaration**: Interfaces declare abstract methods without providing method bodies. This ensures that any class implementing the interface must provide implementations for these methods.
- **Implementation**: Classes that implement an interface must provide concrete implementations for all of its methods. This enforces a contract that the class must adhere to.
- **Abstraction**: Interfaces provide complete abstraction by exposing only method signatures and hiding implementation details. This allows users to interact with objects at a high level of abstraction.

- **Example**:
    ```java
    // Abstract class
    abstract class Vehicle {
        // Abstract method (does not have a body)
        abstract void start();

        // Concrete method
        void stop() {
            System.out.println("Vehicle stopped.");
        }

        // Constructor
        Vehicle() {
            System.out.println("Vehicle is created.");
        }
    }

    // Interface definition
    interface Drivable {
        void accelerate();
        void brake();
    }

    // Subclass (inherited from Vehicle) and implementing Drivable interface
    class Car extends Vehicle implements Drivable {
        // Providing implementation for abstract method
        @Override
        void start() {
            System.out.println("Car started.");
        }

        // Implementing interface methods
        @Override
        public void accelerate() {
            System.out.println("Car is accelerating.");
        }

        @Override
        public void brake() {
            System.out.println("Car is braking.");
        }
    }

    // Main class
    public class Main {
        public static void main(String[] args) {
            // Create an object of the subclass
            Car myCar = new Car();
            
            // Using methods from the abstract class
            myCar.start();   // Output: Car started.
            myCar.stop();    // Output: Vehicle stopped.

            // Using methods from the Drivable interface
            myCar.accelerate(); // Output: Car is accelerating.
            myCar.brake();      // Output: Car is braking.
        }
    }
    ```

## 3. Encapsulation
Encapsulation is a core principle of Object-Oriented Programming (OOP) that involves bundling the data (attributes) and methods (functions) into a single unit, typically a class. It restricts direct access to some of the object's components.

**Real-Life Example:**
- A real-life example of encapsulation can be seen in a bank account system. In this system, the details of a bank account, such as the balance, account number, and account holder's information, are hidden from the outside world. Instead, access to these details is provided through well-defined methods that control how the data is accessed or modified.

### Achieving Encapsulation in Java
i. **Declare Variables as Private**: Make the data members (variables) of the class private to restrict direct access from outside the class.

ii. **Provide Public Getters and Setters**: Define public methods (getters and setters) to access and update the private variables. This allows controlled access to the data.

**Advantages of Encapsulation:**
- **Control Over Data**: You can add logic to setter methods to validate data before setting it, ensuring that only valid data is stored.

- **Data Hiding**: By making data members private, you hide the internal state of the object from the outside world, protecting it from unintended interference.

- **Example:**
    ```java
    // Encapsulated class
    public class Vehicle {
        // Private variables
        private String brand;
        private int speed;

        // Public constructor
        public Vehicle(String brand, int speed) {
            this.brand = brand;
            setSpeed(speed); // Using setter to enforce validation
        }

        // Getter for brand
        public String getBrand() {
            return brand;
        }

        // Setter for brand
        public void setBrand(String brand) {
            this.brand = brand;
        }

        // Getter for speed
        public int getSpeed() {
            return speed;
        }

        // Setter for speed with validation
        public void setSpeed(int speed) {
            if (speed >= 0) { // Validation logic
                this.speed = speed;
            } else {
                System.out.println("Speed must be non-negative.");
            }
        }
    }

    // Main class to test the Vehicle class
    public class Main {
        public static void main(String[] args) {
            // Creating an object of the Vehicle class
            Vehicle vehicle = new Vehicle("Toyota", 120);

            // Accessing and modifying data through getters and setters
            System.out.println("Brand: " + vehicle.getBrand()); // Output: Brand: Toyota
            System.out.println("Speed: " + vehicle.getSpeed() + " km/h"); // Output: Speed: 120 km/h

            // Updating speed
            vehicle.setSpeed(150);
            System.out.println("Updated Speed: " + vehicle.getSpeed() + " km/h"); // Output: Updated Speed: 150 km/h

            // Attempting to set an invalid speed
            vehicle.setSpeed(-10); // Output: Speed must be non-negative.
        }
    }
    ```


## 4. Inheritance

Inheritance is a key concept in Object-Oriented Programming (OOP) where one class (subclass or derived class) inherits the properties and methods of another class (superclass or base class).

**Real-Life Example:**
- Consider the hierarchy of celestial bodies: The planet Earth and Mars inherit properties from the superclass Solar System, and the Solar System inherits from the Milky Way Galaxy. Thus, the Milky Way Galaxy is the top-level superclass for Solar System, Earth, and Mars.

#### Types of Inheritance in Java

I. **Single Inheritance**
   - A subclass inherits from a single superclass.

        ![Single Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/single%20inheritance.jpg)

   - **Example**:
        ```java
        // Superclass
        class Vehicle {
            void start() {
                System.out.println("The vehicle starts.");
            }
        }

        // Subclass
        class Car extends Vehicle {
            void honk() {
                System.out.println("The car honks.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                Car myCar = new Car();
                myCar.start(); // Output: The vehicle starts.
                myCar.honk();  // Output: The car honks.
            }
        }
        ```

II. **Multilevel Inheritance**
   - A subclass inherits from another subclass, forming a chain of inheritance.

        ![Multilevel Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/multilevel%20inheritance.jpg)

   - **Example**:
        ```java
        // Base class
        class Vehicle {
            void start() {
                System.out.println("The vehicle starts.");
            }
        }

        // Intermediate class
        class MotorVehicle extends Vehicle {
            void drive() {
                System.out.println("The motor vehicle drives.");
            }
        }

        // Subclass
        class Car extends MotorVehicle {
            void honk() {
                System.out.println("The car honks.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                Car myCar = new Car();
                myCar.start(); // Output: The vehicle starts.
                myCar.drive(); // Output: The motor vehicle drives.
                myCar.honk();  // Output: The car honks.
            }
        }
        ```

III. **Hierarchy Inheritance**
   - Multiple subclasses inherit from a single superclass.

        ![Hierarchy Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/hierarchical%20inheritance.jpg)

   - **Example**:
        ```java
        // Superclass
        class Vehicle {
            void start() {
                System.out.println("The vehicle starts.");
            }
        }

        // Subclass 1
        class Car extends Vehicle {
            void honk() {
                System.out.println("The car honks.");
            }
        }

        // Subclass 2
        class Motorcycle extends Vehicle {
            void rev() {
                System.out.println("The motorcycle revs.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                Car myCar = new Car();
                myCar.start(); // Output: The vehicle starts.
                myCar.honk();  // Output: The car honks.

                Motorcycle myMotorcycle = new Motorcycle();
                myMotorcycle.start(); // Output: The vehicle starts.
                myMotorcycle.rev();   // Output: The motorcycle revs.
            }
        }
        ```

IV. **Multiple Inheritance (via Interfaces)**
   - A class inherits from more than one superclass. Java does not support multiple inheritance directly with classes, but it can be achieved using interfaces.

        ![Multiple Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/multiple%20inheritance.jpg)

   - **Why Java does not support multiple inheritance?**
   Java does not support multiple inheritance of classes primarily to avoid the complexity and ambiguity that can arise from it. Here are some key reasons:

        i. **Diamond Problem:** In multiple inheritance, a class can inherit from more than one class. This can lead to a situation known as the "diamond problem," where a class inherits from two classes that have a common ancestor.

        ii. **Complexity and Ambiguity:** Multiple inheritance can make the inheritance hierarchy complex and hard to manage. It can create ambiguity in method resolution and increase the potential for conflicts.

        ![Diamond Problem](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/diamond%20problem.png)
 
   - **Achieving multiple inheritance using Interface**
        ```java
        // First interface
        interface Vehicle {
            void move(); // Method defined in Vehicle interface
        }

        // Second interface
        interface Watercraft {
            void move(); // Method defined in Watercraft interface
        }

        // Class implementing both interfaces
        class AmphibiousVehicle implements Vehicle, Watercraft {
            @Override
            public void move() {
                System.out.println("The amphibious vehicle moves on land and water.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                AmphibiousVehicle amphibiousVehicle = new AmphibiousVehicle();
                amphibiousVehicle.move(); // Output: The amphibious vehicle moves on land and water.
            }
        }
        ```

   - **Avoiding Diamond Problem using interface having only Default Methods**
        ```java
        // First interface with default method
        interface Vehicle {
            // Default method in Vehicle interface
            default void move() {
                System.out.println("Vehicle moves.");
            }
        }

        // Second interface with default method
        interface Watercraft {
            // Default method in Watercraft interface
            default void move() {
                System.out.println("Watercraft moves.");
            }
        }

        // Class implementing both interfaces
        class AmphibiousVehicle implements Vehicle, Watercraft {
            // Overriding the move() method to resolve ambiguity
            @Override
            public void move() {
                System.out.println("The amphibious vehicle moves on land and water.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                AmphibiousVehicle amphibiousVehicle = new AmphibiousVehicle();
                amphibiousVehicle.move(); // Output: The amphibious vehicle moves on land and water.
            }
        }
        ```

V. **Hybrid Inheritance**
   - A combination of multiple and hierarchical inheritance. Java does not support hybrid inheritance directly with classes, but it can be managed using interfaces.

        ![Hybrid Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/hybrid%20inheritance.jpg)

   - **Example**:
        ```java
        // Base class
        class Vehicle {
            void start() {
                System.out.println("The vehicle starts.");
            }
        }

        // Intermediate class 1
        class Car extends Vehicle {
            void drive() {
                System.out.println("The car drives.");
            }
        }

        // Intermediate class 2
        class Boat extends Vehicle {
            void floatOnWater() {
                System.out.println("The boat floats on water.");
            }
        }

        // Interface for flying vehicles
        interface Flyable {
            void fly();
        }

        // Class implementing hybrid inheritance via interfaces
        class FlyingCar extends Car implements Flyable {
            @Override
            public void fly() {
                System.out.println("The flying car flies.");
            }
        }

        // Main class
        public class Main {
            public static void main(String[] args) {
                FlyingCar myFlyingCar = new FlyingCar();
                myFlyingCar.start();        // Output: The vehicle starts.
                myFlyingCar.drive();        // Output: The car drives.
                myFlyingCar.fly();          // Output: The flying car flies.
            }
        }
        ```

## 5. Polymorphism in Java

The term "polymorphism" comes from the Greek words "poly" (many) and "morph" (forms), meaning "many forms". Polymorphism enhances flexibility and reusability in programming by allowing one interface to be used for a general class of actions. In Java, polymorphism is achieved through two main mechanisms:

**Real-Life Example:**
Just like in a real payment system, the action of making a payment is common across different payment methods, but the process varies. Polymorphism allows the system to treat all payment types in a uniform way while still allowing each type to behave differently as needed.

### I. Static or Compile-Time Polymorphism
Static polymorphism, also known as compile-time polymorphism, is achieved through method overloading. The compiler determines which method to call based on the method signature.

**Key Points:**
- Method overloading allows methods to have the same name but different parameter lists.
- The return type alone is not sufficient to differentiate overloaded methods.

- **Example**:
    ```java
    public class Vehicle {
        // Method to display vehicle information based on speed
        void displayInfo(int speed) {
            System.out.println("Vehicle speed: " + speed + " km/h");
        }

        // Method to display vehicle information based on fuel level
        void displayInfo(String fuelLevel) {
            System.out.println("Vehicle fuel level: " + fuelLevel);
        }

        // Method to display vehicle information based on model year
        void displayInfo(double modelYear) {
            System.out.println("Vehicle model year: " + modelYear);
        }
    }

    public class Main {
        public static void main(String[] args) {
            Vehicle vehicle = new Vehicle();
            vehicle.displayInfo(80);          // Output: Vehicle speed: 80 km/h
            vehicle.displayInfo("Full");      // Output: Vehicle fuel level: Full
            vehicle.displayInfo(2024.0);      // Output: Vehicle model year: 2024.0
        }
    }
    ```


### II. Dynamic or Run-Time Polymorphism
Dynamic polymorphism, also known as run-time polymorphism, is achieved through method overriding. At runtime, the Java Virtual Machine (JVM) determines the actual object's type and invokes the appropriate overridden method.

**Key Points:**
- Method overriding allows a subclass to provide a specific implementation for a method that is already defined in its superclass.
- The method in the subclass must have the same name, return type, and parameter list as the method in the superclass.
- The actual method that gets executed is determined at runtime based on the object's actual type, not the reference type.

- **Example**:
    ```java
    // Superclass
    class Vehicle {
        void start() {
            System.out.println("Vehicle starts.");
        }
    }

    // Subclass 1
    class Car extends Vehicle {
        @Override
        void start() {
            System.out.println("Car starts with a roar.");
        }
    }

    // Subclass 2
    class Boat extends Vehicle {
        @Override
        void start() {
            System.out.println("Boat starts with a splash.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Vehicle myVehicle;

            // Creating an object of Car
            myVehicle = new Car();
            myVehicle.start();  // Output: Car starts with a roar.

            // Creating an object of Boat
            myVehicle = new Boat();
            myVehicle.start();  // Output: Boat starts with a splash.
        }
    }
    ```
## 6. Association, Aggregation and Composition
Association, Aggregation and Composition in Java describe how instances of classes relate to each other.

![Association, Aggregation and Composition](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/Association.png)

### I. Association
**Definition:** Association is a general term that describes a relationship between two or more objects. It represents how objects interact with each other.

**Characteristics:**
- Can be one-to-one, one-to-many, many-to-one, or many-to-many.
- Represents a loose relationship between objects.
- **Example:**
    ```java
    class Person {
    private String name;
    private Address address;
    
    // Constructor, getters, and setters
    }
    class Address {
        private String street;
        private String city;
        
        // Constructor, getters, and setters
    }
    ```

### II. Aggregation
**Definition:** Aggregation is a special type of association that represents a whole-part relationship where the part can exist independently of the whole.

**Characteristics:**
- It denotes a "has-a" relationship.
- The lifetime of the part is independent of the whole.
- **Example:**
    ```java
    class Department {
    private String name;
    private List<Employee> employees;
    
    // Constructor, getters, and setters
    }

    class Employee {
        private String name;
        
        // Constructor, getters, and setters
    }
    ```

### III. Composition
**Definition:** Composition is a stronger form of aggregation where the part cannot exist independently of the whole. It implies a strict whole-part relationship with a dependent lifetime.

**Characteristics:**
- Also denotes a "has-a" relationship, but with a stronger association.
- The lifetime of the part is bound to the lifetime of the whole.
- **Example:**
    ```java
    class Engine {
    private String type;
    
    // Constructor, getters, and setters
    }

    class Car {
        private Engine engine;
        
        // Constructor
        public Car() {
            this.engine = new Engine(); // Composition: Engine is part of Car
        }
        
        // Constructor, getters, and setters
    }
    ```

## Conclusion
The four pillars of OOP—Encapsulation, Inheritance, Polymorphism, and Abstraction—are fundamental concepts that help in designing robust, flexible, and reusable code. Understanding and applying these principles is crucial for effective object-oriented software development.


# Clean Code: A Handbook of Agile Software Craftsmanship

This documentation provides a concise summary of the key points from the first four chapters of *Clean Code* by Robert C. Martin.

## Table of Contents
1. [Chapter 1: Clean Code](#chapter-1-clean-code)
2. [Chapter 2: Meaningful Names](#chapter-2-meaningful-names)
3. [Chapter 3: Functions](#chapter-3-functions)
4. [Chapter 4: Comments](#chapter-4-comments)

---

## Chapter 1: Clean Code
It's about how to write good code, and how to transform bad code into good code.

### Why write bad code?

- Are you in a rush?
- Do you try to go "fast"?
- Do not you have time to do a good job?
- Are you tired of work in the same program/module?

You are a professional and the code is your responsibility. Let's analyze the following anecdote:

> What if you were a doctor and had a patient who demanded that you stop all the silly hand-washing in preparation for surgery because it was taking too much time? Clearly the patient is the boss; and yet the doctor should absolutely refuse to comply. Why? Because the doctor knows more than the patient about the risks of disease and infection. It would be unprofessional (never mind criminal) for the doctor to comply with the patient.

So too it is unprofessional for programmers to bend to the will of managers who don’t understand the risks of making messes.

### What is Clean Code?
- **Readable:** Code should be easily understandable, even without detailed knowledge of the implementation.
- **Simple:** Avoid complexity by following the principle of simplicity in design and implementation.
- **Well-Structured:** Code should follow consistent conventions and patterns.
- **Testable:** Clean code is written with testing in mind.

### The Importance of Clean Code
- Clean code is essential for maintaining long-term software projects.
- It reduces the risk of bugs, makes the codebase easier to navigate, and helps in onboarding new developers.
- It is crucial for the scalability and evolution of software.

### The Role of the Developer and the Trade-offs:
- Developers are responsible for writing clean code. This involves adopting best practices, continuously improving coding skills to ensure the codebase remains clean and maintainable.
- While striving for clean code, developers may face trade-offs such as balancing between ideal design and practical constraints.

### The boy Scout Rule
> Always leave the code better than you found it.
- **Continuous Improvement:** The rule encourages developers to improve the codebase every time they work on it, even if they are just making minor changes or fixes. 
- **Responsibility:** Each developer should take ownership of the quality of the code they work on.
- **Practical Application:** This rule can be applied in various scenarios, such as when fixing bugs, adding new features, or performing routine maintenance. 

## Chapter 2: Meaningful Names

Naming is crucial for writing clean code. Good names enhance readability and maintainability.

- **Names Should Tell the Intent**
  - Names should clearly indicate why they exist, what they do, and how to use them.
  - **Example**: `studentMarkInfo` is preferred over `sMark`.

- **Avoid Confusing Names**
  - Avoid names that might imply something misleading or incorrect.
  - **Example**: Avoid names like `unix`, `studentList` (if not a list).

- **Choose Clear Names**
  - Use names that convey meaning precisely and clearly.
  - **Example**: Use `deleteItems` rather than `burstThemDown`, and `kill` instead of `whack`.

- **Use Good Distinctions**
  - Make distinctions meaningful rather than relying on arbitrary numbers.
  - **Example**: Use `productIds` and `productDetails` rather than `list1`, `list2`.

- **Use Pronounceable Names**
  - Code is a social activity; names should be easy to pronounce and understand.
  - **Example**: Use `dateOfBirth` instead of `dob`.

- **Use Searchable Names**
  - Names should be descriptive and easy to search for.
  - **Example**: Use `Event` and `maxStudents` rather than `e`, `z`.

- **Don’t Encode Types in Names**
  - Avoid including types in names, as these may change over time.
  - **Example**: Use `payment` instead of `paymentInt`.

- **Avoid Prefixes in Names**
  - Use descriptive names without unnecessary prefixes.
  - **Example**: Use `description` rather than `m_description`, and `ShapeFactory` instead of `IShapeFactory`.

- **Class Names - Nouns, Function Names - Verbs**
  - Use nouns for class names and verbs for function names.
  - **Example**: `Student`, `Car` for classes, and `postPayment`, `startEngine` for functions.

- **Use Names Consistently**
  - Stick to a single naming convention for related concepts.
  - **Example**: Use `controller` consistently instead of mixing `manager` and `controller`.

- **Don’t Use the Same Name for Different Things**
  - Avoid reusing names for different purposes.
  - **Example**: Use `employeePayment` and `employeeBankDetails` rather than `payInfo` for both.

- **Use Domain-Specific Names**
  - Choose names that reflect the domain and provide context.
  - **Example**: Use `accountVisitor` (for the visitor pattern), `jobQueue` (for a queue), and `nameBuilder` (for a builder).

- **Avoid Too Long Names**
  - While names should be descriptive, they should also be concise and not excessively long.

## Chapter 3: Functions

Functions are the building blocks of clean code. They should be designed to perform a single task and be easy to understand.

- **Functions Should Do One Thing**
  - A function should perform a single responsibility or task. This makes it easier to understand, test, and maintain.
  - **Example**: Instead of having a function that processes data and then formats it, split it into separate functions for processing and formatting.

- **Functions Should Have Descriptive Names**
  - Use names that convey what the function does. This improves readability and makes the code self-documenting.
  - **Example**: Use `calculateTax` rather than `compute`, and `sendEmail` instead of `performAction`.

- **Keep Functions Small**
  - Aim to keep functions short and focused. Small functions are easier to understand and manage.
  - **Example**: A function should ideally fit within a single screen of code.

- **Avoid Side Effects**
  - Functions should avoid modifying variables or states outside their scope. This prevents unexpected behavior and makes functions more predictable.
  - **Example**: Instead of modifying a global variable, return values from functions and handle state changes explicitly.

- **Use Parameters Wisely**
  - Limit the number of parameters. Ideally, a function should have zero to three parameters. Use parameter objects if you need more.
  - **Example**: Instead of passing multiple parameters, use a configuration object.

- **Avoid Functions with Multiple Exit Points**
  - Aim for a single exit point (return statement) in a function. This makes it easier to follow the function’s logic.
  - **Example**: Instead of having multiple `return` statements scattered throughout a function, structure it to return at the end.

- **Use Error Handling Appropriately**
  - Handle errors gracefully and use exceptions where appropriate. Avoid using error codes or multiple return values for error handling.
  - **Example**: Use `try-catch` blocks to handle exceptions rather than returning error codes.

- **Keep Function Interfaces Simple**
  - Ensure that functions have a simple and clear interface. Avoid complex interactions with other parts of the system.
  - **Example**: A function should not rely heavily on external state or configuration.

- **Document Function Behavior**
  - Use comments or documentation to describe the function’s purpose, parameters, and return values, especially if the function's behavior is complex.
  - **Example**: Document what each parameter represents and what the function returns.

## Chapter 4: Comments
Comments are crucial for explaining and clarifying code. However, they should be used judiciously and not as a substitute for writing clear, self-explanatory code.

- **Comments Should Not Be Used to Explain Bad Code**
  - If code is difficult to understand, it likely needs refactoring. Write clean, expressive code that minimizes the need for comments. Refactor complex logic into simpler functions with descriptive names rather than adding comments to explain it.
  - **Example**:
    ```java
    // Check to see if the employee is eligible for full benefits
    if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))
    ```

    vs

    ```java
    if (employee.isEligibleForFullBenefits())
    ```

- **Use Comments to Explain Why, Not What**
  - Comments should explain the intent or rationale behind the code, not just what the code is doing. The code itself should be clear about what it does.
  - **Example**: Explain why a particular algorithm is used or why certain decisions were made, rather than describing each line of code.

- **Avoid Redundant Comments**
  - Do not state the obvious or repeat what the code already conveys. Redundant comments add clutter and can become misleading if the code changes.
  - **Example**:
    ```java
    // Utility method that returns when this.closed is true. Throws an exception
    // if the timeout is reached.
    public synchronized void waitForClose(final long timeoutMillis) throws Exception {
        if(!closed) {
            wait(timeoutMillis);
            if(!closed)
            throw new Exception("MockResponseSender could not be closed");
        }
    }
    ```
    What purpose does this comment serve? It’s certainly not more informative than the code. It does not justify the code, or provide intent or rationale. It is not easier to read than the code.

- **Keep Comments Up-to-Date**
  - Ensure that comments are updated along with code changes. Outdated comments can mislead and confuse.
  - **Example**: Regularly review comments during code reviews and updates to ensure they accurately reflect the current state of the code.

- **Use Comments for Documentation**
  - Use comments to provide high-level overviews, explanations of complex sections, or documentation for public APIs.
  - **Example**: Use Javadoc comments to describe the purpose, parameters, and return values of public methods.

- **Avoid Commenting Out Code**
  - Do not leave commented-out code in your source files. If code is not needed, remove it to keep the codebase clean.
  - **Example**: Use version control to track changes rather than leaving obsolete code in the comments.

- **Be Concise and Clear**
  - Write comments that are clear and to the point. Avoid verbosity and ensure comments are easily understandable.
  - **Example**: Use clear language and avoid jargon or abbreviations that might be confusing.

- **Use TODO Comments Sparingly**
  - TODO comments should be used to indicate areas that need attention or future improvements. Follow up on these comments to address the noted issues.
  - **Example**:
    ```java
    //TODO-MdM these are not needed
    // We expect this to go away when we do the checkout model
    protected VersionInfo makeVersion() throws Exception {
        return null;
    }
    ```

## References
- [Clean Code: A Handbook of Agile Software Craftsmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)
- [GeeksforGeeks - Java Tutorial](https://www.geeksforgeeks.org/java/)
- [JavaTpoint - Java Tutorial](https://www.javatpoint.com/java-tutorial)
- [W3Schools - Java Tutorial](https://www.w3schools.com/java/)
