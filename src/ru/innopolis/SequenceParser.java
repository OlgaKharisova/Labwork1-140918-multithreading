package ru.innopolis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Читает источник, разбирает его на предложения и отдает обработчику(Handler)
 */
public class SequenceParser extends Thread {

    private String urlSource;
    private String pathToFile;
    private HashSet<String> searchWords;

    public final static int SEQUENCE_COUNT = 100;

    public SequenceParser(String urlSource, String pathToFile, HashSet<String> hashSet) {
        this.urlSource = urlSource;
        this.pathToFile = pathToFile;
        this.searchWords = hashSet;
    }

    @Override
    public void run() {
        System.out.println("Поток чтения запущен. Читаю путь " + urlSource);
        ArrayList<String> sequences = new ArrayList<>();
        try {
            URL url = new URL(urlSource);
            Scanner scanner = new Scanner(url.openStream());
            scanner.useDelimiter("[.|!|?]");
            while (scanner.hasNext()) {
                sequences.add(scanner.next());
                if (sequences.size() >= SEQUENCE_COUNT) {
                    Handler handler = new Handler(new ArrayList<>(sequences), searchWords, pathToFile);
                    handler.start();
                    sequences.clear();
                }
            }
            if (sequences.size() != 0) {
                Handler handler = new Handler(sequences, searchWords, pathToFile);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Поток чтения завершен. Читал путь " + urlSource);
    }
}