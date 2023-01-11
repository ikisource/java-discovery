package fr.ikisource.oma.java14.record;

public class Main {

    public static void m1() {
        var range = new Range(0, 10);
        System.out.println("range = " + range);

        // accesseur
        int begin = range.begin();
        System.out.println("begin = " + begin);
    }

    public static void main(String[] args) {
        m1();
    }
}
