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
//        int min = numbers[0];

//        Arrays.sort(numbers);
//        this.minNumber = numbers[0];

//        OptionalInt minimal = Arrays.stream(numbers).min();
//
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] < min) {
//                min = numbers[i];
//            }
//        }
//
//        this.minNumber = minimal;
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        this.minNumber = minValue;
    }
}
// END
