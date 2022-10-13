package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AppTest {

    @Test
    void testTakeMoreThenList() {
        // BEGIN
        List<Integer> actual1 = App.take(Arrays.asList(7, 3, 10), 8);
        assertThat(actual1).isEqualTo(Arrays.asList(7, 3, 10));
        // END
    }

    @Test
    void testTakeLessThenList() {
        // BEGIN
        List<Integer> actual1 = App.take(Arrays.asList(1, 2, 3, 4), 3);
        assertThat(actual1).isEqualTo(Arrays.asList(1, 2, 3));
        // END
    }

    @Test
    void testTakeAllList() {
        // BEGIN
        List<Integer> actual1 = App.take(Arrays.asList(1, 2, 3, 4), 4);
        assertThat(actual1).isEqualTo(Arrays.asList(1, 2, 3, 4));
        // END
    }

    @Test
    void testTakeEmptyList() {
        // BEGIN
        List<Integer> actual1 = App.take(Arrays.asList(), 3);
        assertThat(actual1).isEqualTo(Arrays.asList());
        // END
    }
}
