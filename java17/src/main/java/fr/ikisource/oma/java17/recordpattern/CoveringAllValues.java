package fr.ikisource.oma.java17.recordpattern;

public class CoveringAllValues {

    /*static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case String s -> Double.parseDouble(s);
        };
    }*/

    // Erreur de compilation :
    // switch' expression does not cover all possible input values
}