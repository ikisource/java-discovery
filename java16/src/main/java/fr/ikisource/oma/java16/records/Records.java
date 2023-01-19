package fr.ikisource.oma.java16.records;

/**
 * With the release of Java 16, we can now define records as class members of inner classes
 */
public class Records {

    class InnerClass {

        static Book book = new Book("Title", "author", "isbn");
    }

    public static void main(String[] args) {
        System.out.println("book = " + InnerClass.book.toString());
    }
}
