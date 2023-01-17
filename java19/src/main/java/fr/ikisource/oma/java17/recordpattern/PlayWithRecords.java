package fr.ikisource.oma.java17.recordpattern;

public class PlayWithRecords {

    public static void main(String[] args) {

    }

    public int area(Shape shape) {

        if (shape instanceof Rectangle(int width, int height)) { // déconstruire un record
            return width * height; // width et height deviennent des bindings variables
        }
        return 0;
    }

    /* not ready ?
    public void patternMatchingOnArray() {
        // pattern matching sur les tableaux
        Object o = new String[]{"A", "B"};
        if (o instanceof String[] {String s1, String s2} ) {
            System.out.println("concat = " + s1 + s2);
        }
    }*/
}
