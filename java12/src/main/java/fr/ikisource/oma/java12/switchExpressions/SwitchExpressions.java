package fr.ikisource.oma.java12.switchExpressions;

public class SwitchExpressions {

    public static void main(String[] args) {

        // the syntax for defining a switch statement looks similar to that of lambdas
        // the break keyword is not needed
        Month month = Month.APRIL;
        switch (month) {
            case FEBRUARY -> System.out.println(28);
            case APRIL -> System.out.println(30);
            case JUNE -> System.out.println(30);
            case SEPTEMBER -> System.out.println(30);
            case NOVEMBER -> System.out.println(30);
            default -> System.out.println(31);
        }

        // We can use multiple case labels as a comma-separated labels in a single switch label 👍
        month = Month.SEPTEMBER;
        switch (month) {
            case FEBRUARY -> System.out.println(28);
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> System.out.println(30);
            default -> System.out.println(31);
        }

        // The right-hand side of the arrow must be either an expression, a block, or a throws statement.
        switch (month) {
            case FEBRUARY -> {
                int days = 28;
            }
            case APRIL -> {
                int days = 30;
            }
        }

        //  use a switch statement as an expression
        // Before, this was only possible by defining the variable days as null and then assigning it a value inside the switch cases.
        // That meant that days couldn't be final, and could potentially be unassigned if we missed a case.
        month = Month.FEBRUARY;
        final var days = switch (month) {
            case FEBRUARY -> 28;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            default -> 31;
        };
        System.out.println("days = " + days);
    }

    enum Month {
        FEBRUARY,
        APRIL,
        JUNE,
        SEPTEMBER,
        NOVEMBER
    }
}
