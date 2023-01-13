package fr.ikisource.oma.java17.patternmatchingforswitch;

public class ParenthesizedPattern {

    static double getDoubleValueUsingParenthesizedPatterns(Object o) {
        return switch (o) {
            case String s && s.length() > 0 && !(s.contains("#") || s.contains("@")) -> Double.parseDouble(s);
            default -> 0d;
        };
    }

    static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
    }
}
