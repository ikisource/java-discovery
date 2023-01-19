package fr.ikisource.oma.java16.patternMatchingForInstanceof;

public class PatternMatchingForInstanceof {

    public static void main(String[] args) {

        // Pattern matching for the instanceof keyword has been added as of Java 16.
        // Previously we might write code like this:
        Object obj = "TEST";

        if (obj instanceof String) {
            String t = (String) obj;
            System.out.println("t = " + t);
        }

        // Instead of purely focusing on the logic needed for the application, this code must first check the instance of obj, then cast the object to a String and assign it to a new variable t.
        // With the introduction of pattern matching, we can re-write this code:
        if (obj instanceof String t) {
            System.out.println("t = " + t);
        }
        // We can now declare a variable – in this instance t – as part of the instanceof check.
    }
}