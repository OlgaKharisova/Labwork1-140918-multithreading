package ru.innopolis;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Начинаю работу");

//       Scanner scanner = new Scanner(System.in);
//       scanner.nextLine();
        long time = System.currentTimeMillis();
        String packageName = "file://" + System.getenv("PWD") + "/sources/";
        String[] sources = {
                packageName + "1.Vnutrennee_ustrojstvo_Windows_(gl._1-4).txt",
                packageName + "Akcionernye_obwestva._OAO_i_ZAO._Ot_sozdanija_do_likvidacii.txt",
                packageName + "Bandity.txt",
                packageName + "Blesk_i_niweta_informacionnyh_tehnologij._Pochemu_IT_ne_javljajutsja_konkurentnym_preimuwestvom.txt",
                packageName + "Catching Fire.txt",
                //packageName + "fileFromTask",
                packageName + "Voina_i_mir.Tom_3.txt"
        };
        String[] words = {"любовь", "программирование", "работа", "fire", "parana"};
        String res = "result.txt";

        OccurenciesFinder main = new OccurenciesFinder();
        main.getOccurencies(sources, words, res);
        logger.info("Врем работы приложения " + (System.currentTimeMillis()-time)*0.001 + " c.");
    }
}