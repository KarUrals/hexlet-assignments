package exercise;

import java.sql.Array;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(str -> Arrays.stream(str)
                        .flatMap(element -> Stream.of(element, element))
                        .toArray(String[]::new))
                .flatMap(newStr -> Stream.of(newStr, newStr))
                .toArray(String[][]::new);
    }
}
// END
