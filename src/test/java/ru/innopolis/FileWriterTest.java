package ru.innopolis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.innopolis.FileUtils.deleteTestFile;

class FileWriterTest {

    private static final String PATH_TO_FILE = "resultTest.txt";

    @BeforeEach
    void setUp() {
        deleteTestFile(PATH_TO_FILE);
    }

    @AfterAll
    static void tearDown() {
        deleteTestFile(PATH_TO_FILE);
    }

    @Test
    void writeTest() throws IOException {
        List<String> words = Arrays.asList("тест1", "test2", "все хорошо");
        FileWriter.write(PATH_TO_FILE, words);
        assertTrue(new File(PATH_TO_FILE).exists());

        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE));
        for (String word : words) {
            assertEquals(word, bufferedReader.readLine());
        }
    }


}