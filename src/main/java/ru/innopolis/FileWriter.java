package ru.innopolis;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Записывает предложения в файл
 */
public class FileWriter {

    private static Logger logger = LogManager.getLogger(FileWriter.class);

    public static synchronized void write(String pathToFile, List<String> data) {
        logger.debug("Произвожу запись в файл");
        File file = new File(pathToFile);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            data.stream().forEach(sequence -> {
                        try {
                            bos.write(sequence.concat("\n").getBytes());
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
            );
            bos.flush();
            bos.close();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        logger.debug("Запись в файл закончена. Предложений записано " + data.size());
    }
}