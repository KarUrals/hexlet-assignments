package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
import java.util.List;

public class App {
    public static boolean scrabble(String charSet, String word) {
        List<String> charsArray = new ArrayList<>();
    
        for (char ch : charSet.toLowerCase().toCharArray()) {
            charsArray.add(Character.toString(ch));
        }
    
        for (char worldChar: word.toLowerCase().toCharArray()) {
            if (charsArray.contains(Character.toString(worldChar))) {
                charsArray.remove(Character.toString(worldChar));
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
