package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> appartments, int n) {
        List<String> result = new ArrayList<>();
        if (appartments.isEmpty()) {
            return result;
        }

        appartments.sort(Comparator.comparing(Home::getArea));
        for (int i = 0; i < n; i++) {
            result.add(appartments.get(i).toString());
        }

        return result;
    }
}
// END
