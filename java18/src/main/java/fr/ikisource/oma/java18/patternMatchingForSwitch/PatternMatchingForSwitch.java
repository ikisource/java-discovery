package fr.ikisource.oma.java18.patternMatchingForSwitch;

/**
 * - Dominance checking now forces a constant case label to appear before a guarded pattern of the same type
 * - Exhaustiveness checking of switch blocks is now more precise with sealed hierarchies where the permitted direct subclass
 * only extends an instantiation of the (generic) sealed superclass
 */
public class PatternMatchingForSwitch {

    public static void main(String[] args) {

        // Dominance checking
        Object o = new Object();
        switch (o) {
            case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
/*            case String s ->    // Error : Label is dominated by a preceding case label 'CharSequence cs'
                    System.out.println("A string: " + s);
 */
            default -> {
                break;
            }
        }

        // Exhaustiveness checking
        Vehicle vehicle = new Car(5, "i10");
        switch (vehicle) {
            case Car car -> System.out.println("vehicle is a car");
            default -> throw new IllegalStateException("Unexpected value: " + vehicle);
        }

        // All the permitted classes defined by the sealed class Vehicle are listed
        vehicle = new Truck(2, "camion");
        switch (vehicle) {
            case Car car -> System.out.println("vehicle is a car");
            case Truck truck -> {
                System.out.println("vehicle is a truck");
            }
        }
    }
}
