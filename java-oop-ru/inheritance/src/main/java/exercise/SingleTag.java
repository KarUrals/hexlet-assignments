package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<")
                .append(name)
                .append(attributesToString(attributes))
                .append(">");

        return result.toString();
    }
}
// END
