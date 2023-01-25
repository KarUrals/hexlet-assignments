package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
     public static Map<String, Integer> getMinMax(int[] numbers) {

        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        minThread.start();

         try {
             maxThread.join();
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }

         try {
             minThread.join();
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }

         Map<String, Integer> result = Map.of("min", minThread.getMinNumber(), "max", maxThread.getMaxNumber());

        return result;
     }
    // END
}
