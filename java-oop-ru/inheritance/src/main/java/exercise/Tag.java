package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected final String name;
    protected final Map<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public abstract String toString();

    public static String attributesToString(Map<String, String> attributes) {

        return attributes.entrySet().stream()
                .map(entry -> String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(""));
    }
}
// END
