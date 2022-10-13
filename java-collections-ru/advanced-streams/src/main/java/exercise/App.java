package exercise;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String fileContent) {
        String[] fileContentArray = fileContent.split("\n");
        String environmentVariables = Arrays.stream(fileContentArray)
                .filter(fileString -> fileString.startsWith("environment"))
                .map(filteredString -> filteredString.replaceAll("environment=\"", "")
                        .replaceAll("\"", ""))
                .flatMap(preparedSubstring -> Stream.of(preparedSubstring.split(",")))
                .filter(variable ->variable.startsWith("X_FORWARDED_"))
                .map(filteredVariable -> filteredVariable.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        return environmentVariables;
    }

    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("./src/test/resources/fixtures/s2.conf"));
        System.out.println(getForwardedVariables(content));
    }
}
//END
