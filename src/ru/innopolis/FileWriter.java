package ru.innopolis;

import java.io.*;
import java.util.List;

public class FileWriter {

    public static synchronized void write(String pathToFile, List<String> data) {
        File file = new File(pathToFile);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            data.stream().forEach(sequence -> {
                                try {

                                    bos.write(sequence.concat("\n").getBytes());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );

            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
