package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

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
        String value = children.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return String.format("<%s%s>%s%s</%s>", this.name, attributesToString(attributes), this.body, value, this.name);
    }
}
// END
