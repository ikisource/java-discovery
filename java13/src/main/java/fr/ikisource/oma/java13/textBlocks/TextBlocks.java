package fr.ikisource.oma.java13.textBlocks;

/**
 * multi-line Strings such as embedded JSON, XML, HTML
 * Also, java.lang.String now has three new methods to manipulate text blocks:
 * - stripIndent() – mimics the compiler to remove incidental white space
 * - translateEscapes() – translates escape sequences such as “\\t” to “\t”
 * - formatted() – works the same as String::format, but for text blocks
 */
public class TextBlocks {

    public static void main(String[] args) {

        String json
                = "{\r\n" + "\"name\" : \"Baeldung\",\r\n" + "\"website\" : \"https://www.%s.com/\"\r\n" + "}";
        // can be more readable
        String textBlocksJson = """
                {
                    "name" : "Baeldung",
                    "website" : "https://www.%s.com/"
                }
                """;
        System.out.println("textBlocksJson = " + textBlocksJson);
    }
}
