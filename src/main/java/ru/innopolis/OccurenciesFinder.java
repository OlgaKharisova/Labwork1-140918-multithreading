package ru.innopolis;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class OccurenciesFinder {

    private static Logger logger = LogManager.getLogger(OccurenciesFinder.class);

    void getOccurencies(String[] sources, String[] words, String res) {
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(words));
        List<SequenceParser> parsers = new ArrayList<>(sources.length);
        Arrays.stream(sources).forEach(url -> {
            SequenceParser sequenceParser = new SequenceParser(url, res, hashSet);
            sequenceParser.start();
            parsers.add(sequenceParser);

        });
        logger.debug("Ожидаю завершения других потоков");
        for (SequenceParser parser : parsers) {
            try {
                parser.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.debug("Ожидание завершения других потоков окончено");
    }
}