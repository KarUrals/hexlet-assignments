package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {

        for (Map.Entry<String, String> map : storage.toMap().entrySet()) {
            String key = map.getValue();
            String value = map.getKey();

            storage.unset(value);
            storage.set(key, value);
        }
    }
}
// END
