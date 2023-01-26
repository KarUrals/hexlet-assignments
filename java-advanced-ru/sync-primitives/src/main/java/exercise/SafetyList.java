package exercise;

class SafetyList {
    // BEGIN
    int[] numbers = new int[2];
    int size;

    public synchronized void add(int number) {
        if (size < numbers.length) {
            numbers[size] = number;
        } else {
            int[] newNumbers = new int[numbers.length * 2];
            for (int i = 0; i < numbers.length; i++) {
                newNumbers[i] = numbers[i];
            }
            newNumbers[size] = number;
            numbers = newNumbers;
        }
        size++;
    }

    public int get(int index) {
        return numbers[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
