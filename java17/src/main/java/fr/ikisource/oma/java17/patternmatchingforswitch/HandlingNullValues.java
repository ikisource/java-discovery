package fr.ikisource.oma.java17.patternmatchingforswitch;

public class HandlingNullValues {

    static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case String s -> Double.parseDouble(s);
            case null -> 0d;
            default -> 0d;
        };
    }
}
