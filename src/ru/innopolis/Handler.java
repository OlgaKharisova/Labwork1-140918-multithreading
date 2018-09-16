package ru.innopolis;

import java.util.ArrayList;
import java.util.HashSet;

public class Handler extends Thread {

    private ArrayList<String>  sequences;
    private HashSet<String> searchWords;
    private String pathToFile;

    private final String wordSeparator = "[\\p{Punct}\\s]+";

    public Handler(ArrayList<String> sequences, HashSet<String> searchWords, String pathToFile) {
        this.sequences = sequences;
        this.searchWords = searchWords;
        this.pathToFile = pathToFile;
    }

    @Override
    public void run() {
        System.out.println("Поток сравнения начал работу");
        ArrayList<String> match = new ArrayList<>();
        for (int i = 0; i < sequences.size(); i++) {
            String[] words = sequences.get(i).split(wordSeparator);
            for (String word : words) {
                if(searchWords.contains(word)) {
                    match.add(sequences.get(i));
                    break;
                }
            }
        }
        FileWriter.write(pathToFile, match);
        System.out.println("Поток сравнения завершил работу");
    }
}
