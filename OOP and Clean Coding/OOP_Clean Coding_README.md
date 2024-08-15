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

### I. Abstract Class (0 to 100% Abstraction)

An abstract class in Java is a class that cannot be instantiated directly. It can have abstract methods (methods without an implementation), concrete methods (with an implementation), constructors, static methods, and final methods. Abstract classes provide partial abstraction by allowing some methods to be implemented while others are left to be defined by subclasses.

**Key Points:**
- We cannot instantiated an abstract class.
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

### II. Interface (100% Abstraction)

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

i. **Declare Variables as Private**: Make the data members (variables) of the class private to restrict direct access from outside the class.
ii. **Provide Public Getters and Setters**: Define public methods (getters and setters) to access and update the private variables. This allows controlled access to the data.

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

I. **Single Inheritance**
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

II. **Multilevel Inheritance**
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

III. **Hierarchy Inheritance**
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

IV. **Multiple Inheritance (via Interfaces)**
   - **Definition**: A class inherits from more than one superclass. Java does not support multiple inheritance directly with classes, but it can be achieved using interfaces.

   ![Multiple Inheritance](https://github.com/siddiqur-54/task_documentations/blob/main/images/OOP%20and%20Clean%20Coding/multiple%20inheritance.jpg)

   - **Why Java does not support multiple inheritance?**
   Java does not support multiple inheritance of classes primarily to avoid the complexity and ambiguity that can arise from it. Here are some key reasons:
   i. **Diamond Problem:** In multiple inheritance, a class can inherit from more than one class. This can lead to a situation known as the "diamond problem," where a class inherits from two classes that have a common ancestor. This creates ambiguity in determining which methods or attributes to inherit.
   ii. **Complexity and Ambiguity:** Multiple inheritance can make the inheritance hierarchy complex and hard to manage. It can create ambiguity in method resolution and increase the potential for conflicts.

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

V. **Hybrid Inheritance**
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

### I. Static or Compile-Time Polymorphism

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


### II. Dynamic or Run-Time Polymorphism

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
This Book is about good programming. It's about how to write good code, and how to transform bad code into good code.

### Why write bad code?

- Are you in a rush?
- Do you try to go "fast"?
- Do not you have time to do a good job?
- Are you tired of work in the same program/module?
- Does your Boss push you to finish soon?

The previous arguments could create a swamp of senseless code.

If you say "I will back to fix it later" you could fall in the [LeBlanc's law](https://en.wikipedia.org/wiki/Talk%3AList_of_eponymous_laws#Proposal_to_add_LeBlanc.27s_law) "Later equals never"

You are a professional and the code is your responsibility. Let's analyze the following anecdote:

> What if you were a doctor and had a patient who demanded that you stop all the silly hand-washing in preparation for surgery because it was taking too much time? Clearly the patient is the boss; and yet the doctor should absolutely refuse to comply. Why? Because the doctor knows more than the patient about the risks of disease and infection. It would be unprofessional (never mind criminal) for the doctor to comply with the patient.

So too it is unprofessional for programmers to bend to the will of managers who don’t understand the risks of making messes.

Maybe sometime you think in go fast to make the deadline. The only way to go fast is to keep the code as clean as possible at all times.

### What is Clean Code?

In his book Uncle Bob says the next:

> Consider this book a description of the Object Mentor School of Clean Code. The techniques and teachings within are the way that we practice our art. We are willing to claim that if you follow these teachings, you will enjoy the benefits that we have enjoyed, and you will learn to write code that is clean and professional. But don’t make the mistake of thinking that we are somehow “right” in any absolute sense. There are other schools and other masters that have just as much claim to professionalism as we. It would behoove you to learn from them as well.

### The boy Scout Rule

It’s not enough to write the code well. The code has to be kept clean over time. We have all seen code rot and degrade as time passes. So we must take an active role in preventing this degradation.

It's a good practice apply the [Boy Scout Rule](http://programmer.97things.oreilly.com/wiki/index.php/The_Boy_Scout_Rule)

> Always leave the campground cleaner than you found it.

## Chapter 2 - Meaningful Names

- Name should tell the intent
    - Why it exists, what it does and how to use it
    - E.g. good name would be studentMarkInfo vs sMark.

- Avoid confusing names
    - I.e. using names that already imply something!
    - E.g. naming something unix, studentList(even if it is not a list).

- Choose clear names
    - Say what you mean and mean what you say
    E.g. deleteItems over burstThemDown, kill over whack.

- Use good Distinctions
    - Use distinctions that make sense and thus don't just use numbers
    - E.g. list1, list2, instead - productIds, productDetails etc.
    E.g. using productInfo and productDetails - means same and distinction is harder.

- Use pronounceable names
    - Programming is a social activity
    - E.g. Don't use variable name as dob for dateofBirth.

- Usse searchable names
    - E.g. Don't name variable as "e", "z" etc, use - Event, maxStudents etc.

- Don't encode types in names
    - Remember containers of variable can change.
    - E.g. phoneString, paymentInt etc are bad names, payment can be made float in future and thus name also has to now change.

- Avoid prefix to names
    - E.g. m_description -> member_description (easier to understand)
    - E.g. IShapeFactorty to mean it is an interface, instead use ShapeFactory and ShapeFactoryImpl.

- Class names - nouns, Function names - verbs
    - E.g. Class names - student, car, vehicle etc.
    - E.g. Function names - postPayment, startEngine etc.

- Use names consistently
    - Pick one concept and stick to it.
    - E.g. controller everywhere vs manager and controller used interchangeably, driver and controller used in same place.

- Don't use same name to mean two different things!
    - E.g. payInfo to represent amount to pay and payInfo to also represent who to pay and bank info, best employeePyament and employeeBankDetails.

- Use Domain specific names
    - Remember your code is going to be read by computer engineers, helps them give context quickly.
    - E.g. accountVisitor (indicating visitor pattern), jobQueue - (indicating a queue), nameBuilder (indicating a builder).

- Avoid - Too long names!

## Chapter 3: Functions
- Write small functions
- Do only one thing in a function
  - Command and query separation
  - Avoid side effects
  - Avoid switch statements
- Use minimum arguments - at most 2
- DRY - do not repeat yourself
- Prefer exceptions over error codes

## Chapter 4 - Comments

#### Comments rules

1. Always try to **explain yourself in code**. If it's not possible, take your time to write a good comment.
2. Don't be redundant (e.g.: `i++; // increment i`).
3. Don't add obvious noise.
4. Don't use closing brace comments (e.g.: `} // end of function`).
5. **Don't comment out code**. Just remove.
6. Use as **explanation of intent**.
7. Use as **clarification of code**.
8. Use as **warning of consequences**.
   
#### When should you not use comments
- Comments should not explain code, the code should
- Change logs, Attributions
- Position marker comments, ending braces comments
- Not adding any new value - noise comments
- Mumblings or frustration in comments
- Misleading or wrong comments
- Confusing comments - Need a comment to explain comments!
- Private API - Comments or all function - no!
- Too much details in comments

#### When should you use comments?
Write software for humans to understand not for computers to execute.
- Public API's
- Reveal Intent
- Explain rationable behind decisions made
- To provide caution or amplify/ emphasis
- Explaining the "why" program works, "how" is for code to tell
- Difficult to understand code could use comments

## References
- [GeeksforGeeks - Java Tutorial](https://www.geeksforgeeks.org/java/)
- [JavaTpoint - Java Tutorial](https://www.javatpoint.com/java-tutorial)
- [W3Schools - Java Tutorial](https://www.w3schools.com/java/)
