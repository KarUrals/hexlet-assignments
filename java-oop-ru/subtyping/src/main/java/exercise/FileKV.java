package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private String filePath;

    public FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        Utils.writeFile(this.filePath, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> chengedMap = toMap();
        chengedMap.put(key, value);
        Utils.writeFile(this.filePath, Utils.serialize(chengedMap));
    }

    @Override
    public void unset(String key) {
        Map<String, String> chengedMap = toMap();
        chengedMap.remove(key);
        Utils.writeFile(this.filePath, Utils.serialize(chengedMap));
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
