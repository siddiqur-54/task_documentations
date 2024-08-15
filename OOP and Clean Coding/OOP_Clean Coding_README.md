# Object-Oriented Programming (OOP)

## Introduction
Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data and code. The data is in the form of fields (often known as attributes or properties), and the code is in the form of procedures (often known as methods). OOP provides a clear modular structure for programs and allows for code reuse through inheritance.

![OOP Concepts](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/Object-Oriented-Programming-Concepts.jpg)

## 1. Class and Object

### 1. Class

- **Definition**: A class is a blueprint or prototype from which objects are created. It defines the structure and behavior that the objects will have.

- **Components**:
  - **Attributes**: Define the properties or state of the class (e.g., `brand`, `model`, `year`).
  - **Methods**: Define the behaviors or actions that objects of the class can perform (e.g., `startEngine()`).

- **Encapsulation**: Classes encapsulate data and methods, providing a modular structure to the code and hiding internal details.

- **Example**:
    ```java
    public class Car {
        // Attributes
        String brand;
        String model;
        int year;
  
        // Method
        void startEngine() {
            System.out.println("Engine started!");
        }
    }
    ```

### 2. Object

- **Definition**: An object is an instance of a class. It represents a concrete entity with state and behavior.

- **Creation**: Memory is allocated for an object when it is instantiated from a class.

- **Attributes and Methods**:
  - **Attributes**: Store the current state or values of the object (e.g., `myCar.brand`, `myCar.model`).
  - **Methods**: Perform actions on the object's data or define the object’s behavior (e.g., `myCar.startEngine()`).

- **Example**:
    ```java
    public class Main {
        public static void main(String[] args) {
            // Creating an object of class Car
            Car myCar = new Car();
            myCar.brand = "Toyota";
            myCar.model = "Corolla";
            myCar.year = 2020;
  
            // Calling a method on the object
            myCar.startEngine();
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

### 1. Abstract Class (0 to 100% Abstraction)

An abstract class in Java is a class that cannot be instantiated directly. It can have abstract methods (methods without an implementation), concrete methods (with an implementation), constructors, static methods, and final methods. Abstract classes provide partial abstraction by allowing some methods to be implemented while others are left to be defined by subclasses.

**Key Points:**
- An abstract class can have both abstract and concrete methods.
- It can have constructors, which can be called by its subclasses.

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

    // Subclass (inherited from Vehicle)
    class Car extends Vehicle {
        // Providing implementation for abstract method
        @Override
        void start() {
            System.out.println("Car started.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            // Create an object of the subclass
            Car myCar = new Car();
            myCar.start();  // Output: Car started.
            myCar.stop();   // Output: Vehicle stopped.
        }
    }
    ```

### 2. Interface (100% Abstraction)

In Java, an interface is a reference type that defines a contract for what methods a class should implement. Interfaces achieve 100% abstraction by declaring methods without providing implementations. This allows you to define what functionality a class should offer while hiding the implementation details.

**Key Points:**
- **Method Declaration**: Interfaces declare abstract methods without providing method bodies. This ensures that any class implementing the interface must provide implementations for these methods.
- **Implementation**: Classes that implement an interface must provide concrete implementations for all of its methods. This enforces a contract that the class must adhere to.
- **Abstraction**: Interfaces provide complete abstraction by exposing only method signatures and hiding implementation details. This allows users to interact with objects at a high level of abstraction.

- **Example**:
    ```java
    // Interface definition
    interface Drivable {
        void accelerate();
        void brake();
    }

    // Implementing the interface
    class Car implements Drivable {
        @Override
        public void accelerate() {
            System.out.println("Car is accelerating.");
        }

        @Override
        public void brake() {
            System.out.println("Car is braking.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Drivable myCar = new Car(); // Creating an instance of Car
            myCar.accelerate();        // Output: Car is accelerating.
            myCar.brake();             // Output: Car is braking.
        }
    }
    ```

## 3. Encapsulation
Encapsulation is a core principle of Object-Oriented Programming (OOP) that involves bundling the data (attributes) and methods (functions) that operate on the data into a single unit, typically a class. It restricts direct access to some of the object's components, which can prevent the accidental modification of data and ensure a controlled way of accessing and updating it.

**Real-Life Example:**
- Consider a capsule that contains a mix of several medicines. The individual medicines are hidden within the capsule, and the user interacts only with the external form of the capsule without knowing the specifics of the contents. Similarly, encapsulation in programming hides the internal details and exposes only necessary information through a controlled interface.

### Achieving Encapsulation in Java

To achieve encapsulation in Java, follow these steps:

1. **Declare Variables as Private**: Make the data members (variables) of the class private to restrict direct access from outside the class.
2. **Provide Public Getters and Setters**: Define public methods (getters and setters) to access and update the private variables. This allows controlled access to the data.

**Advantages of Encapsulation:**
- **Control Over Data**: You can add logic to setter methods to validate data before setting it, ensuring that only valid data is stored.
- **Data Hiding**: By making data members private, you hide the internal state of the object from the outside world, protecting it from unintended interference.
- **Easy to Test**: Encapsulated classes can be easily tested in isolation, as their data is controlled through well-defined methods.

- **Example:**

    ```java
    // Encapsulated class
    public class Person {
        // Private variables
        private String name;
        private int age;

        // Public constructor
        public Person(String name, int age) {
            this.name = name;
            setAge(age); // Using setter to enforce validation
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Setter for name
        public void setName(String name) {
            this.name = name;
        }

        // Getter for age
        public int getAge() {
            return age;
        }

        // Setter for age with validation
        public void setAge(int age) {
            if (age > 0) { // Validation logic
                this.age = age;
            } else {
                System.out.println("Age must be positive.");
            }
        }
    }

    public class Main {
        public static void main(String[] args) {
            // Creating an object of the Person class
            Person person = new Person("John Doe", 30);
            
            // Accessing and modifying data through getters and setters
            System.out.println("Name: " + person.getName()); // Output: Name: John Doe
            System.out.println("Age: " + person.getAge());   // Output: Age: 30
            
            // Updating age
            person.setAge(35);
            System.out.println("Updated Age: " + person.getAge()); // Output: Updated Age: 35
            
            // Attempting to set an invalid age
            person.setAge(-5); // Output: Age must be positive.
        }
    }
    ```


## 4. Inheritance

Inheritance is a key concept in Object-Oriented Programming (OOP) where one class (subclass or derived class) inherits the properties and methods of another class (superclass or base class). This mechanism allows for code reuse and establishes an "is-a" relationship between the classes. In Java, inheritance is implemented using the `extends` keyword.

**Real-Life Example:**
- Consider the hierarchy of celestial bodies: The planet Earth and Mars inherit properties from the superclass Solar System, and the Solar System inherits from the Milky Way Galaxy. Thus, the Milky Way Galaxy is the top-level superclass for Solar System, Earth, and Mars.

#### Types of Inheritance in Java

1. **Single Inheritance**
   - **Definition**: A subclass inherits from a single superclass.

   ![Single Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/single%20inheritance.jpg)

   - **Example**:
     ```java
     // Superclass
     class Animal {
         void eat() {
             System.out.println("This animal eats food.");
         }
     }

     // Subclass
     class Dog extends Animal {
         void bark() {
             System.out.println("The dog barks.");
         }
     }

     public class Main {
         public static void main(String[] args) {
             Dog myDog = new Dog();
             myDog.eat();  // Output: This animal eats food.
             myDog.bark(); // Output: The dog barks.
         }
     }
     ```

2. **Multilevel Inheritance**
   - **Definition**: A subclass inherits from another subclass, forming a chain of inheritance.

   ![Multilevel Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/multilevel%20inheritance.jpg)

   - **Example**:
     ```java
     // Base class
     class Animal {
         void eat() {
             System.out.println("This animal eats food.");
         }
     }

     // Intermediate class
     class Mammal extends Animal {
         void walk() {
             System.out.println("This mammal walks.");
         }
     }

     // Subclass
     class Dog extends Mammal {
         void bark() {
             System.out.println("The dog barks.");
         }
     }

     public class Main {
         public static void main(String[] args) {
             Dog myDog = new Dog();
             myDog.eat();  // Output: This animal eats food.
             myDog.walk(); // Output: This mammal walks.
             myDog.bark(); // Output: The dog barks.
         }
     }
     ```

3. **Hierarchy Inheritance**
   - **Definition**: Multiple subclasses inherit from a single superclass.

   ![Hierarchy Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/hierarchical%20inheritance.jpg)

   - **Example**:
     ```java
     // Superclass
     class Animal {
         void eat() {
             System.out.println("This animal eats food.");
         }
     }

     // Subclass 1
     class Dog extends Animal {
         void bark() {
             System.out.println("The dog barks.");
         }
     }

     // Subclass 2
     class Cat extends Animal {
         void meow() {
             System.out.println("The cat meows.");
         }
     }

     public class Main {
         public static void main(String[] args) {
             Dog myDog = new Dog();
             myDog.eat();  // Output: This animal eats food.
             myDog.bark(); // Output: The dog barks.

             Cat myCat = new Cat();
             myCat.eat();  // Output: This animal eats food.
             myCat.meow(); // Output: The cat meows.
         }
     }
     ```

4. **Multiple Inheritance (via Interfaces)**
   - **Definition**: A class inherits from more than one superclass. Java does not support multiple inheritance directly with classes, but it can be achieved using interfaces.

   ![Multiple Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/multiple%20inheritance.jpg)

   - **Why Java does not support multiple inheritance?**
   Java does not support multiple inheritance of classes primarily to avoid the complexity and ambiguity that can arise from it. Here are some key reasons:
   1. **Diamond Problem:** In multiple inheritance, a class can inherit from more than one class. This can lead to a situation known as the "diamond problem," where a class inherits from two classes that have a common ancestor. This creates ambiguity in determining which methods or attributes to inherit.
   2. Complexity and Ambiguity: Multiple inheritance can make the inheritance hierarchy complex and hard to manage. It can create ambiguity in method resolution and increase the potential for conflicts.

   ![Diamond Problem](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/diamond%20problem.png)

   - **Example**:
        ```java
        class A {
        void method() {}
        }

        class B extends A {
            void method() {}
        }

        class C extends A {
            void method() {}
        }

        class D extends B, C {  // Error: Java does not support this
            // Which method() should be inherited?
        }
        ```

    - **Achieving multiple inheritance using Interface**
        ```java
        // First interface
        interface CanRun {
            void run();
        }

        // Second interface
        interface CanJump {
            void jump();
        }

        // Class implementing multiple interfaces
        class Athlete implements CanRun, CanJump {
            @Override
            public void run() {
                System.out.println("The athlete runs quickly.");
            }

            @Override
            public void jump() {
                System.out.println("The athlete jumps high.");
            }
        }

        public class Main {
            public static void main(String[] args) {
                Athlete athlete = new Athlete();
                athlete.run();  // Output: The athlete runs quickly.
                athlete.jump(); // Output: The athlete jumps high.
            }
        }
        ```

5. **Hybrid Inheritance**
   - **Definition**: A combination of multiple and hierarchical inheritance. Java does not support hybrid inheritance directly with classes, but it can be managed using interfaces.

   ![Hybrid Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/hybrid%20inheritance.jpg)

   - **Example**:
     ```java
     // Base class
     class Animal {
         void eat() {
             System.out.println("This animal eats food.");
         }
     }

     // Intermediate class 1
     class Mammal extends Animal {
         void walk() {
             System.out.println("This mammal walks.");
         }
     }

     // Intermediate class 2
     class Bird extends Animal {
         void fly() {
             System.out.println("This bird flies.");
         }
     }

     // Class implementing hybrid inheritance via interfaces
     class Bat extends Mammal implements Flyable {
         @Override
         public void fly() {
             System.out.println("The bat flies.");
         }
     }

     public class Main {
         public static void main(String[] args) {
             Bat myBat = new Bat();
             myBat.eat();  // Output: This animal eats food.
             myBat.walk(); // Output: This mammal walks.
             myBat.fly();  // Output: The bat flies.
         }
     }
     ```

## 5. Polymorphism in Java

Polymorphism is a fundamental concept in Object-Oriented Programming (OOP) that allows objects to be treated as instances of their parent class rather than their actual class. The term "polymorphism" comes from the Greek words "poly" (many) and "morph" (forms), meaning "many forms."

Polymorphism enhances flexibility and reusability in programming by allowing one interface to be used for a general class of actions, with the specific action determined by the actual object type at runtime. In Java, polymorphism is achieved through two main mechanisms: method overloading (static polymorphism) and method overriding (dynamic polymorphism).

### 1. Static or Compile-Time Polymorphism

Static polymorphism, also known as compile-time polymorphism, is achieved through method overloading. Method overloading occurs when multiple methods in a class have the same name but different parameter lists (i.e., different number or types of parameters). The compiler determines which method to call based on the method signature.

**Key Points:**
- Method overloading allows methods to have the same name but different parameter lists.
- The return type alone is not sufficient to differentiate overloaded methods.

- **Example**:
     ```java
     public class Printer {
    // Method to print an integer
    void print(int i) {
        System.out.println("Printing integer: " + i);
    }

    // Method to print a string
    void print(String s) {
        System.out.println("Printing string: " + s);
    }

    // Method to print a double
    void print(double d) {
        System.out.println("Printing double: " + d);
    }
    }

    public class Main {
        public static void main(String[] args) {
            Printer printer = new Printer();
            printer.print(100);       // Output: Printing integer: 100
            printer.print("Hello");   // Output: Printing string: Hello
            printer.print(99.99);     // Output: Printing double: 99.99
        }
    }
    ```


### 2. Dynamic or Run-Time Polymorphism

Dynamic polymorphism, also known as run-time polymorphism, is achieved through method overriding. Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. At runtime, the Java Virtual Machine (JVM) determines the actual object's type and invokes the appropriate overridden method.

**Key Points:**
- Method overriding allows a subclass to provide a specific implementation for a method that is already defined in its superclass.
- The method in the subclass must have the same name, return type, and parameter list as the method in the superclass.
- The actual method that gets executed is determined at runtime based on the object's actual type, not the reference type.

- **Example**:
     ```java
     // Superclass
    class Animal {
        void makeSound() {
            System.out.println("Animal makes a sound");
        }
    }

    // Subclass
    class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Dog barks");
        }
    }

    // Another subclass
    class Cat extends Animal {
        @Override
        void makeSound() {
            System.out.println("Cat meows");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Animal myAnimal;

            // Creating an object of Dog
            myAnimal = new Dog();
            myAnimal.makeSound();  // Output: Dog barks

            // Creating an object of Cat
            myAnimal = new Cat();
            myAnimal.makeSound();  // Output: Cat meows
        }
    }
    ```
## 6. Association, Aggregation and Composition
Association, Aggregation and Composition in Java describe how instances of classes relate to each other.

![Association, Aggregation and Composition](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/Association.png)

### 1. Association
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

### 1. Aggregation
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

### 1. Composition
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


## References
- [GeeksforGeeks - Java Tutorial](https://www.geeksforgeeks.org/java/)
- [JavaTpoint - Java Tutorial](https://www.javatpoint.com/java-tutorial)
- [W3Schools - Java Tutorial](https://www.w3schools.com/java/)
