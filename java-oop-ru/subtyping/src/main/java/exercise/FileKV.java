package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private String filePath;
    private Map<String, String> map;

    public FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        this.map = new HashMap<>(map);
        Utils.writeFile(this.filePath, Utils.serialize(this.map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> chengedMap = toMap();
        chengedMap.put(key, value);
        this.map = new HashMap<>(chengedMap);
        Utils.writeFile(this.filePath, Utils.serialize(this.map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> chengedMap = toMap();
        chengedMap.remove(key);
        this.map = new HashMap<>(chengedMap);
        Utils.writeFile(this.filePath, Utils.serialize(this.map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = toMap();

        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(this.filePath));
    }
}
// END
