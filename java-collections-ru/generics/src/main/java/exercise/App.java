package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> filteredBookList = new ArrayList<>();

        for (Map<String, String> book: books) {
            int count = 0;
            for (Map.Entry<String, String> pair: where.entrySet()) {
                String key = pair.getKey();
                String value = pair.getValue();
                if (book.containsKey(key) && book.get(key).equals(value)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == where.size()) {
                filteredBookList.add(book);
            }
        }
        return filteredBookList;
    }
}
//END
