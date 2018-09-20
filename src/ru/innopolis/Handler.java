package ru.innopolis;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Получает список слов, проверяет каждое слово каждого предложения на вхождение searchWords
 */
public class Handler extends Thread {

    private ArrayList<String> sequences;//предложения, в которых нужно искать слова из searchWords
    private HashSet<String> searchWords;//слова в наборе, которые нужно искать в предложениях
    private String pathToFile;          //путь, куда сохраняем предложения, в которых присутствуют найденные слова

    private final String wordSeparator = "[\\p{Punct}\\s]+";

    public Handler(ArrayList<String> sequences, HashSet<String> searchWords, String pathToFile) {
        this.sequences = sequences;
        this.searchWords = searchWords;
        this.pathToFile = pathToFile;
    }

    @Override
    public void run() {
        System.out.println("Поток сравнения начал работу " + sequences.size());
        ArrayList<String> match = new ArrayList<>();
        for (int i = 0; i < sequences.size(); i++) {
            String[] words = sequences.get(i).split(wordSeparator);
            for (String word : words) {
                if (searchWords.contains(word)) {
                    match.add(sequences.get(i));
                    break;
                }
            }
        }
        if (!match.isEmpty()) {
            FileWriter.write(pathToFile, match);
        }
        System.out.println("Поток сравнения завершил работу");
    }
}
