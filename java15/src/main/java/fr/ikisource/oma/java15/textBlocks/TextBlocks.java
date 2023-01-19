package fr.ikisource.oma.java15.textBlocks;

public class TextBlocks {

    public static void main(String[] args) {

        String text = """
                {
                    "name" : "Foo", \
                    "surname" : "Buu" \s
                }
                """;
        System.out.println("text = " + text);
    }
}
