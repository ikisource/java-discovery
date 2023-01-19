package fr.ikisource.oma.java15.records;

/**
 * Java 15 allow us to override some of the default behaviors.
 * For example, we could define a canonical constructor that does some validation:
 */
public class Records {

    public record Person(String name, int age) {

        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
    }

    public static void main(String[] args) {

        Person person = new Person("toto", -1);
    }

}
