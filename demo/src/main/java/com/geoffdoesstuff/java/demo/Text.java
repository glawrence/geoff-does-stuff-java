package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;
import com.geoffdoesstuff.java.utility.JavaSystemInfo;
import com.geoffdoesstuff.java.utility.TextUtilities;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
    The String object is immutable, or in other words, you can't change its contents. StringBuffer and StringBuilder
    are mutable, so can be changed, but they have differences:<br>
    - {@link java.lang.StringBuilder}: faster than StringBuffer but developer must ensure used from single thread (since Java 1.5)<br>
    - {@link java.lang.StringBuffer}: thread-safe but slow (since Java 1.0)<br>
    In Java 15 a new feature called Text Blocks was introduced, these are blocks of text spanning multiple lines but
    without the need for line concatenating. There were previews of a "templating" addition to text blocks, and it was
    in preview, but the preview functionality was removed in Java 23.
    String concatenation is best done with + in Java 9 and above as it has been optimised, so no need for
    StringBuilder or StringBuffer in that scenario. When joining strings elements from a list or an array then
    consider using String.join(), StringJoiner or Collectors.joining().
 */
public class Text {

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
     */
    private Text() {
    }

    /**
     * Main method, for independent running of demo, or use via MainDemoApp menu
     * @param args command line
     * @see com.geoffdoesstuff.java.MainDemoApp
     */
    public static void main(String[] args) {
        DemoUtilities.outputTitle("Using split", true);
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        String example = "this|is|pipe|delimited";
        String[] strings = example.split("\\|", 3);
        for (String item: strings) {
            System.out.println(item);
        }

        textSplitting();

        listTest();
        showTextBlock();

        DemoUtilities.outputTitle("Empty String Demo");
        testIfBlankOrEmpty(" Hello ");
        testIfBlankOrEmpty("  ");
        testIfBlankOrEmpty("\t\t\n\t");
        testIfBlankOrEmpty("");
        testIfBlankOrEmpty(null);

        javaVersionDemo();

        DemoUtilities.outputTitle("Message Format");
        demoMessageFormatClass();
    }

    private static void demoMessageFormatClass() {
        DemoUtilities.outputTitle("Using MessageFormat", true);
        String key = "key";
        String value = "value";
        String exampleFormatting = "[" + key + "=" + value +"]";
        System.out.println(exampleFormatting);
        exampleFormatting = "[%s=%s]".formatted("key", "value"); // Java 15 onwards
        System.out.println(exampleFormatting);
        exampleFormatting = String.format("[%s=%s]", key, value);
        System.out.println(exampleFormatting);
        // TemplatedString is coming in Java 21
        exampleFormatting = MessageFormat.format("[{0}={1}]", key, value);
        System.out.println(exampleFormatting);
        exampleFormatting = MessageFormat.format("The Date {0} and time {1}", LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalTime());
        System.out.println(exampleFormatting);

        DemoUtilities.outputTitle("Using MessageFormat with ChoiceFormat", true);
        MessageFormat format = new MessageFormat("REST call to \"{1}\" returned {0}");
        double[] countLimits = {0,1,2};
        String[] countParts = {"no warnings", "1 warning", "{0,number} warnings"};
        ChoiceFormat choiceFormat = new ChoiceFormat(countLimits, countParts);
        format.setFormatByArgumentIndex(0, choiceFormat);

        Object[] a = {0L, "Azure API"};
        System.out.println(format.format(a));
        Object[] b = {1L, "AWS API"};
        System.out.println(format.format(b));
        Object[] c = {2L, "GCP API"};
        System.out.println(format.format(c));

        DemoUtilities.outputTitle("Basic example of MessageFormat", true);
        System.out.println(TextUtilities.getNumberWithWord(0, "warning"));
        System.out.println(TextUtilities.getNumberWithWord(1, "warning"));
        System.out.println(TextUtilities.getNumberWithWord(2, "warning"));
        System.out.println("Search found " + TextUtilities.getNumberWithWord_alt(0, "file"));
        System.out.println("Search found " + TextUtilities.getNumberWithWord_alt(1, "file"));
        System.out.println("Search found " + TextUtilities.getNumberWithWord_alt(7, "file"));
    }

    private static void testIfBlankOrEmpty(String s) {
        boolean isBlank = TextUtilities.isNullOrBlank(s);
        boolean isEmpty = TextUtilities.isNullOrEmpty(s);
        System.out.printf("isNullOrBlank=%s, isNullOrEmpty=%s ", isBlank, isEmpty);
        if (Objects.isNull(s)) {
            System.out.printf("Null passed - [%s]%n", s);
        } else {
            if (s.trim().isEmpty()) {
                System.out.printf("Empty string - [%s]%n", s);
            } else {
                System.out.printf("String - [%s]%n", s);
            }
        }
    }

    /**
     * These were introduced in JDK 15.
     * Note the text cannot be on the same line as the opening """, and the location of the closing """ is important
     * putting it on a new line adds a new line to the end of the String.
     */
    private static void showTextBlock() {
        DemoUtilities.outputTitle("Text Block Demos");
        String textBlock = """
                Well, let's see how this looks
                It will be over two lines.""";
        System.out.println("[" + textBlock + "]");
        String textBlock2 = """
                Well, let's see how this looks
                It will be over two lines.
                """;
        System.out.println("[" + textBlock2 + "]");
        System.out.println("Is the location of the closing \"\"\" irrelevant? " + textBlock.equalsIgnoreCase(textBlock2));

        DemoUtilities.outputTitle("Single line", true);
        textBlock = """
                Well hello "Geoff", how are you?""";
        System.out.println("[" + textBlock + "]");

        DemoUtilities.outputTitle("HTML block", true);
        textBlock = """
                <html>
                    <body>
                        <p>Some text</p>
                    </body>
                </html>""";
        System.out.println("[" + textBlock + "]");

        DemoUtilities.outputTitle("HTML block (streaming lines)", true);
        textBlock.lines()
                .map(s -> "|" + s + "|")
                .forEach(System.out::println);

        DemoUtilities.outputTitle("SQL block", true);
        textBlock = """
                    SELECT "id", "value"
                    FROM data_table
                      WHERE "author" = 'John'
                """;
        System.out.println("[" + textBlock + "]");

        DemoUtilities.outputTitle("SQL block", true);
        textBlock = """
                    SELECT "id", "value" \
                    FROM data_table \
                      WHERE "author" = 'John' \
                """;
        System.out.println("[" + textBlock + "]");
    }

    private static void textSplitting() {
        List<String> messages = List.of("<@U02C1QCKGHG> nominate", "<@U02C1QCKGHG> nominate <@U02C1NCVOGH>", "<@U02C1QCKGHG> nominate ", "<@U02C1QCKGHG> decline random", " <@U02C1QCKGHG>   nominate ", " <@>   nominate <@a> ");

        DemoUtilities.outputTitle("Text Splitting", true);
        messages.forEach(message -> {
            System.out.println("Message: '" + message + "'");
            String[] items = message.split(" ");
            for (String item : items) {
                System.out.println("  [" + item + "]");
            }
        });
        DemoUtilities.outputTitle("Text Splitting (whitespace) - print", true);
        messages.forEach(message -> {
            System.out.println("Message: '" + message + "'");
            Arrays.stream(message.split("\\s+"))
                    .filter(item -> !(item.isEmpty()))
                    .filter(item -> !(item.matches("^(<@).*(>)")))
                    .forEach(msg -> System.out.println("  [" + msg + "]"));
        });
        DemoUtilities.outputTitle("Text Splitting (whitespace) - to list", true);
        messages.forEach(message -> {
            System.out.println("Message: '" + message + "'");
            List<String> words = Arrays.stream(message.split("\\s+"))
                    .filter(item -> !(item.isEmpty()))
                    .filter(item -> !(item.matches("^(<@).*(>)$")))
                    .toList();
            System.out.println("  " + words);
        });
        DemoUtilities.outputTitle("Text Splitting (whitespace) - to list", true);
        messages.forEach(message -> {
            System.out.println("Message: '" + message + "'");
            List<String> words = Arrays.stream(message.split("\\s+"))
                    .filter(item -> !(item.isEmpty()))
                    .filter(item -> (item.matches("^(<@).+(>)$")))
                    .map(item -> item.substring(2, item.length() - 1).toLowerCase())
                    .toList();
            System.out.println("  " + words);
        });
    }

    private static void listTest() {
        DemoUtilities.outputTitle("List Test");
        List<String> KEYWORDS = List.of("CREATE", "DELETE", "INSERT");
        System.out.println("Keywords: " + KEYWORDS);
        List<String> input = new ArrayList<>(List.of("Hello", "World", "CREATE"));
        System.out.println("Input: " + input);
        boolean match = doesWordListContainKeywords(input, KEYWORDS);
        System.out.println("  " + match);
//        System.out.println("Input: " + input);

        input = new ArrayList<>(List.of("Hello", "World", "INSERT"));
        System.out.println("Input: " + input);
        match = doesWordListContainKeywords(input, KEYWORDS);
        System.out.println("  " + match);

        input = new ArrayList<>(List.of("Hello", "World"));
        System.out.println("Input: " + input);
        match = doesWordListContainKeywords(input, KEYWORDS);
        System.out.println("  " + match);

        input = new ArrayList<>(List.of("Hello", "create", "World"));
        System.out.println("Input: " + input);
        match = doesWordListContainKeywords(input, KEYWORDS);
        System.out.println("  " + match);
    }

    private static boolean doesWordListContainKeywords(List<String> words, List<String> keywords) {
        List<String> wordsCopy = new ArrayList<>(words);
        wordsCopy.retainAll(keywords);
        System.out.println("  - matches " + wordsCopy);
        return !wordsCopy.isEmpty();
    }

    private static void javaVersionDemo() {
        DemoUtilities.outputTitle("Java Version Information", true);
        JavaSystemInfo.javaVersionDemo();
    }
}
