package fr.ikisource.oma.java17.recordpattern;

public class GuardedPattern {

    static double getDoubleValueUsingIf(Object o) {
        return switch (o) {
            case String s -> {
                if (s.length() > 0) {
                    yield Double.parseDouble(s);
                } else {
                    yield 0d;
                }
            }
            default -> 0d;
        };
    }

    static double getDoubleValueUsingGuardedPatterns(Object o) {
        return switch (o) {
            case String s && s.length() > 0 -> Double.parseDouble(s);
            default -> 0d;
        };
    }

}
