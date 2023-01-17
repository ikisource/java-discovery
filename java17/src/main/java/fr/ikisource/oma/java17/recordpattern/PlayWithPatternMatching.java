package fr.ikisource.oma.java17.recordpattern;

public class PlayWithPatternMatching {

    public static void main(String[] args) {
    }

    public int area(Shape shape) {

        /* 
        if (shape instanceof Square square) {
            return square.edge() * square.edge();
        } else if (shape instanceof Rectangle rectangle) {
            return rectangle.width() * rectangle.height();
        }
        return 0;
        */
        return switch (shape) {
            case Square square -> square.edge() * square.edge();
            case Rectangle rectangle -> rectangle.width() * rectangle.height();
            case Circle circle -> circle.radius() * circle.radius() * 314;
        };
    }
}
