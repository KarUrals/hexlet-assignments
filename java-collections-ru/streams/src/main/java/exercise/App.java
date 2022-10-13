package exercise;

import org.apache.commons.lang3.StringUtils;
import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emailsList) {
        String[] freeSuffixes = {"gmail.com", "yandex.ru", "hotmail.com"};
        long count = emailsList.stream()
                .filter(str -> StringUtils.endsWithAny(str, freeSuffixes))
                .count();

        return count;
    }
}
// END
