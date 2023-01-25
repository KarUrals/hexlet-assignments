package exercise;

import java.util.Arrays;
import java.util.OptionalInt;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;
    private int minNumber;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public void run() {
        findMin(numbers);
    }

    private void findMin(int[] numbers) {

        Arrays.sort(numbers);
        this.minNumber = numbers[0];
    }
}
// END
