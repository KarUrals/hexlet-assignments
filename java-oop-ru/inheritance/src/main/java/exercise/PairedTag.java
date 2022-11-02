package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag{
    private final String body;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<")
                .append(name)
                .append(attributesToString(attributes))
                .append(">")
                .append(body);

        for (Tag tag : children) {
            result.append(tag.toString());
        }

        result.append("</")
                .append(name)
                .append(">");

        return result.toString();
    }
}
// END
