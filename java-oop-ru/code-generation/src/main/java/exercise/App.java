package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {
    public static void save(Path filePath, Car car) throws IOException {
        Files.writeString(filePath, car.serialize());
    }

    public static Car extract(Path filePath) throws IOException {
        return Car.unserialize(Files.readString(filePath));
    }
}
// END
