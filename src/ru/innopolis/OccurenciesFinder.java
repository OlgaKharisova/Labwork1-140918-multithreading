package ru.innopolis;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class OccurenciesFinder {

    void getOccurencies(String[] sources, String[] words, String res) throws InterruptedException {
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        List<String> strings = Arrays.asList(words);
        HashSet<String> hashSet = new HashSet<>(strings);
        List<SequenceParser> parsers = new ArrayList<>(sources.length);
        Arrays.stream(sources).forEach(url -> {
            SequenceParser sequenceParser = new SequenceParser(url, res, hashSet);
            sequenceParser.start();
            parsers.add(sequenceParser);
        });
        for (SequenceParser parser : parsers) {
            parser.join();
        }
    }
}