package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] wordsOfSentence = sentence.split(" ");
        Map<String, Integer> resultMap = new HashMap<>();

        if (sentence.equals("")) {
            return resultMap;
        }

        for (String word: wordsOfSentence) {
            if (resultMap.containsKey(word)) {
                resultMap.put(word, resultMap.get(word) + 1);
            } else {
                resultMap.put(word, 1);
            }

        }
        return resultMap;
    }

    public static String toString(Map<String, Integer> map) {
        StringBuilder str = new StringBuilder("{");
        for (String element: map.keySet()) {
            str.append("\n  " + element + ": " + map.get(element));
        }
        if (map.isEmpty()) {
            str.append("}");
        } else {
            str.append("\n}");
        }

        return str.toString();
    }
}
//END
