package exercise;

// BEGIN
public class MaxThread extends Thread{
    private int[] numbers;
    private int maxNumber;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    private void findMax(int[] numbers) {
        int max = numbers[0];

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        this.maxNumber = max;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public void run() {
        findMax(numbers);
    }
}
// END
