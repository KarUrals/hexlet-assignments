package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage{
    private Map<String, String> map;

    public InMemoryKV(Map<String, String> map) {
        this.map = new HashMap<>(map);
    }


    @Override
    public void set(String key, String value) {
        Map<String, String> chengedMap = toMap();
        chengedMap.put(key, value);

        this.map = chengedMap;
    }

    @Override
    public void unset(String key) {
        Map<String, String> chengedMap = toMap();
        chengedMap.remove(key);

        this.map = chengedMap;
    }

    @Override
    public String get(String key, String defaultValue) {
//        return this.map.containsKey(key) ? this.map.get(key) : defaultValue;
        return this.map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(map);
    }
}
// END
