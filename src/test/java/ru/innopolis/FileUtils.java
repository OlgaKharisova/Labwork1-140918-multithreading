package ru.innopolis;

import java.io.File;

public class FileUtils {

    public static void deleteTestFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
