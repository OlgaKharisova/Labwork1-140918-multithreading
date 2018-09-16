package ru.innopolis;

import java.io.*;
import java.util.List;

public class FileWriter {

    public static synchronized void write(String pathToFile, List<String> data) {
        File file = new File(pathToFile);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            for (int i = 0; i < data.size(); i++) {
                bos.write(data.get(i).concat("\n").getBytes());
            }
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
