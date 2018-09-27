package ru.innopolis;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Читает источник, разбирает его на предложения
 */
public class SequenceParser extends Thread {

    private static Logger logger = LogManager.getLogger(SequenceParser.class);
    public final static int SEQUENCE_COUNT = 300;

    private String urlSource;
    private String pathToFile;
    private HashSet<String> searchWords;


    public SequenceParser(String urlSource, String pathToFile, HashSet<String> hashSet) {
        this.urlSource = urlSource;
        this.pathToFile = pathToFile;
        this.searchWords = hashSet;
    }

    @Override
    public void run() {
        logger.debug("Поток чтения запущен. Читаю путь " + urlSource);
        ArrayList<String> sequences = new ArrayList<>();
        try {
            URL url = new URL(urlSource);
            Scanner scanner = new Scanner(url.openStream());
            scanner.useDelimiter("[.|!|?]");
            while (scanner.hasNext()) {
                sequences.add(scanner.next());
                if (sequences.size() >= SEQUENCE_COUNT) {
                    new Thread(getHandler(sequences)).start();
                    sequences.clear();
                }
            }
            if (sequences.size() != 0) {
                new Thread(getHandler(sequences)).start();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("Поток чтения завершен. Читал путь " + urlSource);
    }

    public Runnable getHandler(ArrayList<String> source) {
        final ArrayList<String> sequences = new ArrayList<>(source);
        final String wordSeparator = "[\\p{Punct}\\s]+";
        return () -> {
            logger.debug("getHandler начал работу");
            ArrayList<String> match = new ArrayList<>();
            sequences.stream()
                    .forEach(a -> {
                        String[] words = a.split(wordSeparator);
                        for (String word : words) {
                            if (searchWords.contains(word)) {
                                match.add(a);
                                break;
                            }
                        }
                    });
            if (!match.isEmpty()) {
                FileWriter.write(pathToFile, match);
            }
//            System.out.println("Поток сравнения завершил работу");
            logger.debug("getHandler завершил работу");
        };
    }
}