package fr.ikisource.oma.java15.textBlocks;

import java.util.List;

public class TextBlocks {

    public static void printString() {

        String s = """
                "one"
                'two'
                three
                four
                five
                """;
        System.out.println(s);
    }

    public static void printIdentedString() {

        // l'indentation est prise en compte à partir de la ligne verticale verte sous IntelliJ 🐸
        String s = """
                "one"
                    'two'
                three
                    four
                five
                """;
        System.out.println(s);
    }

    public static void printCharsAfter() {

        String s = """
                "one"        \s
                    'two'    \s
                three        \s
                    four     \s
                five         \s
                """;
        List<String> lines = s.lines().map(string -> "|" + string + "|").toList();
        lines.forEach(System.out::println);
    }

    public static void main(String[] args) {

        TextBlocks.printString();
        TextBlocks.printIdentedString();
        TextBlocks.printCharsAfter();
    }
}
