import java.util.HashMap;
import java.util.Map;

// Prototype Interface
interface Prototype {
    Prototype clone();
}

// ConcretePrototype1: Circle
class Circle implements Prototype {
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

// ConcretePrototype2: Rectangle
class Rectangle implements Prototype {
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

// PrototypeRegistry
class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    public void addPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

// Main method: Client Code
public class PrototypePattern {
    public static void main(String[] args) {
        // Creating prototypes
        Circle circlePrototype = new Circle(5, "Red");
        Rectangle rectanglePrototype = new Rectangle(10, 20, "Blue");

        // Adding prototypes to the registry
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.addPrototype("Large Red Circle", circlePrototype);
        registry.addPrototype("Large Blue Rectangle", rectanglePrototype);

        // Cloning prototypes
        Circle clonedCircle = (Circle) registry.getPrototype("Large Red Circle");
        Rectangle clonedRectangle = (Rectangle) registry.getPrototype("Large Blue Rectangle");

        // Displaying cloned objects
        System.out.println(clonedCircle);  // Output: Circle{radius=5, color='Red'}
        System.out.println(clonedRectangle);  // Output: Rectangle{width=10, height=20, color='Blue'}

        // Modifying cloned objects without affecting the originals
        clonedCircle = new Circle(7, "Green");
        System.out.println(clonedCircle);  // Output: Circle{radius=7, color='Green'}

        // Original remains unchanged
        System.out.println(registry.getPrototype("Large Red Circle"));  // Output: Circle{radius=5, color='Red'}
    }
}
