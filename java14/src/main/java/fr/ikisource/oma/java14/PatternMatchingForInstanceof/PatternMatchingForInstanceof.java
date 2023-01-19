package fr.ikisource.oma.java14.PatternMatchingForInstanceof;

public class PatternMatchingForInstanceof {
    public static void main(String[] args) {

        Shape shape = new Rectangle();
        if (shape instanceof Rectangle rectangle) {
            System.out.println("rectangle");
        } else if (shape instanceof Square) {
            System.out.println("square");
        }
    }
}
