package ru.innopolis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Читает источник, разбирает его на предложения
 */
public class SequenceParser extends Thread {

    private String urlSource;
    private String pathToFile;
    private HashSet<String> searchWords;

    public final static int SEQUENCE_COUNT = 300;

    public SequenceParser(String urlSource, String pathToFile, HashSet<String> hashSet) {
        this.urlSource = urlSource;
        this.pathToFile = pathToFile;
        this.searchWords = hashSet;
    }

    @Override
    public void run() {
//        System.out.println("Поток чтения запущен. Читаю путь " + urlSource);
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
            e.printStackTrace();
        }
//        System.out.println("Поток чтения завершен. Читал путь " + urlSource);
    }

    public Runnable getHandler(ArrayList<String> source) {
        final ArrayList<String> sequences = new ArrayList<>(source);
        final String wordSeparator = "[\\p{Punct}\\s]+";
        return () -> {
//            System.out.println("Поток сравнения начал работу");
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
        };
    }
}