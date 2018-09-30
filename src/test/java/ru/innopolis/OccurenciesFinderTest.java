package ru.innopolis;

import org.junit.jupiter.api.*;

import javax.xml.transform.Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.innopolis.FileUtils.deleteTestFile;

class OccurenciesFinderTest {

    private static final String PATH_TO_FILE = "resultTest.txt";

    private static OccurenciesFinder finder;
    private static String[] sources;
    private static String[] words;

    @BeforeAll
    static void setUp() {
        String packageName = "file://" + System.getenv("PWD") + "/src/test/resources/";
        sources = new String[]{
                packageName + "test-file1.txt",
                packageName + "test-file2.txt"
        };
        words = new String[]{"Jenkins", "compile"};
        finder = new OccurenciesFinder();
    }

    @AfterAll
    static void tearDown() {
        deleteTestFile(PATH_TO_FILE);
    }

    @Test
    void getOccurencies() throws IOException, InterruptedException {
        finder.getOccurencies(sources, words, PATH_TO_FILE);


        Thread.sleep(1000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE));
        Set<String> lines = bufferedReader.lines().filter((s) -> s.length() != 0).collect(Collectors.toSet());

        assertEquals(4, lines.size());
        assertTrue(lines.contains("Jenkins — проект для непрерывной интеграции с открытым исходным кодом, " +
                "написанный на Java"));
        assertTrue(lines.contains("Возможности Jenkins можно расширять с помощью плагинов"));
        assertTrue(lines.contains("В 2011 году Jenkins получил награду Infoworld[en] как лучший проект с открытым" +
                " исходным кодом[6]"));
        assertTrue(lines.contains(" This specification clearly distinguishes between the compile-time errors that " +
                "can and must be detected at compile time, and those that occur at run time"));
    }
}