package fr.ikisource.oma.java13.switchExpressions;

/**
 * Adding a new yield statement
 * Using yield, we can now effectively return values from a switch expression:
 */
public class SwitchExpressions {

    public static void main(String[] args) {

        var me = 4;
        var operation = "squareMe";
        var result = switch (operation) {
            case "doubleMe" -> {
                yield me * 2;
            }
            case "squareMe" -> {
                yield me * me;
            }
            default -> me;
        };
        System.out.println("result = " + result);
    }
}
