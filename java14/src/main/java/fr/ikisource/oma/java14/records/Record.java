package fr.ikisource.oma.java14.records;

/**
 * A new keyword : record
 */
public class Record {

    enum DAY {MONDAY, TUESDAY}

    // This simple declaration will automatically add a constructor, getters, equals, hashCode and toString methods for us.
    public record User(int id, String password) {

    }
    public static void main(String[] args) {

        User user = new User(1, "pwd1");
        System.out.println("user = " + user);

        System.out.println("user id = " + user.id());
    }

}
