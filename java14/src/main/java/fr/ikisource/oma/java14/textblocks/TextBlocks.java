package fr.ikisource.oma.java14.textblocks;

import java.util.List;

/**
 * in their second preview, text blocks now have two new escape sequences:
 * \: to indicate the end of the line, so that a new line character is not introduced
 * \s: to indicate a single space
 */
public class TextBlocks {

    public static void main(String[] args) {

        String multiline = "A quick brown fox jumps over a lazy dog; the lazy dog howls loudly.";

        multiline = """
                A quick brown fox jumps over a lazy dog; \
                the lazy dog howls loudly.
                """;
        System.out.println("multiline = " + multiline);
    }
}
