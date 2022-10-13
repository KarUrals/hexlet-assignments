package exercise;

import com.sun.jdi.event.StepEvent;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> result = new TreeMap<>();

        Set<String> keysOfTwoMaps = new HashSet<>();
        keysOfTwoMaps.addAll(data1.keySet());
        keysOfTwoMaps.addAll(data2.keySet());

        Object value1, value2;
        for (String key: keysOfTwoMaps) {
            value1 = data1.get(key);
            value2 = data2.get(key);
            if (value1 == null && value2 != null) {
                result.put(key, "added");
            }
            if (value2 == null && value1 != null) {
                result.put(key, "deleted");
            }
            if (value1 != null && value2 != null && Objects.equals(value1, value2)) {
                result.put(key, "unchanged");
            }
            if (value1 != null && value2 != null && value1 != value2) {
                result.put(key, "changed");
            }
        }

        return result;
    }
}
//END
