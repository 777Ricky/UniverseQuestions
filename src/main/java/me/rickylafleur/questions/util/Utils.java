package me.rickylafleur.questions.util;

import java.util.Arrays;
import java.util.List;

public class Utils {

    private Utils() {}

    public static boolean containsWords(String string, String[] words) {
        final List<String> wordsList = Arrays.asList(words);

        return wordsList.stream()
                .allMatch(word -> string.toLowerCase().contains(word));
    }
}
