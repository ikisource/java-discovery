package fr.ikisource.oma.java17.patternmatchingforswitch;

public class OrderingSubClasses {

    /*static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case CharSequence c -> Double.parseDouble(c.toString());
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
    }*/

    // Erreur de compilation :
    // Label is dominated by a preceding case label 'CharSequence c'
}
