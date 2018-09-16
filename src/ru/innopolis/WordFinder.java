package ru.innopolis;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordFinder {
    void getOccurencies(String[] sources, String[] words, String res) {
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        List<String> strings = Arrays.asList(words);
        HashSet<String> hashSet = new HashSet<>(strings);

        for (int i = 0; i < sources.length; i++) {
            Reader reader = new Reader(sources[i], res, hashSet);
            reader.start();
        }
    }

}
